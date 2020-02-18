package com.ucl.ADA.model.dependence_information;

import com.ucl.ADA.model.dependence_information.declaration_information.AttributeDeclaration;
import com.ucl.ADA.model.dependence_information.declaration_information.ConstructorDeclaration;
import com.ucl.ADA.model.dependence_information.declaration_information.MethodDeclaration;
import com.ucl.ADA.model.dependence_information.declaration_information.PackageDeclaration;
import com.ucl.ADA.model.dependence_information.invocation_information.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter @NoArgsConstructor
public class ProjectStructure {
    private Map<String, ClassStructure> classStructures = new HashMap<>();

    public void addExternalMethodInvocations(String declaringClass, List<String> externalMethodInvocations) {
        if (this.classStructures.containsKey(declaringClass)) {
            this.classStructures.get(declaringClass).setExternalMethodCalls(externalMethodInvocations);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.setExternalMethodCalls(externalMethodInvocations);
            this.classStructures.put(declaringClass, classStructure);
        }
    }

    public void addExternalConstructorInvocations(String declaringClass, List<String> externalConstructorInvocations) {
        if (this.classStructures.containsKey(declaringClass)) {
            this.classStructures.get(declaringClass).setExternalConstructorInvocations(externalConstructorInvocations);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.setExternalConstructorInvocations(externalConstructorInvocations);
            this.classStructures.put(declaringClass, classStructure);
        }
    }

    public void addExternalFieldDeclarations(String declaringClass, List<String> externalFieldDeclarations) {
        if (this.classStructures.containsKey(declaringClass)) {
            this.classStructures.get(declaringClass).setExternalFieldInvocations(externalFieldDeclarations);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.setExternalFieldInvocations(externalFieldDeclarations);
            this.classStructures.put(declaringClass, classStructure);
        }
    }

    /**
     * Adds package declaration for the corresponding declaring class where it is declared.
     * @param declaringClass Class where the package is declared
     * @param packageDeclaration The package declaration object
     */
    public void addPackageDeclaration(String declaringClass, PackageDeclaration packageDeclaration) {
        if (this.classStructures.containsKey(declaringClass)) {
            this.classStructures.get(declaringClass).setCurrentPackage(packageDeclaration);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.setCurrentPackage(packageDeclaration);
            this.classStructures.put(declaringClass, classStructure);
        }
    }

    /**
     * Adds attribute declaration for the corresponding declaring class where it is declared.
     * @param declaringClass Class where the attribute is declared
     * @param attributeDeclaration The attribute declaration object
     */
    public void addAttributeDeclaration(String declaringClass, AttributeDeclaration attributeDeclaration) {
        if (this.classStructures.containsKey(declaringClass)) {
            this.classStructures.get(declaringClass).addAttributeDeclaration(attributeDeclaration);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addAttributeDeclaration(attributeDeclaration);
            this.classStructures.put(declaringClass, classStructure);
        }
    }

    /**
     * Adds constructor declaration for the corresponding declaring class where it is declared.
     * @param declaringClass Class where the constructor is declared
     * @param constructorDeclaration The attribute declaration object
     */
    public void addConstructorDeclaration(String declaringClass, ConstructorDeclaration constructorDeclaration) {
        if (this.classStructures.containsKey(declaringClass)) {
            this.classStructures.get(declaringClass).addConstructorDeclaration(constructorDeclaration);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addConstructorDeclaration(constructorDeclaration);
            this.classStructures.put(declaringClass, classStructure);
        }
    }

    /**
     * Adds method declaration for the corresponding declaring class where it is declared.
     * @param declaringClass Class where the method is declared
     * @param methodDeclaration The method declaration object
     */
    public void addMethodDeclaration(String declaringClass, MethodDeclaration methodDeclaration) {
        if (this.classStructures.containsKey(declaringClass)) {
            this.classStructures.get(declaringClass).addMethodDeclaration(methodDeclaration);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addMethodDeclaration(methodDeclaration);
            this.classStructures.put(declaringClass, classStructure);
        }
    }


