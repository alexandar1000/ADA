package com.ucl.ADA.model.dependence_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParameterDeclaration extends ElementDeclaration {

    /**
     * parameter type
     */
    private String type;

    /**
     * Constructor of parameter declaration
     *
     * @param type parameter type
     * @param name parameter name
     */
    public ParameterDeclaration(String type, String name) {
        super(name);
        this.type = type;
    }
}
