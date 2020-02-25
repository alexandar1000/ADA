package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.model.dependence_information.declaration_information.*;
import com.ucl.ADA.model.dependence_information.invocation_information.*;
import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.parser.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SourceFileTransformer {

    private ProjectStructure projectStructure;

    private ADAClass sourceClass;

    protected SourceFileTransformer(ProjectStructure projectStructure, ADAClass sourceClass) {
        this.projectStructure = projectStructure;
        this.sourceClass = sourceClass;
    }

    protected void transformPackageDeclaration() {
        PackageDeclaration packageDeclaration = new PackageDeclaration(sourceClass.getPackageName());

        projectStructure.addPackageDeclaration(sourceClass.getClassName(), packageDeclaration);
    }

    protected void transformAttributeDeclaration() {
        String className = sourceClass.getClassName();

        for (ADAClassAttribute ADAClassAttribute : sourceClass.getAdaClassAttributes()) {
            Set<ModifierType> modifierTypes = ModifierTransformer.getModifierTypes(ADAClassAttribute.getModifiers());

            AttributeDeclaration attributeDeclaration = new AttributeDeclaration
                    (modifierTypes, ADAClassAttribute.getType(), ADAClassAttribute.getName(), ADAClassAttribute.getValue());

            projectStructure.addAttributeDeclaration(className, attributeDeclaration);
        }
    }

    protected void processConstructorAndMethodDeclaration() {
        String className = sourceClass.getClassName();

        for (ADAMethodOrConstructorDeclaration declaration : sourceClass.getADAMethodOrConstructorDeclaration()) {
            // constructor declaration
            if (declaration.isConstructor()) {
                Set<ModifierType> modifierTypes = ModifierTransformer.getModifierTypes(declaration.getModifiers());

                List<ParameterDeclaration> parameters = new ArrayList<>();
                for (Map.Entry<String, String> entry : declaration.getParameters().entrySet())
                    parameters.add(new ParameterDeclaration(entry.getKey(), entry.getValue()));

                ConstructorDeclaration constructorDeclaration = new ConstructorDeclaration
                        (modifierTypes, declaration.getName(), parameters);

                projectStructure.addConstructorDeclaration(className, constructorDeclaration);
            } else {
                // method declaration
                Set<ModifierType> modifierTypes = ModifierTransformer.getModifierTypes(declaration.getModifiers());

                List<ParameterDeclaration> parameters = new ArrayList<>();
                for (Map.Entry<String, String> entry : declaration.getParameters().entrySet())
                    parameters.add(new ParameterDeclaration(entry.getKey(), entry.getValue()));

                MethodDeclaration methodDeclaration = new MethodDeclaration
                        (modifierTypes, declaration.getReturnType(), declaration.getName(), parameters);

                projectStructure.addMethodDeclaration(className, methodDeclaration);
            }
        }
    }

    protected void processPackageInvocation() {
        Set<String> importedClasses = sourceClass.getImportedInternalClasses();
        if (importedClasses.isEmpty()) return;

        String className = sourceClass.getClassName();

        for (String importClass : importedClasses) {
            // import class should not end with .*
            assert !importClass.endsWith(".*");

            PackageInvocation packageInvocation = new PackageInvocation(importClass);

            projectStructure.addPackageInvocation(className, importClass, packageInvocation);
        }
    }

    // TODO: look up information in models
    protected void processAttributeInvocation() {

    }

    protected void processConstructorInvocation() {
        String className = sourceClass.getClassName();

        for (ADAConstructorInvocation adaConstructorInvocation : sourceClass.getADAConstructorInvocations()) {
            List<PassedParameter> parameters = new ArrayList<>();
            for (String value : adaConstructorInvocation.getArguments())
                parameters.add(new PassedParameter(value));

            ConstructorInvocation constructorInvocation = new ConstructorInvocation(className, parameters);

            projectStructure.addConstructorInvocation(className, adaConstructorInvocation.getConstructorClassName(), constructorInvocation);
        }
    }

    protected void processMethodInvocation() {
        String className = sourceClass.getClassName();

        for (ADAMethodInvocation adaMethodInvocation : sourceClass.getADAMethodInvocations()) {
            List<PassedParameter> parameters = new ArrayList<>();
            for (String value : adaMethodInvocation.getArguments())
                parameters.add(new PassedParameter(value));

            MethodInvocation methodInvocation = new MethodInvocation(adaMethodInvocation.getMethodCallName(), parameters);

            projectStructure.addMethodInvocation(className, adaMethodInvocation.getCalleeName(), methodInvocation);
        }
    }

    protected void processExternalInvocation() {
        String className = sourceClass.getClassName();

        // TODO: add external invocation
//        for (String exImport : sourceClass.) {
//            PackageInvocation packageInvocation = new PackageInvocation(exImport);
//            projectStructure.addExternalPackageImport(className, packageInvocation);
//        }
        for (String exAttribute : sourceClass.getExFieldInvocation()) {
            AttributeInvocation attributeInvocation = new AttributeInvocation(exAttribute);
            projectStructure.addExternalAttributeDeclarations(className, attributeInvocation);
        }
        for (String exMethodCalls : sourceClass.getExMethodCalls()) {
            MethodInvocation methodInvocation = new MethodInvocation(exMethodCalls, null);
            projectStructure.addExternalMethodInvocations(className, methodInvocation);
        }
        for (String exConstructor : sourceClass.getExConstructorInvocations()) {
            ConstructorInvocation constructorInvocation = new ConstructorInvocation(exConstructor, null);
            projectStructure.addExternalConstructorInvocations(className, constructorInvocation);
        }
    }

}
