package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.invocation_information.ConstructorInvocation;
import com.ucl.ADA.parser.dependence_information.invocation_information.AttributeInvocation;
import com.ucl.ADA.parser.dependence_information.invocation_information.MethodInvocation;
import com.ucl.ADA.parser.dependence_information.invocation_information.PackageInvocation;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class ClassDependenceInformation {

    // For data and control flow coupling:
    /**
     * Attributes present in the class. They can be either declared or invoked.
     */
    private ArrayList<AttributeInvocation> attributes = new ArrayList<>();

    /**
     * Constructors present in the class. They can be either declared or invoked.
     */
    private ArrayList<ConstructorInvocation> constructors = new ArrayList<>();

    /**
     * Methods present in the class. They can be either declared or invoked.
     */
    private ArrayList<MethodInvocation> methods = new ArrayList<>();


    // For global coupling:
    /**
     * Global Data present in the class. It can be either declared or invoked.
     */
    private ArrayList<AttributeInvocation> globalData = new ArrayList<>();

    /**
     * Global Methods present in the class. They can be either declared or invoked.
     */
    private ArrayList<MethodInvocation> globalMethods = new ArrayList<>();


    // For environmental coupling:
    /**
     * Packages present in the class. They can be either declared or imported.
     */
    private ArrayList<PackageInvocation> packages = new ArrayList<>();


    protected ClassDependenceInformation() {
    }

    /**
     * Adds a new attribute to the instance.
     * @param attributeInvocationInformation an attribute invocation information object containing all of the
     *                                       corresponding information about the attribute being added
     */
    protected void addNewAttribute(AttributeInvocation attributeInvocationInformation) {
        this.attributes.add(attributeInvocationInformation);
    }

    /**
     * Adds a new constructor to the instance.
     * @param constructorInvocationInformation a constructor invocation information object containing all of the
     *                                       corresponding information about the constructor being added
     */
    protected void addNewConstructor(ConstructorInvocation constructorInvocationInformation) {
        this.constructors.add(constructorInvocationInformation);
    }

    /**
     * Adds a new method to the instance.
     * @param methodInvocationInformation a method invocation information object containing all of the
     *                                       corresponding information about the method being added
     */
    protected void addNewMethod(MethodInvocation methodInvocationInformation) {
        this.methods.add(methodInvocationInformation);
    }

    /**
     * Adds a new global data to the instance.
     * @param attributeInvocationInformation a global data invocation information object containing all of the
     *                                       corresponding information about the method being added
     */
    protected void addNewGlobalData(AttributeInvocation attributeInvocationInformation) {
        this.globalData.add(attributeInvocationInformation);
    }

    /**
     * Adds a new global method to the instance.
     * @param methodInvocationInformation a global method invocation information object containing all of the
     *                                       corresponding information about the global method being added
     */
    protected void addNewGlobalMethod(MethodInvocation methodInvocationInformation) {
        this.globalMethods.add(methodInvocationInformation);
    }

    /**
     * Adds a new package to the instance.
     * @param packageInvocationInformation a package invocation information object containing all of the
     *                                       corresponding information about the package being added
     */
    protected void addNewPackage(PackageInvocation packageInvocationInformation) {
        this.packages.add(packageInvocationInformation);
    }
}
