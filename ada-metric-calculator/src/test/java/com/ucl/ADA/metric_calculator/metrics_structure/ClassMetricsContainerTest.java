package com.ucl.ADA.metric_calculator.metrics_structure;

import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import com.ucl.ADA.parser.dependence_information.declaration_information.*;
import com.ucl.ADA.parser.dependence_information.invocation_information.AttributeInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.ConstructorInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.MethodInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.PackageInvocationInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ClassMetricsContainerTest {
    private ProjectDependenceTree pdt;
    private ClassMetricsContainer classMetricsContainer;
    private ArrayList<String> classNames = new ArrayList<>(Arrays.asList("FirstClass", "SecondClass", "ThirdClass", "FourthClass", "FifthClass", "SixthClass"));

    @BeforeEach
    void setUp() {
        pdt = new ProjectDependenceTree();
        classMetricsContainer = new ClassMetricsContainer();

        // Add the package declaration to every class
        for (String className : classNames) {
            PackageDeclarationInformation packageDeclarationInformation = new PackageDeclarationInformation("com.ADA.example." + className);
            pdt.addPackageDeclaration(className, packageDeclarationInformation);
        }

        // Add three attributes to each class
        for (String className : classNames) {
            for (int j = 0; j < 3; j++) {
                String attributeName = "attribute" + j + "in" + className;
                AttributeDeclarationInformation attributeDeclarationInformation = new AttributeDeclarationInformation(AccessModifierType.DEFAULT, "String", attributeName, "declaringAttributeName" + j, true);
                pdt.addAttributeDeclaration(className, attributeDeclarationInformation);
            }
        }


        // Add two constructors to each class
        for (String className : classNames) {
            for (int j = 0; j < 2; j++) {
                String constructorName = "constructor" + j + "in" + className;
                ConstructorDeclarationInformation constructorDeclarationInformation = new ConstructorDeclarationInformation(AccessModifierType.DEFAULT, constructorName, new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));
                pdt.addConstructorDeclaration(className, constructorDeclarationInformation);
            }
        }

        // Add two methods to each class
        for (String className : classNames) {
            for (int j = 0; j < 2; j++) {
                String methodName = "method" + j + "in" + className;
                MethodDeclarationInformation methodDeclarationInformation = new MethodDeclarationInformation(AccessModifierType.DEFAULT, "String", methodName, new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));
                pdt.addMethodDeclaration(className, methodDeclarationInformation);
            }
        }

        String className;
        PackageInvocationInformation packageInvocationInformation = new PackageInvocationInformation("com.ADA.invocation_example");
        AttributeInvocationInformation attributeInvocationInformation = new AttributeInvocationInformation("attributeExample");
        ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation("constructorExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));
        MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("methodExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        // For FirstClass
        className = classNames.get(0);
        pdt.addPackageInvocation(className, classNames.get(1), packageInvocationInformation);
        pdt.addPackageInvocation(className, classNames.get(4), packageInvocationInformation);

        pdt.addAttributeInvocation(className, classNames.get(1), attributeInvocationInformation);
        pdt.addAttributeInvocation(className, classNames.get(4), attributeInvocationInformation);

        pdt.addConstructorInvocation(className, classNames.get(1), constructorInvocationInformation);
        pdt.addConstructorInvocation(className, classNames.get(4), constructorInvocationInformation);

        pdt.addMethodInvocation(className, classNames.get(1), methodInvocationInformation);
        pdt.addMethodInvocation(className, classNames.get(4), methodInvocationInformation);


        // For SecondClass
        className = classNames.get(1);
        pdt.addPackageInvocation(className, classNames.get(0), packageInvocationInformation);

        pdt.addAttributeInvocation(className, classNames.get(0), attributeInvocationInformation);

        pdt.addConstructorInvocation(className, classNames.get(0), constructorInvocationInformation);

        pdt.addMethodInvocation(className, classNames.get(0), methodInvocationInformation);

        // For ThirdClass
        className = classNames.get(2);
        pdt.addPackageInvocation(className, classNames.get(0), packageInvocationInformation);
        pdt.addPackageInvocation(className, classNames.get(1), packageInvocationInformation);
        pdt.addPackageInvocation(className, classNames.get(3), packageInvocationInformation);
        pdt.addPackageInvocation(className, classNames.get(4), packageInvocationInformation);

        pdt.addAttributeInvocation(className, classNames.get(0), attributeInvocationInformation);
        pdt.addAttributeInvocation(className, classNames.get(1), attributeInvocationInformation);
        pdt.addAttributeInvocation(className, classNames.get(3), attributeInvocationInformation);
        pdt.addAttributeInvocation(className, classNames.get(4), attributeInvocationInformation);

        pdt.addConstructorInvocation(className, classNames.get(0), constructorInvocationInformation);
        pdt.addConstructorInvocation(className, classNames.get(1), constructorInvocationInformation);
        pdt.addConstructorInvocation(className, classNames.get(3), constructorInvocationInformation);
        pdt.addConstructorInvocation(className, classNames.get(4), constructorInvocationInformation);

        pdt.addMethodInvocation(className, classNames.get(0), methodInvocationInformation);
        pdt.addMethodInvocation(className, classNames.get(1), methodInvocationInformation);
        pdt.addMethodInvocation(className, classNames.get(3), methodInvocationInformation);
        pdt.addMethodInvocation(className, classNames.get(4), methodInvocationInformation);

        // For FourthClass

        // For FifthClass
        className = classNames.get(4);
        pdt.addPackageInvocation(className, classNames.get(0), packageInvocationInformation);

        pdt.addAttributeInvocation(className, classNames.get(0), attributeInvocationInformation);

        pdt.addConstructorInvocation(className, classNames.get(0), constructorInvocationInformation);

        pdt.addMethodInvocation(className, classNames.get(0), methodInvocationInformation);

        // For SixthClass
        className = classNames.get(5);
        pdt.addPackageInvocation(className, classNames.get(2), packageInvocationInformation);

        pdt.addAttributeInvocation(className, classNames.get(2), attributeInvocationInformation);

        pdt.addConstructorInvocation(className, classNames.get(2), constructorInvocationInformation);

        pdt.addMethodInvocation(className, classNames.get(2), methodInvocationInformation);
    }

    @Test
    public void numberOfClassPackageImportsIncoming_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfClassPackageImportsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfPackageImportsIncoming()).isEqualTo(3);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfClassPackageImportsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfPackageImportsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfClassPackageImportsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfPackageImportsIncoming()).isEqualTo(1);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfClassPackageImportsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfPackageImportsIncoming()).isEqualTo(1);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfClassPackageImportsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfPackageImportsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(5);
        classMetricsContainer.calculateNumberOfClassPackageImportsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfPackageImportsIncoming()).isEqualTo(0);
    }

    @Test
    public void numberOfClassPackageImportsOutgoing_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfClassPackageImportsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfPackageImportsOutgoing()).isEqualTo(2);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfClassPackageImportsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfPackageImportsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfClassPackageImportsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfPackageImportsOutgoing()).isEqualTo(4);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfClassPackageImportsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfPackageImportsOutgoing()).isEqualTo(0);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfClassPackageImportsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfPackageImportsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(5);
        classMetricsContainer.calculateNumberOfClassPackageImportsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfPackageImportsOutgoing()).isEqualTo(1);
    }

    @Test
    public void numberOfClassAttributeInvocationsIncoming_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfClassAttributeInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfAttributeInvocationsIncoming()).isEqualTo(3);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfClassAttributeInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfAttributeInvocationsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfClassAttributeInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfClassAttributeInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfClassAttributeInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfAttributeInvocationsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(5);
        classMetricsContainer.calculateNumberOfClassAttributeInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfAttributeInvocationsIncoming()).isEqualTo(0);
    }

    @Test
    public void numberOfClassAttributeInvocationsOutgoing_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfClassAttributeInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfAttributeInvocationsOutgoing()).isEqualTo(2);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfClassAttributeInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfClassAttributeInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfAttributeInvocationsOutgoing()).isEqualTo(4);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfClassAttributeInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfAttributeInvocationsOutgoing()).isEqualTo(0);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfClassAttributeInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(5);
        classMetricsContainer.calculateNumberOfClassAttributeInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);

    }

    @Test
    public void numberOfClassMethodInvocationsIncoming_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfClassMethodInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfMethodInvocationsIncoming()).isEqualTo(3);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfClassMethodInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfMethodInvocationsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfClassMethodInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfClassMethodInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfClassMethodInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfMethodInvocationsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(5);
        classMetricsContainer.calculateNumberOfClassMethodInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfMethodInvocationsIncoming()).isEqualTo(0);
    }

    @Test
    public void numberOfClassMethodInvocationsOutgoing_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfClassMethodInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfMethodInvocationsOutgoing()).isEqualTo(2);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfClassMethodInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfClassMethodInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfMethodInvocationsOutgoing()).isEqualTo(4);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfClassMethodInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfMethodInvocationsOutgoing()).isEqualTo(0);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfClassMethodInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(5);
        classMetricsContainer.calculateNumberOfClassMethodInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);
    }

    @Test
    public void numberOfClassConstructorInvocationsIncoming_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfClassConstructorInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfConstructorInvocationsIncoming()).isEqualTo(3);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfClassConstructorInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfConstructorInvocationsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfClassConstructorInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfClassConstructorInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfClassConstructorInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfConstructorInvocationsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(5);
        classMetricsContainer.calculateNumberOfClassConstructorInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfConstructorInvocationsIncoming()).isEqualTo(0);
    }

    @Test
    public void numberOfClassConstructorInvocationsOutgoing_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfClassConstructorInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfConstructorInvocationsOutgoing()).isEqualTo(2);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfClassConstructorInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfClassConstructorInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfConstructorInvocationsOutgoing()).isEqualTo(4);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfClassConstructorInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfConstructorInvocationsOutgoing()).isEqualTo(0);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfClassConstructorInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(5);
        classMetricsContainer.calculateNumberOfClassConstructorInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
    }

    @Test
    public void bidirectionalNumberOfClassPackageImports_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateBidirectionalNumberOfClassPackageImports(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfPackageImports()).isEqualTo(5);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateBidirectionalNumberOfClassPackageImports(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfPackageImports()).isEqualTo(3);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateBidirectionalNumberOfClassPackageImports(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfPackageImports()).isEqualTo(5);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateBidirectionalNumberOfClassPackageImports(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfPackageImports()).isEqualTo(1);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateBidirectionalNumberOfClassPackageImports(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfPackageImports()).isEqualTo(3);

        correspondingClass = classNames.get(5);
        classMetricsContainer.calculateBidirectionalNumberOfClassPackageImports(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfPackageImports()).isEqualTo(1);
    }

    @Test
    public void bidirectionalNumberOfClassAttributeInvocations_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateBidirectionalNumberOfClassAttributeInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfAttributeInvocations()).isEqualTo(5);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateBidirectionalNumberOfClassAttributeInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfAttributeInvocations()).isEqualTo(3);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateBidirectionalNumberOfClassAttributeInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfAttributeInvocations()).isEqualTo(5);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateBidirectionalNumberOfClassAttributeInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateBidirectionalNumberOfClassAttributeInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfAttributeInvocations()).isEqualTo(3);

        correspondingClass = classNames.get(5);
        classMetricsContainer.calculateBidirectionalNumberOfClassAttributeInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
    }

    @Test
    public void bidirectionalNumberOfClassMethodInvocations_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateBidirectionalNumberOfClassMethodInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfMethodInvocations()).isEqualTo(5);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateBidirectionalNumberOfClassMethodInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfMethodInvocations()).isEqualTo(3);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateBidirectionalNumberOfClassMethodInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfMethodInvocations()).isEqualTo(5);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateBidirectionalNumberOfClassMethodInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateBidirectionalNumberOfClassMethodInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfMethodInvocations()).isEqualTo(3);

        correspondingClass = classNames.get(5);
        classMetricsContainer.calculateBidirectionalNumberOfClassMethodInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);
    }

    @Test
    public void bidirectionalNumberOfClassConstructorInvocations_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateBidirectionalNumberOfClassConstructorInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfConstructorInvocations()).isEqualTo(5);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateBidirectionalNumberOfClassConstructorInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfConstructorInvocations()).isEqualTo(3);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateBidirectionalNumberOfClassConstructorInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfConstructorInvocations()).isEqualTo(5);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateBidirectionalNumberOfClassConstructorInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateBidirectionalNumberOfClassConstructorInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfConstructorInvocations()).isEqualTo(3);

        correspondingClass = classNames.get(5);
        classMetricsContainer.calculateBidirectionalNumberOfClassConstructorInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
    }
}