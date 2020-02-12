package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.AttributeDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.MethodDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.PackageDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ClassDependenceTree {

    /**
     * Class package
     */
    private PackageDeclarationInformation currentPackage = null;

    /**
     * Attributes declared in this class.
     */
    private ArrayList<AttributeDeclarationInformation> attributeDeclarations = new ArrayList<>();

    /**
     * Constructors declared in this class.
     */
    private ArrayList<ConstructorDeclarationInformation> constructorDeclarations = new ArrayList<>();

    /**
     * Methods declared in this class.
     */
    private ArrayList<MethodDeclarationInformation> methodsDeclarations = new ArrayList<>();

    /**
     * Information about the invocations of the elements from the other classes from this class.
     */
    private HashMap<String, ClassDependenceInformation> outgoingDependenceInfo = new HashMap<>();

    /**
     * Information about the invocations of elements from this class by the other classes.
     */
    private HashMap<String, ClassDependenceInformation> incomingDependenceInfo = new HashMap<>();

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
    protected ClassDependenceTree(PackageDeclarationInformation packageDeclarationInformation) {
        this.currentPackage = packageDeclarationInformation;
    }

    /**
     * Adds a Attribute Declaration to the ClassDependenceTree
     * @param attributeDeclarationInformation the attribute declaration object
     */
    protected void addAttributeDeclaration(AttributeDeclarationInformation attributeDeclarationInformation) {
        this.attributeDeclarations.add(attributeDeclarationInformation);
    }

    /**
     * Adds a Method Declaration to the ClassDependenceTree
     * @param methodDeclarationInformation the method declaration object
     */
    protected void addMethodDeclaration(MethodDeclarationInformation methodDeclarationInformation) {
        this.methodsDeclarations.add(methodDeclarationInformation);
    }

    /**
     * Adds a Constructor Declaration to the ClassDependenceTree
     * @param constructorDeclarationInformation the constructor declaration object
     */
    protected void addConstructorDeclaration(ConstructorDeclarationInformation constructorDeclarationInformation) {
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
    protected void addPackageInvocationElement(String relatingClass, InvocationType invocationType, PackageInvocationInformation packageInvocationInformation) {
        if (invocationType == InvocationType.OUTGOING_INVOCATION) {
            if (this.outgoingDependenceInfo.containsKey(relatingClass)) {
                this.outgoingDependenceInfo.get(relatingClass).addNewPackage(packageInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewPackage(packageInvocationInformation);
                this.outgoingDependenceInfo.put(relatingClass, classDependenceInformation);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(relatingClass)) {
                this.incomingDependenceInfo.get(relatingClass).addNewPackage(packageInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewPackage(packageInvocationInformation);
                this.incomingDependenceInfo.put(relatingClass, classDependenceInformation);
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
    protected void addAttributeInvocationElement(String relatingClass, InvocationType invocationType, AttributeInvocationInformation attributeInvocationInformation) {
        if (invocationType == InvocationType.OUTGOING_INVOCATION) {
            if (this.outgoingDependenceInfo.containsKey(relatingClass)) {
                this.outgoingDependenceInfo.get(relatingClass).addNewAttribute(attributeInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewAttribute(attributeInvocationInformation);
                this.outgoingDependenceInfo.put(relatingClass, classDependenceInformation);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(relatingClass)) {
                this.incomingDependenceInfo.get(relatingClass).addNewAttribute(attributeInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewAttribute(attributeInvocationInformation);
                this.incomingDependenceInfo.put(relatingClass, classDependenceInformation);
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
    protected void addConstructorInvocationElement(String relatingClass, InvocationType invocationType, ConstructorInvocationInformation constructorInvocationInformation) {
        if (invocationType == InvocationType.OUTGOING_INVOCATION) {
            if (this.outgoingDependenceInfo.containsKey(relatingClass)) {
                this.outgoingDependenceInfo.get(relatingClass).addNewConstructor(constructorInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewConstructor(constructorInvocationInformation);
                this.outgoingDependenceInfo.put(relatingClass, classDependenceInformation);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(relatingClass)) {
                this.incomingDependenceInfo.get(relatingClass).addNewConstructor(constructorInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewConstructor(constructorInvocationInformation);
                this.incomingDependenceInfo.put(relatingClass, classDependenceInformation);
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
    protected void addMethodInvocationElement(String relatingClass, InvocationType invocationType, MethodInvocationInformation methodInvocationInformation) {
        if (invocationType == InvocationType.OUTGOING_INVOCATION) {
            if (this.outgoingDependenceInfo.containsKey(relatingClass)) {
                this.outgoingDependenceInfo.get(relatingClass).addNewMethod(methodInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewMethod(methodInvocationInformation);
                this.outgoingDependenceInfo.put(relatingClass, classDependenceInformation);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(relatingClass)) {
                this.incomingDependenceInfo.get(relatingClass).addNewMethod(methodInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewMethod(methodInvocationInformation);
                this.incomingDependenceInfo.put(relatingClass, classDependenceInformation);
            }
        }
    }
}
