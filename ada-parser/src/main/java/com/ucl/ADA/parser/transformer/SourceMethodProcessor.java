package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import com.ucl.ADA.parser.dependence_information.declaration_information.AccessModifierType;
import com.ucl.ADA.parser.dependence_information.declaration_information.MethodDeclarationInformation;
import com.ucl.ADA.parser.model.SourceMethod;

import java.util.ArrayList;

public class SourceMethodProcessor {

    public void processMethodDeclaration(ProjectDependenceTree projectDependenceTree, String className, SourceMethod sourceMethod) {
        AccessModifierType accessModifierType = null;
        for (String am : sourceMethod.getAccessModifiers()) {
            if (am.equals("public")) {
                accessModifierType = AccessModifierType.PUBLIC;
                break;
            } else if (am.equals("private")) {
                accessModifierType = AccessModifierType.PRIVATE;
                break;
            } else if (am.equals("protected")) {
                accessModifierType = AccessModifierType.PROTECTED;
                break;
            } else if (am.equals("")) {
                accessModifierType = AccessModifierType.DEFAULT;
                break;
            }
        }

        ArrayList<String> parameters = new ArrayList<>();
        sourceMethod.getParameters().forEach((name, type) -> {
            parameters.add(type + " " + name);
        });

        MethodDeclarationInformation methodDeclarationInformation = new MethodDeclarationInformation(accessModifierType, sourceMethod.getReturnType(), sourceMethod.getName(), parameters);

        projectDependenceTree.addMethodDeclaration(className, methodDeclarationInformation);
    }

//    public void processMethodInvocation(ProjectDependenceTree projectDependenceTree, String className, SourceMethod sourceMethod) {
//        MethodCallProcessor methodCallProcessor = new MethodCallProcessor();
//
//        sourceMethod.getMethodCalls().forEach(mc -> {
//            methodCallProcessor.processMethodCall();
//        });
//
//
//    }

}
