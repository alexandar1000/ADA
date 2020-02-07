package com.ucl.ADA.parser.dependence_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter @NoArgsConstructor
public class ConstructorDeclarationInformation extends ElementDeclarationInformation  {
    /**
     * The access modifier assigned to the constructor.
     */
    private AccessModifierType accessModifierType ;

    /**
     * List of the parameters which the constructor accepts.
     */
    private ArrayList<String> parameters = new ArrayList<>();

    /**
     * The constructor of the constructor declaration object.
     * @param accessModifierType the access modifier associated with the constructor
     * @param name name of the constructor
     * @param parameters the parameters in the constructor
     */
    public ConstructorDeclarationInformation(AccessModifierType accessModifierType, String name, ArrayList<String> parameters) {
        super(name);
        this.accessModifierType = accessModifierType;
        this.parameters.addAll(parameters);
    }
}
