package com.ucl.ADA.parser.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
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
     * @param rootDirectory Source repository path
     * @param filePaths     list of file path(relative) that needs to be parsed
     * @return A map of parsed class in from of ADAClass model
     */
    public SetMultimap<String, ADAClass> getParsedSourceFile(String rootDirectory, List<String> filePaths) {
        return parseSourceFilesConcurrently(rootDirectory, filePaths);
    }


    /**
     * It parses all the .*java source files and populates a set of ADAClass model.
     * It then prints the parsed ADAClasses in JSON format.
     *
     * @param rootDirectory Source repository path
     * @JsonProcessingException if error occurs while preparing the JSON
     */
    public void printParsedSourceFileInJSON(String rootDirectory, List<String> filePaths) {
        SetMultimap<String, ADAClass> classes = getParsedSourceFile(rootDirectory, filePaths);
        for (Map.Entry<String, ADAClass> entry : classes.entries()) {
            ObjectMapper objMapper = new ObjectMapper();
            String jsonStr = "[]";
            try {
                jsonStr = objMapper.writeValueAsString(entry.getValue());
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
     * @param rootDirectory Source repository path
     * @param filePaths     list of file path(relative) that needs to be parsed
     * @return A list of parsed class in from of ADAClass model
     * @InterruptedException if errors occur in thread pooling.
     */
    private SetMultimap<String, ADAClass> parseSourceFilesConcurrently(String rootDirectory, List<String> filePaths) {

        SetMultimap<String, ADAClass> parsedClasses = MultimapBuilder.hashKeys().hashSetValues().build();
        List<List<String>> filePathBatches = ListUtils.partition(filePaths, NUMBER_OF_FILES_IN_A_BATCH);
        List<Map<String, String>> allFileContents = sourceFileProcessor.getSourceContentsInChunks(rootDirectory, filePathBatches);
        String[] allSrcDirectories = sourceFileProcessor.getSourceDirectories(new File(rootDirectory));
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        // Callable, return a future, submit and run the task async
        List<Callable<SetMultimap<String, ADAClass>>> listOfCallable = new ArrayList<>();
        for (Map<String, String> filePathBatch : allFileContents) {
            Callable<SetMultimap<String, ADAClass>> c = () -> {
                return parseSourceFilesInBatch(filePathBatch, allSrcDirectories);
            };
            listOfCallable.add(c);
        }
        try {
            List<Future<SetMultimap<String, ADAClass>>> futures = executor.invokeAll(listOfCallable);
            for (Future<SetMultimap<String, ADAClass>> future : futures) {
                if (future.isDone()) {
                    parsedClasses.putAll(future.get());
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
     * @param filePathContentPair A map containing file path->file contents
     * @param sourceDirectories   All the source directories (src/) insider the repository.
     * @return A map of ADAClass model with parsed data in form of path->ADAClass.
     */
    private SetMultimap<String, ADAClass> parseSourceFilesInBatch(Map<String, String> filePathContentPair, String[] sourceDirectories) {
        SetMultimap<String, ADAClass> parsedClassMap = MultimapBuilder.hashKeys().hashSetValues().build();
        for (Map.Entry<String, String> sourceEntry : filePathContentPair.entrySet()) {
            String filePath = sourceEntry.getKey();
            String sourceCode = sourceEntry.getValue();
            CompilationUnit compilationUnit = compilationUnitBuilder.getCompilationUnit(filePath, sourceCode, sourceDirectories);
            List<ADAClass> classes = getParsedClass(compilationUnit);
            for (ADAClass adc : classes) {
                parsedClassMap.put(filePath, adc);
            }
        }
        return parsedClassMap;
    }


    /**
     * It takes a prepared CompilationUnit and generates a list of parsed ADAClass model.
     *
     * @param compilationUnit A compilation unit derived from CompilationUnit builder.
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
