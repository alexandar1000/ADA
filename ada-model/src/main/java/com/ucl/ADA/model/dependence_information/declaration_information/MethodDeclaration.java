package com.ucl.ADA.model.dependence_information.declaration_information;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class MethodDeclaration extends ElementDeclaration {
    /**
     * The return type of the method.
     */
    private String returnType;

    /**
     * The access modifier assigned to the method.
     */
    private Set<ModifierType> modifierTypes = new HashSet<>();

    /**
     * The parameters which the method accepts.
     */
    private List<ParameterDeclaration> parameters = new ArrayList<>();

    /**
     * The constructor of the method declaration object.
     * @param modifierTypes the access modifier associated with the method
     * @param returnType the method return type
     * @param name name of the method
     * @param parameters the parameters which the method accepts
     */
    public MethodDeclaration(Set<ModifierType> modifierTypes, String returnType, String name, List<ParameterDeclaration> parameters) {
        super(name);
        this.modifierTypes.addAll(modifierTypes);
        this.returnType = returnType;
        this.parameters.addAll(parameters);
    }
}
