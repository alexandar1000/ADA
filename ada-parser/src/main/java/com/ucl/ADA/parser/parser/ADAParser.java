package com.ucl.ADA.parser.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import com.ucl.ADA.parser.model.ADAClass;
import com.ucl.ADA.parser.parser.visitor.PackageAndImportVisitor;
import com.ucl.ADA.parser.parser.visitor.TypeDeclarationVisitor;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ADAParser {


    public Set<ADAClass> getParsedSourceFile(String sourceRoot) {
        Set<ADAClass> allParsedFile = new HashSet<>();
        List<String> filePaths = getAllJavaFilesFromSourceRoot(sourceRoot);
        String[] allSrcDirectories = getAllSrcDirectories(new File(sourceRoot));
        filePaths.forEach(file -> {
            ASTParser parser = buildASTParser_V_1_5(sourceRoot, allSrcDirectories);
            List<ADAClass> parsedClasses = parseSourceFile(parser, file);
            allParsedFile.addAll(parsedClasses);
        });
        return allParsedFile;
    }

    public void printParsedSourceFileInJSON(String src_dir) {
        List<ADAClass> classes = parseSourceFile(src_dir);
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

    private ASTParser buildASTParser_V_1_5(String sourceRoot, String[] sourceDirectories) {
        String[] encoding = new String[sourceDirectories.length];
        Arrays.fill(encoding, "UTF-8");
        ASTParser parser = ASTParser.newParser(AST.JLS13);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setBindingsRecovery(true);
        parser.setResolveBindings(true);
        parser.setStatementsRecovery(true);
        Map options = JavaCore.getOptions();
        options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5);
        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_5);
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);
        parser.setCompilerOptions(options);


        parser.setCompilerOptions(options);
        String[] sources = sourceDirectories;
        String[] classpath = {};
        parser.setEnvironment(classpath, sources, encoding, true);
        // parser.setEnvironment(classpath, sources, new String[]{"UTF-8"}, true);
        return parser;
    }


    private ASTParser buildASTParser_V_1_3(String sourceRoot, String[] sourceDirectories) {
        String[] encoding = new String[sourceDirectories.length];
        Arrays.fill(encoding, "UTF-8");
        ASTParser parser = ASTParser.newParser(AST.JLS13);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setBindingsRecovery(true);
        parser.setResolveBindings(true);
        parser.setStatementsRecovery(true);
        Map options = JavaCore.getOptions();
        parser.setCompilerOptions(options);
        String[] sources = sourceDirectories;
        String[] classpath = {};
        parser.setEnvironment(classpath, sources, encoding, true);
        return parser;
    }


    private List<ADAClass> parseSourceFile(ASTParser parser, String sourceFile) {
        String sourceCode = getSourceFromFile(sourceFile);
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
            JavaClassParser jp = new JavaClassParser(pv.getPackageName(), pv.getImportedInternalClasses(), pv.getImportedExternalClasses());
            cl.accept(jp);
            ADAClass cm = jp.getExtractedClass();
            parsedClasses.add(cm);
        }
        return parsedClasses;

    }


    private List<ADAClass> parseSourceFile(String sourceRoot) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Source files path  collection started on...." + dtf.format(now));
        List<String> filePaths = getAllJavaFilesFromSourceRoot(sourceRoot);
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


    private List<ADAClass> getParsedClass(CompilationUnit cu) {
        List<ADAClass> parsedClasses = new ArrayList<>();
        if (cu.getAST().hasBindingsRecovery()) {
            // System.out.println("Binding activated.");
        }
        PackageAndImportVisitor pv = new PackageAndImportVisitor();
        cu.accept(pv);
        TypeDeclarationVisitor tv = new TypeDeclarationVisitor();
        cu.accept(tv);
        List<AbstractTypeDeclaration> allClassAndEnums = tv.getAbstractTypeDeclaration();
        for (int i = 0; i < allClassAndEnums.size(); i++) {
            AbstractTypeDeclaration cl = allClassAndEnums.get(i);
            JavaClassParser jp = new JavaClassParser(pv.getPackageName(), pv.getImportedInternalClasses(), pv.getImportedExternalClasses());
            cl.accept(jp);
            ADAClass cm = jp.getExtractedClass();
            parsedClasses.add(cm);
        }
        return parsedClasses;
    }


    private List<CompilationUnit> getCompilationUnits(Map<String, String> sourceCodes, String sourceRoot) {
        String[] allSrcDirectories = getAllSrcDirectories(new File(sourceRoot));
        List<CompilationUnit> compilationUnits = new ArrayList<>();
        int count = 0;
        for (String path : sourceCodes.keySet()) {
            try {
                count++;
                ASTParser parser = buildASTParser_V_1_5(sourceRoot, allSrcDirectories);
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


    private Map<String, String> getAllSourceContent(List<String> paths) {
        //List<List<String>> filePathBatches = ListUtils.partition(paths, 25);
        Map<String, String> sourceContents = new HashMap<>();
        for (String path : paths) {
            String code = getSourceFromFile(path);
            sourceContents.put(path, code);
        }
        return sourceContents;
    }


    private String getSourceFromFile(String filePath) {
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

    // Recursive function to list all files in a directory with Apache Commons IO
    public String[] getAllSrcDirectories(File rootDir) {
        List<String> allSrcDirectories = new ArrayList<>();
        Collection<File> files = FileUtils.listFilesAndDirs(rootDir,
                TrueFileFilter.INSTANCE,
                TrueFileFilter.INSTANCE);
        for (File file : files) {
            if (file.isDirectory()) {
                if (file.getName().equals("src"))
                    allSrcDirectories.add(file.getAbsolutePath());
                //System.out.println(file);
            }
        }
        String[] srcDirs = allSrcDirectories.stream().toArray(String[]::new);
        return srcDirs;
    }


    public List<String> getAllJavaFilesFromSourceRoot(String rootPath) {
        List<String> sourceFiles = new ArrayList<>();
        final String[] SUFFIX = {"java"};  // use the suffix to filter
        File rootDir = new File(rootPath);
        Collection<File> files = FileUtils.listFiles(rootDir, SUFFIX, true);
        for (File file : files) {
            if (!file.getPath().endsWith("Test.java") && !file.getPath().endsWith("Tests.java")) {
                {
                    sourceFiles.add(file.getAbsolutePath());
                }
            }
        }
        return sourceFiles;
    }

}


