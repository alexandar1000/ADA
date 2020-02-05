package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.invocation_information.ConstructorInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.DataInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.MethodInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.ModuleInvocationInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClassDependenceInformationTest {

    private ClassDependenceInformation cdi;

    @BeforeEach
    void setUp() {
        cdi = new ClassDependenceInformation();
    }

    @Test
    void addNewDataField_addNewElement() {
        DataInvocationInformation dataInvocationInformation = new DataInvocationInformation("invocationInformationName");

        cdi.addNewDataField(dataInvocationInformation);

        assertThat(cdi.getDataFields()).containsExactly(dataInvocationInformation);
    }

    @Test
    void addNewConstructor_addNewElement() {
        ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation("invocationInformationName");

        cdi.addNewConstructor(constructorInvocationInformation);

        assertThat(cdi.getConstructors()).containsExactly(constructorInvocationInformation);
    }

    @Test
    void addNewMethod_addNewElement() {
         MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("invocationInformationName");

        cdi.addNewMethod(methodInvocationInformation);

        assertThat(cdi.getMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    void addNewGlobalData_addNewElement() {
         DataInvocationInformation dataInvocationInformation = new DataInvocationInformation("globalInvocationInformationName");

        cdi.addNewGlobalData(dataInvocationInformation);

        assertThat(cdi.getGlobalData()).containsExactly(dataInvocationInformation);
    }

    @Test
    void addNewGlobalMethod_addNewElement() {
         MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("globalInvocationInformationName");

        cdi.addNewGlobalMethod(methodInvocationInformation);

        assertThat(cdi.getGlobalMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    void addNewModule_addNewElement() {
         ModuleInvocationInformation moduleInvocationInformation = new ModuleInvocationInformation("moduleInvocationInformationName");

        cdi.addNewModule(moduleInvocationInformation);

        assertThat(cdi.getPackages()).containsExactly(moduleInvocationInformation);
    }
}