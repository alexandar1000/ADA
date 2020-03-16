package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.model.static_information.declaration_information.*;
import com.ucl.ADA.model.dependence_information.invocation_information.*;
import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.parser.ada_model.*;

import java.util.*;

class SourceClassTransformer {

    /**
     * the ProjectStructure object to hold all information of a source class
     */
    private ProjectStructure projectStructure;

    /**
     * parsed source class that contains raw information that needs to be processed
     */
    private ADAClass sourceClass;

    /**
     * source class qualified name
     */
    private String className;

    /**
     * all qualified class names under a project
     */
    private Set<String> classNames;

    /**
     * a utility class that read package names from qualified class names
     */
    private PackageBreaker packageBreaker;

    /**
     * Constructor of source class transformer
     *
     * @param projectStructure the ProjectStructure object to hold all data
     * @param sourceClass      the source class which holds parsed data
     * @param classNames       all qualified class names of a project
     * @param packageBreaker   a utility class
     */
    protected SourceClassTransformer(ProjectStructure projectStructure, ADAClass sourceClass, Set<String> classNames, PackageBreaker packageBreaker) {
        this.projectStructure = projectStructure;
        this.sourceClass = sourceClass;
        className = sourceClass.getClassName();
        this.classNames = classNames;
        this.packageBreaker = packageBreaker;
    }

    /**
     * get all class names from source classes
     *
     * @param sourceClasses all source classes
     * @return a set of class names
     */
    protected static Set<String> getClassNames(Set<ADAClass> sourceClasses) {
        Set<String> classNames = new HashSet<>();
        for (ADAClass sourceClass : sourceClasses) {
            classNames.add(sourceClass.getClassName());
        }
        return classNames;
    }

    /**
     * read package declaration from source class and add into the ProjectStructure object
     */
    protected void transformPackageDeclaration() {
        PackageDeclaration packageDeclaration = new PackageDeclaration(sourceClass.getPackageName());
        projectStructure.addPackageDeclaration(sourceClass.getClassName(), packageDeclaration);
    }

    /**
     * read attribute declaration from source class and add into the ProjectStructure object
     */
    protected void transformAttributeDeclaration() {
        for (ADAClassAttribute adaClassAttribute : sourceClass.getAdaClassAttributes()) {
            Set<ModifierType> modifierTypes = ModifierTransformer.getModifierTypes(adaClassAttribute.getModifiers());
            AttributeDeclaration attributeDeclaration = new AttributeDeclaration
                    (modifierTypes, adaClassAttribute.getType(), adaClassAttribute.getName(), adaClassAttribute.getValue());
            projectStructure.addAttributeDeclaration(className, attributeDeclaration);
        }
    }

    /**
     * read constructor and method declaration from source class and add into the ProjectStructure object
     */
    protected void transformConstructorAndMethodDeclaration() {
        for (ADAMethodOrConstructorDeclaration declaration : sourceClass.getADAMethodOrConstructorDeclaration()) {
            // get modifiers and parameters
            Set<ModifierType> modifierTypes = ModifierTransformer.getModifierTypes(declaration.getModifiers());
            List<ParameterDeclaration> parameters = new ArrayList<>();
            for (Map.Entry<String, String> entry : declaration.getParameters().entrySet()) {
                parameters.add(new ParameterDeclaration(entry.getKey(), entry.getValue()));
            }

            if (declaration.isConstructor()) {
                // constructor declaration
                ConstructorDeclaration constructorDeclaration = new ConstructorDeclaration
                        (modifierTypes, declaration.getName(), parameters);
                projectStructure.addConstructorDeclaration(className, constructorDeclaration);
            } else {
                // method declaration
                MethodDeclaration methodDeclaration = new MethodDeclaration
                        (modifierTypes, declaration.getReturnType(), declaration.getName(), parameters);
                projectStructure.addMethodDeclaration(className, methodDeclaration);
            }
        }
    }

