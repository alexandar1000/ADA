package com.ucl.ADA.parser.parser.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PackageAndImportVisitor extends ASTVisitor {

    String packageName = "";
    Set<String> importedInternalClasses;
    Set<String> importedExternalClasses;

    public PackageAndImportVisitor() {
        importedInternalClasses = new HashSet<>();
        importedExternalClasses = new HashSet<>();
    }

    public String getPackageName() {
        return packageName;
    }

    public Set<String> getImportedInternalClasses() {
        return importedInternalClasses;
    }

    public Set<String> getImportedExternalClasses() {
        return importedExternalClasses;
    }

    // package declaration
    public boolean visit(PackageDeclaration node) {
        this.packageName = node.getName().toString();
        return true;
    }

    // import declaration
    public boolean visit(ImportDeclaration node) {
        IBinding binding = node.resolveBinding();
        if (binding != null) {
            importedInternalClasses.add(node.getName().toString());
        } else {
            importedExternalClasses.add(node.getName().toString());
        }
        return true;
    }


}
