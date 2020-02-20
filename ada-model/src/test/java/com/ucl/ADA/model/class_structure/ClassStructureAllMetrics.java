package com.ucl.ADA.model.class_structure;

import com.ucl.ADA.model.dependence_information.declaration_information.*;
import com.ucl.ADA.model.dependence_information.invocation_information.*;
import com.ucl.ADA.model.project_structure.ProjectStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ClassStructureAllMetrics {
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
    public void computeAllClassMetrics_testMetricCorrectness() {
        classStructure1.computeAllClassMetrics();
        assertThat(classStructure1.getClassMetricValues().getBidirectionalNumberOfPackageImports()).isEqualTo(5);
        assertThat(classStructure1.getClassMetricValues().getBidirectionalNumberOfAttributeInvocations()).isEqualTo(5);
        assertThat(classStructure1.getClassMetricValues().getBidirectionalNumberOfConstructorInvocations()).isEqualTo(5);
        assertThat(classStructure1.getClassMetricValues().getBidirectionalNumberOfMethodInvocations()).isEqualTo(5);

        assertThat(classStructure1.getClassMetricValues().getNumberOfPackageImportsIncoming()).isEqualTo(3);
        assertThat(classStructure1.getClassMetricValues().getNumberOfAttributeInvocationsIncoming()).isEqualTo(3);
        assertThat(classStructure1.getClassMetricValues().getNumberOfConstructorInvocationsIncoming()).isEqualTo(3);
        assertThat(classStructure1.getClassMetricValues().getNumberOfMethodInvocationsIncoming()).isEqualTo(3);

        assertThat(classStructure1.getClassMetricValues().getNumberOfPackageImportsOutgoing()).isEqualTo(2);
        assertThat(classStructure1.getClassMetricValues().getNumberOfAttributeInvocationsOutgoing()).isEqualTo(2);
        assertThat(classStructure1.getClassMetricValues().getNumberOfConstructorInvocationsOutgoing()).isEqualTo(2);
        assertThat(classStructure1.getClassMetricValues().getNumberOfMethodInvocationsOutgoing()).isEqualTo(2);

        classStructure2.computeAllClassMetrics();
        assertThat(classStructure2.getClassMetricValues().getBidirectionalNumberOfPackageImports()).isEqualTo(3);
        assertThat(classStructure2.getClassMetricValues().getBidirectionalNumberOfAttributeInvocations()).isEqualTo(3);
        assertThat(classStructure2.getClassMetricValues().getBidirectionalNumberOfConstructorInvocations()).isEqualTo(3);
        assertThat(classStructure2.getClassMetricValues().getBidirectionalNumberOfMethodInvocations()).isEqualTo(3);

        assertThat(classStructure2.getClassMetricValues().getNumberOfPackageImportsIncoming()).isEqualTo(2);
        assertThat(classStructure2.getClassMetricValues().getNumberOfAttributeInvocationsIncoming()).isEqualTo(2);
        assertThat(classStructure2.getClassMetricValues().getNumberOfConstructorInvocationsIncoming()).isEqualTo(2);
        assertThat(classStructure2.getClassMetricValues().getNumberOfMethodInvocationsIncoming()).isEqualTo(2);

        assertThat(classStructure2.getClassMetricValues().getNumberOfPackageImportsOutgoing()).isEqualTo(1);
        assertThat(classStructure2.getClassMetricValues().getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
        assertThat(classStructure2.getClassMetricValues().getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
        assertThat(classStructure2.getClassMetricValues().getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        classStructure3.computeAllClassMetrics();
        assertThat(classStructure3.getClassMetricValues().getBidirectionalNumberOfPackageImports()).isEqualTo(5);
        assertThat(classStructure3.getClassMetricValues().getBidirectionalNumberOfAttributeInvocations()).isEqualTo(5);
        assertThat(classStructure3.getClassMetricValues().getBidirectionalNumberOfConstructorInvocations()).isEqualTo(5);
        assertThat(classStructure3.getClassMetricValues().getBidirectionalNumberOfMethodInvocations()).isEqualTo(5);

        assertThat(classStructure3.getClassMetricValues().getNumberOfPackageImportsIncoming()).isEqualTo(1);
        assertThat(classStructure3.getClassMetricValues().getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);
        assertThat(classStructure3.getClassMetricValues().getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);
        assertThat(classStructure3.getClassMetricValues().getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        assertThat(classStructure3.getClassMetricValues().getNumberOfPackageImportsOutgoing()).isEqualTo(4);
        assertThat(classStructure3.getClassMetricValues().getNumberOfAttributeInvocationsOutgoing()).isEqualTo(4);
        assertThat(classStructure3.getClassMetricValues().getNumberOfConstructorInvocationsOutgoing()).isEqualTo(4);
        assertThat(classStructure3.getClassMetricValues().getNumberOfMethodInvocationsOutgoing()).isEqualTo(4);

        classStructure4.computeAllClassMetrics();
        assertThat(classStructure4.getClassMetricValues().getBidirectionalNumberOfPackageImports()).isEqualTo(1);
        assertThat(classStructure4.getClassMetricValues().getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
        assertThat(classStructure4.getClassMetricValues().getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
        assertThat(classStructure4.getClassMetricValues().getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);

        assertThat(classStructure4.getClassMetricValues().getNumberOfPackageImportsIncoming()).isEqualTo(1);
        assertThat(classStructure4.getClassMetricValues().getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);
        assertThat(classStructure4.getClassMetricValues().getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);
        assertThat(classStructure4.getClassMetricValues().getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        assertThat(classStructure4.getClassMetricValues().getNumberOfPackageImportsOutgoing()).isEqualTo(0);
        assertThat(classStructure4.getClassMetricValues().getNumberOfAttributeInvocationsOutgoing()).isEqualTo(0);
        assertThat(classStructure4.getClassMetricValues().getNumberOfConstructorInvocationsOutgoing()).isEqualTo(0);
        assertThat(classStructure4.getClassMetricValues().getNumberOfMethodInvocationsOutgoing()).isEqualTo(0);

        classStructure5.computeAllClassMetrics();
        assertThat(classStructure5.getClassMetricValues().getBidirectionalNumberOfPackageImports()).isEqualTo(3);
        assertThat(classStructure5.getClassMetricValues().getBidirectionalNumberOfAttributeInvocations()).isEqualTo(3);
        assertThat(classStructure5.getClassMetricValues().getBidirectionalNumberOfConstructorInvocations()).isEqualTo(3);
        assertThat(classStructure5.getClassMetricValues().getBidirectionalNumberOfMethodInvocations()).isEqualTo(3);

        assertThat(classStructure5.getClassMetricValues().getNumberOfPackageImportsIncoming()).isEqualTo(2);
        assertThat(classStructure5.getClassMetricValues().getNumberOfAttributeInvocationsIncoming()).isEqualTo(2);
        assertThat(classStructure5.getClassMetricValues().getNumberOfConstructorInvocationsIncoming()).isEqualTo(2);
        assertThat(classStructure5.getClassMetricValues().getNumberOfMethodInvocationsIncoming()).isEqualTo(2);

        assertThat(classStructure5.getClassMetricValues().getNumberOfPackageImportsOutgoing()).isEqualTo(1);
        assertThat(classStructure5.getClassMetricValues().getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
        assertThat(classStructure5.getClassMetricValues().getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
        assertThat(classStructure5.getClassMetricValues().getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        classStructure6.computeAllClassMetrics();
        assertThat(classStructure6.getClassMetricValues().getBidirectionalNumberOfPackageImports()).isEqualTo(1);
        assertThat(classStructure6.getClassMetricValues().getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
        assertThat(classStructure6.getClassMetricValues().getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
        assertThat(classStructure6.getClassMetricValues().getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);

        assertThat(classStructure6.getClassMetricValues().getNumberOfPackageImportsIncoming()).isEqualTo(0);
        assertThat(classStructure6.getClassMetricValues().getNumberOfAttributeInvocationsIncoming()).isEqualTo(0);
        assertThat(classStructure6.getClassMetricValues().getNumberOfConstructorInvocationsIncoming()).isEqualTo(0);
        assertThat(classStructure6.getClassMetricValues().getNumberOfMethodInvocationsIncoming()).isEqualTo(0);

        assertThat(classStructure6.getClassMetricValues().getNumberOfPackageImportsOutgoing()).isEqualTo(1);
        assertThat(classStructure6.getClassMetricValues().getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
        assertThat(classStructure6.getClassMetricValues().getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
        assertThat(classStructure6.getClassMetricValues().getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);
    }


    @Test
    public void computeAllRelationMetrics_testMetricCorrectness() {
        classStructure1.computeAllRelationMetrics();
        assertThat(classStructure1.getRelationMetricValues().keySet().size()).isEqualTo(3);

        assertThat(classStructure1.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfPackageImports()).isEqualTo(2);
        assertThat(classStructure1.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(2);
        assertThat(classStructure1.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(2);
        assertThat(classStructure1.getRelationMetricValues().get(secondClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(2);

        assertThat(classStructure1.getRelationMetricValues().get(secondClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(secondClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(secondClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(secondClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        assertThat(classStructure1.getRelationMetricValues().get(secondClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(secondClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(secondClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(secondClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        assertThat(classStructure1.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);

        assertThat(classStructure1.getRelationMetricValues().get(thirdClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(thirdClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(thirdClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(thirdClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        assertThat(classStructure1.getRelationMetricValues().get(thirdClass).getNumberOfPackageImportsOutgoing()).isEqualTo(0);
        assertThat(classStructure1.getRelationMetricValues().get(thirdClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(0);
        assertThat(classStructure1.getRelationMetricValues().get(thirdClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(0);
        assertThat(classStructure1.getRelationMetricValues().get(thirdClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(0);

        assertThat(classStructure1.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfPackageImports()).isEqualTo(2);
        assertThat(classStructure1.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(2);
        assertThat(classStructure1.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(2);
        assertThat(classStructure1.getRelationMetricValues().get(fifthClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(2);

        assertThat(classStructure1.getRelationMetricValues().get(fifthClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(fifthClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(fifthClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(fifthClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        assertThat(classStructure1.getRelationMetricValues().get(fifthClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(fifthClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(fifthClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
        assertThat(classStructure1.getRelationMetricValues().get(fifthClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);


        classStructure2.computeAllRelationMetrics();
        assertThat(classStructure2.getRelationMetricValues().keySet().size()).isEqualTo(2);

        assertThat(classStructure2.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfPackageImports()).isEqualTo(2);
        assertThat(classStructure2.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(2);
        assertThat(classStructure2.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(2);
        assertThat(classStructure2.getRelationMetricValues().get(firstClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(2);

        assertThat(classStructure2.getRelationMetricValues().get(firstClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);
        assertThat(classStructure2.getRelationMetricValues().get(firstClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);
        assertThat(classStructure2.getRelationMetricValues().get(firstClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);
        assertThat(classStructure2.getRelationMetricValues().get(firstClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        assertThat(classStructure2.getRelationMetricValues().get(firstClass).getNumberOfPackageImportsOutgoing()).isEqualTo(1);
        assertThat(classStructure2.getRelationMetricValues().get(firstClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(1);
        assertThat(classStructure2.getRelationMetricValues().get(firstClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(1);
        assertThat(classStructure2.getRelationMetricValues().get(firstClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(1);

        assertThat(classStructure2.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfPackageImports()).isEqualTo(1);
        assertThat(classStructure2.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfAttributeInvocations()).isEqualTo(1);
        assertThat(classStructure2.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfConstructorInvocations()).isEqualTo(1);
        assertThat(classStructure2.getRelationMetricValues().get(thirdClass).getBidirectionalNumberOfMethodInvocations()).isEqualTo(1);

        assertThat(classStructure2.getRelationMetricValues().get(thirdClass).getNumberOfPackageImportsIncoming()).isEqualTo(1);
        assertThat(classStructure2.getRelationMetricValues().get(thirdClass).getNumberOfAttributeInvocationsIncoming()).isEqualTo(1);
        assertThat(classStructure2.getRelationMetricValues().get(thirdClass).getNumberOfConstructorInvocationsIncoming()).isEqualTo(1);
        assertThat(classStructure2.getRelationMetricValues().get(thirdClass).getNumberOfMethodInvocationsIncoming()).isEqualTo(1);

        assertThat(classStructure2.getRelationMetricValues().get(thirdClass).getNumberOfPackageImportsOutgoing()).isEqualTo(0);
        assertThat(classStructure2.getRelationMetricValues().get(thirdClass).getNumberOfAttributeInvocationsOutgoing()).isEqualTo(0);
        assertThat(classStructure2.getRelationMetricValues().get(thirdClass).getNumberOfConstructorInvocationsOutgoing()).isEqualTo(0);
        assertThat(classStructure2.getRelationMetricValues().get(thirdClass).getNumberOfMethodInvocationsOutgoing()).isEqualTo(0);
    }

}
