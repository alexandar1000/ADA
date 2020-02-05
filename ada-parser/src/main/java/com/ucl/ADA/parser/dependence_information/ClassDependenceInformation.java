package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.invocation_information.ConstructorInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.AttributeInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.MethodInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.ModuleInvocationInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class ClassDependenceInformation {

    // For data and control flow coupling:
    // Data fields invoked
    private ArrayList<AttributeInvocationInformation> attributes = new ArrayList<>();

    // Constructors invoked
    private ArrayList<ConstructorInvocationInformation> constructors = new ArrayList<>();

    // Methods invoked
    private ArrayList<MethodInvocationInformation> methods = new ArrayList<>();


    // For global coupling:
    // Global data invoked
    private ArrayList<AttributeInvocationInformation> globalData = new ArrayList<>();

    // Global methods invoked
    private ArrayList<MethodInvocationInformation> globalMethods = new ArrayList<>();


    // For environmental coupling:
    // Packages imported/exported
    private ArrayList<ModuleInvocationInformation> packages = new ArrayList<>();


    public ClassDependenceInformation() {
    }

    public void addNewDataField(AttributeInvocationInformation attributeInvocationInformation) {
        this.attributes.add(attributeInvocationInformation);
    }

    public void addNewConstructor(ConstructorInvocationInformation constructorInvocationInformation) {
        this.constructors.add(constructorInvocationInformation);
    }

    public void addNewMethod(MethodInvocationInformation methodInvocationInformation) {
        this.methods.add(methodInvocationInformation);
    }

    public void addNewGlobalData(AttributeInvocationInformation attributeInvocationInformation) {
        this.globalData.add(attributeInvocationInformation);
    }

    public void addNewGlobalMethod(MethodInvocationInformation methodInvocationInformation) {
        this.globalMethods.add(methodInvocationInformation);
    }

    public void addNewModule(ModuleInvocationInformation moduleInvocationInformation) {
        this.packages.add(moduleInvocationInformation);
    }
}
