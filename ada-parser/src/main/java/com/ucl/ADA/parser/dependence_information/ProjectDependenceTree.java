package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.AttributeDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.MethodDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.PackageDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.InvocationType;
import com.ucl.ADA.parser.dependence_information.invocation_information.PackageInvocationInformation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ProjectDependenceTree {
    private HashMap<String, ClassDependenceTree> classDependenceTrees = new HashMap<>();

    private ArrayList<String> classNames = new ArrayList<>(Arrays.asList("Aaa", "Bbb", "Ccc", "Ddd", "Eee", "Fff"));


    public void addModuleDeclaration(String className, PackageDeclarationInformation packageDeclarationInformation) {
        if (this.classDependenceTrees.containsKey(className)) {
            this.classDependenceTrees.get(className).setCurrentPackage(packageDeclarationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.setCurrentPackage(packageDeclarationInformation);
            this.classDependenceTrees.put(className, classDependenceTree);
        }
    }

    public void addAttributeDeclaration(String className, AttributeDeclarationInformation attributeDeclarationInformation) {
        if (this.classDependenceTrees.containsKey(className)) {
            this.classDependenceTrees.get(className).addAttributeDeclaration(attributeDeclarationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.addAttributeDeclaration(attributeDeclarationInformation);
            this.classDependenceTrees.put(className, classDependenceTree);
        }
    }

    public void addConstructorDeclaration(String className, ConstructorDeclarationInformation constructorDeclarationInformation) {
        if (this.classDependenceTrees.containsKey(className)) {
            this.classDependenceTrees.get(className).addConstructorDeclaration(constructorDeclarationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.addConstructorDeclaration(constructorDeclarationInformation);
            this.classDependenceTrees.put(className, classDependenceTree);
        }
    }

    public void addMethodDeclaration(String className, MethodDeclarationInformation methodDeclarationInformation) {
        if (this.classDependenceTrees.containsKey(className)) {
            this.classDependenceTrees.get(className).addMethodDeclaration(methodDeclarationInformation);
        } else {
            ClassDependenceTree classDependenceTree = new ClassDependenceTree();
            classDependenceTree.addMethodDeclaration(methodDeclarationInformation);
            this.classDependenceTrees.put(className, classDependenceTree);
        }
    }

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
}