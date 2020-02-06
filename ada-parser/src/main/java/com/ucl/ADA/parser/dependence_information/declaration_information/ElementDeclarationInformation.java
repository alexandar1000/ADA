package com.ucl.ADA.parser.dependence_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public abstract class ElementDeclarationInformation {
    private String name;

    public ElementDeclarationInformation(String name) {
        this.name = name;
    }
}
