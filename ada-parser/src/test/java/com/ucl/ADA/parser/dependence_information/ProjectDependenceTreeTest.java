package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.AttributeDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.MethodDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.ModuleDeclarationInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectDependenceTreeTest {
    private ProjectDependenceTree pdt;

    @BeforeEach
    void setUp() {
        pdt = new ProjectDependenceTree();
    }

    @Test
    public void addModuleDeclaration() {
        String declaringClassName = "DeclaringTestClass";
        ModuleDeclarationInformation moduleDeclarationInformation = new ModuleDeclarationInformation("com.ADA.example");

        pdt.addModuleDeclaration(declaringClassName, moduleDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getCurrentPackage()).isEqualTo(moduleDeclarationInformation);
    }

    @Test
    public void addAttributeDeclaration() {
        String declaringClassName = "DeclaringTestClass";
        AttributeDeclarationInformation attributeDeclarationInformation = new AttributeDeclarationInformation("exampleAttribute");

        pdt.addAttributeDeclaration(declaringClassName, attributeDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getAttributeDeclarations()).containsExactly(attributeDeclarationInformation);
    }

    @Test
    public void addConstructorDeclaration() {
        String declaringClassName = "DeclaringTestClass";
        ConstructorDeclarationInformation constructorDeclarationInformation = new ConstructorDeclarationInformation("exampleConstructor");

        pdt.addConstructorDeclaration(declaringClassName, constructorDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getConstructorDeclarations()).containsExactly(constructorDeclarationInformation);
    }

    @Test
    public void addMethodDeclaration() {
        String declaringClassName = "DeclaringTestClass";
        MethodDeclarationInformation methodDeclarationInformation = new MethodDeclarationInformation("exampleMethod");

        pdt.addMethodDeclaration(declaringClassName, methodDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getMethodsDeclarations()).containsExactly(methodDeclarationInformation);
    }



}