package com.ucl.ADA.model.dependence_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public abstract class ElementDeclaration {
    /**
     * The name of the element.
     */
    private String name;

    /**
     * The constructor of the element declaration object.
     * @param name the name of the element being created
     */
    public ElementDeclaration(String name) {
        this.name = name;
    }
}
