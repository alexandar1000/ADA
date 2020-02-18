package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclaration;
import com.ucl.ADA.parser.dependence_information.declaration_information.AttributeDeclaration;
import com.ucl.ADA.parser.dependence_information.declaration_information.MethodDeclaration;
import com.ucl.ADA.parser.dependence_information.declaration_information.PackageDeclaration;
import com.ucl.ADA.parser.dependence_information.invocation_information.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ClassStructure {

    /**
     * Class package
     */
    private PackageDeclaration currentPackage = null;

    /**
     * Attributes declared in this class.
     */
    private ArrayList<AttributeDeclaration> attributeDeclarations = new ArrayList<>();

    /**
     * Constructors declared in this class.
     */
    private ArrayList<ConstructorDeclaration> constructorDeclarations = new ArrayList<>();

    /**
     * Methods declared in this class.
     */
    private ArrayList<MethodDeclaration> methodsDeclarations = new ArrayList<>();

    /**
     * Information about the invocations of the elements from the other classes from this class.
     */
    private HashMap<String, DependenceInfo> outgoingDependenceInfo = new HashMap<>();

    /**
     * Information about the invocations of elements from this class by the other classes.
     */
    private HashMap<String, DependenceInfo> incomingDependenceInfo = new HashMap<>();

    /**
     * External Invocation Information, Setter is called
     */
    private ArrayList<String> ExternalMethodCalls = new ArrayList<>();

    private ArrayList<String> ExternalConstructorInvocations = new ArrayList<>();

    private ArrayList<String> ExternalFieldInvocations = new ArrayList<>();


    /**
     * Creates a new instance using the package name.
     * @param packageDeclarationInformation the name of the package corresponding to the current class
     */
    protected ClassStructure(PackageDeclaration packageDeclarationInformation) {
        this.currentPackage = packageDeclarationInformation;
    }

    /**
     * Adds a Attribute Declaration to the ClassDependenceTree
     * @param attributeDeclarationInformation the attribute declaration object
     */
    protected void addAttributeDeclaration(AttributeDeclaration attributeDeclarationInformation) {
        this.attributeDeclarations.add(attributeDeclarationInformation);
    }

    /**
     * Adds a Method Declaration to the ClassDependenceTree
     * @param methodDeclarationInformation the method declaration object
     */
    protected void addMethodDeclaration(MethodDeclaration methodDeclarationInformation) {
        this.methodsDeclarations.add(methodDeclarationInformation);
    }

    /**
     * Adds a Constructor Declaration to the ClassDependenceTree
     * @param constructorDeclarationInformation the constructor declaration object
     */
    protected void addConstructorDeclaration(ConstructorDeclaration constructorDeclarationInformation) {
        this.constructorDeclarations.add(constructorDeclarationInformation);
    }

    /**
     * Adds a package invocation element as either an incoming or outgoing dependency, depending on the call.
     * @param relatingClass the class which the invocation relates to
     * @param invocationType either an incoming invocation type in the case that the package is being invoked from the
     *                       relatingClass, or an outgoing invocation type in the case that the package is invoked from
     *                       the relatingClass
     * @param packageInvocationInformation the package invocation object containing the data corresponding to the
     *                                     invocation in question
     */
    protected void addPackageInvocationElement(String relatingClass, InvocationType invocationType, PackageInvocation packageInvocationInformation) {
        if (invocationType == InvocationType.OUTGOING) {
            if (this.outgoingDependenceInfo.containsKey(relatingClass)) {
                this.outgoingDependenceInfo.get(relatingClass).addNewPackage(packageInvocationInformation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewPackage(packageInvocationInformation);
                this.outgoingDependenceInfo.put(relatingClass, dependenceInfo);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(relatingClass)) {
                this.incomingDependenceInfo.get(relatingClass).addNewPackage(packageInvocationInformation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewPackage(packageInvocationInformation);
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
     * @param attributeInvocationInformation the attribute invocation object containing the data corresponding to the
     *                                     invocation in question
     */
    protected void addAttributeInvocationElement(String relatingClass, InvocationType invocationType, AttributeInvocation attributeInvocationInformation) {
        if (invocationType == InvocationType.OUTGOING) {
            if (this.outgoingDependenceInfo.containsKey(relatingClass)) {
                this.outgoingDependenceInfo.get(relatingClass).addNewAttribute(attributeInvocationInformation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewAttribute(attributeInvocationInformation);
                this.outgoingDependenceInfo.put(relatingClass, dependenceInfo);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(relatingClass)) {
                this.incomingDependenceInfo.get(relatingClass).addNewAttribute(attributeInvocationInformation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewAttribute(attributeInvocationInformation);
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
     * @param constructorInvocationInformation the constructor invocation object containing the data corresponding to the
     *                                     invocation in question
     */
    protected void addConstructorInvocationElement(String relatingClass, InvocationType invocationType, ConstructorInvocation constructorInvocationInformation) {
        if (invocationType == InvocationType.OUTGOING) {
            if (this.outgoingDependenceInfo.containsKey(relatingClass)) {
                this.outgoingDependenceInfo.get(relatingClass).addNewConstructor(constructorInvocationInformation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewConstructor(constructorInvocationInformation);
                this.outgoingDependenceInfo.put(relatingClass, dependenceInfo);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(relatingClass)) {
                this.incomingDependenceInfo.get(relatingClass).addNewConstructor(constructorInvocationInformation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewConstructor(constructorInvocationInformation);
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
     * @param methodInvocationInformation the method invocation object containing the data corresponding to the
     *                                     invocation in question
     */
    protected void addMethodInvocationElement(String relatingClass, InvocationType invocationType, MethodInvocation methodInvocationInformation) {
        if (invocationType == InvocationType.OUTGOING) {
            if (this.outgoingDependenceInfo.containsKey(relatingClass)) {
                this.outgoingDependenceInfo.get(relatingClass).addNewMethod(methodInvocationInformation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewMethod(methodInvocationInformation);
                this.outgoingDependenceInfo.put(relatingClass, dependenceInfo);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(relatingClass)) {
                this.incomingDependenceInfo.get(relatingClass).addNewMethod(methodInvocationInformation);
            } else {
                DependenceInfo dependenceInfo = new DependenceInfo();
                dependenceInfo.addNewMethod(methodInvocationInformation);
                this.incomingDependenceInfo.put(relatingClass, dependenceInfo);
            }
        }
    }
}
