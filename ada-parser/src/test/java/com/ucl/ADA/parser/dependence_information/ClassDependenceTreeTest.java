package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.DataDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.MethodDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.ModuleDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ClassDependenceTreeTest {

    private ClassDependenceTree cdt;
    @BeforeEach
    void setUp() {
        cdt = new ClassDependenceTree();
    }

    @Test
    void setCurrentModule_testSetUp() {
        ModuleDeclarationInformation module = new ModuleDeclarationInformation("com.ADA");
        cdt.setCurrentPackage(module);

        assertThat(cdt.getCurrentPackage()).isEqualTo(module);
    }

    @Test
    void addDataDeclaration_addNewElement() {
        DataDeclarationInformation dataDeclarationInformation = new DataDeclarationInformation("declarationInformationName");

        cdt.addDataDeclaration(dataDeclarationInformation);

        assertThat(cdt.getDataFieldDeclarations()).containsExactly(dataDeclarationInformation);
    }

    @Test
    void addConstructorDeclaration_addNewElement() {
        ConstructorDeclarationInformation constructorDeclarationInformation = new ConstructorDeclarationInformation("declarationInformationName");

        cdt.addConstructorDeclaration(constructorDeclarationInformation);

        assertThat(cdt.getConstructorDeclarations()).containsExactly(constructorDeclarationInformation);
    }

    @Test
    void addMethodDeclaration_addNewElement() {
        MethodDeclarationInformation methodDeclarationInformation = new MethodDeclarationInformation("declarationInformationName");

        cdt.addMethodDeclaration(methodDeclarationInformation);

        assertThat(cdt.getMethodsDeclarations()).containsExactly(methodDeclarationInformation);
    }

    @Test
    void addModuleElement_testAddingOutgoingInvocation() {
        String className = "TestClass";

        ModuleInvocationInformation moduleInvocationInformation = new ModuleInvocationInformation("moduleImportInformationName");

        cdt.addModuleInvocationElement(className, InvocationType.OUTGOING_INVOCATION, moduleInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getPackages()).containsExactly(moduleInvocationInformation);
    }

    @Test
    void addDataElement_testAddingOutgoingInvocation() {
        String className = "TestClass";

        DataInvocationInformation dataInvocationInformation = new DataInvocationInformation("dataInvocationInformationName");

        cdt.addDataInvocationElement(className, InvocationType.OUTGOING_INVOCATION, dataInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getDataFields()).containsExactly(dataInvocationInformation);
    }

    @Test
    void addConstructorElement_testAddingOutgoingInvocation() {
        String className = "TestClass";

        ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation("constructorInvocationInformationName");

        cdt.addConstructorInvocationElement(className, InvocationType.OUTGOING_INVOCATION, constructorInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getConstructors()).containsExactly(constructorInvocationInformation);
    }

    @Test
    void addMethodElement_testAddingOutgoingInvocation() {
        String className = "TestClass";

        MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("methodInvocationInformationName");

        cdt.addMethodInvocationElement(className, InvocationType.OUTGOING_INVOCATION, methodInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    void addModuleElement_testAddingIncomingInvocation() {
        String className = "TestClass";

        ModuleInvocationInformation moduleInvocationInformation = new ModuleInvocationInformation("moduleImportInformationName");


        cdt.addModuleInvocationElement(className, InvocationType.INCOMING_INVOCATION, moduleInvocationInformation);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getPackages()).containsExactly(moduleInvocationInformation);
    }

    @Test
    void addDataElement_testAddingIncomingInvocation() {
        String className = "TestClass";

        DataInvocationInformation dataInvocationInformation = new DataInvocationInformation("dataInvocationInformationName");

        cdt.addDataInvocationElement(className, InvocationType.INCOMING_INVOCATION, dataInvocationInformation);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getDataFields()).containsExactly(dataInvocationInformation);
    }

    @Test
    void addConstructorElement_testAddingIncomingInvocation() {
        String className = "TestClass";

        ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation("constructorInvocationInformationName");

        cdt.addConstructorInvocationElement(className, InvocationType.INCOMING_INVOCATION, constructorInvocationInformation);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getConstructors()).containsExactly(constructorInvocationInformation);
    }

    @Test
    void addMethodElement_testAddingIncomingInvocation() {
        String className = "TestClass";

        MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("methodInvocationInformationName");

        cdt.addMethodInvocationElement(className, InvocationType.INCOMING_INVOCATION, methodInvocationInformation);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getMethods()).containsExactly(methodInvocationInformation);
    }


    @Test
    public void addNewElements_testCreationOfKeys() {
        ArrayList<String> classNames = new ArrayList<>(Arrays.asList("FirstClass", "SecondClass", "ThirdClass", "FourthClass"));

        cdt.addModuleInvocationElement(classNames.get(0), InvocationType.OUTGOING_INVOCATION, null);
        cdt.addDataInvocationElement(classNames.get(1), InvocationType.OUTGOING_INVOCATION, null);
        cdt.addConstructorInvocationElement(classNames.get(2), InvocationType.OUTGOING_INVOCATION, null);
        cdt.addMethodInvocationElement(classNames.get(3), InvocationType.OUTGOING_INVOCATION, null);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactlyInAnyOrderElementsOf(classNames);
    }

    @Test
    public void addNewElements_testInsertionOfSingleElementPerClassAndType() {
        ArrayList<String> classNames = new ArrayList<>(Arrays.asList("FirstClass", "SecondClass", "ThirdClass", "FourthClass"));

        ModuleInvocationInformation moduleInvocationInformation = new ModuleInvocationInformation("moduleImportInformationName");
        DataInvocationInformation dataInvocationInformation = new DataInvocationInformation("dataInvocationInformationName");
        ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation("constructorInvocationInformationName");
        MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("methodInvocationInformationName");


        cdt.addModuleInvocationElement(classNames.get(0), InvocationType.OUTGOING_INVOCATION, moduleInvocationInformation);
        cdt.addDataInvocationElement(classNames.get(1), InvocationType.OUTGOING_INVOCATION, dataInvocationInformation);
        cdt.addConstructorInvocationElement(classNames.get(2), InvocationType.OUTGOING_INVOCATION, constructorInvocationInformation);
        cdt.addMethodInvocationElement(classNames.get(3), InvocationType.OUTGOING_INVOCATION, methodInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactlyInAnyOrderElementsOf(classNames);

        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(0)).getPackages()).containsExactly(moduleInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(1)).getDataFields()).containsExactly(dataInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(2)).getConstructors()).containsExactly(constructorInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(3)).getMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    public void addNewElements_testInsertionOfSingleElementWithAnExistingKey() {
        String className = "TestClass";

        ModuleInvocationInformation moduleInvocationInformation = new ModuleInvocationInformation("moduleImportInformationName");
        DataInvocationInformation dataInvocationInformation = new DataInvocationInformation("dataInvocationInformationName");
        ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation("constructorInvocationInformationName");
        MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("methodInvocationInformationName");


        cdt.addModuleInvocationElement(className, InvocationType.OUTGOING_INVOCATION, moduleInvocationInformation);
        cdt.addDataInvocationElement(className, InvocationType.OUTGOING_INVOCATION, dataInvocationInformation);
        cdt.addConstructorInvocationElement(className, InvocationType.OUTGOING_INVOCATION, constructorInvocationInformation);
        cdt.addMethodInvocationElement(className, InvocationType.OUTGOING_INVOCATION, methodInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getPackages()).containsExactly(moduleInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(className).getDataFields()).containsExactly(dataInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(className).getConstructors()).containsExactly(constructorInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(className).getMethods()).containsExactly(methodInvocationInformation);
    }
}