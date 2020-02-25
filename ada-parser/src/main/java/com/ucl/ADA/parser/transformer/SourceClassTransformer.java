package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.model.dependence_information.declaration_information.*;
import com.ucl.ADA.model.dependence_information.invocation_information.*;
import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.parser.model.*;

import java.util.*;

class SourceClassTransformer {

    private ProjectStructure projectStructure;

    private ADAClass sourceClass;

    private String className;

    private Set<String> classNames;

    protected SourceClassTransformer(ProjectStructure projectStructure, ADAClass sourceClass, Set<String> classNames) {
        this.projectStructure = projectStructure;
        this.sourceClass = sourceClass;
        className = sourceClass.getClassName();
        this.classNames = classNames;
    }

    protected static Set<String> getClassNames(Set<ADAClass> sourceClasses) {
        Set<String> classNames = new HashSet<>();
        for (ADAClass sourceClass : sourceClasses)
            classNames.add(sourceClass.getClassName());
        return classNames;
    }

    protected void transformPackageDeclaration() {
        PackageDeclaration packageDeclaration = new PackageDeclaration(sourceClass.getPackageName());
        projectStructure.addPackageDeclaration(sourceClass.getClassName(), packageDeclaration);
    }

    protected void transformAttributeDeclaration() {
        for (ADAClassAttribute adaClassAttribute : sourceClass.getAdaClassAttributes()) {
            Set<ModifierType> modifierTypes = ModifierTransformer.getModifierTypes(adaClassAttribute.getModifiers());
            AttributeDeclaration attributeDeclaration = new AttributeDeclaration
                    (modifierTypes, adaClassAttribute.getType(), adaClassAttribute.getName(), adaClassAttribute.getValue());
            projectStructure.addAttributeDeclaration(className, attributeDeclaration);
        }
    }

    protected void transformConstructorAndMethodDeclaration() {
        for (ADAMethodOrConstructorDeclaration declaration : sourceClass.getADAMethodOrConstructorDeclaration()) {
            if (declaration.isConstructor()) {
                // constructor declaration
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

    protected void transformInAndExPackageInvocation() {
        Set<String> importedClasses = sourceClass.getImportedInternalClasses();

        for (String importClass : importedClasses) {
            // TODO: build project hierarchy tree to decode .*
            if (importClass.endsWith(".*")) continue;

            PackageInvocation packageInvocation = new PackageInvocation(importClass);
            if (classNames.contains(importClass)) {
                // internal package invocation
                projectStructure.addPackageInvocation(className, importClass, packageInvocation);
            } else {
                // external package invocation
                projectStructure.addExternalPackageImport(className, packageInvocation);
            }
        }
    }

    protected void transformAttributeInvocation() {
        // as class attributes
        for (ADAClassAttribute adaClassAttribute : sourceClass.getAdaClassAttributes()) {
            String type = adaClassAttribute.getType();
            if (classNames.contains(type)) {
                AttributeInvocation attributeInvocation = new AttributeInvocation(adaClassAttribute.getName());
                projectStructure.addAttributeInvocation(className, type, attributeInvocation);
            }
        }
        // as local variables
        for (ADAMethodOrConstructorDeclaration declaration: sourceClass.getADAMethodOrConstructorDeclaration()) {
            for (Map.Entry<String, String> entry : declaration.getLocalVariables().entrySet()) {
                if (classNames.contains(entry.getValue())) {
                    AttributeInvocation attributeInvocation = new AttributeInvocation(entry.getKey());
                    projectStructure.addAttributeInvocation(className, entry.getValue(), attributeInvocation);
                }
            }
        }
    }

    protected void transformConstructorInvocation() {
        for (ADAConstructorInvocation adaConstructorInvocation : sourceClass.getADAConstructorInvocations()) {
            List<PassedParameter> parameters = new ArrayList<>();
            for (String value : adaConstructorInvocation.getArguments())
                parameters.add(new PassedParameter(value));
            ConstructorInvocation constructorInvocation = new ConstructorInvocation(adaConstructorInvocation.getConstructorClassName(), parameters);
            projectStructure.addConstructorInvocation(className, adaConstructorInvocation.getConstructorClassName(), constructorInvocation);
        }
    }

    protected void transformMethodInvocation() {
        for (ADAMethodInvocation adaMethodInvocation : sourceClass.getADAMethodInvocations()) {
            List<PassedParameter> parameters = new ArrayList<>();
            for (String value : adaMethodInvocation.getArguments())
                parameters.add(new PassedParameter(value));
            MethodInvocation methodInvocation = new MethodInvocation(adaMethodInvocation.getMethodCallName(), parameters);
            projectStructure.addMethodInvocation(className, adaMethodInvocation.getCalleeName(), methodInvocation);
        }
    }

    protected void transformExternalInvocation() {
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
