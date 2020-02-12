package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import com.ucl.ADA.parser.model.SourceFile;
import com.ucl.ADA.parser.model.SourceMethod;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

class SourceFileProcessorTest {

    private ProjectDependenceTree projectDependenceTree;
    private SourceFileProcessor sourceFileProcessor;
    private SourceFile sourceFile;

    @BeforeAll
    static void setUpProcessor() {
//        sourceFileProcessor = new SourceFileProcessor();
//
//        String packageName = "";
//        String className = "";
//        String parentClassName = "";
//        Set<String> implementedInterfaces;
//        Map<String, String> staticFields;
//        Map<String, String> publicFields;
//        Set<SourceMethod> methods;
//
//        sourceFile = SourceFile.builder().build();
    }

    @BeforeEach
    void setUpDependenceTree() {
        projectDependenceTree = new ProjectDependenceTree();
    }

    @Test
    void processPackageDeclaration() {
    }

    @Test
    void processAttributeDeclaration() {
    }

    @Test
    void processConstructorDeclaration() {
    }

    @Test
    void processMethodDeclaration() {
    }

    @Test
    void processPackageInvocation() {
    }

    @Test
    void processAttributeInvocation() {
    }

    @Test
    void processConstructorInvocation() {
    }

    @Test
    void processMethodInvocation() {
    }

}