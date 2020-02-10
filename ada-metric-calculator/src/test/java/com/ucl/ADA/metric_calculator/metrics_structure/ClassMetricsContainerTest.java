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

    private String firstClass = classNames.get(0);
    private String secondClass = classNames.get(1);
    private String thirdClass = classNames.get(2);
    private String fourthClass = classNames.get(3);
    private String fifthClass = classNames.get(4);
    private String sixthClass = classNames.get(5);

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

    @Test
    public void numberOfRelationPackageImportsIncoming_testMetricCorrectness() {
        classMetricsContainer.calculateNumberOfRelationPackageImportsIncoming(firstClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationPackageImportsIncoming(secondClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationPackageImportsIncoming(thirdClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(sixthClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationPackageImportsIncoming(fourthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationPackageImportsIncoming(fifthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationPackageImportsIncoming(sixthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().containsKey(sixthClass)).isFalse();
    }

    @Test
    public void numberOfRelationPackageImportsOutgoing_testMetricCorrectness() {
        classMetricsContainer.calculateNumberOfRelationPackageImportsOutgoing(firstClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationPackageImportsOutgoing(secondClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationPackageImportsOutgoing(thirdClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(4);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationPackageImportsOutgoing(fourthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().containsKey(fourthClass)).isFalse();

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationPackageImportsOutgoing(fifthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationPackageImportsOutgoing(sixthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);
    }

    @Test
    public void numberOfRelationAttributeInvocationsIncoming_testMetricCorrectness() {
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsIncoming(firstClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsIncoming(secondClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsIncoming(thirdClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(sixthClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsIncoming(fourthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsIncoming(fifthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsIncoming(sixthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().containsKey(sixthClass)).isFalse();
    }

    @Test
    public void numberOfRelationAttributeInvocationsOutgoing_testMetricCorrectness() {
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsOutgoing(firstClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsOutgoing(secondClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsOutgoing(thirdClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(4);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsOutgoing(fourthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().containsKey(fourthClass)).isFalse();

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsOutgoing(fifthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsOutgoing(sixthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
    }

    @Test
    public void numberOfRelationMethodInvocationsIncoming_testMetricCorrectness() {
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsIncoming(firstClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsIncoming(secondClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsIncoming(thirdClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(sixthClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsIncoming(fourthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsIncoming(fifthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsIncoming(sixthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().containsKey(sixthClass)).isFalse();
    }

    @Test
    public void numberOfRelationMethodInvocationsOutgoing_testMetricCorrectness() {
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsOutgoing(firstClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsOutgoing(secondClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsOutgoing(thirdClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(4);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsOutgoing(fourthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().containsKey(fourthClass)).isFalse();

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsOutgoing(fifthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsOutgoing(sixthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);
    }

    @Test
    public void numberOfRelationConstructorInvocationsIncoming_testMetricCorrectness() {
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsIncoming(firstClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsIncoming(secondClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsIncoming(thirdClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(sixthClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsIncoming(fourthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsIncoming(fifthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsIncoming(sixthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().containsKey(sixthClass)).isFalse();
    }

    @Test
    public void numberOfRelationConstructorInvocationsOutgoing_testMetricCorrectness() {
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsOutgoing(firstClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsOutgoing(secondClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsOutgoing(thirdClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(4);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsOutgoing(fourthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().containsKey(fourthClass)).isFalse();

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsOutgoing(fifthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsOutgoing(sixthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
    }

    @Test
    public void bidirectionalNumberOfRelationPackageImports_testMetricCorrectness() {
        classMetricsContainer.calculateBidirectionalNumberOfRelationPackageImports(firstClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfPackageImports()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfPackageImports()).isEqualTo(2);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationPackageImports(secondClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfPackageImports()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationPackageImports(thirdClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(5);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(sixthClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationPackageImports(fourthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationPackageImports(fifthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfPackageImports()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationPackageImports(sixthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);
    }

    @Test
    public void bidirectionalNumberOfRelationAttributeInvocations_testMetricCorrectness() {
        classMetricsContainer.calculateBidirectionalNumberOfRelationAttributeInvocations(firstClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(2);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationAttributeInvocations(secondClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationAttributeInvocations(thirdClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(5);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(sixthClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationAttributeInvocations(fourthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationAttributeInvocations(fifthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationAttributeInvocations(sixthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
    }

    @Test
    public void bidirectionalNumberOfRelationMethodInvocations_testMetricCorrectness() {
        classMetricsContainer.calculateBidirectionalNumberOfRelationMethodInvocations(firstClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(2);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationMethodInvocations(secondClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationMethodInvocations(thirdClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(5);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(sixthClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationMethodInvocations(fourthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationMethodInvocations(fifthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationMethodInvocations(sixthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);
    }

    @Test
    public void bidirectionalNumberOfRelationConstructorInvocations_testMetricCorrectness() {
        classMetricsContainer.calculateBidirectionalNumberOfRelationConstructorInvocations(firstClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(2);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationConstructorInvocations(secondClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationConstructorInvocations(thirdClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(5);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(sixthClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationConstructorInvocations(fourthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationConstructorInvocations(fifthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationConstructorInvocations(sixthClass, pdt);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
    }
}