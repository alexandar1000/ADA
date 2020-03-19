package com.ucl.ADA.model.dependence_information;

import com.ucl.ADA.model.base_entity.BaseEntity;
import com.ucl.ADA.model.dependence_information.invocation_information.AttributeInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.ConstructorInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.MethodInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.PackageInvocation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class DependenceInfo extends BaseEntity {

    /* ************************************************************************
     *
     *  For environmental coupling
     *
     **************************************************************************/

    /**
     * Packages present in the class. They can be either declared or imported.
     */
    private Set<PackageInvocation> packages = new HashSet<>();

    /* ************************************************************************
     *
     *  For data and control flow coupling
     *
     **************************************************************************/

    /**
     * Attributes present in the class. They can be either declared or invoked.
     */
    private Set<AttributeInvocation> attributes = new HashSet<>();

    /**
     * Constructors present in the class. They can be either declared or invoked.
     */
    private Set<ConstructorInvocation> constructors = new HashSet<>();

    /**
     * Methods present in the class. They can be either declared or invoked.
     */
    private Set<MethodInvocation> methods = new HashSet<>();


    /* ************************************************************************
     *
     *  public functions to update dependence info
     *
     **************************************************************************/

    /**
     * Adds a new attribute to the instance.
     *
     * @param attributeInvocationInformation an attribute invocation information object containing all of the
     *                                       corresponding information about the attribute being added
     */
    public void addNewAttribute(AttributeInvocation attributeInvocationInformation) {
        this.attributes.add(attributeInvocationInformation);
    }

    /**
     * Adds a new constructor to the instance.
     *
     * @param constructorInvocationInformation a constructor invocation information object containing all of the
     *                                         corresponding information about the constructor being added
     */
    public void addNewConstructor(ConstructorInvocation constructorInvocationInformation) {
        this.constructors.add(constructorInvocationInformation);
    }

    /**
     * Adds a new method to the instance.
     *
     * @param methodInvocationInformation a method invocation information object containing all of the
     *                                    corresponding information about the method being added
     */
    public void addNewMethod(MethodInvocation methodInvocationInformation) {
        this.methods.add(methodInvocationInformation);
    }

    /**
     * Adds a new package to the instance.
     *
     * @param packageInvocationInformation a package invocation information object containing all of the
     *                                     corresponding information about the package being added
     */
    public void addNewPackage(PackageInvocation packageInvocationInformation) {
        this.packages.add(packageInvocationInformation);
    }
}
