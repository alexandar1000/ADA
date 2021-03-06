package com.ucl.ADA.model.project_structure;

import com.ucl.ADA.model.dependence_information.declaration_information.*;
import com.ucl.ADA.model.dependence_information.invocation_information.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectStructureTest {
    private ProjectStructure pdt;
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
        pdt = new ProjectStructure();
    }

    @Test
    public void testAddPackageDeclaration() {
        String declaringClassName = "DeclaringTestClass";
        PackageDeclaration packageDeclarationInformation = new PackageDeclaration("com.ADA.example");

        pdt.addPackageDeclaration(declaringClassName, packageDeclarationInformation);

        assertThat(pdt.getClassStructures().get(declaringClassName).getCurrentPackage()).isEqualTo(packageDeclarationInformation);
    }

    @Test
    public void testAddAttributeDeclaration() {
        String declaringClassName = "DeclaringTestClass";

        AttributeDeclaration attributeDeclarationInformation = new AttributeDeclaration(modifiers, "String", "attribute", "declaringAttributeName");

        pdt.addAttributeDeclaration(declaringClassName, attributeDeclarationInformation);

        assertThat(pdt.getClassStructures().get(declaringClassName).getAttributeDeclarations()).containsExactly(attributeDeclarationInformation);
    }

    @Test
    public void testAddConstructorDeclaration() {
        String declaringClassName = "DeclaringTestClass";
        ConstructorDeclaration constructorDeclarationInformation = new ConstructorDeclaration(modifiers, "String", declaredParameters);

        pdt.addConstructorDeclaration(declaringClassName, constructorDeclarationInformation);

        assertThat(pdt.getClassStructures().get(declaringClassName).getConstructorDeclarations()).containsExactly(constructorDeclarationInformation);
    }

    @Test
    public void testAddMethodDeclaration() {
        String declaringClassName = "DeclaringTestClass";
        MethodDeclaration methodDeclarationInformation = new MethodDeclaration(modifiers, "String", "testingTesting", declaredParameters);

        pdt.addMethodDeclaration(declaringClassName, methodDeclarationInformation);

        assertThat(pdt.getClassStructures().get(declaringClassName).getMethodsDeclarations()).containsExactly(methodDeclarationInformation);
    }

    @Test
    public void addPackageInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocation packageDeclarationInformation = new PackageInvocation("com.ADA.invocation_example");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageDeclarationInformation);

        assertThat(pdt.getClassStructures().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getPackages()).containsExactly(packageDeclarationInformation);
    }


    @Test
    public void addPackageInvocation_testIfItIsStoredInIncomingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocation packageInvocationInformation = new PackageInvocation("com.ADA.invocation_example");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageInvocationInformation);

        assertThat(pdt.getClassStructures().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getPackages()).containsExactly(packageInvocationInformation);
    }

    @Test
    public void addPackageInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocation packageInvocationInformation0 = new PackageInvocation("com.ADA.invocation_example0");
        PackageInvocation packageInvocationInformation1 = new PackageInvocation("com.ADA.invocation_example1");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageInvocationInformation0);
        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageInvocationInformation1);

        assertThat(pdt.getClassStructures().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getPackages()).containsExactlyInAnyOrderElementsOf(Arrays.asList(packageInvocationInformation0, packageInvocationInformation1));
    }


    @Test
    public void addPackageInvocation_testIfItIsStoredInIncomingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocation packageInvocationInformation0 = new PackageInvocation("com.ADA.invocation_example0");
        PackageInvocation packageInvocationInformation1 = new PackageInvocation("com.ADA.invocation_example1");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageInvocationInformation0);
        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageInvocationInformation1);

        assertThat(pdt.getClassStructures().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getPackages()).containsExactlyInAnyOrderElementsOf(Arrays.asList(packageInvocationInformation0, packageInvocationInformation1));
    }

    @Test
    public void addAttributeInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("attributeExample");

        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation);

        assertThat(pdt.getClassStructures().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getAttributes()).containsExactly(attributeInvocationInformation);
    }


    @Test
    public void addAttributeInvocation_testIfItIsStoredInIncomingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("attributeExample");

        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation);


        assertThat(pdt.getClassStructures().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getAttributes()).containsExactly(attributeInvocationInformation);
    }

    @Test
    public void addAttributeInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        AttributeInvocation attributeInvocationInformation0 = new AttributeInvocation("attributeExample0");
        AttributeInvocation attributeInvocationInformation1 = new AttributeInvocation("attributeExample1");

        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation0);
        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation1);

        assertThat(pdt.getClassStructures().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getAttributes()).containsExactlyInAnyOrderElementsOf(Arrays.asList(attributeInvocationInformation0, attributeInvocationInformation1));
    }


    @Test
    public void addAttributeInvocation_testIfItIsStoredInIncomingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        AttributeInvocation attributeInvocationInformation0 = new AttributeInvocation("attributeExample0");
        AttributeInvocation attributeInvocationInformation1 = new AttributeInvocation("attributeExample1");

        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation0);
        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation1);

        assertThat(pdt.getClassStructures().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getAttributes()).containsExactlyInAnyOrderElementsOf(Arrays.asList(attributeInvocationInformation0, attributeInvocationInformation1));
    }

    @Test
    public void addConstructorInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", passedParameterList0);

        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation);

        assertThat(pdt.getClassStructures().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getConstructors()).containsExactly(constructorInvocationInformation);
    }


    @Test
    public void addConstructorInvocation_testIfItIsStoredInIncomingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", passedParameterList0);

        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation);

        assertThat(pdt.getClassStructures().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getConstructors()).containsExactly(constructorInvocationInformation);
    }

    @Test
    public void addConstructorInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        ConstructorInvocation constructorInvocationInformation0 = new ConstructorInvocation("constructorExample0", passedParameterList0);
        ConstructorInvocation constructorInvocationInformation1 = new ConstructorInvocation("constructorExample1", passedParameterList1);

        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation0);
        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation1);

        assertThat(pdt.getClassStructures().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getConstructors()).containsExactlyInAnyOrderElementsOf(Arrays.asList(constructorInvocationInformation0, constructorInvocationInformation1));
    }


    @Test
    public void addConstructorInvocation_testIfItIsStoredInIncomingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";


        ConstructorInvocation constructorInvocationInformation0 = new ConstructorInvocation("constructorExample0", passedParameterList0);
        ConstructorInvocation constructorInvocationInformation1 = new ConstructorInvocation("constructorExample1", passedParameterList1);

        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation0);
        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation1);

        assertThat(pdt.getClassStructures().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getConstructors()).containsExactlyInAnyOrderElementsOf(Arrays.asList(constructorInvocationInformation0, constructorInvocationInformation1));
    }

    @Test
    public void addMethodInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", passedParameterList0);

        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation);

        assertThat(pdt.getClassStructures().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getMethods()).containsExactly(methodInvocationInformation);
    }


    @Test
    public void addMethodInvocation_testIfItIsStoredInIncomingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", passedParameterList0);

        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation);

        assertThat(pdt.getClassStructures().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    public void addMethodInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        MethodInvocation methodInvocationInformation0 = new MethodInvocation("methodExample0", passedParameterList0);
        MethodInvocation methodInvocationInformation1 = new MethodInvocation("methodExample1", passedParameterList1);

        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation0);
        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation1);

        assertThat(pdt.getClassStructures().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getMethods()).containsExactlyInAnyOrderElementsOf(Arrays.asList(methodInvocationInformation0, methodInvocationInformation1));
    }


    @Test
    public void addMethodInvocation_testIfItIsStoredInIncomingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        MethodInvocation methodInvocationInformation0 = new MethodInvocation("methodExample0", passedParameterList0);
        MethodInvocation methodInvocationInformation1 = new MethodInvocation("methodExample1", passedParameterList1);

        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation0);
        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation1);

       assertThat(pdt.getClassStructures().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getMethods()).containsExactlyInAnyOrderElementsOf(Arrays.asList(methodInvocationInformation0, methodInvocationInformation1));
    }

    @Test
    void addExternalPackageImport() {
        String declaringClassName = "DeclaringTestClass";
        PackageInvocation packageInvocation = new PackageInvocation("com.ADA.example");

        pdt.addExternalPackageImport(declaringClassName, packageInvocation);

        assertThat(pdt.getClassStructures().get(declaringClassName).getExternalPackageImports()).containsExactly(packageInvocation);
    }

    @Test
    void addExternalMethodInvocations() {
        String declaringClassName = "DeclaringTestClass";
        MethodInvocation methodInvocation = new MethodInvocation("method", passedParameterList0);

        pdt.addExternalMethodInvocations(declaringClassName, methodInvocation);

        assertThat(pdt.getClassStructures().get(declaringClassName).getExternalMethodInvocations()).containsExactly(methodInvocation);
    }

    @Test
    void addExternalConstructorInvocations() {
        String declaringClassName = "DeclaringTestClass";
        ConstructorInvocation constructorInvocation = new ConstructorInvocation("constructor", passedParameterList0);

        pdt.addExternalConstructorInvocations(declaringClassName, constructorInvocation);

        assertThat(pdt.getClassStructures().get(declaringClassName).getExternalConstructorInvocations()).containsExactly(constructorInvocation);
    }

    @Test
    void addExternalFieldDeclarations() {
        String declaringClassName = "DeclaringTestClass";

        AttributeInvocation attributeInvocation = new AttributeInvocation("attribute");

        pdt.addExternalAttributeDeclarations(declaringClassName, attributeInvocation);

        assertThat(pdt.getClassStructures().get(declaringClassName).getExternalAttributeInvocations()).containsExactly(attributeInvocation);
    }
}
