package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.DataDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.MethodDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.ModuleDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.ConstructorInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.DataInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.MethodInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.ModuleInvocationInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

class ClassDependenceTreeTest {

    private ClassDependenceTree cdt;
    @BeforeEach
    void setUp() {
        cdt = new ClassDependenceTree();
    }

    @Test
    void setCurrentModule_testSetUp() {
        ModuleDeclarationInformation module = new ModuleDeclarationInformation("com.ADA");
        cdt.setCurrentModule(module);

        assertThat(cdt.getCurrentModule()).isEqualTo(module);
    }

    @Test
    void addDataDeclaration_addNewElement() {
        DataDeclarationInformation dataDeclarationInformation = new DataDeclarationInformation("declarationInformationName");

        cdt.addDataDeclaration(dataDeclarationInformation);

        assertThat(cdt.getDataDeclarations()).containsExactly(dataDeclarationInformation);
    }

    @Test
    void addConstructorDeclaration_addNewElement() {
        ConstructorDeclarationInformation constructorDeclarationInformation = new ConstructorDeclarationInformation("declarationInformationName");

        cdt.addConstructorDeclaration(constructorDeclarationInformation);

        assertThat(cdt.getConstructorDeclarations()).containsExactly(constructorDeclarationInformation);
    }

    @Test
    void addMethodDeclaration_addNewElement() {
        MethodDeclarationInformation methodDeclarationInformation = new MethodDeclarationInformation("declarationInformationName");

        cdt.addMethodDeclaration(methodDeclarationInformation);

        assertThat(cdt.getMethodsDeclarations()).containsExactly(methodDeclarationInformation);
    }

    @Test
    public void addNewElements_testCreationOfKeys() {
        ArrayList<String> classNames = new ArrayList<>(Arrays.asList("FirstClass", "SecondClass", "ThirdClass", "FourthClass"));

        cdt.addModuleInvocationElement(classNames.get(0), null);
        cdt.addDataInvocationElement(classNames.get(1), null);
        cdt.addConstructorInvocationElement(classNames.get(2), null);
        cdt.addMethodInvocationElement(classNames.get(3), null);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactlyInAnyOrderElementsOf(classNames);
    }

    @Test
    public void addNewElements_testInsertionOfSingleElementPerClassAndType() {
        ArrayList<String> classNames = new ArrayList<>(Arrays.asList("FirstClass", "SecondClass", "ThirdClass", "FourthClass"));

        ModuleInvocationInformation moduleInvocationInformation = new ModuleInvocationInformation("moduleImportInformationName");
        DataInvocationInformation dataInvocationInformation = new DataInvocationInformation("dataInvocationInformationName");
        ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation("constructorInvocationInformationName");
        MethodInvocationInformation methodInvocationInformation0 = new MethodInvocationInformation("methodInvocationInformationName");


        cdt.addModuleInvocationElement(classNames.get(0), moduleInvocationInformation);
        cdt.addDataInvocationElement(classNames.get(1), dataInvocationInformation);
        cdt.addConstructorInvocationElement(classNames.get(2), constructorInvocationInformation);
        cdt.addMethodInvocationElement(classNames.get(3), methodInvocationInformation0);

        assertThat(cdt.getOutgoingDependenceInfo().keySet()).containsExactlyInAnyOrderElementsOf(classNames);

        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(0)).getImportedModules()).containsExactly(moduleInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(1)).getInvokedData()).containsExactly(dataInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(2)).getInvokedConstructors()).containsExactly(constructorInvocationInformation);
        assertThat(cdt.getOutgoingDependenceInfo().get(classNames.get(3)).getInvokedMethods()).containsExactly(methodInvocationInformation0);
    }

    @Test
    public void addNewElements_testAddingAnExistingKey() {
        fail("Not yet implemented");
    }

}