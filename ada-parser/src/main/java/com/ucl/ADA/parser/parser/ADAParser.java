package com.ucl.ADA.parser.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucl.ADA.parser.model.ADAClass;
import com.ucl.ADA.parser.parser.visitor.PackageAndImportVisitor;
import com.ucl.ADA.parser.parser.visitor.TypeDeclatorVisitor;
import com.ucl.ADA.parser.util.SourceFileCollector;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.io.*;
import java.util.*;

public class ADAParser {

    public Set<ADAClass> getParsedSourceFile(String src_dir) {
        Set<ADAClass> allParsedFile = new HashSet<>();
        List<String> filePaths = SourceFileCollector.getJavaFilesFromSourceDirectory(new File(src_dir));
        filePaths.forEach(file -> {
            ASTParser parser = buildASTParser(src_dir);
            List<ADAClass> parsedClasses = parseSourceFile(parser, file);
            allParsedFile.addAll(parsedClasses);
        });
        return allParsedFile;
    }


    public void printParsedSourceFileInJSON(String src_dir) {
        List<String> filePaths = SourceFileCollector.getJavaFilesFromSourceDirectory(new File(src_dir));
        //List<String> filePathNew = getAllSourceFiles(src_dir);
        for (int i = 0, filePathsSize = filePaths.size(); i < filePathsSize; i++) {
            String file = filePaths.get(i);
            System.out.println("Processing->  " + i + "->th->Path" + file);
            ASTParser parser = buildASTParser(src_dir);
            List<ADAClass> classes = parseSourceFile(parser, file);
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
    }

    private ASTParser buildASTParser(String sourceRoot) {
        String[] srcDirs = getAllSrcDirectories(new File(sourceRoot));

        String[] encoding = new String[srcDirs.length];
        for (int i = 0; i < encoding.length; i++) {
            encoding[i] = "UTF-8";
        }
        ASTParser parser = ASTParser.newParser(AST.JLS13);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setBindingsRecovery(true);
        parser.setResolveBindings(true);
        parser.setStatementsRecovery(true);
        Map options = JavaCore.getOptions();
        parser.setCompilerOptions(options);
        String[] sources = srcDirs;
        String[] classpath = {};
        parser.setEnvironment(classpath, sources, encoding, true);
        // parser.setEnvironment(classpath, sources, new String[]{"UTF-8"}, true);
        return parser;
    }

    private List<ADAClass> parseSourceFile(ASTParser parser, String sourceFile) {
        String sourceCode = getSourceFromFile(sourceFile);
        String unitName = new File(sourceFile).getName();
        parser.setSource(sourceCode.toCharArray());
        parser.setUnitName(unitName);
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        if (cu.getAST().hasBindingsRecovery()) {
            // System.out.println("Binding activated.");
        }

        PackageAndImportVisitor pv = new PackageAndImportVisitor();
        cu.accept(pv);
        TypeDeclatorVisitor tv = new TypeDeclatorVisitor();
        cu.accept(tv);
        List<TypeDeclaration> classes = tv.getClasses();
        List<ADAClass> parsedClasses = new ArrayList<>();
        for (TypeDeclaration cl : classes) {
            JavaClassParser jp = new JavaClassParser(pv.getPackageName(), pv.getImportedInternalClasses(), pv.getImportedExternalClasses());
            cl.accept(jp);
            ADAClass cm = jp.getExtractedClass();
            parsedClasses.add(cm);
        }
        return parsedClasses;

    }

    private static String getSourceFromFile(String filePath) {
        File file = new File(filePath);
        StringBuilder sb = new StringBuilder();
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line + '\n');
                }
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        }
        return sb.toString();
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


    public List<String> getAllSourceFiles(String rootPath) {
        File rootDir = new File(rootPath);
        List<String> sourcesFiles = new ArrayList<>();
        Iterator<File> files = FileUtils.iterateFiles(rootDir, null, true);
        while (files.hasNext()) {
            File f = files.next();
            if (f.getAbsolutePath().endsWith(".java")) {
                sourcesFiles.add(files.next().getAbsolutePath());
            }
        }
        return sourcesFiles;
    }
}


