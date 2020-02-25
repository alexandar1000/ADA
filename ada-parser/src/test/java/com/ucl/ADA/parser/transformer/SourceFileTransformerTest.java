package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.model.project_structure.ProjectStructure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SourceFileTransformerTest {

    private ProjectStructure projectStructure;
    private SourceFileTransformer sourceFileTransformer;
   // private SourceFile sourceFile;

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
