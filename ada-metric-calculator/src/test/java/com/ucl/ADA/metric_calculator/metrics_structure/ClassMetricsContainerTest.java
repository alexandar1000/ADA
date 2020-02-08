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
        className = classNames.get(3);

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
    public void numberOfPackageImportsIncoming_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfPackageImportsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfPackageImportsIncoming()).isEqualTo(0);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfPackageImportsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfPackageImportsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfPackageImportsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfPackageImportsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfPackageImportsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfPackageImportsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfPackageImportsIncoming()).isEqualTo(2);
    }

    @Test
    public void numberOfPackageImportsOutgoing_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfPackageImportsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfPackageImportsOutgoing()).isEqualTo(4);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfPackageImportsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfPackageImportsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfPackageImportsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfPackageImportsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfPackageImportsOutgoing()).isEqualTo(0);
    }

    @Test
    public void numberOfAttributeInvocationsIncoming_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfAttributeInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(0);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfAttributeInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfAttributeInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfAttributeInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfAttributeInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(2);
    }

    @Test
    public void numberOfAttributeInvocationsOutgoing_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfAttributeInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(4);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfAttributeInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfAttributeInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfAttributeInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfAttributeInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(0);

    }

    @Test
    public void numberOfMethodInvocationsIncoming_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfMethodInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(0);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfMethodInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfMethodInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfMethodInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfMethodInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(2);
    }

    @Test
    public void numberOfMethodInvocationsOutgoing_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfMethodInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(4);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfMethodInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfMethodInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfMethodInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfMethodInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(0);
    }

    @Test
    public void numberOfConstructorInvocationsIncoming_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfConstructorInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(0);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfConstructorInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfConstructorInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfConstructorInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(2);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfConstructorInvocationsIncoming(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(2);
    }

    @Test
    public void numberOfConstructorInvocationsOutgoing_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateNumberOfConstructorInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(4);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateNumberOfConstructorInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateNumberOfConstructorInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateNumberOfConstructorInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateNumberOfConstructorInvocationsOutgoing(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(0);
    }

    @Test
    public void bidirectionalNumberOfPackageImports_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateBidirectionalNumberOfPackageImports(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfPackageImports()).isEqualTo(4);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateBidirectionalNumberOfPackageImports(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfPackageImports()).isEqualTo(2);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateBidirectionalNumberOfPackageImports(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfPackageImports()).isEqualTo(3);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateBidirectionalNumberOfPackageImports(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfPackageImports()).isEqualTo(3);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateBidirectionalNumberOfPackageImports(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfPackageImports()).isEqualTo(2);
    }

    @Test
    public void bidirectionalNumberOfAttributeInvocations_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateBidirectionalNumberOfAttributeInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(4);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateBidirectionalNumberOfAttributeInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(2);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateBidirectionalNumberOfAttributeInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(3);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateBidirectionalNumberOfAttributeInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(3);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateBidirectionalNumberOfAttributeInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(2);
    }

    @Test
    public void bidirectionalNumberOfMethodInvocations_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateBidirectionalNumberOfMethodInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(4);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateBidirectionalNumberOfMethodInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(2);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateBidirectionalNumberOfMethodInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(3);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateBidirectionalNumberOfMethodInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(3);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateBidirectionalNumberOfMethodInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(2);
    }

    @Test
    public void bidirectionalNumberOfConstructorInvocations_testMetricCorrectness() {
        String correspondingClass = classNames.get(0);
        classMetricsContainer.calculateBidirectionalNumberOfConstructorInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(4);

        correspondingClass = classNames.get(1);
        classMetricsContainer.calculateBidirectionalNumberOfConstructorInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(2);

        correspondingClass = classNames.get(2);
        classMetricsContainer.calculateBidirectionalNumberOfConstructorInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(3);

        correspondingClass = classNames.get(3);
        classMetricsContainer.calculateBidirectionalNumberOfConstructorInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(3);

        correspondingClass = classNames.get(4);
        classMetricsContainer.calculateBidirectionalNumberOfConstructorInvocations(correspondingClass, pdt);
        assertThat(classMetricsContainer.getMetricValues().get(correspondingClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(2);
    }
}