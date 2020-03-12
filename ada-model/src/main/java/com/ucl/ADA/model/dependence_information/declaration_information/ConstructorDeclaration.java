package com.ucl.ADA.model.dependence_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ConstructorDeclaration extends ElementDeclaration {

    /**
     * The access modifier assigned to the constructor.
     */
    private Set<ModifierType> modifierTypes = new HashSet<>();

    /**
     * List of the parameters which the constructor accepts.
     */
    private List<ParameterDeclaration> parameters = new ArrayList<>();

    /**
     * The constructor of the constructor declaration object.
     *
     * @param modifierTypes the set of access modifiers associated with the constructor
     * @param name          name of the constructor
     * @param parameters    the parameters in the constructor
     */
    public ConstructorDeclaration(Set<ModifierType> modifierTypes, String name, List<ParameterDeclaration> parameters) {
        super(name);
        if (modifierTypes != null) {
            this.modifierTypes.addAll(modifierTypes);
        }
        if (parameters != null) {
            this.parameters.addAll(parameters);
        }
    }
}