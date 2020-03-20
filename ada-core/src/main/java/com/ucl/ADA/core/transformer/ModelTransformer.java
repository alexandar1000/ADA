package com.ucl.ADA.core.transformer;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.ucl.ADA.model.dependence_information.invocation_information.AttributeInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.ConstructorInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.MethodInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.PassedParameter;
import com.ucl.ADA.model.snapshot.Snapshot;
import com.ucl.ADA.model.static_information.declaration_information.*;
import com.ucl.ADA.parser.ada_model.*;

import java.util.*;

/**
 * the class transform the information in data structures of parser module into ada-model
 */
public class ModelTransformer {

    /* ************************************************************************
     *
     *  transform information for the reuse of snapshot and class structure
     *
     **************************************************************************/

    /**
     * transform the ADAClass into the qualified class name of the ADAClass for the given data structure returned by
     * parsing service
     *
     * @param filePathToClassStructuresMap a map where the key is the file path, and the value is a set of ADAClass
     *                                     which is parsed from the key source file
     * @return a map where the kay is the file path, and the value is the set of qualified class names in the source
     * file
     */
    public static SetMultimap<String, String> getFilePathToClassNamesMap(SetMultimap<String, ADAClass> filePathToClassStructuresMap) {
        SetMultimap<String, String> filePathToClassNamesMap = MultimapBuilder.hashKeys().hashSetValues().build();
        for (String filePath : filePathToClassStructuresMap.keySet()) {
            Set<ADAClass> adaClasses = filePathToClassStructuresMap.get(filePath);
            for (ADAClass adaClass : adaClasses) {
                filePathToClassNamesMap.put(filePath, adaClass.getClassName());
            }
        }
        return filePathToClassNamesMap;
    }

    /**
     * get the set of qualified name of all classes affected by classes in added source files
     *
     * @param filePathToClassStructuresMap a map where the key is the path to a source file, and the value is a set of
     *                                     ADAClass received from the parsing service
     * @return the set of qualified name of all classes affected by classes in added source files
     */
    public static Set<String> getIncomingToAddSet(SetMultimap<String, ADAClass> filePathToClassStructuresMap) {
        Set<String> incomingToAddSet = new HashSet<>();

        for (ADAClass adaClass : filePathToClassStructuresMap.values()) {
            // get affected class from constructor invocation
            for (ADAConstructorInvocation adaConstructorInvocation : adaClass.getADAConstructorInvocations()) {
                String constructorFullName = adaConstructorInvocation.getConstructorClassName();
                if (!constructorFullName.startsWith("java")) {
                    incomingToAddSet.add(constructorFullName);
                }
            }
            // get affected class from method invocation
            for (ADAMethodInvocation adaMethodInvocation : adaClass.getADAMethodInvocations()) {
                String calleeName = adaMethodInvocation.getCalleeName();
                if (!calleeName.startsWith("java")) {
                    incomingToAddSet.add(calleeName);
                }
            }
            // get affected class from attribute invocation
            // as class attributes
            for (ADAClassAttribute adaClassAttribute : adaClass.getAdaClassAttributes()) {
                incomingToAddSet.add(adaClassAttribute.getType());
            }
            // as local variables
            for (ADAMethodOrConstructorDeclaration declaration : adaClass.getADAMethodOrConstructorDeclaration()) {
                for (Map.Entry<String, String> entry : declaration.getLocalVariables().entrySet()) {
                    incomingToAddSet.add(entry.getValue());
                }
            }
        }
        return incomingToAddSet;
    }

    /* ************************************************************************
     *
     *  transform the information for updating the class structures in snapshot
     *
     **************************************************************************/

    /**
     * transform information in ADAClass into snapshot
     *
     * @param snapshot      the snapshot that has all class structures initialized
     * @param sourceClasses the set of ADAClass to transform
     */
    public static void transform(Snapshot snapshot, Set<ADAClass> sourceClasses) {
        for (ADAClass sourceClass : sourceClasses) {
            transformPackageDeclaration(sourceClass);
            transformAttributeDeclaration(sourceClass);
            transformConstructorAndMethodDeclaration(sourceClass);
            transformAttributeInvocation(sourceClass);
            transformConstructorInvocation(sourceClass);
            transformMethodInvocation(sourceClass);
            transformExternalInvocation(sourceClass);
        }
    }

