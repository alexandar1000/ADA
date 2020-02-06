package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.AttributeDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.MethodDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.PackageDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.AttributeInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.ConstructorInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.MethodInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.PackageInvocationInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectDependenceTreeTest {
    private ProjectDependenceTree pdt;

    @BeforeEach
    void setUp() {
        pdt = new ProjectDependenceTree();
    }

    @Test
    public void testAddModuleDeclaration() {
        String declaringClassName = "DeclaringTestClass";
        PackageDeclarationInformation packageDeclarationInformation = new PackageDeclarationInformation("com.ADA.example");

        pdt.addPackageDeclaration(declaringClassName, packageDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getCurrentPackage()).isEqualTo(packageDeclarationInformation);
    }

    @Test
    public void testAddAttributeDeclaration() {
        String declaringClassName = "DeclaringTestClass";
        AttributeDeclarationInformation attributeDeclarationInformation = new AttributeDeclarationInformation("exampleAttribute");

        pdt.addAttributeDeclaration(declaringClassName, attributeDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getAttributeDeclarations()).containsExactly(attributeDeclarationInformation);
    }

    @Test
    public void testAddConstructorDeclaration() {
        String declaringClassName = "DeclaringTestClass";
        ConstructorDeclarationInformation constructorDeclarationInformation = new ConstructorDeclarationInformation("exampleConstructor");

        pdt.addConstructorDeclaration(declaringClassName, constructorDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getConstructorDeclarations()).containsExactly(constructorDeclarationInformation);
    }

    @Test
    public void testAddMethodDeclaration() {
        String declaringClassName = "DeclaringTestClass";
        MethodDeclarationInformation methodDeclarationInformation = new MethodDeclarationInformation("exampleMethod");

        pdt.addMethodDeclaration(declaringClassName, methodDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getMethodsDeclarations()).containsExactly(methodDeclarationInformation);
    }

    @Test
    public void addPackageInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocationInformation packageDeclarationInformation = new PackageInvocationInformation("com.ADA.invocation_example");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getPackages()).containsExactly(packageDeclarationInformation);
    }


    @Test
    public void addPackageInvocation_testIfItIsStoredInIncomingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocationInformation packageDeclarationInformation = new PackageInvocationInformation("com.ADA.invocation_example");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getPackages()).containsExactly(packageDeclarationInformation);
    }

    @Test
    public void addPackageInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocationInformation packageDeclarationInformation0 = new PackageInvocationInformation("com.ADA.invocation_example0");
        PackageInvocationInformation packageDeclarationInformation1 = new PackageInvocationInformation("com.ADA.invocation_example1");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageDeclarationInformation0);
        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageDeclarationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getPackages()).containsExactlyInAnyOrderElementsOf(Arrays.asList(packageDeclarationInformation0, packageDeclarationInformation1));
    }


    @Test
    public void addPackageInvocation_testIfItIsStoredInIncomingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocationInformation packageDeclarationInformation0 = new PackageInvocationInformation("com.ADA.invocation_example0");
        PackageInvocationInformation packageDeclarationInformation1 = new PackageInvocationInformation("com.ADA.invocation_example1");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageDeclarationInformation0);
        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageDeclarationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getPackages()).containsExactlyInAnyOrderElementsOf(Arrays.asList(packageDeclarationInformation0, packageDeclarationInformation1));
    }

    @Test
    public void addAttributeInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        AttributeInvocationInformation attributeInvocationInformation = new AttributeInvocationInformation("attributeExample");

        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getAttributes()).containsExactly(attributeInvocationInformation);
    }


    @Test
    public void addAttributeInvocation_testIfItIsStoredInIncomingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        AttributeInvocationInformation attributeInvocationInformation = new AttributeInvocationInformation("attributeExample");

        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation);


        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getAttributes()).containsExactly(attributeInvocationInformation);
    }

    @Test
    public void addAttributeInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        AttributeInvocationInformation attributeInvocationInformation0 = new AttributeInvocationInformation("attributeExample0");
        AttributeInvocationInformation attributeInvocationInformation1 = new AttributeInvocationInformation("attributeExample1");

        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation0);
        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getAttributes()).containsExactlyInAnyOrderElementsOf(Arrays.asList(attributeInvocationInformation0, attributeInvocationInformation1));
    }


    @Test
    public void addAttributeInvocation_testIfItIsStoredInIncomingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        AttributeInvocationInformation attributeInvocationInformation0 = new AttributeInvocationInformation("attributeExample0");
        AttributeInvocationInformation attributeInvocationInformation1 = new AttributeInvocationInformation("attributeExample1");

        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation0);
        pdt.addAttributeInvocation(consumingClassName, declaringClassName, attributeInvocationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getAttributes()).containsExactlyInAnyOrderElementsOf(Arrays.asList(attributeInvocationInformation0, attributeInvocationInformation1));
    }

    @Test
    public void addConstructorInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation("constructorExample");

        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getConstructors()).containsExactly(constructorInvocationInformation);
    }


    @Test
    public void addConstructorInvocation_testIfItIsStoredInIncomingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation("constructorExample");

        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getConstructors()).containsExactly(constructorInvocationInformation);
    }

    @Test
    public void addConstructorInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        ConstructorInvocationInformation constructorInvocationInformation0 = new ConstructorInvocationInformation("constructorExample0");
        ConstructorInvocationInformation constructorInvocationInformation1 = new ConstructorInvocationInformation("constructorExample1");

        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation0);
        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getConstructors()).containsExactlyInAnyOrderElementsOf(Arrays.asList(constructorInvocationInformation0, constructorInvocationInformation1));
    }


    @Test
    public void addConstructorInvocation_testIfItIsStoredInIncomingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        ConstructorInvocationInformation constructorInvocationInformation0 = new ConstructorInvocationInformation("constructorExample0");
        ConstructorInvocationInformation constructorInvocationInformation1 = new ConstructorInvocationInformation("constructorExample1");

        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation0);
        pdt.addConstructorInvocation(consumingClassName, declaringClassName, constructorInvocationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getConstructors()).containsExactlyInAnyOrderElementsOf(Arrays.asList(constructorInvocationInformation0, constructorInvocationInformation1));
    }

    @Test
    public void addMethodInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("methodExample");

        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getMethods()).containsExactly(methodInvocationInformation);
    }


    @Test
    public void addMethodInvocation_testIfItIsStoredInIncomingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("methodExample");

        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    public void addMethodInvocation_testIfItIsStoredInOutgoingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        MethodInvocationInformation methodInvocationInformation0 = new MethodInvocationInformation("methodExample0");
        MethodInvocationInformation methodInvocationInformation1 = new MethodInvocationInformation("methodExample1");

        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation0);
        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getMethods()).containsExactlyInAnyOrderElementsOf(Arrays.asList(methodInvocationInformation0, methodInvocationInformation1));
    }


    @Test
    public void addMethodInvocation_testIfItIsStoredInIncomingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        MethodInvocationInformation methodInvocationInformation0 = new MethodInvocationInformation("methodExample0");
        MethodInvocationInformation methodInvocationInformation1 = new MethodInvocationInformation("methodExample1");

        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation0);
        pdt.addMethodInvocation(consumingClassName, declaringClassName, methodInvocationInformation1);

       assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getMethods()).containsExactlyInAnyOrderElementsOf(Arrays.asList(methodInvocationInformation0, methodInvocationInformation1));
    }

}