package com.ucl.ADA.parser.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucl.ADA.parser.model.ADAClass;
import com.ucl.ADA.parser.parser.visitor.TypeDeclatorVisitor;
import com.ucl.ADA.parser.util.SourceFileCollector;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

        for (String file : filePaths) {
            System.out.println("Processing->  " + file);
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
        ASTParser parser = ASTParser.newParser(AST.JLS13);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setBindingsRecovery(true);
        parser.setResolveBindings(true);
        parser.setStatementsRecovery(true);
        Map options = JavaCore.getOptions();
        parser.setCompilerOptions(options);
        String[] sources = {sourceRoot};
        String[] classpath = {};
        parser.setEnvironment(classpath, sources, new String[]{"UTF-8"}, true);
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

        TypeDeclatorVisitor tv = new TypeDeclatorVisitor();
        cu.accept(tv);
        List<TypeDeclaration> classes = tv.getClasses();
        List<ADAClass> parsedClasses = new ArrayList<>();
        for (TypeDeclaration cl : classes) {
            JavaClassParser jp = new JavaClassParser();
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


}


