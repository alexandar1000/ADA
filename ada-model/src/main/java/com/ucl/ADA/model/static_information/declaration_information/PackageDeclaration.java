package com.ucl.ADA.model.static_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PACKAGE_DECLARATION")
public class PackageDeclaration extends ElementDeclaration {

    /**
     * The constructor of the package declaration object. I.e.
     * "com.ucl.ADA.parser.dependence_information.declaration_information"
     *
     * @param name the name of the package being constructed
     */
    public PackageDeclaration(String name) {
        super(name);
    }

}
