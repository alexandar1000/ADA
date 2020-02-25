package com.ucl.ADA.model.dependence_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public abstract class ElementDeclaration {
    /**
     * The name of the element.
     */
    private String elementName;

    /**
     * The constructor of the element declaration object.
     * @param elementName the name of the element being created
     */
    public ElementDeclaration(String elementName) {
        this.elementName = elementName;
    }
}
