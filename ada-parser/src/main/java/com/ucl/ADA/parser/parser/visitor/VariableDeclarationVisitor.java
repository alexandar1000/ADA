package com.ucl.ADA.parser.parser.visitor;

import lombok.Getter;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Getter
public class VariableDeclarationVisitor extends ASTVisitor {

    private Map<String, String> localVariables;


    /**
     * A constructor of VariableDeclarationVisitor
     */
    public VariableDeclarationVisitor() {
        this.localVariables = new HashMap<>();
    }


    /**
     * It visits the VariableDeclarationStatement node from the AST,
     * and populate a list of local variables.
     *
     * @param node  A VariableDeclarationStatement node derived from the AST.
     * @return true if it is required to visit the children node otherwise false
     */
    public boolean visit(VariableDeclarationStatement node) {
        for (Iterator iter = node.fragments().iterator(); iter.hasNext(); ) {
            VariableDeclarationFragment fragment = (VariableDeclarationFragment) iter.next();
            IVariableBinding binding = fragment.resolveBinding();
            if (binding != null) {
                localVariables.put(binding.getName(), binding.getType().getQualifiedName());
            }
        }
        return true;
    }
}
