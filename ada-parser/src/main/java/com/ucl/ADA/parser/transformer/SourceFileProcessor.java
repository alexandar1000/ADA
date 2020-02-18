package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.model.dependence_information.declaration_information.ModifierType;
import com.ucl.ADA.model.dependence_information.declaration_information.AttributeDeclaration;
import com.ucl.ADA.model.dependence_information.declaration_information.ConstructorDeclaration;
import com.ucl.ADA.model.dependence_information.declaration_information.PackageDeclaration;
import com.ucl.ADA.model.dependence_information.invocation_information.ConstructorInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.MethodInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.PackageInvocation;
import com.ucl.ADA.parser.model.ExternalInvocationInfo;
import com.ucl.ADA.parser.model.SourceFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class SourceFileProcessor {

    // OK
    public void processPackageDeclaration(ProjectStructure projectStructure, SourceFile sourceFile) {
        PackageDeclaration packageDeclarationInformation = new PackageDeclaration(sourceFile.getPackageName());
        projectStructure.addPackageDeclaration(sourceFile.getClassName(), packageDeclarationInformation);
    }

    // OK
    public void processAttributeDeclaration(ProjectStructure projectStructure, SourceFile sourceFile) {
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
            projectStructure.addAttributeDeclaration(sourceFile.getClassName(), attributeDeclarationInformation);
        });

        sourceFile.getMethods().forEach(m -> {
            for (Map.Entry<String, String> localVariables : m.getLocalVariables().entrySet()) {
                String type = localVariables.getValue();
                String name = localVariables.getKey();
                // TODO: local variable no value
                // TODO: check at the ModifierType.DEFAULT in the line below
                AttributeDeclaration attributeDeclarationInformation = new AttributeDeclaration(ModifierType.DEFAULT, type, name, null);
                projectStructure.addAttributeDeclaration(sourceFile.getClassName(), attributeDeclarationInformation);
            }
        });
    }

    // OK
    public void processConstructorDeclaration(ProjectStructure projectStructure, SourceFile sourceFile) {
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
            projectStructure.addConstructorDeclaration(sourceFile.getClassName(), constructorDeclarationInformation);
        });
    }

    // OK
    public void processMethodDeclaration(ProjectStructure projectStructure, SourceFile sourceFile) {
        SourceMethodProcessor sourceMethodProcessor = new SourceMethodProcessor();
        String className = sourceFile.getClassName();
        sourceFile.getMethods().forEach(m -> {
            sourceMethodProcessor.processMethodDeclaration(projectStructure, className, m);
        });
    }

    // TODO: What if import end up with '*' or import lombok
    public void processPackageInvocation(ProjectStructure projectStructure, SourceFile sourceFile) {
        Set<String> importPackages = sourceFile.getImportedPackages();
        if (importPackages.isEmpty())
            return;

        for (String im : importPackages) {
            PackageInvocation packageInvocationInformation = new PackageInvocation(im);
            String[] package_arr = im.split("\\.");
            projectStructure.addPackageInvocation(sourceFile.getClassName(), package_arr[package_arr.length - 1], packageInvocationInformation);
        }
    }

    // TODO: ...
    public void processAttributeInvocation(ProjectStructure projectStructure, SourceFile sourceFile) {

    }

    // OK
    public void processConstructorInvocation(ProjectStructure projectStructure, SourceFile sourceFile) {
        sourceFile.getMethods().forEach(m -> {
            m.getConstructorInvocations().forEach(ci -> {
                ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation(sourceFile.getClassName(), (ArrayList<String>) ci.getArgumentsValues());
                projectStructure.addConstructorInvocation(sourceFile.getClassName(), ci.getConstructorClassName(), constructorInvocationInformation);
            });
        });
    }

    // OK
    public void processMethodInvocation(ProjectStructure projectStructure, SourceFile sourceFile) {
        SourceMethodProcessor sourceMethodProcessor = new SourceMethodProcessor();
        String className = sourceFile.getClassName();
        sourceFile.getMethods().forEach(m -> {
            m.getMethodCalls().forEach(mc -> {
                String[] calleeNames = mc.getCalleeName().split("\\.");
                MethodInvocation methodInvocationInformation = new MethodInvocation(calleeNames[calleeNames.length - 1], new ArrayList<>(mc.getArguments()));
                projectStructure.addMethodInvocation(sourceFile.getClassName(), calleeNames[calleeNames.length - 2], methodInvocationInformation);
            });
        });
    }

    public void processExternalInvocation(ProjectStructure projectStructure, SourceFile sourceFile) {
        ExternalInvocationInfo externalInvocationInfo = sourceFile.getExternalInvocationInfo();

        projectStructure.addExternalMethodInvocations(sourceFile.getClassName(), (ArrayList<String>) externalInvocationInfo.getExMethodCalls());
        projectStructure.addExternalConstructorInvocations(sourceFile.getClassName(), (ArrayList<String>) externalInvocationInfo.getExConstructorInvocations());
        projectStructure.addExternalAttributeDeclarations(sourceFile.getClassName(), (ArrayList<String>) externalInvocationInfo.getExFieldInvocation());
    }

}
