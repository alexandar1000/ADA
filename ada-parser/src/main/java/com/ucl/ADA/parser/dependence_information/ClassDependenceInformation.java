package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.invocation_information.ConstructorInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.DataInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.MethodInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.ModuleInvocationInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class ClassDependenceInformation {

    // For data and control flow coupling:
    // Data fields from other classes which this class invokes
    private ArrayList<DataInvocationInformation> invokedData = new ArrayList<>();

    // Constructors of other classes which are invoked from this class
    private ArrayList<ConstructorInvocationInformation> invokedConstructors = new ArrayList<>();

    // Methods from other classes which this class invokes
    private ArrayList<MethodInvocationInformation> invokedMethods = new ArrayList<>();

    // Data from this class which are invoked by other classes
    private ArrayList<DataInvocationInformation> incomingDataInvocations = new ArrayList<>();

    // Constructors of this class which are invoked from other classes
    private ArrayList<ConstructorInvocationInformation> incomingConstructorInvocations = new ArrayList<>();

    // Methods from this class which are invoked by other classes
    private ArrayList<MethodInvocationInformation> incomingMethodInvocations = new ArrayList<>();


    // For global coupling:
    // Global data fields which this class invokes
    private ArrayList<DataInvocationInformation> invokedGlobalData = new ArrayList<>();

    // Global methods which this class invokes
    private ArrayList<MethodInvocationInformation> invokedGlobalMethods = new ArrayList<>();


    // For environmental coupling:
    // Packages imported into this package(fan-out)
    private ArrayList<ModuleInvocationInformation> importedModules = new ArrayList<>();

    // Packages importing this package (fan-in)
    private ArrayList<ModuleInvocationInformation> incomingModuleImports = new ArrayList<>();


    public ClassDependenceInformation() {
    }

    public void addInvokedData(DataInvocationInformation dataInvocationInformation) {
        this.invokedData.add(dataInvocationInformation);
    }

    public void addInvokedConstructor(ConstructorInvocationInformation constructorInvocationInformation) {
        this.invokedConstructors.add(constructorInvocationInformation);
    }

    public void addInvokedMethod(MethodInvocationInformation methodInvocationInformation) {
        this.invokedMethods.add(methodInvocationInformation);
    }

    public void addIncomingDataInvocation(DataInvocationInformation dataInvocationInformation) {
        this.incomingDataInvocations.add(dataInvocationInformation);
    }

    public void addIncomingConstructorInvocation(ConstructorInvocationInformation constructorInvocationInformation) {
        this.incomingConstructorInvocations.add(constructorInvocationInformation);
    }

    public void addIncomingMethodInvocation(MethodInvocationInformation methodInvocationInformation) {
        this.incomingMethodInvocations.add(methodInvocationInformation);
    }

    public void addInvokedGlobalData(DataInvocationInformation dataInvocationInformation) {
        this.invokedGlobalData.add(dataInvocationInformation);
    }

    public void addInvokedGlobalMethod(MethodInvocationInformation methodInvocationInformation) {
        this.invokedGlobalMethods.add(methodInvocationInformation);
    }

    public void addImportedModule(ModuleInvocationInformation moduleInvocationInformation) {
        this.importedModules.add(moduleInvocationInformation);
    }

    public void addIncomingModuleImport(ModuleInvocationInformation moduleInvocationInformation) {
        this.incomingModuleImports.add(moduleInvocationInformation);
    }
}
