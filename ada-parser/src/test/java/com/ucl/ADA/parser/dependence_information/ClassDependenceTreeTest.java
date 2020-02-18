package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.*;
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
        PackageDeclaration module = new PackageDeclaration("com.ADA");
        cdt.setCurrentPackage(module);

        assertThat(cdt.getCurrentPackage()).isEqualTo(module);
    }

    @Test
    void addDataDeclaration_addNewElement() {
        AttributeDeclaration attributeDeclarationInformation = new AttributeDeclaration(ModifierType.DEFAULT, "String", "attribute", "declaringAttributeName", true);

        cdt.addAttributeDeclaration(attributeDeclarationInformation);

        assertThat(cdt.getAttributeDeclarations()).containsExactly(attributeDeclarationInformation);
    }

    @Test
    void addConstructorDeclaration_addNewElement() {
        ConstructorDeclaration constructorDeclarationInformation = new ConstructorDeclaration(ModifierType.DEFAULT, "String", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        cdt.addConstructorDeclaration(constructorDeclarationInformation);

        assertThat(cdt.getConstructorDeclarations()).containsExactly(constructorDeclarationInformation);
    }

    @Test
    void addMethodDeclaration_addNewElement() {
        MethodDeclaration methodDeclarationInformation = new MethodDeclaration(ModifierType.DEFAULT, "String", "testingTesting", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        cdt.addMethodDeclaration(methodDeclarationInformation);

        assertThat(cdt.getMethodsDeclarations()).containsExactly(methodDeclarationInformation);
    }

    @Test
    void addModuleElement_testAddingOutgoingInvocation() {
        String className = "TestClass";

        PackageInvocation packageInvocationInformation = new PackageInvocation("moduleImportInformationName");

        cdt.addPackageInvocationElement(className, InvocationType.OUTGOING, packageInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getPackages()).containsExactly(packageInvocationInformation);
    }

    @Test
    void addDataElement_testAddingOutgoingInvocation() {
        String className = "TestClass";

        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("dataInvocationInformationName");

        cdt.addAttributeInvocationElement(className, InvocationType.OUTGOING, attributeInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getAttributes()).containsExactly(attributeInvocationInformation);
    }

    @Test
    void addConstructorElement_testAddingOutgoingInvocation() {
        String className = "TestClass";

        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        cdt.addConstructorInvocationElement(className, InvocationType.OUTGOING, constructorInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getConstructors()).containsExactly(constructorInvocationInformation);
    }

    @Test
    void addMethodElement_testAddingOutgoingInvocation() {
        String className = "TestClass";

        MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        cdt.addMethodInvocationElement(className, InvocationType.OUTGOING, methodInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    void addModuleElement_testAddingIncomingInvocation() {
        String className = "TestClass";

        PackageInvocation packageInvocationInformation = new PackageInvocation("moduleImportInformationName");


        cdt.addPackageInvocationElement(className, InvocationType.INCOMING, packageInvocationInformation);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getPackages()).containsExactly(packageInvocationInformation);
    }

    @Test
    void addDataElement_testAddingIncomingInvocation() {
        String className = "TestClass";

        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("dataInvocationInformationName");

        cdt.addAttributeInvocationElement(className, InvocationType.INCOMING, attributeInvocationInformation);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getAttributes()).containsExactly(attributeInvocationInformation);
    }

    @Test
    void addConstructorElement_testAddingIncomingInvocation() {
        String className = "TestClass";

        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        cdt.addConstructorInvocationElement(className, InvocationType.INCOMING, constructorInvocationInformation);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getConstructors()).containsExactly(constructorInvocationInformation);
    }

    @Test
    void addMethodElement_testAddingIncomingInvocation() {
        String className = "TestClass";

        MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        cdt.addMethodInvocationElement(className, InvocationType.INCOMING, methodInvocationInformation);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getMethods()).containsExactly(methodInvocationInformation);
    }


    @Test
    void addModuleElement_testAddingOutgoingInvocationWithExistingElement() {
        String className = "TestClass";

        PackageInvocation packageInvocationInformation0 = new PackageInvocation("moduleImportInformationName0");
        PackageInvocation packageInvocationInformation1 = new PackageInvocation("moduleImportInformationName1");

        cdt.addPackageInvocationElement(className, InvocationType.OUTGOING, packageInvocationInformation0);
        cdt.addPackageInvocationElement(className, InvocationType.OUTGOING, packageInvocationInformation1);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getPackages()).containsExactlyInAnyOrderElementsOf(Arrays.asList(packageInvocationInformation0, packageInvocationInformation1));
    }

    @Test
    void addDataElement_testAddingOutgoingInvocationWithExistingElement() {
        String className = "TestClass";

        AttributeInvocation attributeInvocationInformation0 = new AttributeInvocation("dataInvocationInformationName0");
        AttributeInvocation attributeInvocationInformation1 = new AttributeInvocation("dataInvocationInformationName1");

        cdt.addAttributeInvocationElement(className, InvocationType.OUTGOING, attributeInvocationInformation0);
        cdt.addAttributeInvocationElement(className, InvocationType.OUTGOING, attributeInvocationInformation1);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getAttributes()).containsExactlyInAnyOrderElementsOf(Arrays.asList(attributeInvocationInformation0, attributeInvocationInformation1));
    }

    @Test
    void addConstructorElement_testAddingOutgoingInvocationWithExistingElement() {
        String className = "TestClass";

        ConstructorInvocation constructorInvocationInformation0 = new ConstructorInvocation("constructorExample0", new ArrayList<>(Arrays.asList("String FirstParameter0", "Integer SecondParameter0")));
        ConstructorInvocation constructorInvocationInformation1 = new ConstructorInvocation("constructorExample1", new ArrayList<>(Arrays.asList("String FirstParameter1", "Integer SecondParameter1")));

        cdt.addConstructorInvocationElement(className, InvocationType.OUTGOING, constructorInvocationInformation0);
        cdt.addConstructorInvocationElement(className, InvocationType.OUTGOING, constructorInvocationInformation1);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getConstructors()).containsExactlyInAnyOrderElementsOf(Arrays.asList(constructorInvocationInformation0, constructorInvocationInformation1));
    }

    @Test
    void addMethodElement_testAddingOutgoingInvocationWithExistingElement() {
        String className = "TestClass";

        MethodInvocation methodInvocationInformation0 = new MethodInvocation("methodExample0", new ArrayList<>(Arrays.asList("String FirstParameter0", "Integer SecondParameter0")));
        MethodInvocation methodInvocationInformation1 = new MethodInvocation("methodExample1", new ArrayList<>(Arrays.asList("String FirstParameter1", "Integer SecondParameter1")));

        cdt.addMethodInvocationElement(className, InvocationType.OUTGOING, methodInvocationInformation0);
        cdt.addMethodInvocationElement(className, InvocationType.OUTGOING, methodInvocationInformation1);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getMethods()).containsExactlyInAnyOrderElementsOf(Arrays.asList(methodInvocationInformation0, methodInvocationInformation1));
    }

    @Test
    void addModuleElement_testAddingIncomingInvocationWithExistingElement() {
        String className = "TestClass";

        PackageInvocation packageInvocationInformation0 = new PackageInvocation("moduleImportInformationName0");
        PackageInvocation packageInvocationInformation1 = new PackageInvocation("moduleImportInformationName1");


        cdt.addPackageInvocationElement(className, InvocationType.INCOMING, packageInvocationInformation0);
        cdt.addPackageInvocationElement(className, InvocationType.INCOMING, packageInvocationInformation1);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getPackages()).containsExactlyInAnyOrderElementsOf(Arrays.asList(packageInvocationInformation0, packageInvocationInformation1));
    }

    @Test
    void addDataElement_testAddingIncomingInvocationWithExistingElement() {
        String className = "TestClass";

        AttributeInvocation attributeInvocationInformation0 = new AttributeInvocation("dataInvocationInformationName0");
        AttributeInvocation attributeInvocationInformation1 = new AttributeInvocation("dataInvocationInformationName1");

        cdt.addAttributeInvocationElement(className, InvocationType.INCOMING, attributeInvocationInformation0);
        cdt.addAttributeInvocationElement(className, InvocationType.INCOMING, attributeInvocationInformation1);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getAttributes()).containsExactlyInAnyOrderElementsOf(Arrays.asList(attributeInvocationInformation0, attributeInvocationInformation1));
    }

    @Test
    void addConstructorElement_testAddingIncomingInvocationWithExistingElement() {
        String className = "TestClass";

        ConstructorInvocation constructorInvocationInformation0 = new ConstructorInvocation("constructorExample0", new ArrayList<>(Arrays.asList("String FirstParameter0", "Integer SecondParameter0")));
        ConstructorInvocation constructorInvocationInformation1 = new ConstructorInvocation("constructorExample1", new ArrayList<>(Arrays.asList("String FirstParameter1", "Integer SecondParameter1")));

        cdt.addConstructorInvocationElement(className, InvocationType.INCOMING, constructorInvocationInformation0);
        cdt.addConstructorInvocationElement(className, InvocationType.INCOMING, constructorInvocationInformation1);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getConstructors()).containsExactlyInAnyOrderElementsOf(Arrays.asList(constructorInvocationInformation0, constructorInvocationInformation1));
    }

    @Test
    void addMethodElement_testAddingIncomingInvocationWithExistingElement() {
        String className = "TestClass";

        MethodInvocation methodInvocationInformation0 = new MethodInvocation("methodExample0", new ArrayList<>(Arrays.asList("String FirstParameter0", "Integer SecondParameter0")));
        MethodInvocation methodInvocationInformation1 = new MethodInvocation("methodExample1", new ArrayList<>(Arrays.asList("String FirstParameter1", "Integer SecondParameter1")));

        cdt.addMethodInvocationElement(className, InvocationType.INCOMING, methodInvocationInformation0);
        cdt.addMethodInvocationElement(className, InvocationType.INCOMING, methodInvocationInformation1);

        assertThat(cdt.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getIncomingDependenceInfo().get(className).getMethods()).containsExactlyInAnyOrderElementsOf(Arrays.asList(methodInvocationInformation0, methodInvocationInformation1));
    }

    @Test
    public void addNewElements_testCreationOfKeys() {
        ArrayList<String> classNames = new ArrayList<>(Arrays.asList("FirstClass", "SecondClass", "ThirdClass", "FourthClass"));

        cdt.addPackageInvocationElement(classNames.get(0), InvocationType.OUTGOING, null);
        cdt.addAttributeInvocationElement(classNames.get(1), InvocationType.OUTGOING, null);
        cdt.addConstructorInvocationElement(classNames.get(2), InvocationType.OUTGOING, null);
        cdt.addMethodInvocationElement(classNames.get(3), InvocationType.OUTGOING, null);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactlyInAnyOrderElementsOf(classNames);
    }

    @Test
    public void addNewElements_testInsertionOfSingleElementPerClassAndType() {
        ArrayList<String> classNames = new ArrayList<>(Arrays.asList("FirstClass", "SecondClass", "ThirdClass", "FourthClass"));

        PackageInvocation packageInvocationInformation = new PackageInvocation("moduleImportInformationName");
        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("dataInvocationInformationName");
        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));
        MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));


        cdt.addPackageInvocationElement(classNames.get(0), InvocationType.OUTGOING, packageInvocationInformation);
        cdt.addAttributeInvocationElement(classNames.get(1), InvocationType.OUTGOING, attributeInvocationInformation);
        cdt.addConstructorInvocationElement(classNames.get(2), InvocationType.OUTGOING, constructorInvocationInformation);
        cdt.addMethodInvocationElement(classNames.get(3), InvocationType.OUTGOING, methodInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactlyInAnyOrderElementsOf(classNames);

        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(0)).getPackages()).containsExactly(packageInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(1)).getAttributes()).containsExactly(attributeInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(2)).getConstructors()).containsExactly(constructorInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(3)).getMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    public void addNewElements_testInsertionOfSingleElementWithAnExistingKey() {
        String className = "TestClass";

        PackageInvocation packageInvocationInformation = new PackageInvocation("moduleImportInformationName");
        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("dataInvocationInformationName");
        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));
        MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));


        cdt.addPackageInvocationElement(className, InvocationType.OUTGOING, packageInvocationInformation);
        cdt.addAttributeInvocationElement(className, InvocationType.OUTGOING, attributeInvocationInformation);
        cdt.addConstructorInvocationElement(className, InvocationType.OUTGOING, constructorInvocationInformation);
        cdt.addMethodInvocationElement(className, InvocationType.OUTGOING, methodInvocationInformation);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(cdt.getOutgoingDependenceInfo().get(className).getPackages()).containsExactly(packageInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(className).getAttributes()).containsExactly(attributeInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(className).getConstructors()).containsExactly(constructorInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(className).getMethods()).containsExactly(methodInvocationInformation);
    }
}
