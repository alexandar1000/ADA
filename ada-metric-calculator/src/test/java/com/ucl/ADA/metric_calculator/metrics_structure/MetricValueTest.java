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

class MetricValueTest {
    private ProjectDependenceTree pdt;
    private ArrayList<String> classNames = new ArrayList<>(Arrays.asList("FirstClass", "SecondClass", "ThirdClass", "FourthClass", "FifthClass"));

    @BeforeEach
    void setUp() {
        pdt = new ProjectDependenceTree();

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

        // Add a package import to every class apart from the last one
        for (int i = 0; i < classNames.size() - 1; i++) {
            PackageInvocationInformation packageInvocationInformation = new PackageInvocationInformation("com.ADA.invocation_example." + classNames.get(i+1));
            pdt.addPackageInvocation(classNames.get(i), classNames.get(i+1), packageInvocationInformation);
        }

        // Add a package import of the remaining classes to the first class
        for (int i = 2; i < classNames.size(); i++) {
            PackageInvocationInformation packageInvocationInformation = new PackageInvocationInformation("com.ADA.invocation_example." + classNames.get(i));
            pdt.addPackageInvocation(classNames.get(0), classNames.get(i), packageInvocationInformation);
        }

        // Add an attribute import to every class apart from the last one
        for (int i = 0; i < classNames.size() - 1; i++) {
            AttributeInvocationInformation attributeInvocationInformation = new AttributeInvocationInformation("attributeExampleFromClass" + classNames.get(i+1));
            pdt.addAttributeInvocation(classNames.get(i), classNames.get(i+1), attributeInvocationInformation);
        }

        // Add an attribute invocation to the remaining classes from the first class
        for (int i = 2; i < classNames.size(); i++) {
            AttributeInvocationInformation attributeInvocationInformation = new AttributeInvocationInformation("attributeExampleFromClass" + classNames.get(i));
            pdt.addAttributeInvocation(classNames.get(0), classNames.get(i), attributeInvocationInformation);
        }

        // Add a constructor invocation to every class apart from the last one
        for (int i = 0; i < classNames.size() - 1; i++) {
            ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation("constructorExample" + classNames.get(i+1), new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));
            pdt.addConstructorInvocation(classNames.get(i), classNames.get(i+1), constructorInvocationInformation);
        }

        // Add a constructor invocation to the remaining classes from the first class
        for (int i = 2; i < classNames.size(); i++) {
            ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation("constructorExample" + classNames.get(i), new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));
            pdt.addConstructorInvocation(classNames.get(0), classNames.get(i), constructorInvocationInformation);
        }

        // Add a method invocation to every class apart from the last one
        for (int i = 0; i < classNames.size() - 1; i++) {
            MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("methodExampleIn" + classNames.get(i+1), new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));
            pdt.addMethodInvocation(classNames.get(i), classNames.get(i+1), methodInvocationInformation);
        }

        // Add a method invocation to the remaining classes from the first class
        for (int i = 2; i < classNames.size(); i++) {
            MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("methodExampleIn" + classNames.get(i), new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));
            pdt.addMethodInvocation(classNames.get(0), classNames.get(i), methodInvocationInformation);
        }

        /*
        At this point:
        - the first class in the list depends on all other classes by all elements,
        - all other classes apart from the last one depend by all elements on the subsequent class
        - the last class does not depend on any other class by any element
         */
    }

    @Test
    public void numberOfPackageImportsIncoming_testMetricCorrectness() {

    }

    @Test
    public void numberOfPackageImportsOutgoing_testMetricCorrectness() {

    }

    @Test
    public void numberOfAttributeInvocationsIncoming_testMetricCorrectness() {

    }

    @Test
    public void numberOfAttributeInvocationsOutgoing_testMetricCorrectness() {

    }

    @Test
    public void numberOfMethodInvocationsIncoming_testMetricCorrectness() {

    }

    @Test
    public void numberOfMethodInvocationsOutgoing_testMetricCorrectness() {

    }

    @Test
    public void numberOfConstructorInvocationsIncoming_testMetricCorrectness() {

    }

    @Test
    public void numberOfConstructorInvocationsOutgoing_testMetricCorrectness() {

    }

    @Test
    public void bidirectionalNumberOfPackageImports_testMetricCorrectness() {

    }

    @Test
    public void bidirectionalNumberOfAttributeInvocations_testMetricCorrectness() {

    }

    @Test
    public void bidirectionalNumberOfMethodInvocations_testMetricCorrectness() {

    }

    @Test
    public void bidirectionalNumberOfConstructorInvocations_testMetricCorrectness() {

    }
}