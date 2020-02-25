package com.ucl.ADA.parser.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucl.ADA.parser.model.ADAClassModel;
import com.ucl.ADA.parser.util.SourceFileCollector;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ADAParser {

    public Set<ADAClassModel> getParsedSourceFile(String src_dir) {
        Set<ADAClassModel> allParsedFile = new HashSet<>();
        List<String> filePaths = SourceFileCollector.getJavaFilesFromSourceDirectory(new File(src_dir));
        filePaths.forEach(file -> {
            ASTParser parser = buildASTParser(src_dir);
            ADAClassModel cm = parseSourceFile(parser, file);
            allParsedFile.add(cm);
        });
        return allParsedFile;
    }

    public void printParsedSourceFileInJSON(String src_dir) {

        Set<ADAClassModel> allParsedFile = new HashSet<>();
        List<String> filePaths = SourceFileCollector.getJavaFilesFromSourceDirectory(new File(src_dir));

        for (String file : filePaths) {
            System.out.println("Processing->  "+file);
            ASTParser parser = buildASTParser(src_dir);
            ADAClassModel cm = parseSourceFile(parser, file);
            allParsedFile.add(cm);
            ObjectMapper objMapper = new ObjectMapper();
            String jsonStr = "[]";
            try {
                jsonStr = objMapper.writeValueAsString(cm);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            System.out.println(jsonStr);
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

    private ADAClassModel parseSourceFile(ASTParser parser, String sourceFile) {
        String sourceCode = getSourceFromFile(sourceFile);
        String unitName = new File(sourceFile).getName();
        parser.setSource(sourceCode.toCharArray());
        parser.setUnitName(unitName);
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        if (cu.getAST().hasBindingsRecovery()) {
           // System.out.println("Binding activated.");
        }
        JavaClassParser jp = new JavaClassParser();
        cu.accept(jp);
        ADAClassModel cm = jp.getExtractedClass();
        return cm;

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


