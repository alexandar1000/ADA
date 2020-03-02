package com.ucl.ADA.parser.parser.visitor;

import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.List;

public class TypeDeclarationVisitor extends ASTVisitor {

    List<TypeDeclaration> classes;
    List<EnumDeclaration> enums;


    public TypeDeclarationVisitor() {
        classes = new ArrayList<>();
        enums = new ArrayList<>();
    }

    public List<TypeDeclaration> getClasses() {
        return classes;
    }

    public List<EnumDeclaration> getEnums() {
        return enums;
    }

    public List<AbstractTypeDeclaration> getAbstractTypeDeclaration() {
        List<AbstractTypeDeclaration> absList = new ArrayList<>();
        absList.addAll(this.classes);
        absList.addAll(this.enums);
        return absList;
    }

    public boolean visit(TypeDeclaration typeDeclaration) {
        if (typeDeclaration.isPackageMemberTypeDeclaration()) {
            classes.add(typeDeclaration);
        }
        return true;
    }

    public boolean visit(EnumDeclaration enumDeclaration) {
        if (enumDeclaration.isPackageMemberTypeDeclaration()) {
            enums.add(enumDeclaration);
        }
        return true;
    }
}
