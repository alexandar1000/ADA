package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.invocation_information.ConstructorInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.AttributeInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.MethodInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.PackageInvocationInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class ClassDependenceInformation {

    // For data and control flow coupling:
    /**
     * Attributes present in the class. They can be either declared or invoked.
     */
    private ArrayList<AttributeInvocationInformation> attributes = new ArrayList<>();

    /**
     * Constructors present in the class. They can be either declared or invoked.
     */
    private ArrayList<ConstructorInvocationInformation> constructors = new ArrayList<>();

    /**
     * Methods present in the class. They can be either declared or invoked.
     */
    private ArrayList<MethodInvocationInformation> methods = new ArrayList<>();


    // For global coupling:
    /**
     * Global Data present in the class. It can be either declared or invoked.
     */
    private ArrayList<AttributeInvocationInformation> globalData = new ArrayList<>();

    /**
     * Global Methods present in the class. They can be either declared or invoked.
     */
    private ArrayList<MethodInvocationInformation> globalMethods = new ArrayList<>();


    // For environmental coupling:
    /**
     * Packages present in the class. They can be either declared or imported.
     */
    private ArrayList<PackageInvocationInformation> packages = new ArrayList<>();


    public ClassDependenceInformation() {
    }

    /**
     * Adds a new attribute to the instance.
     * @param attributeInvocationInformation an attribute invocation information object containing all of the
     *                                       corresponding information about the attribute being added
     */
    public void addNewAttribute(AttributeInvocationInformation attributeInvocationInformation) {
        this.attributes.add(attributeInvocationInformation);
    }

    /**
     * Adds a new constructor to the instance.
     * @param constructorInvocationInformation a constructor invocation information object containing all of the
     *                                       corresponding information about the constructor being added
     */
    public void addNewConstructor(ConstructorInvocationInformation constructorInvocationInformation) {
        this.constructors.add(constructorInvocationInformation);
    }

    /**
     * Adds a new method to the instance.
     * @param methodInvocationInformation a method invocation information object containing all of the
     *                                       corresponding information about the method being added
     */
    public void addNewMethod(MethodInvocationInformation methodInvocationInformation) {
        this.methods.add(methodInvocationInformation);
    }

    /**
     * Adds a new global data to the instance.
     * @param attributeInvocationInformation a global data invocation information object containing all of the
     *                                       corresponding information about the method being added
     */
    public void addNewGlobalData(AttributeInvocationInformation attributeInvocationInformation) {
        this.globalData.add(attributeInvocationInformation);
    }

    /**
     * Adds a new global method to the instance.
     * @param methodInvocationInformation a global method invocation information object containing all of the
     *                                       corresponding information about the global method being added
     */
    public void addNewGlobalMethod(MethodInvocationInformation methodInvocationInformation) {
        this.globalMethods.add(methodInvocationInformation);
    }

    /**
     * Adds a new package to the instance.
     * @param packageInvocationInformation a package invocation information object containing all of the
     *                                       corresponding information about the package being added
     */
    public void addNewPackage(PackageInvocationInformation packageInvocationInformation) {
        this.packages.add(packageInvocationInformation);
    }
}
