package com.ucl.ADA.parser.dependence_information.declaration_information;

import java.util.ArrayList;

public class MethodDeclaration extends ElementDeclaration {
    /**
     * The return type of the method.
     */
    private String returnType;

    /**
     * The access modifier assigned to the method.
     */
    private ModifierType modifierType;

    /**
     * The parameters which the method accepts.
     */
    private ArrayList<String> parameters = new ArrayList<>();

    /**
     * The constructor of the method declaration object.
     * @param modifierType the access modifier associated with the method
     * @param returnType the method return type
     * @param name name of the method
     * @param parameters the parameters which the method accepts
     */
    public MethodDeclaration(ModifierType modifierType, String returnType, String name, ArrayList<String> parameters) {
        super(name);
        this.modifierType = modifierType;
        this.returnType = returnType;
        this.parameters.addAll(parameters);
    }
}
