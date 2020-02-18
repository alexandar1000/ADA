package com.ucl.ADA.metric_calculator.metrics_structure;

import com.ucl.ADA.parser.dependence_information.ProjectStructure;
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

class ProjectMetricsContainerTest {
    private ProjectStructure pdt;
    private ProjectMetricsContainer projectMetricsContainer;

    private ArrayList<String> classNames = new ArrayList<>(Arrays.asList("FirstClass", "SecondClass", "ThirdClass", "FourthClass", "FifthClass", "SixthClass"));

    private String firstClass = classNames.get(0);
    private String secondClass = classNames.get(1);
    private String thirdClass = classNames.get(2);
    private String fourthClass = classNames.get(3);
    private String fifthClass = classNames.get(4);
    private String sixthClass = classNames.get(5);

    @BeforeEach
    void setUp() {
        pdt = new ProjectStructure();
        projectMetricsContainer = new ProjectMetricsContainer();

        // Add the package declaration to every class
        for (String className : classNames) {
            PackageDeclaration packageDeclarationInformation = new PackageDeclaration("com.ADA.example." + className);
            pdt.addPackageDeclaration(className, packageDeclarationInformation);
        }

        // Add three attributes to each class
        for (String className : classNames) {
            for (int j = 0; j < 3; j++) {
                String attributeName = "attribute" + j + "in" + className;
                AttributeDeclaration attributeDeclarationInformation = new AttributeDeclaration(ModifierType.DEFAULT, "String", attributeName, "declaringAttributeName" + j);
                pdt.addAttributeDeclaration(className, attributeDeclarationInformation);
            }
        }


        // Add two constructors to each class
        for (String className : classNames) {
            for (int j = 0; j < 2; j++) {
                String constructorName = "constructor" + j + "in" + className;
                ConstructorDeclaration constructorDeclarationInformation = new ConstructorDeclaration(ModifierType.DEFAULT, constructorName, new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));
                pdt.addConstructorDeclaration(className, constructorDeclarationInformation);
            }
        }

        // Add two methods to each class
        for (String className : classNames) {
            for (int j = 0; j < 2; j++) {
                String methodName = "method" + j + "in" + className;
                MethodDeclaration methodDeclarationInformation = new MethodDeclaration(ModifierType.DEFAULT, "String", methodName, new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));
                pdt.addMethodDeclaration(className, methodDeclarationInformation);
            }
        }

        String className;
        PackageInvocation packageInvocationInformation = new PackageInvocation("com.ADA.invocation_example");
        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("attributeExample");
        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));
        MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

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
    void computeAllMetrics_testPresenceOfAllClasses() {
        projectMetricsContainer = new ProjectMetricsContainer();
        projectMetricsContainer.computeAllMetrics(pdt);
        assertThat(projectMetricsContainer.getClassMetrics().keySet()).containsExactlyInAnyOrderElementsOf(pdt.getClassDependenceTrees().keySet());
    }
}
