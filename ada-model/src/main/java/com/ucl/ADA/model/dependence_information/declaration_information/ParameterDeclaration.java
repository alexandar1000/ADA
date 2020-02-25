package com.ucl.ADA.model.dependence_information.declaration_information;

import lombok.Getter;

@Getter
public class ParameterDeclaration extends ElementDeclaration {
    private String type;

    public ParameterDeclaration(String name, String type) {
        super(name);
        this.type = type;
    }
}
