package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.model.dependence_information.declaration_information.*;
import com.ucl.ADA.model.dependence_information.invocation_information.ConstructorInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.MethodInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.PackageInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.PassedParameter;
import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.parser.model.SourceFile;

import java.util.*;

public class SourceFileProcessor {

    private Set<ModifierType> modifiers = new HashSet<>(Collections.singletonList(
            ModifierType.DEFAULT
    ));
    private ArrayList<ParameterDeclaration> declaredParameters = new ArrayList<>(Arrays.asList(
            new ParameterDeclaration("String", "FirstParameter"),
            new ParameterDeclaration("Integer", "SecondParameter")
    ));

    private ArrayList<PassedParameter> passedParameterList0 = new ArrayList<>(Arrays.asList(
            new PassedParameter("FirstParameter0"),
            new PassedParameter("SecondParameter0")
    ));

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

            AttributeDeclaration attributeDeclarationInformation = new AttributeDeclaration(modifiers, a.getType(), a.getName(), a.getAssignedValue());
            projectStructure.addAttributeDeclaration(sourceFile.getClassName(), attributeDeclarationInformation);
        });

        sourceFile.getMethods().forEach(m -> {
            for (Map.Entry<String, String> localVariables : m.getLocalVariables().entrySet()) {
                String type = localVariables.getValue();
                String name = localVariables.getKey();
                // TODO: local variable no value
                // TODO: check at the ModifierType.DEFAULT in the line below
                AttributeDeclaration attributeDeclarationInformation = new AttributeDeclaration(modifiers, type, name, null);
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
            ConstructorDeclaration constructorDeclarationInformation = new ConstructorDeclaration(modifiers, c.getName(), declaredParameters);
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
                ConstructorInvocation constructorInvocationInformation = new ConstructorInvocation(sourceFile.getClassName(), passedParameterList0);
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
                MethodInvocation methodInvocationInformation = new MethodInvocation(calleeNames[calleeNames.length - 1], passedParameterList0);
                projectStructure.addMethodInvocation(sourceFile.getClassName(), calleeNames[calleeNames.length - 2], methodInvocationInformation);
            });
        });
    }

    public void processExternalInvocation(ProjectStructure projectStructure, SourceFile sourceFile) {
//        ExternalInvocationInfo externalInvocationInfo = sourceFile.getExternalInvocationInfo();
//
//        projectStructure.addExternalMethodInvocations(sourceFile.getClassName(), (ArrayList<String>) externalInvocationInfo.getExMethodCalls());
//        projectStructure.addExternalConstructorInvocations(sourceFile.getClassName(), (ArrayList<String>) externalInvocationInfo.getExConstructorInvocations());
//        projectStructure.addExternalAttributeDeclarations(sourceFile.getClassName(), (ArrayList<String>) externalInvocationInfo.getExFieldInvocation());
    }

}
