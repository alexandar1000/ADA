package com.ucl.ADA.parser.parser.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VariableDeclarationVisitor extends ASTVisitor {

    private Map<String, String> localVariables;

    public VariableDeclarationVisitor() {
        this.localVariables = new HashMap<>();
    }

    public Map<String, String> getLocalVariables() {
        return localVariables;
    }

    public boolean visit(VariableDeclarationStatement node) {
        for (Iterator iter = node.fragments().iterator(); iter.hasNext(); ) {
            VariableDeclarationFragment fragment = (VariableDeclarationFragment) iter.next();
            IVariableBinding binding = fragment.resolveBinding();
            if (binding != null) {
                //System.out.println(binding.getName() + "=" + binding.getType().getQualifiedName());
                localVariables.put(binding.getName(), binding.getType().getQualifiedName());
            }
        }
        return true;
    }
}
