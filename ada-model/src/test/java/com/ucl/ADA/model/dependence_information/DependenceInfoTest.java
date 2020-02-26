package com.ucl.ADA.model.dependence_information;

import com.ucl.ADA.model.dependence_information.invocation_information.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class DependenceInfoTest {

    private DependenceInfo dependenceInfo;
    private ArrayList<PassedParameter> passedParameterList = new ArrayList<>(Arrays.asList(
            new PassedParameter("FirstParameter"),
            new PassedParameter("SecondParameter")
    ));

    @BeforeEach
    void setUp() {
        dependenceInfo = new DependenceInfo();

    }

    @Test
    void addNewAttribute_addNewElement() {
        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("invocationInformationName");

        dependenceInfo.addNewAttribute(attributeInvocationInformation);

        assertThat(dependenceInfo.getAttributes()).containsExactly(attributeInvocationInformation);
    }

    @Test
    void addNewConstructor_addNewElement() {
        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", passedParameterList);

        dependenceInfo.addNewConstructor(constructorInvocationInformation);

        assertThat(dependenceInfo.getConstructors()).containsExactly(constructorInvocationInformation);
    }

    @Test
    void addNewMethod_addNewElement() {
         MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", passedParameterList);

        dependenceInfo.addNewMethod(methodInvocationInformation);

        assertThat(dependenceInfo.getMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    void addNewPackage_addNewElement() {
        PackageInvocation packageInvocationInformation = new PackageInvocation("moduleInvocationInformationName");

        dependenceInfo.addNewPackage(packageInvocationInformation);

        assertThat(dependenceInfo.getPackages()).containsExactly(packageInvocationInformation);
    }
}