    /**
     * transfer a set of string modifiers to a set of ModifierType enum
     *
     * @param modifiers a set of string modifiers
     * @return a set of ModifierType enum
     */
    protected static Set<ModifierType> getModifierTypes(Set<String> modifiers) {
        Set<ModifierType> modifierTypes = new HashSet<>();

        if (modifiers.contains("public")) modifierTypes.add(ModifierType.PUBLIC);
        else if (modifiers.contains("private")) modifierTypes.add(ModifierType.PRIVATE);
        else if (modifiers.contains("protected")) modifierTypes.add(ModifierType.PROTECTED);
        else if (modifiers.contains("default")) modifierTypes.add(ModifierType.DEFAULT);
        else if (modifiers.contains("static")) modifierTypes.add(ModifierType.STATIC);
        else if (modifiers.contains("final")) modifierTypes.add(ModifierType.FINAL);
        else if (modifiers.contains("abstract")) modifierTypes.add(ModifierType.ABSTRACT);
        else modifierTypes.add(ModifierType.UNRESOLVED);

        return modifierTypes;
    }

    // TODO: update all javadoc below

    public static void transformImportDeclaration(ADAClass sourceClass) {
        String className = sourceClass.getClassName();

        // TODO: replace imported internal class in parser module
        for (String importDeclaration : sourceClass.getImportedInternalClasses()) {
            ImportDeclaration importDeclaration1 = new ImportDeclaration(importDeclaration);

        }
    }

    /**
     * read package declaration from source class and add into the ProjectStructure object
     */
    public static void transformPackageDeclaration(ADAClass sourceClass) {
        String className = sourceClass.getClassName();

        PackageDeclaration packageDeclaration = new PackageDeclaration(sourceClass.getPackageName());
        projectStructure.addPackageDeclaration(sourceClass.getClassName(), packageDeclaration);
    }

    /**
     * read attribute declaration from source class and add into the ProjectStructure object
     */
    public static void transformAttributeDeclaration(ADAClass sourceClass) {
        String className = sourceClass.getClassName();

        for (ADAClassAttribute adaClassAttribute : sourceClass.getAdaClassAttributes()) {
            Set<ModifierType> modifierTypes = getModifierTypes(adaClassAttribute.getModifiers());
            AttributeDeclaration attributeDeclaration = new AttributeDeclaration
                    (modifierTypes, adaClassAttribute.getType(), adaClassAttribute.getName(), adaClassAttribute.getValue());
            projectStructure.addAttributeDeclaration(className, attributeDeclaration);
        }
    }

    /**
     * read constructor and method declaration from source class and add into the ProjectStructure object
     */
    public static void transformConstructorAndMethodDeclaration(ADAClass sourceClass) {
        String className = sourceClass.getClassName();

        for (ADAMethodOrConstructorDeclaration declaration : sourceClass.getADAMethodOrConstructorDeclaration()) {
            // get modifiers and parameters
            Set<ModifierType> modifierTypes = getModifierTypes(declaration.getModifiers());
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
     * read attribute invocation from source class and add into the ProjectStructure object
     */
    public static void transformAttributeInvocation(ADAClass sourceClass) {
        String className = sourceClass.getClassName();

        // as class attributes
        for (ADAClassAttribute adaClassAttribute : sourceClass.getAdaClassAttributes()) {
            String type = adaClassAttribute.getType();
            AttributeInvocation attributeInvocation = new AttributeInvocation(adaClassAttribute.getName());
            projectStructure.addAttributeInvocation(className, type, attributeInvocation);
        }
        // as local variables
        for (ADAMethodOrConstructorDeclaration declaration : sourceClass.getADAMethodOrConstructorDeclaration()) {
            for (Map.Entry<String, String> entry : declaration.getLocalVariables().entrySet()) {
                AttributeInvocation attributeInvocation = new AttributeInvocation(entry.getKey());
                projectStructure.addAttributeInvocation(className, entry.getValue(), attributeInvocation);
            }
        }
    }

    /**
     * read constructor invocation from source class and add into the ProjectStructure object
     */
    public static void transformConstructorInvocation(ADAClass sourceClass) {
        String className = sourceClass.getClassName();

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
    public static void transformMethodInvocation(ADAClass sourceClass) {
        String className = sourceClass.getClassName();

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
     * read external attribute, method and constructor invocation from source class and add into the ProjectStructure
     * object
     */
    public static void transformExternalInvocation(ADAClass sourceClass) {
        String className = sourceClass.getClassName();

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
