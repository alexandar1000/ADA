package com.ucl.ADA.parser.dependence_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PackageDeclarationInformation extends ElementDeclarationInformation {
    /**
     * The constructor of the package declaration object.
     * @param packageName the name of the package being constructed
     */
    public PackageDeclarationInformation(String packageName) {
        super(packageName);
    }
}
