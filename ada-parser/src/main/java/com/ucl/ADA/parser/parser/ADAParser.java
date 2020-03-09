package com.ucl.ADA.parser.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.parser.parser.visitor.ADAClassVisitor;
import com.ucl.ADA.parser.parser.visitor.PackageAndImportVisitor;
import com.ucl.ADA.parser.parser.visitor.TypeDeclarationVisitor;
import org.apache.commons.collections4.ListUtils;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;

public class ADAParser {

    private final int NUMBER_OF_THREADS = 8;
    private final int NUMBER_OF_FILES_IN_A_BATCH = 80;
    private final SourceFileProcessor sourceFileProcessor;
    private final CompilationUnitBuilder compilationUnitBuilder;


    /**
     * Constructor of ADAParser
     */
    public ADAParser() {
        sourceFileProcessor = new SourceFileProcessor();
        compilationUnitBuilder = new CompilationUnitBuilder();
    }


    /**
     * It parses all the .*java source files and populates a set of ADAClass model.
     *
     * @param rootDirectory :  Source repository path
     * @return A set of parsed class in from of ADAClass model
     */
    public Set<ADAClass> getParsedSourceFile(String rootDirectory) {
        Set<ADAClass> allParsedFile = new HashSet<>();
        List<ADAClass> classes = parsingSourceFilesConcurrently(rootDirectory);
        allParsedFile.addAll(classes);
        return allParsedFile;
    }


    /**
     * It parses all the .*java source files and populates a set of ADAClass model.
     * It then prints the parsed ADAClasses in JSON format.
     *
     * @param rootDirectory :Source repository path
     */
    public void printParsedSourceFileInJSON(String rootDirectory) {
        Set<ADAClass> classes = getParsedSourceFile(rootDirectory);
        for (ADAClass aClass : classes) {
            ObjectMapper objMapper = new ObjectMapper();
            String jsonStr = "[]";
            try {
                jsonStr = objMapper.writeValueAsString(aClass);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            System.out.println(jsonStr);
        }
    }


    /**
     * This method parsed all source *.java files for a given source repository and make a list of ADAClass models
     * It performs thread pooling to parsing the source files and populate list of ADAClass models concurrently.
     *
     * @param rootDirectory: :Source repository path
     * @return A list of parsed class in from of ADAClass model
     */
    private List<ADAClass> parsingSourceFilesConcurrently(String rootDirectory) {
        List<ADAClass> parsedClasses = new ArrayList<>();
        List<String> filePaths = sourceFileProcessor.getSourceFilePaths(rootDirectory);
        List<List<String>> filePathBatches = ListUtils.partition(filePaths, NUMBER_OF_FILES_IN_A_BATCH);
        List<Map<String, String>> allFileContents = sourceFileProcessor.getSourceContentsInChunks(filePathBatches);
        String[] allSrcDirectories = sourceFileProcessor.getSourceDirectories(new File(rootDirectory));
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        // Callable, return a future, submit and run the task async
        List<Callable<List<ADAClass>>> listOfCallable = new ArrayList<>();
        for (Map<String, String> filePathBatch : allFileContents) {
            Callable<List<ADAClass>> c = () -> {
                return parseSourceFiles(filePathBatch, allSrcDirectories);
            };
            listOfCallable.add(c);
        }
        try {
            List<Future<List<ADAClass>>> futures = executor.invokeAll(listOfCallable);
            for (Future<List<ADAClass>> future : futures) {
                if (future.isDone()) {
                    parsedClasses.addAll(future.get());
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            // thread was interrupted
            e.printStackTrace();
        } finally {
            // shut down the executor manually
            executor.shutdown();
        }
        return parsedClasses;
    }


    /**
     * It parses a given list of files and populates a list of ADAClass model.
     *
     * @param filePathContentPair : A map containing file path->file contents
     * @param sourceDirectories   : All the source directories (src/) insider the repository.
     * @return A list of ADAClass model with parsed data.
     */
    private List<ADAClass> parseSourceFiles(Map<String, String> filePathContentPair, String[] sourceDirectories) {
        List<ADAClass> parsedClasses = new ArrayList<>();
        for (Map.Entry<String, String> sourceEntry : filePathContentPair.entrySet()) {
            String filePath = sourceEntry.getKey();
            String sourceCode = sourceEntry.getValue();
            CompilationUnit compilationUnit = compilationUnitBuilder.getCompilationUnit(filePath, sourceCode, sourceDirectories);
            List<ADAClass> classes = getParsedClass(compilationUnit);
            parsedClasses.addAll(classes);
        }
        return parsedClasses;
    }


    /**
     * It takes a prepared CompilationUnit and generates a list of parsed ADAClass model.
     *
     * @param compilationUnit : A compilation unit derived from CompilationUnit builder.
     * @return A list of parsed ADAClass model.
     */
    private List<ADAClass> getParsedClass(CompilationUnit compilationUnit) {
        List<ADAClass> parsedClasses = new ArrayList<>();
        if (compilationUnit != null) {
            if (compilationUnit.getAST().hasBindingsRecovery()) {
                PackageAndImportVisitor packageVisitor = new PackageAndImportVisitor();
                compilationUnit.accept(packageVisitor);
                TypeDeclarationVisitor typeVisitor = new TypeDeclarationVisitor();
                compilationUnit.accept(typeVisitor);
                List<AbstractTypeDeclaration> allClassAndEnums = typeVisitor.getAbstractTypeDeclaration();
                for (int i = 0; i < allClassAndEnums.size(); i++) {
                    AbstractTypeDeclaration classAndEnumType = allClassAndEnums.get(i);
                    ADAClassVisitor ADAClassVisitor = new ADAClassVisitor(packageVisitor.getPackageName(), packageVisitor.getImportedInternalClasses(), packageVisitor.getImportedExternalClasses());
                    classAndEnumType.accept(ADAClassVisitor);
                    ADAClass extractedClass = ADAClassVisitor.getExtractedClass();
                    parsedClasses.add(extractedClass);
                }
            }
        }
        return parsedClasses;
    }


}
