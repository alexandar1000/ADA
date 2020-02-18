package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.AttributeDeclaration;
import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclaration;
import com.ucl.ADA.parser.dependence_information.declaration_information.MethodDeclaration;
import com.ucl.ADA.parser.dependence_information.declaration_information.PackageDeclaration;
import com.ucl.ADA.parser.dependence_information.invocation_information.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ProjectStructure {
    private HashMap<String, ClassStructure> classDependenceTrees = new HashMap<>();

    public void addExternalMethodInvocations(String declaringClass, ArrayList<String> externalMethodInvocations) {
        if (this.classDependenceTrees.containsKey(declaringClass)) {
            this.classDependenceTrees.get(declaringClass).setExternalMethodCalls(externalMethodInvocations);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.setExternalMethodCalls(externalMethodInvocations);
            this.classDependenceTrees.put(declaringClass, classStructure);
        }
    }

    public void addExternalConstructorInvocations(String declaringClass, ArrayList<String> externalConstructorInvocations) {
        if (this.classDependenceTrees.containsKey(declaringClass)) {
            this.classDependenceTrees.get(declaringClass).setExternalConstructorInvocations(externalConstructorInvocations);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.setExternalConstructorInvocations(externalConstructorInvocations);
            this.classDependenceTrees.put(declaringClass, classStructure);
        }
    }

    public void addExternalFieldDeclarations(String declaringClass, ArrayList<String> externalFieldDeclarations) {
        if (this.classDependenceTrees.containsKey(declaringClass)) {
            this.classDependenceTrees.get(declaringClass).setExternalFieldInvocations(externalFieldDeclarations);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.setExternalFieldInvocations(externalFieldDeclarations);
            this.classDependenceTrees.put(declaringClass, classStructure);
        }
    }

    /**
     * Adds package declaration for the corresponding declaring class where it is declared.
     * @param declaringClass Class where the package is declared
     * @param packageDeclarationInformation The package declaration object
     */
    public void addPackageDeclaration(String declaringClass, PackageDeclaration packageDeclarationInformation) {
        if (this.classDependenceTrees.containsKey(declaringClass)) {
            this.classDependenceTrees.get(declaringClass).setCurrentPackage(packageDeclarationInformation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.setCurrentPackage(packageDeclarationInformation);
            this.classDependenceTrees.put(declaringClass, classStructure);
        }
    }

    /**
     * Adds attribute declaration for the corresponding declaring class where it is declared.
     * @param declaringClass Class where the attribute is declared
     * @param attributeDeclarationInformation The attribute declaration object
     */
    public void addAttributeDeclaration(String declaringClass, AttributeDeclaration attributeDeclarationInformation) {
        if (this.classDependenceTrees.containsKey(declaringClass)) {
            this.classDependenceTrees.get(declaringClass).addAttributeDeclaration(attributeDeclarationInformation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addAttributeDeclaration(attributeDeclarationInformation);
            this.classDependenceTrees.put(declaringClass, classStructure);
        }
    }

    /**
     * Adds constructor declaration for the corresponding declaring class where it is declared.
     * @param declaringClass Class where the constructor is declared
     * @param constructorDeclarationInformation The attribute declaration object
     */
    public void addConstructorDeclaration(String declaringClass, ConstructorDeclaration constructorDeclarationInformation) {
        if (this.classDependenceTrees.containsKey(declaringClass)) {
            this.classDependenceTrees.get(declaringClass).addConstructorDeclaration(constructorDeclarationInformation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addConstructorDeclaration(constructorDeclarationInformation);
            this.classDependenceTrees.put(declaringClass, classStructure);
        }
    }

    /**
     * Adds method declaration for the corresponding declaring class where it is declared.
     * @param declaringClass Class where the method is declared
     * @param methodDeclarationInformation The method declaration object
     */
    public void addMethodDeclaration(String declaringClass, MethodDeclaration methodDeclarationInformation) {
        if (this.classDependenceTrees.containsKey(declaringClass)) {
            this.classDependenceTrees.get(declaringClass).addMethodDeclaration(methodDeclarationInformation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addMethodDeclaration(methodDeclarationInformation);
            this.classDependenceTrees.put(declaringClass, classStructure);
        }
    }


    /**
     * Adds the package invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     * @param consumingClassName class where the package has been invoked from
     * @param declaringClassName class where the package has been declared in
     * @param packageDeclarationInformation The package declaration object
     */
    public void addPackageInvocation(String consumingClassName, String declaringClassName, PackageInvocation packageDeclarationInformation) {
        if (this.classDependenceTrees.containsKey(consumingClassName)) {
            this.classDependenceTrees.get(consumingClassName).addPackageInvocationElement(declaringClassName, InvocationType.OUTGOING, packageDeclarationInformation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addPackageInvocationElement(declaringClassName, InvocationType.OUTGOING, packageDeclarationInformation);
            this.classDependenceTrees.put(consumingClassName, classStructure);
        }

        if (this.classDependenceTrees.containsKey(declaringClassName)) {
            this.classDependenceTrees.get(declaringClassName).addPackageInvocationElement(consumingClassName, InvocationType.INCOMING, packageDeclarationInformation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addPackageInvocationElement(consumingClassName, InvocationType.INCOMING, packageDeclarationInformation);
            this.classDependenceTrees.put(declaringClassName, classStructure);
        }
    }

    /**
     * Adds the attribute invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     * @param consumingClassName class where the attribute has been invoked from
     * @param declaringClassName class where the attribute has been declared in
     * @param attributeInvocationInformation The package declaration object
     */
    public void addAttributeInvocation(String consumingClassName, String declaringClassName, AttributeInvocation attributeInvocationInformation) {
        if (this.classDependenceTrees.containsKey(consumingClassName)) {
            this.classDependenceTrees.get(consumingClassName).addAttributeInvocationElement(declaringClassName, InvocationType.OUTGOING, attributeInvocationInformation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addAttributeInvocationElement(declaringClassName, InvocationType.OUTGOING, attributeInvocationInformation);
            this.classDependenceTrees.put(consumingClassName, classStructure);
        }

        if (this.classDependenceTrees.containsKey(declaringClassName)) {
            this.classDependenceTrees.get(declaringClassName).addAttributeInvocationElement(consumingClassName, InvocationType.INCOMING, attributeInvocationInformation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addAttributeInvocationElement(consumingClassName, InvocationType.INCOMING, attributeInvocationInformation);
            this.classDependenceTrees.put(declaringClassName, classStructure);
        }
    }

    /**
     * Adds the constructor invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     * @param consumingClassName class where the constructor has been invoked from
     * @param declaringClassName class where the constructor has been declared in
     * @param constructorInvocationInformation The package declaration object
     */
    public void addConstructorInvocation(String consumingClassName, String declaringClassName, ConstructorInvocation constructorInvocationInformation) {
        if (this.classDependenceTrees.containsKey(consumingClassName)) {
            this.classDependenceTrees.get(consumingClassName).addConstructorInvocationElement(declaringClassName, InvocationType.OUTGOING, constructorInvocationInformation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addConstructorInvocationElement(declaringClassName, InvocationType.OUTGOING, constructorInvocationInformation);
            this.classDependenceTrees.put(consumingClassName, classStructure);
        }

        if (this.classDependenceTrees.containsKey(declaringClassName)) {
            this.classDependenceTrees.get(declaringClassName).addConstructorInvocationElement(consumingClassName, InvocationType.INCOMING, constructorInvocationInformation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addConstructorInvocationElement(consumingClassName, InvocationType.INCOMING, constructorInvocationInformation);
            this.classDependenceTrees.put(declaringClassName, classStructure);
        }
    }

    /**
     * Adds the method invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     * @param consumingClassName class where the method has been invoked from
     * @param declaringClassName class where the method has been declared in
     * @param methodInvocationInformation The package declaration object
     */
    public void addMethodInvocation(String consumingClassName, String declaringClassName, MethodInvocation methodInvocationInformation) {
        if (this.classDependenceTrees.containsKey(consumingClassName)) {
            this.classDependenceTrees.get(consumingClassName).addMethodInvocationElement(declaringClassName, InvocationType.OUTGOING, methodInvocationInformation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addMethodInvocationElement(declaringClassName, InvocationType.OUTGOING, methodInvocationInformation);
            this.classDependenceTrees.put(consumingClassName, classStructure);
        }

        if (this.classDependenceTrees.containsKey(declaringClassName)) {
            this.classDependenceTrees.get(declaringClassName).addMethodInvocationElement(consumingClassName, InvocationType.INCOMING, methodInvocationInformation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addMethodInvocationElement(consumingClassName, InvocationType.INCOMING, methodInvocationInformation);
            this.classDependenceTrees.put(declaringClassName, classStructure);
        }
    }
}
