package com.ucl.ADA.parser.parser.visitor;

import lombok.Getter;
import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TypeDeclarationVisitor extends ASTVisitor {

    private List<TypeDeclaration> classes;
    private List<EnumDeclaration> enums;

    /**
     * A constructor of TypeDeclarationVisitor
     */

    public TypeDeclarationVisitor() {
        this.classes = new ArrayList<>();
        this.enums = new ArrayList<>();
    }

    /**
     * It collects all the class types and enums type declared in the AST and
     * makes a list of class types and Enum types
     *
     * @return A list of AbstractTypeDeclaration containing all class types and Enum types
     */
    public List<AbstractTypeDeclaration> getAbstractTypeDeclaration() {
        List<AbstractTypeDeclaration> absList = new ArrayList<>();
        absList.addAll(this.classes);
        absList.addAll(this.enums);
        return absList;
    }


    /**
     * It visits the TypeDeclaration node from the AST, then
     * check weather it is an TypeDeclaration (class) type or
     * not and populate a list of class type Declaration.
     *
     * @param node  A TypeDeclaration node derived from the AST.
     * @return true if it is required to visit the children node otherwise false
     */
    public boolean visit(TypeDeclaration node) {
        if (node.isPackageMemberTypeDeclaration()) {
            this.classes.add(node);
        }
        return true;
    }


    /**
     * It visits the EnumDeclaration node from the AST, then
     * check weather it is an Enum type or not and populate a list of Enum type declaration.
     *
     * @param node  A EnumDeclaration node derived from the AST.
     * @return true if it is required to visit the children node otherwise false
     */
    public boolean visit(EnumDeclaration node) {
        if (node.isPackageMemberTypeDeclaration()) {
            this.enums.add(node);
        }
        return true;
    }
}
