package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.*;
import com.ucl.ADA.parser.dependence_information.invocation_information.AttributeInvocation;
import com.ucl.ADA.parser.dependence_information.invocation_information.ConstructorInvocation;
import com.ucl.ADA.parser.dependence_information.invocation_information.MethodInvocation;
import com.ucl.ADA.parser.dependence_information.invocation_information.PackageInvocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectDependenceTreeTest {
    private ProjectDependenceTree pdt;

    @BeforeEach
    void setUp() {
        pdt = new ProjectDependenceTree();
    }

    @Test
    public void testAddPackageDeclaration() {
        String declaringClassName = "DeclaringTestClass";
        PackageDeclaration packageDeclarationInformation = new PackageDeclaration("com.ADA.example");

        pdt.addPackageDeclaration(declaringClassName, packageDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getCurrentPackage()).isEqualTo(packageDeclarationInformation);
    }

    @Test
    public void testAddAttributeDeclaration() {
        String declaringClassName = "DeclaringTestClass";

        AttributeDeclaration attributeDeclarationInformation = new AttributeDeclaration(ModifierType.DEFAULT, "String", "attribute", "declaringAttributeName", true);

        pdt.addAttributeDeclaration(declaringClassName, attributeDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getAttributeDeclarations()).containsExactly(attributeDeclarationInformation);
    }

    @Test
    public void testAddConstructorDeclaration() {
        String declaringClassName = "DeclaringTestClass";
        ConstructorDeclaration constructorDeclarationInformation = new ConstructorDeclaration(ModifierType.DEFAULT, "String", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        pdt.addConstructorDeclaration(declaringClassName, constructorDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getConstructorDeclarations()).containsExactly(constructorDeclarationInformation);
    }

    @Test
    public void testAddMethodDeclaration() {
        String declaringClassName = "DeclaringTestClass";
        MethodDeclaration methodDeclarationInformation = new MethodDeclaration(ModifierType.DEFAULT, "String", "testingTesting", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        pdt.addMethodDeclaration(declaringClassName, methodDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getMethodsDeclarations()).containsExactly(methodDeclarationInformation);
    }

    @Test
    public void addPackageInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocation packageDeclarationInformation = new PackageInvocation("com.ADA.invocation_example");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getPackages()).containsExactly(packageDeclarationInformation);
    }


    @Test
    public void addPackageInvocation_testIfItIsStoredInIncomingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocation packageInvocationInformation = new PackageInvocation("com.ADA.invocation_example");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageInvocationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getPackages()).containsExactly(packageInvocationInformation);
    }

    @Test
    public void addPackageInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocation packageInvocationInformation0 = new PackageInvocation("com.ADA.invocation_example0");
        PackageInvocation packageInvocationInformation1 = new PackageInvocation("com.ADA.invocation_example1");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageInvocationInformation0);
        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageInvocationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getPackages()).containsExactlyInAnyOrderElementsOf(Arrays.asList(packageInvocationInformation0, packageInvocationInformation1));
    }


    @Test
    public void addPackageInvocation_testIfItIsStoredInIncomingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocation packageInvocationInformation0 = new PackageInvocation("com.ADA.invocation_example0");
        PackageInvocation packageInvocationInformation1 = new PackageInvocation("com.ADA.invocation_example1");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageInvocationInformation0);
        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageInvocationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getPackages()).containsExactlyInAnyOrderElementsOf(Arrays.asList(packageInvocationInformation0, packageInvocationInformation1));
    }

    @Test
    public void addAttributeInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("attributeExample");

        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getAttributes()).containsExactly(attributeInvocationInformation);
    }


    @Test
    public void addAttributeInvocation_testIfItIsStoredInIncomingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("attributeExample");

        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation);


        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getAttributes()).containsExactly(attributeInvocationInformation);
    }

    @Test
    public void addAttributeInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        AttributeInvocation attributeInvocationInformation0 = new AttributeInvocation("attributeExample0");
        AttributeInvocation attributeInvocationInformation1 = new AttributeInvocation("attributeExample1");

        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation0);
        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getAttributes()).containsExactlyInAnyOrderElementsOf(Arrays.asList(attributeInvocationInformation0, attributeInvocationInformation1));
    }


    @Test
    public void addAttributeInvocation_testIfItIsStoredInIncomingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        AttributeInvocation attributeInvocationInformation0 = new AttributeInvocation("attributeExample0");
        AttributeInvocation attributeInvocationInformation1 = new AttributeInvocation("attributeExample1");

        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation0);
        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getAttributes()).containsExactlyInAnyOrderElementsOf(Arrays.asList(attributeInvocationInformation0, attributeInvocationInformation1));
    }

    @Test
    public void addConstructorInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getConstructors()).containsExactly(constructorInvocationInformation);
    }


    @Test
    public void addConstructorInvocation_testIfItIsStoredInIncomingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getConstructors()).containsExactly(constructorInvocationInformation);
    }

    @Test
    public void addConstructorInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        ConstructorInvocation constructorInvocationInformation0 = new ConstructorInvocation("constructorExample0", new ArrayList<>(Arrays.asList("String FirstParameter0", "Integer SecondParameter0")));
        ConstructorInvocation constructorInvocationInformation1 = new ConstructorInvocation("constructorExample1", new ArrayList<>(Arrays.asList("String FirstParameter1", "Integer SecondParameter1")));

        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation0);
        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getConstructors()).containsExactlyInAnyOrderElementsOf(Arrays.asList(constructorInvocationInformation0, constructorInvocationInformation1));
    }


    @Test
    public void addConstructorInvocation_testIfItIsStoredInIncomingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";


        ConstructorInvocation constructorInvocationInformation0 = new ConstructorInvocation("constructorExample0", new ArrayList<>(Arrays.asList("String FirstParameter0", "Integer SecondParameter0")));
        ConstructorInvocation constructorInvocationInformation1 = new ConstructorInvocation("constructorExample1", new ArrayList<>(Arrays.asList("String FirstParameter1", "Integer SecondParameter1")));

        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation0);
        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getConstructors()).containsExactlyInAnyOrderElementsOf(Arrays.asList(constructorInvocationInformation0, constructorInvocationInformation1));
    }

    @Test
    public void addMethodInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getMethods()).containsExactly(methodInvocationInformation);
    }


    @Test
    public void addMethodInvocation_testIfItIsStoredInIncomingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    public void addMethodInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        MethodInvocation methodInvocationInformation0 = new MethodInvocation("methodExample0", new ArrayList<>(Arrays.asList("String FirstParameter0", "Integer SecondParameter0")));
        MethodInvocation methodInvocationInformation1 = new MethodInvocation("methodExample1", new ArrayList<>(Arrays.asList("String FirstParameter1", "Integer SecondParameter1")));

        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation0);
        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getMethods()).containsExactlyInAnyOrderElementsOf(Arrays.asList(methodInvocationInformation0, methodInvocationInformation1));
    }


    @Test
    public void addMethodInvocation_testIfItIsStoredInIncomingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        MethodInvocation methodInvocationInformation0 = new MethodInvocation("methodExample0", new ArrayList<>(Arrays.asList("String FirstParameter0", "Integer SecondParameter0")));
        MethodInvocation methodInvocationInformation1 = new MethodInvocation("methodExample1", new ArrayList<>(Arrays.asList("String FirstParameter1", "Integer SecondParameter1")));

        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation0);
        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation1);

       assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getMethods()).containsExactlyInAnyOrderElementsOf(Arrays.asList(methodInvocationInformation0, methodInvocationInformation1));
    }

}
