package com.ucl.ADA.model.class_structure;

import com.ucl.ADA.model.dependence_information.declaration_information.*;
import com.ucl.ADA.model.dependence_information.invocation_information.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class ClassStructureTest {

    private ClassStructure classStructure;

    private Set<ModifierType> modifiers = new HashSet<>(Collections.singletonList(
            ModifierType.DEFAULT
    ));

    private ArrayList<ParameterDeclaration> declaredParameters = new ArrayList<>(Arrays.asList(
            new ParameterDeclaration("String", "FirstParameter"),
            new ParameterDeclaration("Integer", "SecondParameter")
    ));

    private ArrayList<PassedParameter> passedParameterList0 = new ArrayList<>(Arrays.asList(
            new PassedParameter("FirstParameter0"),
            new PassedParameter("SecondParameter0")
    ));

    private ArrayList<PassedParameter> passedParameterList1 = new ArrayList<>(Arrays.asList(
            new PassedParameter("FirstParameter1"),
            new PassedParameter("SecondParameter1")
    ));

    @BeforeEach
    void setUp() {
        classStructure = new ClassStructure();
    }

    @Test
    void setCurrentModule_testSetUp() {
        PackageDeclaration module = new PackageDeclaration("com.ADA");
        classStructure.setCurrentPackage(module);

        assertThat(classStructure.getCurrentPackage()).isEqualTo(module);
    }

    @Test
    void addDataDeclaration_addNewElement() {
        AttributeDeclaration attributeDeclarationInformation = new AttributeDeclaration(modifiers, "String", "attribute", "declaringAttributeName");

        classStructure.addAttributeDeclaration(attributeDeclarationInformation);

        assertThat(classStructure.getAttributeDeclarations()).containsExactly(attributeDeclarationInformation);
    }

    @Test
    void addConstructorDeclaration_addNewElement() {
        ConstructorDeclaration constructorDeclarationInformation = new ConstructorDeclaration(modifiers, "String", declaredParameters);

        classStructure.addConstructorDeclaration(constructorDeclarationInformation);

        assertThat(classStructure.getConstructorDeclarations()).containsExactly(constructorDeclarationInformation);
    }

    @Test
    void addMethodDeclaration_addNewElement() {
        MethodDeclaration methodDeclarationInformation = new MethodDeclaration(modifiers, "String", "testingTesting", declaredParameters);

        classStructure.addMethodDeclaration(methodDeclarationInformation);

        assertThat(classStructure.getMethodsDeclarations()).containsExactly(methodDeclarationInformation);
    }

    @Test
    void addNewGlobalData_addNewElement() {
        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("globalInvocationInformationName");

        classStructure.addNewGlobalData(attributeInvocationInformation);

        assertThat(classStructure.getGlobalData()).containsExactly(attributeInvocationInformation);
    }

    @Test
    void addNewGlobalMethod_addNewElement() {
        MethodInvocation methodInvocationInformation = new MethodInvocation("globalMethodExample", passedParameterList0);

        classStructure.addGlobalMethod(methodInvocationInformation);

        assertThat(classStructure.getGlobalMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    void addNewExternalPackageImport_addNewElement() {
        PackageInvocation packageInvocationInformation = new PackageInvocation("moduleInvocationInformationName");

        classStructure.addExternalPackageImport(packageInvocationInformation);

        assertThat(classStructure.getExternalPackageImports()).containsExactly(packageInvocationInformation);
    }

    @Test
    void addNewExternalAttributeInvocation_addNewElement() {
        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("invocationInformationName");

        classStructure.addExternalAttributeInvocation(attributeInvocationInformation);

        assertThat(classStructure.getExternalAttributeInvocations()).containsExactly(attributeInvocationInformation);
    }

    @Test
    void addNewExternalConstructorInvocation_addNewElement() {
        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", passedParameterList0);

        classStructure.addExternalConstructorInvocation(constructorInvocationInformation);

        assertThat(classStructure.getExternalConstructorInvocations()).containsExactly(constructorInvocationInformation);
    }

    @Test
    void addNewExternalMethodInvocation_addNewElement() {
        MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", passedParameterList0);

        classStructure.addExternalMethodInvocation(methodInvocationInformation);

        assertThat(classStructure.getExternalMethodInvocations()).containsExactly(methodInvocationInformation);
    }

    @Test
    void addModuleElement_testAddingOutgoingInvocation() {
        String className = "TestClass";

        PackageInvocation packageInvocationInformation = new PackageInvocation("moduleImportInformationName");

        classStructure.addPackageInvocationElement(className, InvocationType.OUTGOING, packageInvocationInformation);

        assertThat(classStructure.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getOutgoingDependenceInfo().get(className).getPackages()).containsExactly(packageInvocationInformation);
    }

    @Test
    void addDataElement_testAddingOutgoingInvocation() {
        String className = "TestClass";

        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("dataInvocationInformationName");

        classStructure.addAttributeInvocationElement(className, InvocationType.OUTGOING, attributeInvocationInformation);

        assertThat(classStructure.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getOutgoingDependenceInfo().get(className).getAttributes()).containsExactly(attributeInvocationInformation);
    }

    @Test
    void addConstructorElement_testAddingOutgoingInvocation() {
        String className = "TestClass";

        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", passedParameterList0);

        classStructure.addConstructorInvocationElement(className, InvocationType.OUTGOING, constructorInvocationInformation);

        assertThat(classStructure.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getOutgoingDependenceInfo().get(className).getConstructors()).containsExactly(constructorInvocationInformation);
    }

    @Test
    void addMethodElement_testAddingOutgoingInvocation() {
        String className = "TestClass";

        MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", passedParameterList0);

        classStructure.addMethodInvocationElement(className, InvocationType.OUTGOING, methodInvocationInformation);

        assertThat(classStructure.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getOutgoingDependenceInfo().get(className).getMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    void addModuleElement_testAddingIncomingInvocation() {
        String className = "TestClass";

        PackageInvocation packageInvocationInformation = new PackageInvocation("moduleImportInformationName");


        classStructure.addPackageInvocationElement(className, InvocationType.INCOMING, packageInvocationInformation);

        assertThat(classStructure.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getIncomingDependenceInfo().get(className).getPackages()).containsExactly(packageInvocationInformation);
    }

    @Test
    void addDataElement_testAddingIncomingInvocation() {
        String className = "TestClass";

        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("dataInvocationInformationName");

        classStructure.addAttributeInvocationElement(className, InvocationType.INCOMING, attributeInvocationInformation);

        assertThat(classStructure.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getIncomingDependenceInfo().get(className).getAttributes()).containsExactly(attributeInvocationInformation);
    }

    @Test
    void addConstructorElement_testAddingIncomingInvocation() {
        String className = "TestClass";

        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", passedParameterList0);

        classStructure.addConstructorInvocationElement(className, InvocationType.INCOMING, constructorInvocationInformation);

        assertThat(classStructure.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getIncomingDependenceInfo().get(className).getConstructors()).containsExactly(constructorInvocationInformation);
    }

    @Test
    void addMethodElement_testAddingIncomingInvocation() {
        String className = "TestClass";

        MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", passedParameterList0);

        classStructure.addMethodInvocationElement(className, InvocationType.INCOMING, methodInvocationInformation);

        assertThat(classStructure.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getIncomingDependenceInfo().get(className).getMethods()).containsExactly(methodInvocationInformation);
    }


    @Test
    void addModuleElement_testAddingOutgoingInvocationWithExistingElement() {
        String className = "TestClass";

        PackageInvocation packageInvocationInformation0 = new PackageInvocation("moduleImportInformationName0");
        PackageInvocation packageInvocationInformation1 = new PackageInvocation("moduleImportInformationName1");

        classStructure.addPackageInvocationElement(className, InvocationType.OUTGOING, packageInvocationInformation0);
        classStructure.addPackageInvocationElement(className, InvocationType.OUTGOING, packageInvocationInformation1);

        assertThat(classStructure.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getOutgoingDependenceInfo().get(className).getPackages()).containsExactlyInAnyOrderElementsOf(Arrays.asList(packageInvocationInformation0, packageInvocationInformation1));
    }

    @Test
    void addDataElement_testAddingOutgoingInvocationWithExistingElement() {
        String className = "TestClass";

        AttributeInvocation attributeInvocationInformation0 = new AttributeInvocation("dataInvocationInformationName0");
        AttributeInvocation attributeInvocationInformation1 = new AttributeInvocation("dataInvocationInformationName1");

        classStructure.addAttributeInvocationElement(className, InvocationType.OUTGOING, attributeInvocationInformation0);
        classStructure.addAttributeInvocationElement(className, InvocationType.OUTGOING, attributeInvocationInformation1);

        assertThat(classStructure.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getOutgoingDependenceInfo().get(className).getAttributes()).containsExactlyInAnyOrderElementsOf(Arrays.asList(attributeInvocationInformation0, attributeInvocationInformation1));
    }

    @Test
    void addConstructorElement_testAddingOutgoingInvocationWithExistingElement() {
        String className = "TestClass";

        ConstructorInvocation constructorInvocationInformation0 = new ConstructorInvocation("constructorExample0", passedParameterList0);
        ConstructorInvocation constructorInvocationInformation1 = new ConstructorInvocation("constructorExample1", passedParameterList1);

        classStructure.addConstructorInvocationElement(className, InvocationType.OUTGOING, constructorInvocationInformation0);
        classStructure.addConstructorInvocationElement(className, InvocationType.OUTGOING, constructorInvocationInformation1);

        assertThat(classStructure.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getOutgoingDependenceInfo().get(className).getConstructors()).containsExactlyInAnyOrderElementsOf(Arrays.asList(constructorInvocationInformation0, constructorInvocationInformation1));
    }

    @Test
    void addMethodElement_testAddingOutgoingInvocationWithExistingElement() {
        String className = "TestClass";

        MethodInvocation methodInvocationInformation0 = new MethodInvocation("methodExample0", passedParameterList0);
        MethodInvocation methodInvocationInformation1 = new MethodInvocation("methodExample1", passedParameterList1);

        classStructure.addMethodInvocationElement(className, InvocationType.OUTGOING, methodInvocationInformation0);
        classStructure.addMethodInvocationElement(className, InvocationType.OUTGOING, methodInvocationInformation1);

        assertThat(classStructure.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getOutgoingDependenceInfo().get(className).getMethods()).containsExactlyInAnyOrderElementsOf(Arrays.asList(methodInvocationInformation0, methodInvocationInformation1));
    }

    @Test
    void addModuleElement_testAddingIncomingInvocationWithExistingElement() {
        String className = "TestClass";

        PackageInvocation packageInvocationInformation0 = new PackageInvocation("moduleImportInformationName0");
        PackageInvocation packageInvocationInformation1 = new PackageInvocation("moduleImportInformationName1");


        classStructure.addPackageInvocationElement(className, InvocationType.INCOMING, packageInvocationInformation0);
        classStructure.addPackageInvocationElement(className, InvocationType.INCOMING, packageInvocationInformation1);

        assertThat(classStructure.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getIncomingDependenceInfo().get(className).getPackages()).containsExactlyInAnyOrderElementsOf(Arrays.asList(packageInvocationInformation0, packageInvocationInformation1));
    }

    @Test
    void addDataElement_testAddingIncomingInvocationWithExistingElement() {
        String className = "TestClass";

        AttributeInvocation attributeInvocationInformation0 = new AttributeInvocation("dataInvocationInformationName0");
        AttributeInvocation attributeInvocationInformation1 = new AttributeInvocation("dataInvocationInformationName1");

        classStructure.addAttributeInvocationElement(className, InvocationType.INCOMING, attributeInvocationInformation0);
        classStructure.addAttributeInvocationElement(className, InvocationType.INCOMING, attributeInvocationInformation1);

        assertThat(classStructure.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getIncomingDependenceInfo().get(className).getAttributes()).containsExactlyInAnyOrderElementsOf(Arrays.asList(attributeInvocationInformation0, attributeInvocationInformation1));
    }

    @Test
    void addConstructorElement_testAddingIncomingInvocationWithExistingElement() {
        String className = "TestClass";

        ConstructorInvocation constructorInvocationInformation0 = new ConstructorInvocation("constructorExample0", passedParameterList0);
        ConstructorInvocation constructorInvocationInformation1 = new ConstructorInvocation("constructorExample1", passedParameterList1);

        classStructure.addConstructorInvocationElement(className, InvocationType.INCOMING, constructorInvocationInformation0);
        classStructure.addConstructorInvocationElement(className, InvocationType.INCOMING, constructorInvocationInformation1);

        assertThat(classStructure.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getIncomingDependenceInfo().get(className).getConstructors()).containsExactlyInAnyOrderElementsOf(Arrays.asList(constructorInvocationInformation0, constructorInvocationInformation1));
    }

    @Test
    void addMethodElement_testAddingIncomingInvocationWithExistingElement() {
        String className = "TestClass";

        MethodInvocation methodInvocationInformation0 = new MethodInvocation("methodExample0", passedParameterList0);
        MethodInvocation methodInvocationInformation1 = new MethodInvocation("methodExample1", passedParameterList1);

        classStructure.addMethodInvocationElement(className, InvocationType.INCOMING, methodInvocationInformation0);
        classStructure.addMethodInvocationElement(className, InvocationType.INCOMING, methodInvocationInformation1);

        assertThat(classStructure.getIncomingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getIncomingDependenceInfo().get(className).getMethods()).containsExactlyInAnyOrderElementsOf(Arrays.asList(methodInvocationInformation0, methodInvocationInformation1));
    }

    @Test
    public void addNewElements_testCreationOfKeys() {
        ArrayList<String> classNames = new ArrayList<>(Arrays.asList("FirstClass", "SecondClass", "ThirdClass", "FourthClass"));

        classStructure.addPackageInvocationElement(classNames.get(0), InvocationType.OUTGOING, null);
        classStructure.addAttributeInvocationElement(classNames.get(1), InvocationType.OUTGOING, null);
        classStructure.addConstructorInvocationElement(classNames.get(2), InvocationType.OUTGOING, null);
        classStructure.addMethodInvocationElement(classNames.get(3), InvocationType.OUTGOING, null);

        assertThat(classStructure.getOutgoingDependenceInfo().keySet()).containsExactlyInAnyOrderElementsOf(classNames);
    }

    @Test
    public void addNewElements_testInsertionOfSingleElementPerClassAndType() {
        ArrayList<String> classNames = new ArrayList<>(Arrays.asList("FirstClass", "SecondClass", "ThirdClass", "FourthClass"));

        PackageInvocation packageInvocationInformation = new PackageInvocation("moduleImportInformationName");
        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("dataInvocationInformationName");
        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", passedParameterList0);
        MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", passedParameterList1);


        classStructure.addPackageInvocationElement(classNames.get(0), InvocationType.OUTGOING, packageInvocationInformation);
        classStructure.addAttributeInvocationElement(classNames.get(1), InvocationType.OUTGOING, attributeInvocationInformation);
        classStructure.addConstructorInvocationElement(classNames.get(2), InvocationType.OUTGOING, constructorInvocationInformation);
        classStructure.addMethodInvocationElement(classNames.get(3), InvocationType.OUTGOING, methodInvocationInformation);

        assertThat(classStructure.getOutgoingDependenceInfo().keySet()).containsExactlyInAnyOrderElementsOf(classNames);

        assertThat(classStructure.getOutgoingDependenceInfo().get(classNames.get(0)).getPackages()).containsExactly(packageInvocationInformation);
        assertThat(classStructure.getOutgoingDependenceInfo().get(classNames.get(1)).getAttributes()).containsExactly(attributeInvocationInformation);
        assertThat(classStructure.getOutgoingDependenceInfo().get(classNames.get(2)).getConstructors()).containsExactly(constructorInvocationInformation);
        assertThat(classStructure.getOutgoingDependenceInfo().get(classNames.get(3)).getMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    public void addNewElements_testInsertionOfSingleElementWithAnExistingKey() {
        String className = "TestClass";

        PackageInvocation packageInvocationInformation = new PackageInvocation("moduleImportInformationName");
        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("dataInvocationInformationName");
        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", passedParameterList0);
        MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", passedParameterList1);


        classStructure.addPackageInvocationElement(className, InvocationType.OUTGOING, packageInvocationInformation);
        classStructure.addAttributeInvocationElement(className, InvocationType.OUTGOING, attributeInvocationInformation);
        classStructure.addConstructorInvocationElement(className, InvocationType.OUTGOING, constructorInvocationInformation);
        classStructure.addMethodInvocationElement(className, InvocationType.OUTGOING, methodInvocationInformation);

        assertThat(classStructure.getOutgoingDependenceInfo().keySet()).containsExactly(className);

        assertThat(classStructure.getOutgoingDependenceInfo().get(className).getPackages()).containsExactly(packageInvocationInformation);
        assertThat(classStructure.getOutgoingDependenceInfo().get(className).getAttributes()).containsExactly(attributeInvocationInformation);
        assertThat(classStructure.getOutgoingDependenceInfo().get(className).getConstructors()).containsExactly(constructorInvocationInformation);
        assertThat(classStructure.getOutgoingDependenceInfo().get(className).getMethods()).containsExactly(methodInvocationInformation);
    }
}
