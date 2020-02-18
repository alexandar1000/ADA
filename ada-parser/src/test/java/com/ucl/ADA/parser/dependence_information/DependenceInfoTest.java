package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.model.dependence_information.DependenceInfo;
import com.ucl.ADA.model.dependence_information.invocation_information.AttributeInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.ConstructorInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.MethodInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.PackageInvocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class DependenceInfoTest {

    private DependenceInfo cdi;

    @BeforeEach
    void setUp() {
        cdi = new DependenceInfo();
    }

    @Test
    void addNewAttribute_addNewElement() {
        AttributeInvocation attributeInvocationInformation = new AttributeInvocation("invocationInformationName");

        cdi.addNewAttribute(attributeInvocationInformation);

        assertThat(cdi.getAttributes()).containsExactly(attributeInvocationInformation);
    }

    @Test
    void addNewConstructor_addNewElement() {
        ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation("constructorExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        cdi.addNewConstructor(constructorInvocationInformation);

        assertThat(cdi.getConstructors()).containsExactly(constructorInvocationInformation);
    }

    @Test
    void addNewMethod_addNewElement() {
         MethodInvocation methodInvocationInformation = new MethodInvocation("methodExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        cdi.addNewMethod(methodInvocationInformation);

        assertThat(cdi.getMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    void addNewGlobalData_addNewElement() {
         AttributeInvocation attributeInvocationInformation = new AttributeInvocation("globalInvocationInformationName");

        cdi.addNewGlobalData(attributeInvocationInformation);

        assertThat(cdi.getGlobalData()).containsExactly(attributeInvocationInformation);
    }

    @Test
    void addNewGlobalMethod_addNewElement() {
         MethodInvocation methodInvocationInformation = new MethodInvocation("globalMethodExample", new ArrayList<>(Arrays.asList("String FirstParameter", "Integer SecondParameter")));

        cdi.addNewGlobalMethod(methodInvocationInformation);

        assertThat(cdi.getGlobalMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    void addNewModule_addNewElement() {
         PackageInvocation packageInvocationInformation = new PackageInvocation("moduleInvocationInformationName");

        cdi.addNewPackage(packageInvocationInformation);

        assertThat(cdi.getPackages()).containsExactly(packageInvocationInformation);
    }
}
