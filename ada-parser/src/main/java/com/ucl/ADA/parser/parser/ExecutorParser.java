package com.ucl.ADA.parser.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.parser.parser.visitor.PackageAndImportVisitor;
import com.ucl.ADA.parser.parser.visitor.TypeDeclarationVisitor;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

public class ExecutorParser {

    final static int numberOfThreads = 10;
    static int count = 0;

    public void printParsedSourceFileInJSON(String sourceRoot) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println("Started on...." + dtf.format(LocalDateTime.now()));
        List<ADAClass> classes = parallelParser(sourceRoot);
        System.out.println("Ends on...." + dtf.format(LocalDateTime.now()));
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

    private List<ADAClass> parallelParser(String sourceRoot) {
        List<ADAClass> parsedClasses = new ArrayList<>();
        List<String> filePaths = getJavaSourceFilePathsFromSourceRoot(sourceRoot);
        List<List<String>> filePathBatches = ListUtils.partition(filePaths, 100);
        List<Map<String, String>> allFileContents = getAllFileContents(filePathBatches);
        String[] allSrcDirectories = getSourceDirectories(new File(sourceRoot));
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
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

    private List<Map<String, String>> getAllFileContents(List<List<String>> listsOfFilePaths) {
        List<Map<String, String>> allFileContents = new ArrayList<>();
        for (List<String> listOfFilePaths : listsOfFilePaths) {
            Map<String, String> fileContents = new HashMap<>();
            fileContents.putAll(getAllSourceContent(listOfFilePaths));
            allFileContents.add(fileContents);
        }
        return allFileContents;
    }

    private List<ADAClass> parseSourceFiles(Map<String, String> fileContents, String[] allSrcDirectories) {
        List<ADAClass> parsedClasses = new ArrayList<>();
        for (Map.Entry<String, String> sourceEntry : fileContents.entrySet()) {
            String filePath = sourceEntry.getKey();
            count++;
            System.out.println("Processing-> " + count + "->" + filePath);
            String sourceCode = sourceEntry.getValue();
            CompilationUnit compilationUnit = getCompilationUnit(filePath, sourceCode, allSrcDirectories);
            List<ADAClass> classes = getParsedClass(compilationUnit);
            parsedClasses.addAll(classes);
        }
        return parsedClasses;
    }

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
                    JavaClassParser javaClassParser = new JavaClassParser(packageVisitor.getPackageName(), packageVisitor.getImportedInternalClasses(), packageVisitor.getImportedExternalClasses());
                    classAndEnumType.accept(javaClassParser);
                    ADAClass extractedClass = javaClassParser.getExtractedClass();
                    parsedClasses.add(extractedClass);
                }
            }
        }
        return parsedClasses;
    }

    private Map getParserVersion_1_5() {
        Map options = JavaCore.getOptions();
        options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5);
        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_5);
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);
        return options;
    }

    private ASTParser buildASTParser(String[] sourceDirectories, Map options) {
        String[] encoding = new String[sourceDirectories.length];
        Arrays.fill(encoding, "UTF-8");
        ASTParser parser = ASTParser.newParser(AST.JLS13);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setBindingsRecovery(true);
        parser.setResolveBindings(true);
        parser.setStatementsRecovery(true);
        parser.setCompilerOptions(options);
        String[] classpath = {};
        parser.setEnvironment(classpath, sourceDirectories, encoding, true);
        return parser;
    }

    private CompilationUnit getCompilationUnit(String filePath, String sourceCodes, String[] allSrcDirectories) {
        Map options = getParserVersion_1_5();
        ASTParser parser = buildASTParser(allSrcDirectories, options);
        String unitName = new File(filePath).getName();
        parser.setSource(sourceCodes.toCharArray());
        parser.setUnitName(unitName);
        CompilationUnit compilationUnit = null;
        try {
            compilationUnit = (CompilationUnit) parser.createAST(null);
        } catch (Exception ex) {
            System.err.println("Parsing Error at file-> " + filePath);
        }
        return compilationUnit;
    }

    private String getSourceCodeFromSourcePath(String filePath) {
        String sourceCode = "";
        File sourceFile = new File(filePath);
        if (sourceFile.exists()) {
            try {
                sourceCode = FileUtils.readFileToString(sourceFile, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sourceCode;
    }

    private Map<String, String> getAllSourceContent(List<String> paths) {
        Map<String, String> sourceContents = new HashMap<>();
        for (String path : paths) {
            String code = getSourceCodeFromSourcePath(path);
            sourceContents.put(path, code);
        }
        return sourceContents;
    }

    private List<String> getJavaSourceFilePathsFromSourceRoot(String rootPath) {
        String[] allSrcDirectories = getSourceDirectories(new File(rootPath));
        List<String> sourceFiles = new ArrayList<>();
        final String[] suffix = {"java"};
        for (String srcDir : allSrcDirectories) {
            File rootDir = new File(srcDir);
            Collection<File> files = FileUtils.listFiles(rootDir, suffix, true);
            for (File file : files) {
                if (!file.getPath().endsWith("Test.java") && !file.getPath().endsWith("Tests.java")) {
                    {
                        sourceFiles.add(file.getAbsolutePath());
                    }
                }
            }
        }
        return sourceFiles;
    }

    private String[] getSourceDirectories(File rootDir) {
        List<String> allSourceDirectories = new ArrayList<>();
        Collection<File> files = FileUtils.listFilesAndDirs(rootDir,
                TrueFileFilter.INSTANCE,
                TrueFileFilter.INSTANCE);
        for (File file : files) {
            if (file.isDirectory()) {
                if (file.getName().equals("src") || file.getName().equals("Src"))
                    allSourceDirectories.add(file.getAbsolutePath());
            }
        }
        String[] srcDirs = allSourceDirectories.stream().toArray(String[]::new);
        return srcDirs;
    }

}
