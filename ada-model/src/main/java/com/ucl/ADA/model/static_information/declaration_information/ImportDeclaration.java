package com.ucl.ADA.model.static_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImportDeclaration extends ElementDeclaration {

    /**
     * The constructor of the import declaration object.
     *
     * @param name the name of the import package
     */
    public ImportDeclaration(String name) {
        super(name);
    }
}
