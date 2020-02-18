package com.ucl.ADA.model.class_structure;

import com.ucl.ADA.model.dependence_information.DependenceInfo;
import com.ucl.ADA.model.dependence_information.declaration_information.AttributeDeclaration;
import com.ucl.ADA.model.dependence_information.declaration_information.ConstructorDeclaration;
import com.ucl.ADA.model.dependence_information.declaration_information.MethodDeclaration;
import com.ucl.ADA.model.dependence_information.declaration_information.PackageDeclaration;
import com.ucl.ADA.model.dependence_information.invocation_information.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter @NoArgsConstructor
public class ClassStructure {

    /**
     * Class package
     */
    private PackageDeclaration currentPackage = null;

    /**
     * Attributes declared in this class.
     */
    private List<AttributeDeclaration> attributeDeclarations = new ArrayList<>();

    /**
     * Constructors declared in this class.
     */
    private List<ConstructorDeclaration> constructorDeclarations = new ArrayList<>();

    /**
     * Methods declared in this class.
     */
    private List<MethodDeclaration> methodsDeclarations = new ArrayList<>();

    /**
     * Information about the invocations of the elements from the other classes from this class. String is the qualified
     * name of the class.
     */
    private Map<String, DependenceInfo> outgoingDependenceInfo = new HashMap<>();

    /**
     * Information about the invocations of elements from this class by the other classes. String is the qualified
     * name of the class.
     */
    private Map<String, DependenceInfo> incomingDependenceInfo = new HashMap<>();

    /**
     * External Invocation Information, Setter is called
     */
    // TODO: make sure that this makes sense, and if possible extract into a new class
    private List<String> ExternalMethodCalls = new ArrayList<>();

    private List<String> ExternalConstructorInvocations = new ArrayList<>();

    private List<String> ExternalFieldInvocations = new ArrayList<>();


    /**
     * Creates a new instance using the package name.
     * @param packageDeclaration the name of the package corresponding to the current class
     */
    public ClassStructure(PackageDeclaration packageDeclaration) {
        this.currentPackage = packageDeclaration;
    }

    /**
     * Adds a Attribute Declaration to the ClassDependenceTree
     * @param attributeDeclaration the attribute declaration object
     */
    public void addAttributeDeclaration(AttributeDeclaration attributeDeclaration) {
        this.attributeDeclarations.add(attributeDeclaration);
    }

    /**
     * Adds a Method Declaration to the ClassDependenceTree
     * @param methodDeclaration the method declaration object
     */
    public void addMethodDeclaration(MethodDeclaration methodDeclaration) {
        this.methodsDeclarations.add(methodDeclaration);
    }

    /**
     * Adds a Constructor Declaration to the ClassDependenceTree
     * @param constructorDeclaration the constructor declaration object
     */
    public void addConstructorDeclaration(ConstructorDeclaration constructorDeclaration) {
        this.constructorDeclarations.add(constructorDeclaration);
    }

    /**
     * Adds a package invocation element as either an incoming or outgoing dependency, depending on the call.
     * @param relatingClass the class which the invocation relates to
     * @param invocationType either an incoming invocation type in the case that the package is being invoked from the
     *                       relatingClass, or an outgoing invocation type in the case that the package is invoked from
     *                       the relatingClass
     * @param packageInvocation the package invocation object containing the data corresponding to the
     *                                     invocation in question
     */
    public void addPackageInvocationElement(String relatingClass, InvocationType invocationType, PackageInvocation packageInvocation) {
        if (invocationType == InvocationType.OUTGOING) {
            if (this.outgoingDependenceInfo.containsKey(relatingClass)) {
                this.outgoingDependenceInfo.get(relatingClass).addNewPackage(packageInvocation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewPackage(packageInvocation);
                this.outgoingDependenceInfo.put(relatingClass, dependenceInfo);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(relatingClass)) {
                this.incomingDependenceInfo.get(relatingClass).addNewPackage(packageInvocation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewPackage(packageInvocation);
                this.incomingDependenceInfo.put(relatingClass, dependenceInfo);
            }
        }
    }

    /**
     * Adds an attribute invocation element as either an incoming or outgoing dependency, depending on the call.
     * @param relatingClass the class which the invocation relates to
     * @param invocationType either an incoming invocation type in the case that the attribute is being invoked from the
     *                       relatingClass, or an outgoing invocation type in the case that the attribute is invoked from
     *                       the relatingClass
     * @param attributeInvocation the attribute invocation object containing the data corresponding to the
     *                                     invocation in question
     */
    public void addAttributeInvocationElement(String relatingClass, InvocationType invocationType, AttributeInvocation attributeInvocation) {
        if (invocationType == InvocationType.OUTGOING) {
            if (this.outgoingDependenceInfo.containsKey(relatingClass)) {
                this.outgoingDependenceInfo.get(relatingClass).addNewAttribute(attributeInvocation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewAttribute(attributeInvocation);
                this.outgoingDependenceInfo.put(relatingClass, dependenceInfo);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(relatingClass)) {
                this.incomingDependenceInfo.get(relatingClass).addNewAttribute(attributeInvocation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewAttribute(attributeInvocation);
                this.incomingDependenceInfo.put(relatingClass, dependenceInfo);
            }
        }
    }

    /**
     * Adds a constructor invocation element as either an incoming or outgoing dependency, depending on the call.
     * @param relatingClass the class which the invocation relates to
     * @param invocationType either an incoming invocation type in the case that the constructor is being invoked from the
     *                       relatingClass, or an outgoing invocation type in the case that the constructor is invoked from
     *                       the relatingClass
     * @param constructorInvocation the constructor invocation object containing the data corresponding to the
     *                                     invocation in question
     */
    public void addConstructorInvocationElement(String relatingClass, InvocationType invocationType, ConstructorInvocation constructorInvocation) {
        if (invocationType == InvocationType.OUTGOING) {
            if (this.outgoingDependenceInfo.containsKey(relatingClass)) {
                this.outgoingDependenceInfo.get(relatingClass).addNewConstructor(constructorInvocation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewConstructor(constructorInvocation);
                this.outgoingDependenceInfo.put(relatingClass, dependenceInfo);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(relatingClass)) {
                this.incomingDependenceInfo.get(relatingClass).addNewConstructor(constructorInvocation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewConstructor(constructorInvocation);
                this.incomingDependenceInfo.put(relatingClass, dependenceInfo);
            }
        }
    }

    /**
     * Adds a method invocation element as either an incoming or outgoing dependency, depending on the call.
     * @param relatingClass the class which the invocation relates to
     * @param invocationType either an incoming invocation type in the case that the method is being invoked from the
     *                       relatingClass, or an outgoing invocation type in the case that the method is invoked from
     *                       the relatingClass
     * @param methodInvocation the method invocation object containing the data corresponding to the
     *                                     invocation in question
     */
    public void addMethodInvocationElement(String relatingClass, InvocationType invocationType, MethodInvocation methodInvocation) {
        if (invocationType == InvocationType.OUTGOING) {
            if (this.outgoingDependenceInfo.containsKey(relatingClass)) {
                this.outgoingDependenceInfo.get(relatingClass).addNewMethod(methodInvocation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewMethod(methodInvocation);
                this.outgoingDependenceInfo.put(relatingClass, dependenceInfo);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(relatingClass)) {
                this.incomingDependenceInfo.get(relatingClass).addNewMethod(methodInvocation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewMethod(methodInvocation);
                this.incomingDependenceInfo.put(relatingClass, dependenceInfo);
            }
        }
    }
}
