package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.parser.dependence_information.ProjectStructure;
import com.ucl.ADA.parser.model.SourceFile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SourceFileProcessorTest {

    private ProjectStructure projectStructure;
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
        projectStructure = new ProjectStructure();
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
