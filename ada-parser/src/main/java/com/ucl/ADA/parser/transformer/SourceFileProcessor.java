package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import com.ucl.ADA.parser.dependence_information.declaration_information.ModifierType;
import com.ucl.ADA.parser.dependence_information.declaration_information.AttributeDeclaration;
import com.ucl.ADA.parser.dependence_information.declaration_information.ConstructorDeclaration;
import com.ucl.ADA.parser.dependence_information.declaration_information.PackageDeclaration;
import com.ucl.ADA.parser.dependence_information.invocation_information.ConstructorInvocation;
import com.ucl.ADA.parser.dependence_information.invocation_information.MethodInvocation;
import com.ucl.ADA.parser.dependence_information.invocation_information.PackageInvocation;
import com.ucl.ADA.parser.model.ExternalInvocationInfo;
import com.ucl.ADA.parser.model.SourceFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class SourceFileProcessor {

    // OK
    public void processPackageDeclaration(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        PackageDeclaration packageDeclarationInformation = new PackageDeclaration(sourceFile.getPackageName());
        projectDependenceTree.addPackageDeclaration(sourceFile.getClassName(), packageDeclarationInformation);
    }

    // OK
    public void processAttributeDeclaration(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        sourceFile.getClassAttributes().forEach(a -> {
            ModifierType modifierType = null;
            if (a.getModifiers().contains("public")) {
                modifierType = ModifierType.PUBLIC;
            } else if (a.getModifiers().contains("private")) {
                modifierType = ModifierType.PRIVATE;
            } else if (a.getModifiers().contains("protected")) {
                modifierType = ModifierType.PROTECTED;
            } else if (a.getModifiers().contains("default")) {
                modifierType = ModifierType.DEFAULT;
            }
            AttributeDeclaration attributeDeclarationInformation = new AttributeDeclaration(modifierType, a.getType(), a.getName(), a.getAssignedValue());
            projectDependenceTree.addAttributeDeclaration(sourceFile.getClassName(), attributeDeclarationInformation);
        });

        sourceFile.getMethods().forEach(m -> {
            for (Map.Entry<String, String> localVariables : m.getLocalVariables().entrySet()) {
                String type = localVariables.getValue();
                String name = localVariables.getKey();
                // TODO: local variable no value
                // TODO: check at the ModifierType.DEFAULT in the line below
                AttributeDeclaration attributeDeclarationInformation = new AttributeDeclaration(ModifierType.DEFAULT, type, name, null);
                projectDependenceTree.addAttributeDeclaration(sourceFile.getClassName(), attributeDeclarationInformation);
            }
        });
    }

    // OK
    public void processConstructorDeclaration(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        sourceFile.getDeclaredSourceConstructors().forEach(c -> {
            String modifier = c.getAccessModifier();
            ModifierType modifierType = null;
            if (modifier.equalsIgnoreCase("public")) {
                modifierType = ModifierType.PUBLIC;
            } else if (modifier.equalsIgnoreCase("private")) {
                modifierType = ModifierType.PRIVATE;
            } else if (modifier.equalsIgnoreCase("protected")) {
                modifierType = ModifierType.PROTECTED;
            } else if (modifier.equalsIgnoreCase("default")) {
                modifierType = ModifierType.DEFAULT;
            }
            ArrayList<String> parameters = new ArrayList<>();
            c.getParameters().forEach((name, type) -> {
                parameters.add(type + " " + name);
            });
            ConstructorDeclaration constructorDeclarationInformation = new ConstructorDeclaration(modifierType, c.getName(), parameters);
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

    // TODO: What if import end up with '*' or import lombok
    public void processPackageInvocation(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        Set<String> importPackages = sourceFile.getImportedPackages();
        if (importPackages.isEmpty())
            return;

        for (String im : importPackages) {
            PackageInvocation packageInvocationInformation = new PackageInvocation(im);
            String[] package_arr = im.split("\\.");
            projectDependenceTree.addPackageInvocation(sourceFile.getClassName(), package_arr[package_arr.length - 1], packageInvocationInformation);
        }
    }

    // TODO: ...
    public void processAttributeInvocation(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {

    }

    // OK
    public void processConstructorInvocation(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        sourceFile.getMethods().forEach(m -> {
            m.getConstructorInvocations().forEach(ci -> {
                ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation(sourceFile.getClassName(), (ArrayList<String>) ci.getArgumentsValues());
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
                String[] calleeNames = mc.getCalleeName().split("\\.");
                MethodInvocation methodInvocationInformation = new MethodInvocation(calleeNames[calleeNames.length - 1], new ArrayList<>(mc.getArguments()));
                projectDependenceTree.addMethodInvocation(sourceFile.getClassName(), calleeNames[calleeNames.length - 2], methodInvocationInformation);
            });
        });
    }

    public void processExternalInvocation(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        ExternalInvocationInfo externalInvocationInfo = sourceFile.getExternalInvocationInfo();

        projectDependenceTree.addExternalMethodInvocations(sourceFile.getClassName(), (ArrayList<String>) externalInvocationInfo.getExMethodCalls());
        projectDependenceTree.addExternalConstructorInvocations(sourceFile.getClassName(), (ArrayList<String>) externalInvocationInfo.getExConstructorInvocations());
        projectDependenceTree.addExternalFieldDeclarations(sourceFile.getClassName(), (ArrayList<String>) externalInvocationInfo.getExFieldInvocation());
    }

}
