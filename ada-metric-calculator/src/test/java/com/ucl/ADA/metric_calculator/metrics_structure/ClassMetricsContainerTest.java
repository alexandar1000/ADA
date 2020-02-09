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

    private ArrayList<Float> classSolutionsOutgoing = new ArrayList<>(Arrays.asList(2.0F, 1.0F, 4.0F, 0.0F, 1.0F, 1.0F));
    private ArrayList<Float> classSolutionsIncoming = new ArrayList<>(Arrays.asList(3.0F, 2.0F, 1.0F, 1.0F, 2.0F, 0.0F));
    private ArrayList<Float> classSolutionsBidirectional = new ArrayList<>(Arrays.asList(5.0F, 3.0F, 5.0F, 1.0F, 3.0F, 1.0F));

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


    /**
     * Class Metrics
     */

    @Test
    public void numberOfClassPackageImportsIncoming_testMetricCorrectness() {
        for (int i = 0; i < classNames.size(); i++) {
            classMetricsContainer.calculateNumberOfClassPackageImportsIncoming(classNames.get(i), pdt);
            assertThat(classMetricsContainer.getClassMetricValues().getNumberOfPackageImportsIncoming()).isEqualTo(classSolutionsIncoming.get(i));
        }
    }

    @Test
    public void numberOfClassPackageImportsOutgoing_testMetricCorrectness() {
        for (int i = 0; i < classNames.size(); i++) {
            classMetricsContainer.calculateNumberOfClassPackageImportsOutgoing(classNames.get(i), pdt);
            assertThat(classMetricsContainer.getClassMetricValues().getNumberOfPackageImportsOutgoing()).isEqualTo(classSolutionsOutgoing.get(i));
        }
    }

    @Test
    public void numberOfClassAttributeInvocationsIncoming_testMetricCorrectness() {
        for (int i = 0; i < classNames.size(); i++) {
            classMetricsContainer.calculateNumberOfClassAttributeInvocationsIncoming(classNames.get(i), pdt);
            assertThat(classMetricsContainer.getClassMetricValues().getNumberOfAttributeInvocationsIncoming()).isEqualTo(classSolutionsIncoming.get(i));
        }
    }

    @Test
    public void numberOfClassAttributeInvocationsOutgoing_testMetricCorrectness() {
        for (int i = 0; i < classNames.size(); i++) {
            classMetricsContainer.calculateNumberOfClassAttributeInvocationsOutgoing(classNames.get(i), pdt);
            assertThat(classMetricsContainer.getClassMetricValues().getNumberOfAttributeInvocationsOutgoing()).isEqualTo(classSolutionsOutgoing.get(i));
        }
    }

    @Test
    public void numberOfClassMethodInvocationsIncoming_testMetricCorrectness() {
        for (int i = 0; i < classNames.size(); i++) {
            classMetricsContainer.calculateNumberOfClassMethodInvocationsIncoming(classNames.get(i), pdt);
            assertThat(classMetricsContainer.getClassMetricValues().getNumberOfMethodInvocationsIncoming()).isEqualTo(classSolutionsIncoming.get(i));
        }
    }

    @Test
    public void numberOfClassMethodInvocationsOutgoing_testMetricCorrectness() {
        for (int i = 0; i < classNames.size(); i++) {
            classMetricsContainer.calculateNumberOfClassMethodInvocationsOutgoing(classNames.get(i), pdt);
            assertThat(classMetricsContainer.getClassMetricValues().getNumberOfMethodInvocationsOutgoing()).isEqualTo(classSolutionsOutgoing.get(i));
        }
    }

    @Test
    public void numberOfClassConstructorInvocationsIncoming_testMetricCorrectness() {
        for (int i = 0; i < classNames.size(); i++) {
            classMetricsContainer.calculateNumberOfClassConstructorInvocationsIncoming(classNames.get(i), pdt);
            assertThat(classMetricsContainer.getClassMetricValues().getNumberOfConstructorInvocationsIncoming()).isEqualTo(classSolutionsIncoming.get(i));
        }
    }

    @Test
    public void numberOfClassConstructorInvocationsOutgoing_testMetricCorrectness() {
        for (int i = 0; i < classNames.size(); i++) {
            classMetricsContainer.calculateNumberOfClassConstructorInvocationsOutgoing(classNames.get(i), pdt);
            assertThat(classMetricsContainer.getClassMetricValues().getNumberOfConstructorInvocationsOutgoing()).isEqualTo(classSolutionsOutgoing.get(i));
        }
    }

    @Test
    public void bidirectionalNumberOfClassPackageImports_testMetricCorrectness() {
        for (int i = 0; i < classNames.size(); i++) {
            classMetricsContainer.calculateBidirectionalNumberOfClassPackageImports(classNames.get(i), pdt);
            assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfPackageImports()).isEqualTo(classSolutionsBidirectional.get(i));
        }
    }

    @Test
    public void bidirectionalNumberOfClassAttributeInvocations_testMetricCorrectness() {
        for (int i = 0; i < classNames.size(); i++) {
            classMetricsContainer.calculateBidirectionalNumberOfClassAttributeInvocations(classNames.get(i), pdt);
            assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfAttributeInvocations()).isEqualTo(classSolutionsBidirectional.get(i));
        }
    }

    @Test
    public void bidirectionalNumberOfClassMethodInvocations_testMetricCorrectness() {
        for (int i = 0; i < classNames.size(); i++) {
            classMetricsContainer.calculateBidirectionalNumberOfClassMethodInvocations(classNames.get(i), pdt);
            assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfMethodInvocations()).isEqualTo(classSolutionsBidirectional.get(i));
        }
    }

    @Test
    public void bidirectionalNumberOfClassConstructorInvocations_testMetricCorrectness() {
        for (int i = 0; i < classNames.size(); i++) {
            classMetricsContainer.calculateBidirectionalNumberOfClassConstructorInvocations(classNames.get(i), pdt);
            assertThat(classMetricsContainer.getClassMetricValues().getBidirectionalNumberOfConstructorInvocations()).isEqualTo(classSolutionsBidirectional.get(i));
        }
    }


    /**
     * Relation Metrics
     */



}