    /**
     * read internal and external package declaration from source class and add into the ProjectStructure object
     */
    protected void transformInAndExPackageInvocation() {
        Set<String> importedClasses = sourceClass.getImportedInternalClasses();

        for (String importClass : importedClasses) {

            if (classNames.contains(importClass)) {
                // internal package invocation
                if (importClass.endsWith(".*")) {
                    // resolve .*
                    int p = className.lastIndexOf(".");
                    String packageName = className.substring(0, p);
                    Set<String> importNames = packageBreaker.getPackageContents().get(packageName);
                    for (String importName : importNames) {
                        PackageInvocation packageInvocation = new PackageInvocation(importName);
                        projectStructure.addPackageInvocation(className, importName, packageInvocation);
                    }
                } else {
                    // normal import class
                    PackageInvocation packageInvocation = new PackageInvocation(importClass);
                    projectStructure.addPackageInvocation(className, importClass, packageInvocation);
                }
            } else {
                // external package invocation
                PackageInvocation packageInvocation = new PackageInvocation(importClass);
                projectStructure.addExternalPackageImport(className, packageInvocation);
            }

        }
    }

    /**
     * read attribute invocation from source class and add into the ProjectStructure object
     */
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
        for (ADAMethodOrConstructorDeclaration declaration : sourceClass.getADAMethodOrConstructorDeclaration()) {
            for (Map.Entry<String, String> entry : declaration.getLocalVariables().entrySet()) {
                if (classNames.contains(entry.getValue())) {
                    AttributeInvocation attributeInvocation = new AttributeInvocation(entry.getKey());
                    projectStructure.addAttributeInvocation(className, entry.getValue(), attributeInvocation);
                }
            }
        }
    }

    /**
     * read constructor invocation from source class and add into the ProjectStructure object
     */
    protected void transformConstructorInvocation() {
        for (ADAConstructorInvocation adaConstructorInvocation : sourceClass.getADAConstructorInvocations()) {
            if (adaConstructorInvocation.getConstructorClassName().startsWith("java")) {
                continue;
            }
            List<PassedParameter> parameters = new ArrayList<>();
            for (String value : adaConstructorInvocation.getArguments()) {
                parameters.add(new PassedParameter(value));
            }
            String constructorFullName = adaConstructorInvocation.getConstructorClassName();
            String[] constructorNameArr = constructorFullName.split("\\.");
            ConstructorInvocation constructorInvocation = new ConstructorInvocation(constructorNameArr[constructorNameArr.length - 1], parameters);
            projectStructure.addConstructorInvocation(className, constructorFullName, constructorInvocation);
        }
    }

    /**
     * read method invocation from source class and add into the ProjectStructure object
     */
    protected void transformMethodInvocation() {
        for (ADAMethodInvocation adaMethodInvocation : sourceClass.getADAMethodInvocations()) {
            if (adaMethodInvocation.getCalleeName().startsWith("java")) {
                continue;
            }
            List<PassedParameter> parameters = new ArrayList<>();
            for (String value : adaMethodInvocation.getArguments()) {
                parameters.add(new PassedParameter(value));
            }
            MethodInvocation methodInvocation = new MethodInvocation(adaMethodInvocation.getMethodCallName(), parameters);
            projectStructure.addMethodInvocation(className, adaMethodInvocation.getCalleeName(), methodInvocation);
        }
    }

    /**
     * read external attribute, method and constructor invocation from source class and add into the ProjectStructure object
     */
    protected void transformExternalInvocation() {
        for (String exAttribute : sourceClass.getExFieldInvocation()) {
            AttributeInvocation attributeInvocation = new AttributeInvocation(exAttribute);
            projectStructure.addExternalAttributeDeclarations(className, attributeInvocation);
        }
        for (String exMethodCalls : sourceClass.getExMethodCalls()) {
            MethodInvocation methodInvocation = new MethodInvocation(exMethodCalls, new ArrayList<>(Collections.singletonList(new PassedParameter("parameter_placeholder"))));
            projectStructure.addExternalMethodInvocations(className, methodInvocation);
        }
        for (String exConstructor : sourceClass.getExConstructorInvocations()) {
            ConstructorInvocation constructorInvocation = new ConstructorInvocation(exConstructor, new ArrayList<>(Collections.singletonList(new PassedParameter("parameter_placeholder"))));
            projectStructure.addExternalConstructorInvocations(className, constructorInvocation);
        }
    }

}
