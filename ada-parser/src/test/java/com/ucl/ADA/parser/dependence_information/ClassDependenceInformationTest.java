package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.DataDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.MethodDeclarationInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClassDependenceInformationTest {

    private ClassDependenceInformation cdi;

    @BeforeEach
    void setUp() {
        cdi = new ClassDependenceInformation();
    }

    @Test
    void addDataDeclaration_addNewElement() {
        DataDeclarationInformation dataDeclarationInformation = new DataDeclarationInformation("dataDeclarationField");

        cdi.addDataDeclaration(dataDeclarationInformation);

        assertThat(cdi.getDataDeclarations()).containsExactly(dataDeclarationInformation);
    }

    @Test
    void addMethodDeclaration_addNewElement() {
        MethodDeclarationInformation methodDeclarationInformation = new MethodDeclarationInformation("methodDeclarationField");

        cdi.addMethodDeclaration(methodDeclarationInformation);

        assertThat(cdi.getMethodsDeclarations()).containsExactly(methodDeclarationInformation);
    }

    @Test
    void addConstructorDeclaration_addNewElement() {
        ConstructorDeclarationInformation constructorDeclarationInformation = new ConstructorDeclarationInformation("constructorDeclarationField");

        cdi.addConstructorDeclaration(constructorDeclarationInformation);

        assertThat(cdi.getConstructorDeclarations()).containsExactly(constructorDeclarationInformation);
    }
}