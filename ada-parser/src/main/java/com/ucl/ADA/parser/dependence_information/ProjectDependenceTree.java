package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.AttributeDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.MethodDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.PackageDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ProjectDependenceTree {
    private HashMap<String, ClassDependenceTree> classDependenceTrees = new HashMap<>();

    /**
     * Adds package declaration for the corresponding declaring class where it is declared.
     * @param declaringClass Class where the package is declared
     * @param packageDeclarationInformation The package declaration object
     */
    public void addPackageDeclaration(String declaringClass, PackageDeclarationInformation packageDeclarationInformation) {
        if (this.classDependenceTrees.containsKey(declaringClass)) {
            this.classDependenceTrees.get(declaringClass).setCurrentPackage(packageDeclarationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.setCurrentPackage(packageDeclarationInformation);
            this.classDependenceTrees.put(declaringClass, classDependenceTree);
        }
    }

    /**
     * Adds attribute declaration for the corresponding declaring class where it is declared.
     * @param declaringClass Class where the attribute is declared
     * @param attributeDeclarationInformation The attribute declaration object
     */
    public void addAttributeDeclaration(String declaringClass, AttributeDeclarationInformation attributeDeclarationInformation) {
        if (this.classDependenceTrees.containsKey(declaringClass)) {
            this.classDependenceTrees.get(declaringClass).addAttributeDeclaration(attributeDeclarationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.addAttributeDeclaration(attributeDeclarationInformation);
            this.classDependenceTrees.put(declaringClass, classDependenceTree);
        }
    }

    /**
     * Adds constructor declaration for the corresponding declaring class where it is declared.
     * @param declaringClass Class where the constructor is declared
     * @param constructorDeclarationInformation The attribute declaration object
     */
    public void addConstructorDeclaration(String declaringClass, ConstructorDeclarationInformation constructorDeclarationInformation) {
        if (this.classDependenceTrees.containsKey(declaringClass)) {
            this.classDependenceTrees.get(declaringClass).addConstructorDeclaration(constructorDeclarationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.addConstructorDeclaration(constructorDeclarationInformation);
            this.classDependenceTrees.put(declaringClass, classDependenceTree);
        }
    }

    /**
     * Adds method declaration for the corresponding declaring class where it is declared.
     * @param declaringClass Class where the method is declared
     * @param methodDeclarationInformation The method declaration object
     */
    public void addMethodDeclaration(String declaringClass, MethodDeclarationInformation methodDeclarationInformation) {
        if (this.classDependenceTrees.containsKey(declaringClass)) {
            this.classDependenceTrees.get(declaringClass).addMethodDeclaration(methodDeclarationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.addMethodDeclaration(methodDeclarationInformation);
            this.classDependenceTrees.put(declaringClass, classDependenceTree);
        }
    }


    /**
     * Adds the package invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     * @param consumingClassName class where the package has been invoked from
     * @param declaringClassName class where the package has been declared in
     * @param packageDeclarationInformation The package declaration object
     */
    public void addPackageInvocation(String consumingClassName, String declaringClassName, PackageInvocationInformation packageDeclarationInformation) {
        if (this.classDependenceTrees.containsKey(consumingClassName)) {
            this.classDependenceTrees.get(consumingClassName).addPackageInvocationElement(declaringClassName, InvocationType.OUTGOING_INVOCATION, packageDeclarationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.addPackageInvocationElement(declaringClassName, InvocationType.OUTGOING_INVOCATION, packageDeclarationInformation);
            this.classDependenceTrees.put(consumingClassName, classDependenceTree);
        }

        if (this.classDependenceTrees.containsKey(declaringClassName)) {
            this.classDependenceTrees.get(declaringClassName).addPackageInvocationElement(consumingClassName, InvocationType.INCOMING_INVOCATION, packageDeclarationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.addPackageInvocationElement(consumingClassName, InvocationType.INCOMING_INVOCATION, packageDeclarationInformation);
            this.classDependenceTrees.put(declaringClassName, classDependenceTree);
        }
    }

    /**
     * Adds the attribute invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     * @param consumingClassName class where the attribute has been invoked from
     * @param declaringClassName class where the attribute has been declared in
     * @param attributeInvocationInformation The package declaration object
     */
    public void addAttributeInvocation(String consumingClassName, String declaringClassName, AttributeInvocationInformation attributeInvocationInformation) {
        if (this.classDependenceTrees.containsKey(consumingClassName)) {
            this.classDependenceTrees.get(consumingClassName).addAttributeInvocationElement(declaringClassName, InvocationType.OUTGOING_INVOCATION, attributeInvocationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.addAttributeInvocationElement(declaringClassName, InvocationType.OUTGOING_INVOCATION, attributeInvocationInformation);
            this.classDependenceTrees.put(consumingClassName, classDependenceTree);
        }

        if (this.classDependenceTrees.containsKey(declaringClassName)) {
            this.classDependenceTrees.get(declaringClassName).addAttributeInvocationElement(consumingClassName, InvocationType.INCOMING_INVOCATION, attributeInvocationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.addAttributeInvocationElement(consumingClassName, InvocationType.INCOMING_INVOCATION, attributeInvocationInformation);
            this.classDependenceTrees.put(declaringClassName, classDependenceTree);
        }
    }

    /**
     * Adds the constructor invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     * @param consumingClassName class where the constructor has been invoked from
     * @param declaringClassName class where the constructor has been declared in
     * @param constructorInvocationInformation The package declaration object
     */
    public void addConstructorInvocation(String consumingClassName, String declaringClassName, ConstructorInvocationInformation constructorInvocationInformation) {
        if (this.classDependenceTrees.containsKey(consumingClassName)) {
            this.classDependenceTrees.get(consumingClassName).addConstructorInvocationElement(declaringClassName, InvocationType.OUTGOING_INVOCATION, constructorInvocationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.addConstructorInvocationElement(declaringClassName, InvocationType.OUTGOING_INVOCATION, constructorInvocationInformation);
            this.classDependenceTrees.put(consumingClassName, classDependenceTree);
        }

        if (this.classDependenceTrees.containsKey(declaringClassName)) {
            this.classDependenceTrees.get(declaringClassName).addConstructorInvocationElement(consumingClassName, InvocationType.INCOMING_INVOCATION, constructorInvocationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.addConstructorInvocationElement(consumingClassName, InvocationType.INCOMING_INVOCATION, constructorInvocationInformation);
            this.classDependenceTrees.put(declaringClassName, classDependenceTree);
        }
    }

    /**
     * Adds the method invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     * @param consumingClassName class where the method has been invoked from
     * @param declaringClassName class where the method has been declared in
     * @param methodInvocationInformation The package declaration object
     */
    public void addMethodInvocation(String consumingClassName, String declaringClassName, MethodInvocationInformation methodInvocationInformation) {
        if (this.classDependenceTrees.containsKey(consumingClassName)) {
            this.classDependenceTrees.get(consumingClassName).addMethodInvocationElement(declaringClassName, InvocationType.OUTGOING_INVOCATION, methodInvocationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.addMethodInvocationElement(declaringClassName, InvocationType.OUTGOING_INVOCATION, methodInvocationInformation);
            this.classDependenceTrees.put(consumingClassName, classDependenceTree);
        }

        if (this.classDependenceTrees.containsKey(declaringClassName)) {
            this.classDependenceTrees.get(declaringClassName).addMethodInvocationElement(consumingClassName, InvocationType.INCOMING_INVOCATION, methodInvocationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.addMethodInvocationElement(consumingClassName, InvocationType.INCOMING_INVOCATION, methodInvocationInformation);
            this.classDependenceTrees.put(declaringClassName, classDependenceTree);
        }
    }
}