package com.ucl.ADA.parser.dependence_information.declaration_information;

import java.util.ArrayList;

public class MethodDeclarationInformation extends ElementDeclarationInformation {
    /**
     * The return type of the method.
     */
    private String returnType;

    /**
     * The access modifier assigned to the method.
     */
    private AccessModifierType accessModifierType;

    /**
     * The parameters which the method accepts.
     */
    private ArrayList<String> parameters = new ArrayList<>();

    /**
     * The constructor of the method declaration object.
     * @param accessModifierType the access modifier associated with the method
     * @param returnType the method return type
     * @param name name of the method
     * @param parameters the parameters which the method accepts
     */
    public MethodDeclarationInformation(AccessModifierType accessModifierType, String returnType, String name, ArrayList<String> parameters) {
        super(name);
        this.accessModifierType = accessModifierType;
        this.returnType = returnType;
        this.parameters.addAll(parameters);
    }
}
