package com.ucl.ADA.parser.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.parser.parser.visitor.ADAClassVisitor;
import com.ucl.ADA.parser.parser.visitor.PackageAndImportVisitor;
import com.ucl.ADA.parser.parser.visitor.TypeDeclarationVisitor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class ADAParser {

    public Set<ADAClass> getParsedSourceFile(String sourceRoot) {
        Set<ADAClass> allParsedFile = new HashSet<>();
        List<ADAClass> classes = parseEachSourceFile(sourceRoot);
        allParsedFile.addAll(classes);
        return allParsedFile;
    }

    public void printParsedSourceFileInJSON(String sourceRoot) {
        List<ADAClass> classes = parseEachSourceFile(sourceRoot);
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

    private List<ADAClass> parseSourceFile(ASTParser parser, String sourceFile) {
        String sourceCode = getSourceCodeFromSourcePath(sourceFile);
        String unitName = new File(sourceFile).getName();
        parser.setSource(sourceCode.toCharArray());
        parser.setUnitName(unitName);
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        if (cu.getAST().hasBindingsRecovery()) {
            //System.out.println("Binding activated.");
        }

        PackageAndImportVisitor pv = new PackageAndImportVisitor();
        cu.accept(pv);
        TypeDeclarationVisitor tv = new TypeDeclarationVisitor();
        cu.accept(tv);

        List<AbstractTypeDeclaration> allClassAndEnums = tv.getAbstractTypeDeclaration();
        List<ADAClass> parsedClasses = new ArrayList<>();
        for (AbstractTypeDeclaration cl : allClassAndEnums) {
            ADAClassVisitor jp = new ADAClassVisitor(pv.getPackageName(), pv.getImportedInternalClasses(), pv.getImportedExternalClasses());
            cl.accept(jp);
            ADAClass cm = jp.getExtractedClass();
            parsedClasses.add(cm);
        }
        return parsedClasses;

    }

    private List<ADAClass> parseAllSourceFiles(String sourceRoot) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Source files path  collection started on...." + dtf.format(now));
        List<String> filePaths = getJavaSourceFilePathsFromSourceRoot(sourceRoot);
        System.out.println("Collection is completed on: " + dtf.format(now));

        System.out.println("Source file reading is started on......" + dtf.format(now));
        Map<String, String> fileContents = getAllSourceContent(filePaths);
        System.out.println("Reading is completed on: " + dtf.format(now));

        System.out.println("Compilation unit preparation is started on....");
        List<CompilationUnit> compilationUnits = getCompilationUnits(fileContents, sourceRoot);
        System.out.println("Compilation Unit building finished: " + dtf.format(now));

        System.out.println("ADA parser is started on.........." + dtf.format(now));
        List<ADAClass> parsedClasses = new ArrayList<>();
        for (CompilationUnit cu : compilationUnits) {
            List<ADAClass> classes = getParsedClass(cu);
            parsedClasses.addAll(classes);
        }
        System.out.println("ADAClass Model building finished on: " + dtf.format(now));
        return parsedClasses;
    }

    private Map<String, String> getFileContents(String sourceRoot) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Source files path  collection started on...." + dtf.format(now));
        List<String> filePaths = getJavaSourceFilePathsFromSourceRoot(sourceRoot);
        System.out.println("Collection is completed on: " + dtf.format(now));
        System.out.println("Source file reading is started on......" + dtf.format(now));
        Map<String, String> fileContents = new HashMap<>();
        fileContents.putAll(getAllSourceContent(filePaths));
        System.out.println("Reading is completed on: " + dtf.format(now));
        System.out.println("ADA parser is started on.........." + dtf.format(now));
        return fileContents;
    }

    private List<ADAClass> parseEachSourceFile(String sourceRoot) {
        Map<String, String> fileContents = getFileContents(sourceRoot);
        List<ADAClass> parsedClasses = new ArrayList<>();
        String[] allSrcDirectories = getSourceDirectories(new File(sourceRoot));
        for (Map.Entry<String, String> sourceEntry : fileContents.entrySet()) {
            String filePath = sourceEntry.getKey();
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
                    ADAClassVisitor ADAClassVisitor = new ADAClassVisitor(packageVisitor.getPackageName(), packageVisitor.getImportedInternalClasses(), packageVisitor.getImportedExternalClasses());
                    classAndEnumType.accept(ADAClassVisitor);
                    ADAClass extractedClass = ADAClassVisitor.getExtractedClass();
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

    private List<CompilationUnit> getCompilationUnits(Map<String, String> sourceCodes, String sourceRoot) {
        String[] allSrcDirectories = getSourceDirectories(new File(sourceRoot));
        List<CompilationUnit> compilationUnits = new ArrayList<>();
        int count = 0;
        for (String path : sourceCodes.keySet()) {
            try {
                count++;
                Map options = getParserVersion_1_5();
                ASTParser parser = buildASTParser(allSrcDirectories, options);
                String code = sourceCodes.get(path);
                String unitName = new File(path).getName();
                parser.setSource(code.toCharArray());
                parser.setUnitName(unitName);
                System.out.println(count + "->" + path);
                CompilationUnit cu = (CompilationUnit) parser.createAST(null);
                compilationUnits.add(cu);
            } catch (Exception e) {
                System.err.println("Exception occurred in " + path);
            }
        }
        return compilationUnits;
    }


}


