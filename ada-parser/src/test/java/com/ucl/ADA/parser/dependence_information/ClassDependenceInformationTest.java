package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.DataDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.MethodDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.DataInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.MethodInvocationInformation;
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
        DataDeclarationInformation dataDeclarationInformation = new DataDeclarationInformation("declarationInformationName");

        cdi.addDataDeclaration(dataDeclarationInformation);

        assertThat(cdi.getDataDeclarations()).containsExactly(dataDeclarationInformation);
    }

    @Test
    void addMethodDeclaration_addNewElement() {
        MethodDeclarationInformation methodDeclarationInformation = new MethodDeclarationInformation("declarationInformationName");

        cdi.addMethodDeclaration(methodDeclarationInformation);

        assertThat(cdi.getMethodsDeclarations()).containsExactly(methodDeclarationInformation);
    }

    @Test
    void addConstructorDeclaration_addNewElement() {
        ConstructorDeclarationInformation constructorDeclarationInformation = new ConstructorDeclarationInformation("declarationInformationName");

        cdi.addConstructorDeclaration(constructorDeclarationInformation);

        assertThat(cdi.getConstructorDeclarations()).containsExactly(constructorDeclarationInformation);
    }

    @Test
    void addInvokedData_addNewElement() {
        DataInvocationInformation dataInvocationInformation = new DataInvocationInformation("invocationInformationName");

        cdi.addInvokedData(dataInvocationInformation);

        assertThat(cdi.getInvokedData()).containsExactly(dataInvocationInformation);
    }

    @Test
    void addInvokedMethod_addNewElement() {
         MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("invocationInformationName");

        cdi.addInvokedMethod(methodInvocationInformation);

        assertThat(cdi.getInvokedMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    void addIncomingDataInvocation_addNewElement() {
        DataInvocationInformation dataInvocationInformation = new DataInvocationInformation("invocationInformationName");

        cdi.addIncomingDataInvocation(dataInvocationInformation);

        assertThat(cdi.getIncomingDataInvocations()).containsExactly(dataInvocationInformation);
    }

    @Test
    void addIncomingMethodInvocation_addNewElement() {
         MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("invocationInformationName");

        cdi.addIncomingMethodInvocation(methodInvocationInformation);

        assertThat(cdi.getIncomingMethodInvocations()).containsExactly(methodInvocationInformation);
    }


}