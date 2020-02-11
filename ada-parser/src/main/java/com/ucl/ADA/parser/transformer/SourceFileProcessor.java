package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import com.ucl.ADA.parser.dependence_information.declaration_information.AccessModifierType;
import com.ucl.ADA.parser.dependence_information.declaration_information.AttributeDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.declaration_information.PackageDeclarationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.ConstructorInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.MethodInvocationInformation;
import com.ucl.ADA.parser.dependence_information.invocation_information.PackageInvocationInformation;
import com.ucl.ADA.parser.model.SourceFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SourceFileProcessor {

    // OK
    public void processPackageDeclaration(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        PackageDeclarationInformation packageDeclarationInformation = new PackageDeclarationInformation(sourceFile.getPackageName());
        projectDependenceTree.addPackageDeclaration(sourceFile.getClassName(), packageDeclarationInformation);
    }

    // OK
    public void processAttributeDeclaration(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        sourceFile.getClassAttributes().forEach(a -> {
            AccessModifierType accessModifierType = null;
            if (a.getModifiers().contains("public")) {
                accessModifierType = AccessModifierType.PUBLIC;
            } else if (a.getModifiers().contains("private")) {
                accessModifierType = AccessModifierType.PRIVATE;
            } else if (a.getModifiers().contains("protected")) {
                accessModifierType = AccessModifierType.PROTECTED;
            } else if (a.getModifiers().contains("default")) {
                accessModifierType = AccessModifierType.DEFAULT;
            }
            AttributeDeclarationInformation attributeDeclarationInformation = new AttributeDeclarationInformation(accessModifierType, a.getType(), a.getName(), a.getAssignedValue(), false);
            projectDependenceTree.addAttributeDeclaration(sourceFile.getClassName(), attributeDeclarationInformation);
        });

        sourceFile.getMethods().forEach(m -> {
            for (Map.Entry<String, String> localVariables : m.getLocalVariables().entrySet()) {
                String type = localVariables.getValue();
                String name = localVariables.getKey();
                // TODO: local variable no value
                AttributeDeclarationInformation attributeDeclarationInformation = new AttributeDeclarationInformation(AccessModifierType.LOCAL, type, name, null, true);
                projectDependenceTree.addAttributeDeclaration(sourceFile.getClassName(), attributeDeclarationInformation);
            }
        });
    }

    // OK
    public void processConstructorDeclaration(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        sourceFile.getDeclaredSourceConstructors().forEach(c -> {
            String modifier = c.getAccessModifier();
            AccessModifierType accessModifierType = null;
            if (modifier.equalsIgnoreCase("public")) {
                accessModifierType = AccessModifierType.PUBLIC;
            } else if (modifier.equalsIgnoreCase("private")) {
                accessModifierType = AccessModifierType.PRIVATE;
            } else if (modifier.equalsIgnoreCase("protected")) {
                accessModifierType = AccessModifierType.PROTECTED;
            } else if (modifier.equalsIgnoreCase("default")) {
                accessModifierType = AccessModifierType.DEFAULT;
            }
            ArrayList<String> parameters = new ArrayList<>();
            c.getParameters().forEach((name, type) -> {
                parameters.add(type + " " + name);
            });
            ConstructorDeclarationInformation constructorDeclarationInformation = new ConstructorDeclarationInformation(accessModifierType, c.getName(), parameters);
            projectDependenceTree.addConstructorDeclaration(sourceFile.getClassName(), constructorDeclarationInformation);
        });
    }

    // OK
    public void processMethodDeclaration(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        SourceMethodProcessor sourceMethodProcessor = new SourceMethodProcessor();
        String className = sourceFile.getClassName();
        sourceFile.getMethods().forEach(m -> {
            sourceMethodProcessor.processMethodDeclaration(projectDependenceTree, className, m);
        });
    }

    // OK
    public void processPackageInvocation(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        sourceFile.getImportedPackages().forEach(im -> {
            PackageInvocationInformation packageInvocationInformation = new PackageInvocationInformation(im);
            String[] names = sourceFile.getClassName().split(".");
            projectDependenceTree.addPackageInvocation(sourceFile.getClassName(), names[names.length - 1], packageInvocationInformation);
        });
    }

    // TODO: ...
    public void processAttributeInvocation(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {

    }

    // OK
    public void processConstructorInvocation(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        sourceFile.getMethods().forEach(m -> {
            m.getConstructorInvocations().forEach(ci -> {
                ConstructorInvocationInformation constructorInvocationInformation = new ConstructorInvocationInformation(sourceFile.getClassName(), (ArrayList<String>) ci.getArgumentsValues());
                projectDependenceTree.addConstructorInvocation(sourceFile.getClassName(), ci.getConstructorClassName(), constructorInvocationInformation);
            });
        });
    }

    // OK
    public void processMethodInvocation(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        SourceMethodProcessor sourceMethodProcessor = new SourceMethodProcessor();
        String className = sourceFile.getClassName();
        sourceFile.getMethods().forEach(m -> {
            m.getMethodCalls().forEach(mc -> {
                String[] calleeNames = mc.getCalleeName().split(".");
                MethodInvocationInformation methodInvocationInformation = new MethodInvocationInformation(calleeNames[calleeNames.length - 1], new ArrayList<>(mc.getArguments()));
                projectDependenceTree.addMethodInvocation(sourceFile.getClassName(), calleeNames[calleeNames.length - 2], methodInvocationInformation);
            });
        });
    }

}
