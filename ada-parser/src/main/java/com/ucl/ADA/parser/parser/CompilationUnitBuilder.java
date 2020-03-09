package com.ucl.ADA.parser.parser;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

public class CompilationUnitBuilder {

    /**
     * Default constructor for SourceFileProcessor
     */
    protected CompilationUnitBuilder() {
    }

    /**
     * It makes the compilation unit for a given file.
     *
     * @param filePath                 : Source file path
     * @param sourceCode               :Source content that needs to make compilation unit.
     * @param allSrcDirectories:Source directories for configuring the bildings
     * @return A compilation unit for the given source file.
     */

    protected CompilationUnit getCompilationUnit(String filePath, String sourceCode, String[] allSrcDirectories) {
        Map options = getParserVersion_1_5();
        ASTParser parser = buildASTParser(allSrcDirectories, options);
        String unitName = new File(filePath).getName();
        parser.setSource(sourceCode.toCharArray());
        parser.setUnitName(unitName);
        CompilationUnit compilationUnit = null;
        try {
            compilationUnit = (CompilationUnit) parser.createAST(null);
        } catch (Exception ex) {
            System.err.println("Parsing Error at file-> " + filePath);
        }
        return compilationUnit;
    }


    /**
     * It creates the configuration of the JDT parser.
     * Currently it only provides the Java 1.5 version configuration
     * that helps to parse code that is written in 1.5 version and the upper versions.
     *
     * @return A map containing the configuration name value pair
     */
    protected Map getParserVersion_1_5() {
        Map options = JavaCore.getOptions();
        options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5);
        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_5);
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);
        return options;
    }


    /**
     * It builds the ASTParser for a given configuration.
     *
     * @param sourceDirectories: A list of source directories that contains *.java source files
     * @param options            A Map containing configuration of JDT parser
     * @return An instance of ASTParser with the given configurations and source directories for type binding.
     */

    protected ASTParser buildASTParser(String[] sourceDirectories, Map options) {
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

}
