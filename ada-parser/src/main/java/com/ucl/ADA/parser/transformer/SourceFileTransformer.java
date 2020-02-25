package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.model.dependence_information.declaration_information.*;
import com.ucl.ADA.model.dependence_information.invocation_information.*;
import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.parser.model.*;
import org.eclipse.jdt.internal.core.SourceMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SourceFileTransformer {

    private ProjectStructure projectStructure;

    private ADAClassModel sourceFile;

    protected SourceFileTransformer(ProjectStructure projectStructure, ADAClassModel sourceFile) {
        this.projectStructure = projectStructure;
        this.sourceFile = sourceFile;
    }

   /* protected void transformPackageDeclaration() {
        PackageDeclaration packageDeclaration = new PackageDeclaration(sourceFile.getPackageName());

        projectStructure.addPackageDeclaration(sourceFile.getClassName(), packageDeclaration);
    }

    protected void transformAttributeDeclaration() {
        String className = sourceFile.getClassName();

        for (ADAClassAttributeModel ADAClassAttributeModel : sourceFile.getClassAttributes()) {
            Set<ModifierType> modifierTypes = ModifierTransformer.getModifierTypes(ADAClassAttributeModel.getModifiers());

            AttributeDeclaration attributeDeclaration = new AttributeDeclaration
                    (modifierTypes, ADAClassAttributeModel.getType(), ADAClassAttributeModel.getName(), ADAClassAttributeModel.getValue());

            projectStructure.addAttributeDeclaration(className, attributeDeclaration);
        }
    }

    protected void processConstructorDeclaration() {
        String className = sourceFile.getClassName();

        for (SourceConstructor sourceConstructor : sourceFile.getDeclaredSourceConstructors()) {
            Set<ModifierType> modifierTypes = ModifierTransformer.getModifierTypes(sourceConstructor.getModifiers());

            List<ParameterDeclaration> parameters = new ArrayList<>();
            for (Map.Entry<String, String> entry : sourceConstructor.getParameters().entrySet())
                parameters.add(new ParameterDeclaration(entry.getKey(), entry.getValue()));

            ConstructorDeclaration constructorDeclaration = new ConstructorDeclaration
                    (modifierTypes, sourceConstructor.getName(), parameters);

            projectStructure.addConstructorDeclaration(className, constructorDeclaration);
        }
    }

    protected void processMethodDeclaration() {
        String className = sourceFile.getClassName();

        for (SourceMethod sourceMethod : sourceFile.getMethods()) {
            Set<ModifierType> modifierTypes = ModifierTransformer.getModifierTypes(sourceMethod.getModifiers());

            List<ParameterDeclaration> parameters = new ArrayList<>();
            for (Map.Entry<String, String> entry : sourceMethod.getParameters().entrySet())
                parameters.add(new ParameterDeclaration(entry.getKey(), entry.getValue()));

            MethodDeclaration methodDeclaration = new MethodDeclaration
                    (modifierTypes, sourceMethod.getReturnType(), sourceMethod.getName(), parameters);

            projectStructure.addMethodDeclaration(className, methodDeclaration);
        }
    }

    protected void processPackageInvocation() {
        Set<String> importedClasses = sourceFile.getImportedClasses();
        if (importedClasses.isEmpty()) return;

        String className = sourceFile.getClassName();

        for (String importClass : importedClasses) {
            // import class should not end with .*
            assert !importClass.endsWith(".*");

            PackageInvocation packageInvocation = new PackageInvocation(importClass);

            projectStructure.addPackageInvocation(className, importClass, packageInvocation);
        }
    }

    // TODO: Talk to RAKIB
    protected void processAttributeInvocation() {

    }

    protected void processConstructorInvocation() {
        String className = sourceFile.getClassName();

        for (SourceMethod sourceMethod : sourceFile.getMethods()) {
            for (SourceConstructorInvocation ci : sourceMethod.getSourceConstructorInvocations()) {
                List<PassedParameter> parameters = new ArrayList<>();
                for (String value : ci.getArguments())
                    parameters.add(new PassedParameter(value));

                ConstructorInvocation constructorInvocation = new ConstructorInvocation(className, parameters);

                projectStructure.addConstructorInvocation(className, ci.getConstructorClassName(), constructorInvocation);
            }
        }
    }

    protected void processMethodInvocation() {
        String className = sourceFile.getClassName();

        for (SourceMethod sourceMethod : sourceFile.getMethods()) {
            for (ADAMethodCallModel ADAMethodCallModel : sourceMethod.getMethodCalls()) {
                List<PassedParameter> parameters = new ArrayList<>();
                for (String value : ADAMethodCallModel.getArguments())
                    parameters.add(new PassedParameter(value));

                MethodInvocation methodInvocation = new MethodInvocation(ADAMethodCallModel.getMethodCallName(), parameters);

                projectStructure.addMethodInvocation(className, ADAMethodCallModel.getCalleeName(), methodInvocation);
            }
        }
    }

    protected void processExternalInvocation() {
        String className = sourceFile.getClassName();

        ExternalInvocationInfo externalInvocationInfo = sourceFile.getExternalInvocationInfo();

        for (String exImport : externalInvocationInfo.getExImports()) {
            PackageInvocation packageInvocation = new PackageInvocation(exImport);
            projectStructure.addExternalPackageImport(className, packageInvocation);
        }
        for (String exAttribute : externalInvocationInfo.getExFieldInvocation()) {
            AttributeInvocation attributeInvocation = new AttributeInvocation(exAttribute);
            projectStructure.addExternalAttributeDeclarations(className, attributeInvocation);
        }
        // TODO: GOTO ExternalInvocationInfo class
        for (String exMethodCalls : externalInvocationInfo.getExMethodCalls()) {
            MethodInvocation methodInvocation = new MethodInvocation(exMethodCalls, null);
            projectStructure.addExternalMethodInvocations(className, methodInvocation);
        }
        // TODO: GOTO ExternalInvocationInfo class
        for (String exConstructor : externalInvocationInfo.getExConstructorInvocations()) {
            ConstructorInvocation constructorInvocation = new ConstructorInvocation(exConstructor, null);
            projectStructure.addExternalConstructorInvocations(className, constructorInvocation);
        }
    }*/

}
