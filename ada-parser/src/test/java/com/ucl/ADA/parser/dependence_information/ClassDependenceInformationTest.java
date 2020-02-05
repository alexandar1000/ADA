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
    void addInvokedData_addNewElement() {
        DataInvocationInformation dataInvocationInformation = new DataInvocationInformation("invocationInformationName");

        cdi.addInvokedData(dataInvocationInformation);

        assertThat(cdi.getInvokedData()).containsExactly(dataInvocationInformation);
    }

    @Test
    void addInvokedMethod_addNewElement() {
         MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("invocationInformationName");

        cdi.addInvokedMethod(methodInvocationInformation);

        assertThat(cdi.getInvokedMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    void addIncomingDataInvocation_addNewElement() {
        DataInvocationInformation dataInvocationInformation = new DataInvocationInformation("invocationInformationName");

        cdi.addIncomingDataInvocation(dataInvocationInformation);

        assertThat(cdi.getIncomingDataInvocations()).containsExactly(dataInvocationInformation);
    }

    @Test
    void addIncomingMethodInvocation_addNewElement() {
         MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("invocationInformationName");

        cdi.addIncomingMethodInvocation(methodInvocationInformation);

        assertThat(cdi.getIncomingMethodInvocations()).containsExactly(methodInvocationInformation);
    }

    @Test
    void addInvokedGlobalData_addNewElement() {
         DataInvocationInformation dataInvocationInformation = new DataInvocationInformation("globalInvocationInformationName");

        cdi.addInvokedGlobalData(dataInvocationInformation);

        assertThat(cdi.getInvokedGlobalData()).containsExactly(dataInvocationInformation);
    }

    @Test
    void addInvokedGlobalMethods_addNewElement() {
         MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation("globalInvocationInformationName");

        cdi.addInvokedGlobalMethod(methodInvocationInformation);

        assertThat(cdi.getInvokedGlobalMethods()).containsExactly(methodInvocationInformation);
    }

    @Test
    void addImportedModule_addNewElement() {
         ModuleInvocationInformation moduleInvocationInformation = new ModuleInvocationInformation("moduleInvocationInformationName");

        cdi.addImportedModule(moduleInvocationInformation);

        assertThat(cdi.getImportedModules()).containsExactly(moduleInvocationInformation);
    }

    @Test
    void addIncomingModuleImport_addNewElement() {
         ModuleInvocationInformation moduleInvocationInformation = new ModuleInvocationInformation("moduleInvocationInformationName");

        cdi.addIncomingModuleImport(moduleInvocationInformation);

        assertThat(cdi.getIncomingModuleImports()).containsExactly(moduleInvocationInformation);
    }

    @Test
    void addInvokedConstructor_addNewElement() {
        ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation("invocationInformationName");

        cdi.addInvokedConstructor(constructorInvocationInformation);

        assertThat(cdi.getInvokedConstructors()).containsExactly(constructorInvocationInformation);
    }

    @Test
    void addIncomingConstructorInvocation_addNewElement() {
        ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation("invocationInformationName");

        cdi.addIncomingConstructorInvocation(constructorInvocationInformation);

        assertThat(cdi.getIncomingConstructorInvocations()).containsExactly(constructorInvocationInformation);
    }


}