package com.ucl.ADA.parser.dependence_information;

import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.DataDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.MethodDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.ModuleDeclarationInformation;
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
    private ModuleDeclarationInformation currentPackage = null;

    /**
     * Attributes declared in this class.
     */
    private ArrayList<DataDeclarationInformation> dataFieldDeclarations = new ArrayList<>();

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


    public ClassDependenceTree(ModuleDeclarationInformation moduleDeclarationInformation) {
        this.currentPackage = moduleDeclarationInformation;
    }

    public void addDataDeclaration(DataDeclarationInformation dataDeclarationInformation) {
        this.dataFieldDeclarations.add(dataDeclarationInformation);
    }

    public void addMethodDeclaration(MethodDeclarationInformation methodDeclarationInformation) {
        this.methodsDeclarations.add(methodDeclarationInformation);
    }

    public void addConstructorDeclaration(ConstructorDeclarationInformation constructorDeclarationInformation) {
        this.constructorDeclarations.add(constructorDeclarationInformation);
    }

    public void addModuleInvocationElement(String className, InvocationType outgoingInvocation, ModuleInvocationInformation moduleInvocationInformation) {
        if (this.outgoingDependenceInfo.containsKey(className)) {
            if (outgoingInvocation == InvocationType.OUTGOING_INVOCATION) {
                this.outgoingDependenceInfo.get(className).addNewModule(moduleInvocationInformation);
            } else {
                this.incomingDependenceInfo.get(className).addNewModule(moduleInvocationInformation);
            }
        } else {
            ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
            if (outgoingInvocation == InvocationType.OUTGOING_INVOCATION) {
                classDependenceInformation.addNewModule(moduleInvocationInformation);
                this.outgoingDependenceInfo.put(className, classDependenceInformation);
            } else {
                classDependenceInformation.addNewModule(moduleInvocationInformation);
                this.incomingDependenceInfo.put(className, classDependenceInformation);
            }
        }
    }

    public void addDataInvocationElement(String className, InvocationType outgoingInvocation, DataInvocationInformation dataInvocationInformation) {
        if (this.outgoingDependenceInfo.containsKey(className)) {
            if (outgoingInvocation == InvocationType.OUTGOING_INVOCATION) {
                this.outgoingDependenceInfo.get(className).addNewDataField(dataInvocationInformation);
            } else {
                this.incomingDependenceInfo.get(className).addNewDataField(dataInvocationInformation);
            }
        } else {
            ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
            if (outgoingInvocation == InvocationType.OUTGOING_INVOCATION) {
                classDependenceInformation.addNewDataField(dataInvocationInformation);
                this.outgoingDependenceInfo.put(className, classDependenceInformation);
            } else {
                classDependenceInformation.addNewDataField(dataInvocationInformation);
                this.incomingDependenceInfo.put(className, classDependenceInformation);
            }
        }
    }

    public void addConstructorInvocationElement(String className, InvocationType outgoingInvocation, ConstructorInvocationInformation constructorInvocationInformation) {
        if (this.outgoingDependenceInfo.containsKey(className)) {
            if (outgoingInvocation == InvocationType.OUTGOING_INVOCATION) {
                this.outgoingDependenceInfo.get(className).addNewConstructor(constructorInvocationInformation);
            } else {
                this.incomingDependenceInfo.get(className).addNewConstructor(constructorInvocationInformation);
            }
        } else {
            ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
            if (outgoingInvocation == InvocationType.OUTGOING_INVOCATION) {
                classDependenceInformation.addNewConstructor(constructorInvocationInformation);
                this.outgoingDependenceInfo.put(className, classDependenceInformation);
            } else {
                classDependenceInformation.addNewConstructor(constructorInvocationInformation);
                this.incomingDependenceInfo.put(className, classDependenceInformation);
            }
        }
    }

    public void addMethodInvocationElement(String className, InvocationType outgoingInvocation, MethodInvocationInformation methodInvocationInformation) {
        if (this.outgoingDependenceInfo.containsKey(className)) {
            if (outgoingInvocation == InvocationType.OUTGOING_INVOCATION) {
                this.outgoingDependenceInfo.get(className).addNewMethod(methodInvocationInformation);
            } else {
                this.incomingDependenceInfo.get(className).addNewMethod(methodInvocationInformation);
            }
        } else {
            ClassDependenceInformation classDependenceInformation = new ClassDependenceInformation();
            if (outgoingInvocation == InvocationType.OUTGOING_INVOCATION) {
                classDependenceInformation.addNewMethod(methodInvocationInformation);
                this.outgoingDependenceInfo.put(className, classDependenceInformation);
            } else {
                classDependenceInformation.addNewMethod(methodInvocationInformation);
                this.incomingDependenceInfo.put(className, classDependenceInformation);
            }
        }
    }
}
