package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import com.ucl.ADA.parser.dependence_information.declaration_information.PackageDeclarationInformation;
import com.ucl.ADA.parser.model.SourceFile;

public class SourceFileProcessor {

    // OK
    public void processPackageDeclaration(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        PackageDeclarationInformation packageDeclarationInformation = new PackageDeclarationInformation(sourceFile.getPackageName());
        projectDependenceTree.addPackageDeclaration(sourceFile.getClassName(), packageDeclarationInformation);
    }

    // need more information on variables
    public void processAttributeDeclaration(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {

    }

    // no constructor information in SourceFile
    public void processConstructorDeclaration(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {

    }

    // OK
    public void processMethodDeclaration(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        SourceMethodProcessor sourceMethodProcessor = new SourceMethodProcessor();
        String className = sourceFile.getClassName();
        sourceFile.getMethods().forEach(m -> {
            sourceMethodProcessor.processMethodDeclaration(projectDependenceTree, className, m);
        });
    }

    // no import information in SourceFile
    public void processPackageInvocation(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {

    }

    // need more information on variables
    public void processAttributeInvocation(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {

    }

    // no info on constructor in SourceFile
    public void processConstructorInvocation(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {

    }

    // no info on parameter of method call
    // need to create MethodCall model which a list of parameters
    public void processMethodInvocation(ProjectDependenceTree projectDependenceTree, SourceFile sourceFile) {
        SourceMethodProcessor sourceMethodProcessor = new SourceMethodProcessor();
        String className = sourceFile.getClassName();
        sourceFile.getMethods().forEach(m -> {
//            sourceMethodProcessor.processMethodInvocation(projectDependenceTree, className, m);
        });
    }

}
