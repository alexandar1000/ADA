package com.ucl.ADA.parser.parser.visitor;

import lombok.Getter;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;

import java.util.HashSet;
import java.util.Set;

@Getter
public class PackageAndImportVisitor extends ASTVisitor {

    private String packageName = "";
    private Set<String> importedInternalClasses;
    private Set<String> importedExternalClasses;

    /**
     * A constructor of PackageAndImportVisitor.
     */
    public PackageAndImportVisitor() {
        importedInternalClasses = new HashSet<>();
        importedExternalClasses = new HashSet<>();
    }

    /**
     * It visits the PackageDeclaration node from the AST and
     * retrieves the declared package name
     *
     * @param node A PackageDeclaration node derived from the AST.
     * @return true if it is required to visit the children node otherwise false
     */
    public boolean visit(PackageDeclaration node) {
        IBinding binding = node.resolveBinding();
        if (binding != null) {
            this.packageName = node.getName().toString();
        }
        return true;
    }

    /**
     * It visits the ImportDeclaration node from the AST and
     * retrieves the required imported packages names.
     *
     * @param node A ImportDeclaration node derived from the AST.
     * @return true if it is required to visit the children node otherwise false
     */
    public boolean visit(ImportDeclaration node) {
        IBinding binding = node.resolveBinding();
        if (binding != null) {
            String name = node.getName().toString();
            if (!name.startsWith("java"))
                importedInternalClasses.add(node.getName().toString());
        } else {
            importedExternalClasses.add(node.getName().toString());
        }
        return true;
    }


}
