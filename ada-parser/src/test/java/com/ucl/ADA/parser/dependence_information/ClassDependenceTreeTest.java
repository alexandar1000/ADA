package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.AttributeDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.MethodDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.PackageDeclarationInformation;
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
        PackageDeclarationInformation module = new PackageDeclarationInformation("com.ADA");
        cdt.setCurrentPackage(module);

        assertThat(cdt.getCurrentPackage()).isEqualTo(module);
    }

    @Test
    void addDataDeclaration_addNewElement() {
        AttributeDeclarationInformation attributeDeclarationInformation = new AttributeDeclarationInformation("declarationInformationName");

        cdt.addAttributeDeclaration(attributeDeclarationInformation);

        assertThat(cdt.getAttributeDeclarations()).containsExactly(attributeDeclarationInformation);
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

        PackageInvocationInformation packageInvocationInformation = new PackageInvocationInformation("moduleImportInformationName");

        cdt.addPackageInvocationElement(className, InvocationType.OUTGOING_INVOCATION, packageInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getPackages()).containsExactly(packageInvocationInformation);
    }

    @Test
    void addDataElement_testAddingOutgoingInvocation() {
        String className = "TestClass";

        AttributeInvocationInformation attributeInvocationInformation = new AttributeInvocationInformation("dataInvocationInformationName");

        cdt.addAttributeInvocationElement(className, InvocationType.OUTGOING_INVOCATION, attributeInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getAttributes()).containsExactly(attributeInvocationInformation);
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

        PackageInvocationInformation packageInvocationInformation = new PackageInvocationInformation("moduleImportInformationName");


        cdt.addPackageInvocationElement(className, InvocationType.INCOMING_INVOCATION, packageInvocationInformation);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getPackages()).containsExactly(packageInvocationInformation);
    }

    @Test
    void addDataElement_testAddingIncomingInvocation() {
        String className = "TestClass";

        AttributeInvocationInformation attributeInvocationInformation = new AttributeInvocationInformation("dataInvocationInformationName");

        cdt.addAttributeInvocationElement(className, InvocationType.INCOMING_INVOCATION, attributeInvocationInformation);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getAttributes()).containsExactly(attributeInvocationInformation);
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
    void addModuleElement_testAddingOutgoingInvocationWithExistingElement() {
        String className = "TestClass";

        PackageInvocationInformation packageInvocationInformation0 = new PackageInvocationInformation("moduleImportInformationName0");
        PackageInvocationInformation packageInvocationInformation1 = new PackageInvocationInformation("moduleImportInformationName1");

        cdt.addPackageInvocationElement(className, InvocationType.OUTGOING_INVOCATION, packageInvocationInformation0);
        cdt.addPackageInvocationElement(className, InvocationType.OUTGOING_INVOCATION, packageInvocationInformation1);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getPackages()).containsExactlyInAnyOrderElementsOf(Arrays.asList(packageInvocationInformation0, packageInvocationInformation1));
    }

    @Test
    void addDataElement_testAddingOutgoingInvocationWithExistingElement() {
        String className = "TestClass";

        AttributeInvocationInformation attributeInvocationInformation0 = new AttributeInvocationInformation("dataInvocationInformationName0");
        AttributeInvocationInformation attributeInvocationInformation1 = new AttributeInvocationInformation("dataInvocationInformationName1");

        cdt.addAttributeInvocationElement(className, InvocationType.OUTGOING_INVOCATION, attributeInvocationInformation0);
        cdt.addAttributeInvocationElement(className, InvocationType.OUTGOING_INVOCATION, attributeInvocationInformation1);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getAttributes()).containsExactlyInAnyOrderElementsOf(Arrays.asList(attributeInvocationInformation0, attributeInvocationInformation1));
    }

    @Test
    void addConstructorElement_testAddingOutgoingInvocationWithExistingElement() {
        String className = "TestClass";

        ConstructorInvocationInformation constructorInvocationInformation0 = new ConstructorInvocationInformation("constructorInvocationInformationName0");
        ConstructorInvocationInformation constructorInvocationInformation1 = new ConstructorInvocationInformation("constructorInvocationInformationName1");

        cdt.addConstructorInvocationElement(className, InvocationType.OUTGOING_INVOCATION, constructorInvocationInformation0);
        cdt.addConstructorInvocationElement(className, InvocationType.OUTGOING_INVOCATION, constructorInvocationInformation1);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getConstructors()).containsExactlyInAnyOrderElementsOf(Arrays.asList(constructorInvocationInformation0, constructorInvocationInformation1));
    }

    @Test
    void addMethodElement_testAddingOutgoingInvocationWithExistingElement() {
        String className = "TestClass";

        MethodInvocationInformation methodInvocationInformation0 = new MethodInvocationInformation("methodInvocationInformationName0");
        MethodInvocationInformation methodInvocationInformation1 = new MethodInvocationInformation("methodInvocationInformationName1");

        cdt.addMethodInvocationElement(className, InvocationType.OUTGOING_INVOCATION, methodInvocationInformation0);
        cdt.addMethodInvocationElement(className, InvocationType.OUTGOING_INVOCATION, methodInvocationInformation1);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getMethods()).containsExactlyInAnyOrderElementsOf(Arrays.asList(methodInvocationInformation0, methodInvocationInformation1));
    }

    @Test
    void addModuleElement_testAddingIncomingInvocationWithExistingElement() {
        String className = "TestClass";

        PackageInvocationInformation packageInvocationInformation0 = new PackageInvocationInformation("moduleImportInformationName0");
        PackageInvocationInformation packageInvocationInformation1 = new PackageInvocationInformation("moduleImportInformationName1");


        cdt.addPackageInvocationElement(className, InvocationType.INCOMING_INVOCATION, packageInvocationInformation0);
        cdt.addPackageInvocationElement(className, InvocationType.INCOMING_INVOCATION, packageInvocationInformation1);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getPackages()).containsExactlyInAnyOrderElementsOf(Arrays.asList(packageInvocationInformation0, packageInvocationInformation1));
    }

    @Test
    void addDataElement_testAddingIncomingInvocationWithExistingElement() {
        String className = "TestClass";

        AttributeInvocationInformation attributeInvocationInformation0 = new AttributeInvocationInformation("dataInvocationInformationName0");
        AttributeInvocationInformation attributeInvocationInformation1 = new AttributeInvocationInformation("dataInvocationInformationName1");

        cdt.addAttributeInvocationElement(className, InvocationType.INCOMING_INVOCATION, attributeInvocationInformation0);
        cdt.addAttributeInvocationElement(className, InvocationType.INCOMING_INVOCATION, attributeInvocationInformation1);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getAttributes()).containsExactlyInAnyOrderElementsOf(Arrays.asList(attributeInvocationInformation0, attributeInvocationInformation1));
    }

    @Test
    void addConstructorElement_testAddingIncomingInvocationWithExistingElement() {
        String className = "TestClass";

        ConstructorInvocationInformation constructorInvocationInformation0 = new ConstructorInvocationInformation("constructorInvocationInformationName0");
        ConstructorInvocationInformation constructorInvocationInformation1 = new ConstructorInvocationInformation("constructorInvocationInformationName1");

        cdt.addConstructorInvocationElement(className, InvocationType.INCOMING_INVOCATION, constructorInvocationInformation0);
        cdt.addConstructorInvocationElement(className, InvocationType.INCOMING_INVOCATION, constructorInvocationInformation1);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getConstructors()).containsExactlyInAnyOrderElementsOf(Arrays.asList(constructorInvocationInformation0, constructorInvocationInformation1));
    }

    @Test
    void addMethodElement_testAddingIncomingInvocationWithExistingElement() {
        String className = "TestClass";

        MethodInvocationInformation methodInvocationInformation0 = new MethodInvocationInformation("methodInvocationInformationName0");
        MethodInvocationInformation methodInvocationInformation1 = new MethodInvocationInformation("methodInvocationInformationName1");

        cdt.addMethodInvocationElement(className, InvocationType.INCOMING_INVOCATION, methodInvocationInformation0);
        cdt.addMethodInvocationElement(className, InvocationType.INCOMING_INVOCATION, methodInvocationInformation1);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getMethods()).containsExactlyInAnyOrderElementsOf(Arrays.asList(methodInvocationInformation0, methodInvocationInformation1));
    }

    @Test
    public void addNewElements_testCreationOfKeys() {
        ArrayList<String> classNames = new ArrayList<>(Arrays.asList("FirstClass", "SecondClass", "ThirdClass", "FourthClass"));

        cdt.addPackageInvocationElement(classNames.get(0), InvocationType.OUTGOING_INVOCATION, null);
        cdt.addAttributeInvocationElement(classNames.get(1), InvocationType.OUTGOING_INVOCATION, null);
        cdt.addConstructorInvocationElement(classNames.get(2), InvocationType.OUTGOING_INVOCATION, null);
        cdt.addMethodInvocationElement(classNames.get(3), InvocationType.OUTGOING_INVOCATION, null);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactlyInAnyOrderElementsOf(classNames);
    }

    @Test
    public void addNewElements_testInsertionOfSingleElementPerClassAndType() {
        ArrayList<String> classNames = new ArrayList<>(Arrays.asList("FirstClass", "SecondClass", "ThirdClass", "FourthClass"));

        PackageInvocationInformation packageInvocationInformation = new PackageInvocationInformation("moduleImportInformationName");
        AttributeInvocationInformation attributeInvocationInformation = new AttributeInvocationInformation("dataInvocationInformationName");
        ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation("constructorInvocationInformationName");
        MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("methodInvocationInformationName");


        cdt.addPackageInvocationElement(classNames.get(0), InvocationType.OUTGOING_INVOCATION, packageInvocationInformation);
        cdt.addAttributeInvocationElement(classNames.get(1), InvocationType.OUTGOING_INVOCATION, attributeInvocationInformation);
        cdt.addConstructorInvocationElement(classNames.get(2), InvocationType.OUTGOING_INVOCATION, constructorInvocationInformation);
        cdt.addMethodInvocationElement(classNames.get(3), InvocationType.OUTGOING_INVOCATION, methodInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactlyInAnyOrderElementsOf(classNames);

        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(0)).getPackages()).containsExactly(packageInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(1)).getAttributes()).containsExactly(attributeInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(2)).getConstructors()).containsExactly(constructorInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(3)).getMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    public void addNewElements_testInsertionOfSingleElementWithAnExistingKey() {
        String className = "TestClass";

        PackageInvocationInformation packageInvocationInformation = new PackageInvocationInformation("moduleImportInformationName");
        AttributeInvocationInformation attributeInvocationInformation = new AttributeInvocationInformation("dataInvocationInformationName");
        ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation("constructorInvocationInformationName");
        MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("methodInvocationInformationName");


        cdt.addPackageInvocationElement(className, InvocationType.OUTGOING_INVOCATION, packageInvocationInformation);
        cdt.addAttributeInvocationElement(className, InvocationType.OUTGOING_INVOCATION, attributeInvocationInformation);
        cdt.addConstructorInvocationElement(className, InvocationType.OUTGOING_INVOCATION, constructorInvocationInformation);
        cdt.addMethodInvocationElement(className, InvocationType.OUTGOING_INVOCATION, methodInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getPackages()).containsExactly(packageInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(className).getAttributes()).containsExactly(attributeInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(className).getConstructors()).containsExactly(constructorInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(className).getMethods()).containsExactly(methodInvocationInformation);
    }
}