    /**
     * Adds the package invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     * @param consumingClassName class where the package has been invoked from
     * @param declaringClassName class where the package has been declared in
     * @param packageInvocation The package declaration object
     */
    public void addPackageInvocation(String consumingClassName, String declaringClassName, PackageInvocation packageInvocation) {
        if (this.classStructures.containsKey(consumingClassName)) {
            this.classStructures.get(consumingClassName).addPackageInvocationElement(declaringClassName, InvocationType.OUTGOING, packageInvocation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addPackageInvocationElement(declaringClassName, InvocationType.OUTGOING, packageInvocation);
            this.classStructures.put(consumingClassName, classStructure);
        }

        if (this.classStructures.containsKey(declaringClassName)) {
            this.classStructures.get(declaringClassName).addPackageInvocationElement(consumingClassName, InvocationType.INCOMING, packageInvocation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addPackageInvocationElement(consumingClassName, InvocationType.INCOMING, packageInvocation);
            this.classStructures.put(declaringClassName, classStructure);
        }
    }

    /**
     * Adds the attribute invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     * @param consumingClassName class where the attribute has been invoked from
     * @param declaringClassName class where the attribute has been declared in
     * @param attributeInvocation The package declaration object
     */
    public void addAttributeInvocation(String consumingClassName, String declaringClassName, AttributeInvocation attributeInvocation) {
        if (this.classStructures.containsKey(consumingClassName)) {
            this.classStructures.get(consumingClassName).addAttributeInvocationElement(declaringClassName, InvocationType.OUTGOING, attributeInvocation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addAttributeInvocationElement(declaringClassName, InvocationType.OUTGOING, attributeInvocation);
            this.classStructures.put(consumingClassName, classStructure);
        }

        if (this.classStructures.containsKey(declaringClassName)) {
            this.classStructures.get(declaringClassName).addAttributeInvocationElement(consumingClassName, InvocationType.INCOMING, attributeInvocation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addAttributeInvocationElement(consumingClassName, InvocationType.INCOMING, attributeInvocation);
            this.classStructures.put(declaringClassName, classStructure);
        }
    }

    /**
     * Adds the constructor invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     * @param consumingClassName class where the constructor has been invoked from
     * @param declaringClassName class where the constructor has been declared in
     * @param constructorInvocation The package declaration object
     */
    public void addConstructorInvocation(String consumingClassName, String declaringClassName, ConstructorInvocation constructorInvocation) {
        if (this.classStructures.containsKey(consumingClassName)) {
            this.classStructures.get(consumingClassName).addConstructorInvocationElement(declaringClassName, InvocationType.OUTGOING, constructorInvocation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addConstructorInvocationElement(declaringClassName, InvocationType.OUTGOING, constructorInvocation);
            this.classStructures.put(consumingClassName, classStructure);
        }

        if (this.classStructures.containsKey(declaringClassName)) {
            this.classStructures.get(declaringClassName).addConstructorInvocationElement(consumingClassName, InvocationType.INCOMING, constructorInvocation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addConstructorInvocationElement(consumingClassName, InvocationType.INCOMING, constructorInvocation);
            this.classStructures.put(declaringClassName, classStructure);
        }
    }

    /**
     * Adds the method invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     * @param consumingClassName class where the method has been invoked from
     * @param declaringClassName class where the method has been declared in
     * @param methodInvocation The package declaration object
     */
    public void addMethodInvocation(String consumingClassName, String declaringClassName, MethodInvocation methodInvocation) {
        if (this.classStructures.containsKey(consumingClassName)) {
            this.classStructures.get(consumingClassName).addMethodInvocationElement(declaringClassName, InvocationType.OUTGOING, methodInvocation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addMethodInvocationElement(declaringClassName, InvocationType.OUTGOING, methodInvocation);
            this.classStructures.put(consumingClassName, classStructure);
        }

        if (this.classStructures.containsKey(declaringClassName)) {
            this.classStructures.get(declaringClassName).addMethodInvocationElement(consumingClassName, InvocationType.INCOMING, methodInvocation);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addMethodInvocationElement(consumingClassName, InvocationType.INCOMING, methodInvocation);
            this.classStructures.put(declaringClassName, classStructure);
        }
    }
}
