package com.ucl.ADA.parser.parser.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.ArrayList;
import java.util.List;

public class TypeDeclatorVisitor extends ASTVisitor {

    List<TypeDeclaration> classes;

    public TypeDeclatorVisitor() {
        classes = new ArrayList<>();
    }

    public List<TypeDeclaration> getClasses() {
        return classes;
    }

    public boolean visit(TypeDeclaration typeDeclaration) {
        if (typeDeclaration.isPackageMemberTypeDeclaration()) {
            classes.add(typeDeclaration);
        }
        return true;
    }

}
