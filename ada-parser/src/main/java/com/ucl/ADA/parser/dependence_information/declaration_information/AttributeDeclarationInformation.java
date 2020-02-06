package com.ucl.ADA.parser.dependence_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class AttributeDeclarationInformation extends ElementDeclarationInformation  {
    /**
     * The type of the attribute.
     */
    private String type;

    /**
     * The value assigned to the attribute.
     */
    private String value;

    /**
     * The access modifier assigned to the attribute.
     */
    private AccessModifierType accessModifierType ;

    /**
     * Defines whether the attribute declaration is for the entire class (isLocalDeclaration == false), or if it is
     * a local declaration (isLocalDeclaration == true)
     */
    private boolean isLocalDeclaration;

    /**
     * The constructor of the attribute declaration object.
     * @param accessModifierType the access modifier associated with the attribute
     * @param type the type of the attribute
     * @param name name of the attribute
     * @param value the value assigned to the attribute
     * @param isLocalDeclaration describes whether this is a local or class-wide attribute declaration
     */
    public AttributeDeclarationInformation(AccessModifierType accessModifierType, String type, String name, String value, boolean isLocalDeclaration) {
        super(name);
        this.accessModifierType = accessModifierType;
        this.type = type;
        this.value = value;
        this.isLocalDeclaration = isLocalDeclaration;
    }
}
