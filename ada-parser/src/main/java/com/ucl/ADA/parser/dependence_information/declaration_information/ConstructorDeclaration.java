package com.ucl.ADA.parser.dependence_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter @NoArgsConstructor
public class ConstructorDeclaration extends ElementDeclaration {
    /**
     * The access modifier assigned to the constructor.
     */
    private ModifierType modifierType;

    /**
     * List of the parameters which the constructor accepts.
     */
    private ArrayList<String> parameters = new ArrayList<>();

    /**
     * The constructor of the constructor declaration object.
     * @param modifierType the access modifier associated with the constructor
     * @param name name of the constructor
     * @param parameters the parameters in the constructor
     */
    public ConstructorDeclaration(ModifierType modifierType, String name, ArrayList<String> parameters) {
        super(name);
        this.modifierType = modifierType;
        this.parameters.addAll(parameters);
    }
}
