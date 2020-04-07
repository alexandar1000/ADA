package com.ucl.ADA.parser.parser.visitor;

import lombok.Getter;
import org.eclipse.jdt.core.dom.*;

import java.util.*;

@Getter
public class VariableDeclarationVisitor extends ASTVisitor {

    private Map<String, String> localVariables;
    private final String[] primitives_types = {"byte", "short", "int", "float", "double", "long", "boolean", "char"};
    private final Set<String> PRIMITIVE_TYPES = new HashSet<String>(Arrays.asList(primitives_types));

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
     * @param node A VariableDeclarationStatement node derived from the AST.
     * @return true if it is required to visit the children node otherwise false
     */
    public boolean visit(VariableDeclarationStatement node) {
        for (Iterator iter = node.fragments().iterator(); iter.hasNext(); ) {
            VariableDeclarationFragment fragment = (VariableDeclarationFragment) iter.next();
            IVariableBinding binding = fragment.resolveBinding();
            ITypeBinding ibindig = node.getType().resolveBinding();
            if (binding != null && ibindig != null) {
                String type = ibindig.getQualifiedName();
                if ((!type.startsWith("java")) && (!PRIMITIVE_TYPES.contains(type))) {
                    localVariables.put(binding.getName(), type);
                }
            }
        }
        return true;
    }
}
