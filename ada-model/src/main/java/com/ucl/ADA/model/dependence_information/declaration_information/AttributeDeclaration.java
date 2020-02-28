package com.ucl.ADA.model.dependence_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ATTRIBUTE_DECLARATION")
public class AttributeDeclaration extends ElementDeclaration {
    /**
     * The type of the attribute.
     */
    @Column(name = "type", nullable = false)
    private String type;

    /**
     * The value assigned to the attribute.
     */
    @Column(name = "value")
    private String value;

    /**
     * The access modifier assigned to the attribute.
     */
    @ManyToMany(cascade = CascadeType.PERSIST, targetEntity = ModifierType.class)
    @JoinTable(
            name = "ATTRIBUTE_DECLARATION_MODIFIER_TYPE",
            joinColumns = @JoinColumn(name = "attribute_declaration_id"),
            inverseJoinColumns = @JoinColumn(name = "modifier_type_id")
    )
    private Set<ModifierType> modifierTypes = new HashSet<>();

    /**
     * The constructor of the attribute declaration object.
     * @param modifierTypes the set of access modifiers associated with the attribute
     * @param type the type of the attribute
     * @param name name of the attribute
     * @param value the value assigned to the attribute
     */
    public AttributeDeclaration(Set<ModifierType> modifierTypes, String type, String name, String value) {
        super(name);
        if (modifierTypes != null) {
            this.modifierTypes.addAll(modifierTypes);
        }
        this.type = type;
        this.value = value;
    }
}
