package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.AttributeDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.MethodDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.PackageDeclarationInformation;
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

        pdt.addModuleDeclaration(declaringClassName, packageDeclarationInformation);

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
    public void addImportedPackage_testIfItIsStoredInOutgoingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocationInformation packageDeclarationInformation = new PackageInvocationInformation("com.ADA.invocation_example");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getPackages()).containsExactly(packageDeclarationInformation);
    }


    @Test
    public void addImportedPackage_testIfItIsStoredInIncomingDependenciesAndKeyDoesntAlreadyExist() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocationInformation packageDeclarationInformation = new PackageInvocationInformation("com.ADA.invocation_example");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageDeclarationInformation);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getPackages()).containsExactly(packageDeclarationInformation);
    }

    @Test
    public void addImportedPackage_testIfItIsStoredInOutgoingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocationInformation packageDeclarationInformation0 = new PackageInvocationInformation("com.ADA.invocation_example0");
        PackageInvocationInformation packageDeclarationInformation1 = new PackageInvocationInformation("com.ADA.invocation_example1");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageDeclarationInformation0);
        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageDeclarationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(consumingClassName).getOutgoingDependenceInfo().get(declaringClassName).getPackages()).containsExactlyInAnyOrderElementsOf(Arrays.asList(packageDeclarationInformation0, packageDeclarationInformation1));
    }


    @Test
    public void addImportedPackage_testIfItIsStoredInIncomingDependenciesAndKeyAlreadyExists() {
        String declaringClassName = "DeclaringTestClass";
        String consumingClassName = "ConsumingTestClass";

        PackageInvocationInformation packageDeclarationInformation0 = new PackageInvocationInformation("com.ADA.invocation_example0");
        PackageInvocationInformation packageDeclarationInformation1 = new PackageInvocationInformation("com.ADA.invocation_example1");

        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageDeclarationInformation0);
        pdt.addPackageInvocation(consumingClassName, declaringClassName, packageDeclarationInformation1);

        assertThat(pdt.getClassDependenceTrees().get(declaringClassName).getIncomingDependenceInfo().get(consumingClassName).getPackages()).containsExactlyInAnyOrderElementsOf(Arrays.asList(packageDeclarationInformation0, packageDeclarationInformation1));
    }

}