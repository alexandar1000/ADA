package com.ucl.ADA.model.project_structure;

import com.ucl.ADA.model.class_structure.ClassStructure;
import com.ucl.ADA.model.dependence_information.declaration_information.*;
import com.ucl.ADA.model.dependence_information.invocation_information.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectStructureMetricsTest {

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
    void computeAllMetrics_testPresenceOfAllClasses() {
        projectStructure.computeAllMetrics();
        for (ClassStructure classStructure : projectStructure.getClassStructures().values()) {
            assertThat(classStructure.getClassMetricValues()).isNotNull();
            assertThat(classStructure.getRelationMetricValues()).isNotNull();
        }
    }
}
