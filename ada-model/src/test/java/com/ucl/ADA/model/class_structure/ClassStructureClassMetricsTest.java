package com.ucl.ADA.model.class_structure;

import com.ucl.ADA.model.dependence_information.declaration_information.*;
import com.ucl.ADA.model.dependence_information.invocation_information.*;
import com.ucl.ADA.model.metrics.class_metrics.ClassMetricType;
import com.ucl.ADA.model.project_structure.ProjectStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ClassStructureClassMetricsTest {
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
    }


    /**
     * Class Metrics
     */

    @Test
    public void computeClassMetric_testCorrectnessOfTheNumberOfClassPackageImportsIncoming() {
        for (int i = 0; i < classNames.size(); i++) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(classNames.get(i));
            classStructure.computeClassMetric(ClassMetricType.NUMBER_OF_CLASS_PACKAGE_IMPORTS_INCOMING);
            assertThat(classStructure.getClassMetricValues().getNumberOfPackageImportsIncoming()).isEqualTo(classSolutionsIncoming.get(i));
        }
    }

    @Test
    public void computeClassMetric_testCorrectnessOfTheNumberOfClassPackageImportsOutgoing() {
        for (int i = 0; i < classNames.size(); i++) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(classNames.get(i));
            classStructure.computeClassMetric(ClassMetricType.NUMBER_OF_CLASS_PACKAGE_IMPORTS_OUTGOING);
            assertThat(classStructure.getClassMetricValues().getNumberOfPackageImportsOutgoing()).isEqualTo(classSolutionsOutgoing.get(i));
        }
    }

    @Test
    public void computeClassMetric_testCorrectnessOfTheNumberOfClassAttributeInvocationsIncoming() {
        for (int i = 0; i < classNames.size(); i++) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(classNames.get(i));
            classStructure.computeClassMetric(ClassMetricType.NUMBER_OF_CLASS_ATTRIBUTE_INVOCATIONS_INCOMING);
            assertThat(classStructure.getClassMetricValues().getNumberOfAttributeInvocationsIncoming()).isEqualTo(classSolutionsIncoming.get(i));
        }
    }

    @Test
    public void computeClassMetric_testCorrectnessOfTheNumberOfClassAttributeInvocationsOutgoing() {
        for (int i = 0; i < classNames.size(); i++) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(classNames.get(i));
            classStructure.computeClassMetric(ClassMetricType.NUMBER_OF_CLASS_ATTRIBUTE_INVOCATIONS_OUTGOING);
            assertThat(classStructure.getClassMetricValues().getNumberOfAttributeInvocationsOutgoing()).isEqualTo(classSolutionsOutgoing.get(i));
        }
    }

    @Test
    public void computeClassMetric_testCorrectnessOfTheNumberOfClassMethodInvocationsIncoming() {
        for (int i = 0; i < classNames.size(); i++) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(classNames.get(i));
            classStructure.computeClassMetric(ClassMetricType.NUMBER_OF_CLASS_METHOD_INVOCATIONS_INCOMING);
            assertThat(classStructure.getClassMetricValues().getNumberOfMethodInvocationsIncoming()).isEqualTo(classSolutionsIncoming.get(i));
        }
    }

    @Test
    public void computeClassMetric_testCorrectnessOfTheNumberOfClassMethodInvocationsOutgoing() {
        for (int i = 0; i < classNames.size(); i++) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(classNames.get(i));
            classStructure.computeClassMetric(ClassMetricType.NUMBER_OF_CLASS_METHOD_INVOCATIONS_OUTGOING);
            assertThat(classStructure.getClassMetricValues().getNumberOfMethodInvocationsOutgoing()).isEqualTo(classSolutionsOutgoing.get(i));
        }
    }

    @Test
    public void computeClassMetric_testCorrectnessOfTheNumberOfClassConstructorInvocationsIncoming() {
        for (int i = 0; i < classNames.size(); i++) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(classNames.get(i));
            classStructure.computeClassMetric(ClassMetricType.NUMBER_OF_CLASS_CONSTRUCTOR_INVOCATIONS_INCOMING);
            assertThat(classStructure.getClassMetricValues().getNumberOfConstructorInvocationsIncoming()).isEqualTo(classSolutionsIncoming.get(i));
        }
    }

    @Test
    public void computeClassMetric_testCorrectnessOfTheNumberOfClassConstructorInvocationsOutgoing() {
        for (int i = 0; i < classNames.size(); i++) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(classNames.get(i));
            classStructure.computeClassMetric(ClassMetricType.NUMBER_OF_CLASS_CONSTRUCTOR_INVOCATIONS_OUTGOING);
            assertThat(classStructure.getClassMetricValues().getNumberOfConstructorInvocationsOutgoing()).isEqualTo(classSolutionsOutgoing.get(i));
        }
    }

    @Test
    public void computeClassMetric_testCorrectnessOfTheBidirectionalNumberOfClassPackageImports() {
        for (int i = 0; i < classNames.size(); i++) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(classNames.get(i));
            classStructure.computeClassMetric(ClassMetricType.BIDIRECTIONAL_NUMBER_OF_CLASS_PACKAGE_IMPORTS);
            assertThat(classStructure.getClassMetricValues().getBidirectionalNumberOfPackageImports()).isEqualTo(classSolutionsBidirectional.get(i));
        }
    }

    @Test
    public void computeClassMetric_testCorrectnessOfTheBidirectionalNumberOfClassAttributeInvocations() {
        for (int i = 0; i < classNames.size(); i++) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(classNames.get(i));
            classStructure.computeClassMetric(ClassMetricType.BIDIRECTIONAL_NUMBER_OF_CLASS_ATTRIBUTE_INVOCATIONS);
            assertThat(classStructure.getClassMetricValues().getBidirectionalNumberOfAttributeInvocations()).isEqualTo(classSolutionsBidirectional.get(i));
        }
    }

    @Test
    public void computeClassMetric_testCorrectnessOfTheBidirectionalNumberOfClassMethodInvocations() {
        for (int i = 0; i < classNames.size(); i++) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(classNames.get(i));
            classStructure.computeClassMetric(ClassMetricType.BIDIRECTIONAL_NUMBER_OF_CLASS_METHOD_INVOCATIONS);
            assertThat(classStructure.getClassMetricValues().getBidirectionalNumberOfMethodInvocations()).isEqualTo(classSolutionsBidirectional.get(i));
        }
    }

    @Test
    public void computeClassMetric_testCorrectnessOfTheBidirectionalNumberOfClassConstructorInvocations() {
        for (int i = 0; i < classNames.size(); i++) {
            ClassStructure classStructure = projectStructure.getClassStructures().get(classNames.get(i));
            classStructure.computeClassMetric(ClassMetricType.BIDIRECTIONAL_NUMBER_OF_CLASS_CONSTRUCTOR_INVOCATIONS);
            assertThat(classStructure.getClassMetricValues().getBidirectionalNumberOfConstructorInvocations()).isEqualTo(classSolutionsBidirectional.get(i));
        }
    }

}
