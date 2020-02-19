package com.ucl.ADA.model.class_structure;

import com.ucl.ADA.model.dependence_information.declaration_information.*;
import com.ucl.ADA.model.dependence_information.invocation_information.*;
import com.ucl.ADA.model.metrics.relation_metrics.RelationMetricType;
import com.ucl.ADA.model.project_structure.ProjectStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ClassStructureRelationMetricsTest {

    private ProjectStructure projectStructure;

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

    ClassStructure classStructure1;
    ClassStructure classStructure2;
    ClassStructure classStructure3;
    ClassStructure classStructure4;
    ClassStructure classStructure5;
    ClassStructure classStructure6;

    @BeforeEach
    void setUp() {
        projectStructure = new ProjectStructure();

        // Add the package declaration to every class
        for (String className : classNames) {
            PackageDeclaration packageDeclarationInformation = new PackageDeclaration("com.ADA.example." + className);
            projectStructure.addPackageDeclaration(className, packageDeclarationInformation);
        }

        // Add three attributes to each class
        for (String className : classNames) {
            for (int j = 0; j < 3; j++) {
                String attributeName = "attribute" + j + "in" + className;
                AttributeDeclaration attributeDeclarationInformation = new AttributeDeclaration(modifiers, "String", attributeName, "declaringAttributeName" + j);
                projectStructure.addAttributeDeclaration(className, attributeDeclarationInformation);
            }
        }


        // Add two constructors to each class
        for (String className : classNames) {
            for (int j = 0; j < 2; j++) {
                String constructorName = "constructor" + j + "in" + className;
                ConstructorDeclaration constructorDeclarationInformation = new ConstructorDeclaration(modifiers, constructorName, declaredParameters);
                projectStructure.addConstructorDeclaration(className, constructorDeclarationInformation);
            }
        }

        // Add two methods to each class
        for (String className : classNames) {
            for (int j = 0; j < 2; j++) {
                String methodName = "method" + j + "in" + className;
                MethodDeclaration methodDeclarationInformation = new MethodDeclaration(modifiers, "String", methodName, declaredParameters);
                projectStructure.addMethodDeclaration(className, methodDeclarationInformation);
            }
        }

        String className;
        PackageInvocation packageInvocationInformation = new PackageInvocation("com.ADA.invocation_example");
        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("attributeExample");
        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", passedParameterList0);
        MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", passedParameterList1);

        // For FirstClass
        className = classNames.get(0);
        projectStructure.addPackageInvocation(className, classNames.get(1), packageInvocationInformation);
        projectStructure.addPackageInvocation(className, classNames.get(4), packageInvocationInformation);

        projectStructure.addAttributeInvocation(className, classNames.get(1), attributeInvocationInformation);
        projectStructure.addAttributeInvocation(className, classNames.get(4), attributeInvocationInformation);

        projectStructure.addConstructorInvocation(className, classNames.get(1), constructorInvocationInformation);
        projectStructure.addConstructorInvocation(className, classNames.get(4), constructorInvocationInformation);

        projectStructure.addMethodInvocation(className, classNames.get(1), methodInvocationInformation);
        projectStructure.addMethodInvocation(className, classNames.get(4), methodInvocationInformation);


        // For SecondClass
        className = classNames.get(1);
        projectStructure.addPackageInvocation(className, classNames.get(0), packageInvocationInformation);

        projectStructure.addAttributeInvocation(className, classNames.get(0), attributeInvocationInformation);

        projectStructure.addConstructorInvocation(className, classNames.get(0), constructorInvocationInformation);

        projectStructure.addMethodInvocation(className, classNames.get(0), methodInvocationInformation);

        // For ThirdClass
        className = classNames.get(2);
        projectStructure.addPackageInvocation(className, classNames.get(0), packageInvocationInformation);
        projectStructure.addPackageInvocation(className, classNames.get(1), packageInvocationInformation);
        projectStructure.addPackageInvocation(className, classNames.get(3), packageInvocationInformation);
        projectStructure.addPackageInvocation(className, classNames.get(4), packageInvocationInformation);

        projectStructure.addAttributeInvocation(className, classNames.get(0), attributeInvocationInformation);
        projectStructure.addAttributeInvocation(className, classNames.get(1), attributeInvocationInformation);
        projectStructure.addAttributeInvocation(className, classNames.get(3), attributeInvocationInformation);
        projectStructure.addAttributeInvocation(className, classNames.get(4), attributeInvocationInformation);

        projectStructure.addConstructorInvocation(className, classNames.get(0), constructorInvocationInformation);
        projectStructure.addConstructorInvocation(className, classNames.get(1), constructorInvocationInformation);
        projectStructure.addConstructorInvocation(className, classNames.get(3), constructorInvocationInformation);
        projectStructure.addConstructorInvocation(className, classNames.get(4), constructorInvocationInformation);

        projectStructure.addMethodInvocation(className, classNames.get(0), methodInvocationInformation);
        projectStructure.addMethodInvocation(className, classNames.get(1), methodInvocationInformation);
        projectStructure.addMethodInvocation(className, classNames.get(3), methodInvocationInformation);
        projectStructure.addMethodInvocation(className, classNames.get(4), methodInvocationInformation);

        // For FourthClass

        // For FifthClass
        className = classNames.get(4);
        projectStructure.addPackageInvocation(className, classNames.get(0), packageInvocationInformation);

        projectStructure.addAttributeInvocation(className, classNames.get(0), attributeInvocationInformation);

        projectStructure.addConstructorInvocation(className, classNames.get(0), constructorInvocationInformation);

        projectStructure.addMethodInvocation(className, classNames.get(0), methodInvocationInformation);

        // For SixthClass
        className = classNames.get(5);
        projectStructure.addPackageInvocation(className, classNames.get(2), packageInvocationInformation);

        projectStructure.addAttributeInvocation(className, classNames.get(2), attributeInvocationInformation);

        projectStructure.addConstructorInvocation(className, classNames.get(2), constructorInvocationInformation);

        projectStructure.addMethodInvocation(className, classNames.get(2), methodInvocationInformation);

        classStructure1 = projectStructure.getClassStructures().get(firstClass);
        classStructure2 = projectStructure.getClassStructures().get(secondClass);
        classStructure3 = projectStructure.getClassStructures().get(thirdClass);
        classStructure4 = projectStructure.getClassStructures().get(fourthClass);
        classStructure5 = projectStructure.getClassStructures().get(fifthClass);
        classStructure6 = projectStructure.getClassStructures().get(sixthClass);
    }

    @Test
    public void numberOfRelationPackageImportsIncoming_testMetricCorrectness() {
        RelationMetricType relationMetricType = RelationMetricType.NUMBER_OF_RELATION_PACKAGE_IMPORTS_INCOMING;

        classStructure1.computeRelationMetric(relationMetricType);
        assertThat(classStructure1.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classStructure1.getRelationMetricValues().get(secondClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(thirdClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(fifthClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);

        classStructure2.computeRelationMetric(relationMetricType);
        assertThat(classStructure2.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classStructure2.getRelationMetricValues().get(firstClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);
        assertThat(classStructure2.getRelationMetricValues().get(thirdClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);

        classStructure3.computeRelationMetric(relationMetricType);
        assertThat(classStructure3.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classStructure3.getRelationMetricValues().get(sixthClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);

        classStructure4.computeRelationMetric(relationMetricType);
        assertThat(classStructure4.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classStructure4.getRelationMetricValues().get(thirdClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);

        classStructure5.computeRelationMetric(relationMetricType);
        assertThat(classStructure5.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classStructure5.getRelationMetricValues().get(firstClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);
        assertThat(classStructure5.getRelationMetricValues().get(thirdClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);

        classStructure6.computeRelationMetric(relationMetricType);
        assertThat(classStructure6.getRelationMetricValues().containsKey(sixthClass)).isFalse();
    }

   /* @Test
    public void numberOfRelationPackageImportsOutgoing_testMetricCorrectness() {
        classMetricsContainer.calculateNumberOfRelationPackageImportsOutgoing(firstClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationPackageImportsOutgoing(secondClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationPackageImportsOutgoing(thirdClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(4);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationPackageImportsOutgoing(fourthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().containsKey(fourthClass)).isFalse();

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationPackageImportsOutgoing(fifthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationPackageImportsOutgoing(sixthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);
    }

    @Test
    public void numberOfRelationAttributeInvocationsIncoming_testMetricCorrectness() {
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsIncoming(firstClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsIncoming(secondClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsIncoming(thirdClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(sixthClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsIncoming(fourthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsIncoming(fifthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsIncoming(sixthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().containsKey(sixthClass)).isFalse();
    }

    @Test
    public void numberOfRelationAttributeInvocationsOutgoing_testMetricCorrectness() {
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsOutgoing(firstClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsOutgoing(secondClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsOutgoing(thirdClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(4);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsOutgoing(fourthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().containsKey(fourthClass)).isFalse();

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsOutgoing(fifthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationAttributeInvocationsOutgoing(sixthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
    }

    @Test
    public void numberOfRelationMethodInvocationsIncoming_testMetricCorrectness() {
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsIncoming(firstClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsIncoming(secondClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsIncoming(thirdClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(sixthClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsIncoming(fourthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsIncoming(fifthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsIncoming(sixthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().containsKey(sixthClass)).isFalse();
    }

    @Test
    public void numberOfRelationMethodInvocationsOutgoing_testMetricCorrectness() {
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsOutgoing(firstClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsOutgoing(secondClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsOutgoing(thirdClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(4);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsOutgoing(fourthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().containsKey(fourthClass)).isFalse();

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsOutgoing(fifthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationMethodInvocationsOutgoing(sixthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);
    }

    @Test
    public void numberOfRelationConstructorInvocationsIncoming_testMetricCorrectness() {
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsIncoming(firstClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsIncoming(secondClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsIncoming(thirdClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(sixthClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsIncoming(fourthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsIncoming(fifthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsIncoming(sixthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().containsKey(sixthClass)).isFalse();
    }

    @Test
    public void numberOfRelationConstructorInvocationsOutgoing_testMetricCorrectness() {
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsOutgoing(firstClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsOutgoing(secondClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsOutgoing(thirdClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(4);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsOutgoing(fourthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().containsKey(fourthClass)).isFalse();

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsOutgoing(fifthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateNumberOfRelationConstructorInvocationsOutgoing(sixthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
    }

    @Test
    public void bidirectionalNumberOfRelationPackageImports_testMetricCorrectness() {
        classMetricsContainer.calculateBidirectionalNumberOfRelationPackageImports(firstClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfPackageImports()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfPackageImports()).isEqualTo(2);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationPackageImports(secondClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfPackageImports()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationPackageImports(thirdClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(5);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(sixthClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationPackageImports(fourthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationPackageImports(fifthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfPackageImports()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationPackageImports(sixthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);
    }

    @Test
    public void bidirectionalNumberOfRelationAttributeInvocations_testMetricCorrectness() {
        classMetricsContainer.calculateBidirectionalNumberOfRelationAttributeInvocations(firstClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(2);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationAttributeInvocations(secondClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationAttributeInvocations(thirdClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(5);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(sixthClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationAttributeInvocations(fourthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationAttributeInvocations(fifthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationAttributeInvocations(sixthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
    }

    @Test
    public void bidirectionalNumberOfRelationMethodInvocations_testMetricCorrectness() {
        classMetricsContainer.calculateBidirectionalNumberOfRelationMethodInvocations(firstClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(2);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationMethodInvocations(secondClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationMethodInvocations(thirdClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(5);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(sixthClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationMethodInvocations(fourthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationMethodInvocations(fifthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationMethodInvocations(sixthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);
    }

    @Test
    public void bidirectionalNumberOfRelationConstructorInvocations_testMetricCorrectness() {
        classMetricsContainer.calculateBidirectionalNumberOfRelationConstructorInvocations(firstClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(3);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(2);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationConstructorInvocations(secondClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationConstructorInvocations(thirdClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(5);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fourthClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(sixthClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationConstructorInvocations(fourthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationConstructorInvocations(fifthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(2);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);

        classMetricsContainer = new ClassMetricsContainer();
        classMetricsContainer.calculateBidirectionalNumberOfRelationConstructorInvocations(sixthClass, projectStructure);
        assertThat(classMetricsContainer.getRelationMetricValues().size()).isEqualTo(1);
        assertThat(classMetricsContainer.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
    }*/
}
