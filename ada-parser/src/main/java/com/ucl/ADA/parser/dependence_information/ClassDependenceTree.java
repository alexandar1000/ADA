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
     * Information about the invocations of the elements from the other classes from this class
     */
    private HashMap<String, ClassDependenceInformation> outgoingDependenceInfo = new HashMap<>();

    /**
     * Information about the invocations of elements from this class by the other classes
     */
    private HashMap<String, ClassDependenceInformation> incomingDependenceInfo = new HashMap<>();


    public ClassDependenceTree(PackageDeclarationInformation packageDeclarationInformation) {
        this.currentPackage = packageDeclarationInformation;
    }

    public void addAttributeDeclaration(AttributeDeclarationInformation attributeDeclarationInformation) {
        this.attributeDeclarations.add(attributeDeclarationInformation);
    }

    public void addMethodDeclaration(MethodDeclarationInformation methodDeclarationInformation) {
        this.methodsDeclarations.add(methodDeclarationInformation);
    }

    public void addConstructorDeclaration(ConstructorDeclarationInformation constructorDeclarationInformation) {
        this.constructorDeclarations.add(constructorDeclarationInformation);
    }

    public void addPackageInvocationElement(String className, InvocationType invocationType, PackageInvocationInformation packageInvocationInformation) {
        if (invocationType == InvocationType.OUTGOING_INVOCATION) {
            if (this.outgoingDependenceInfo.containsKey(className)) {
                this.outgoingDependenceInfo.get(className).addNewModule(packageInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewModule(packageInvocationInformation);
                this.outgoingDependenceInfo.put(className, classDependenceInformation);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(className)) {
                this.incomingDependenceInfo.get(className).addNewModule(packageInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewModule(packageInvocationInformation);
                this.incomingDependenceInfo.put(className, classDependenceInformation);
            }
        }
    }

    public void addAttributeInvocationElement(String className, InvocationType invocationType, AttributeInvocationInformation attributeInvocationInformation) {
        if (invocationType == InvocationType.OUTGOING_INVOCATION) {
            if (this.outgoingDependenceInfo.containsKey(className)) {
                this.outgoingDependenceInfo.get(className).addNewAttribute(attributeInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewAttribute(attributeInvocationInformation);
                this.outgoingDependenceInfo.put(className, classDependenceInformation);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(className)) {
                this.incomingDependenceInfo.get(className).addNewAttribute(attributeInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewAttribute(attributeInvocationInformation);
                this.incomingDependenceInfo.put(className, classDependenceInformation);
            }
        }
    }

    public void addConstructorInvocationElement(String className, InvocationType invocationType, ConstructorInvocationInformation constructorInvocationInformation) {
        if (invocationType == InvocationType.OUTGOING_INVOCATION) {
            if (this.outgoingDependenceInfo.containsKey(className)) {
                this.outgoingDependenceInfo.get(className).addNewConstructor(constructorInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewConstructor(constructorInvocationInformation);
                this.outgoingDependenceInfo.put(className, classDependenceInformation);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(className)) {
                this.incomingDependenceInfo.get(className).addNewConstructor(constructorInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewConstructor(constructorInvocationInformation);
                this.incomingDependenceInfo.put(className, classDependenceInformation);
            }
        }
    }

    public void addMethodInvocationElement(String className, InvocationType invocationType, MethodInvocationInformation methodInvocationInformation) {
        if (invocationType == InvocationType.OUTGOING_INVOCATION) {
            if (this.outgoingDependenceInfo.containsKey(className)) {
                this.outgoingDependenceInfo.get(className).addNewMethod(methodInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewMethod(methodInvocationInformation);
                this.outgoingDependenceInfo.put(className, classDependenceInformation);
            }
        } else {
            if (this.incomingDependenceInfo.containsKey(className)) {
                this.incomingDependenceInfo.get(className).addNewMethod(methodInvocationInformation);
            } else {
                ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
                classDependenceInformation.addNewMethod(methodInvocationInformation);
                this.incomingDependenceInfo.put(className, classDependenceInformation);
            }
        }
    }
}
