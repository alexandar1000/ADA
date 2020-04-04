package com.ucl.ADA.model.static_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CONSTRUCTOR_DECLARATION")
public class ConstructorDeclaration extends ElementDeclaration {

    /**
     * The access modifier assigned to the constructor.
     */
    @ElementCollection(targetClass = ModifierType.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "CONSTRUCTOR_DECLARATION_MODIFIER_TYPE",
            joinColumns = @JoinColumn(name = "constructor_declaration_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "modifier_type")
    private Set<ModifierType> modifierTypes;

    /**
     * List of the parameters which the constructor accepts.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "CONSTRUCTOR_DECLARATION_PARAMETER_DECLARATION",
            joinColumns = @JoinColumn(name = "constructor_declaration_id"),
            inverseJoinColumns = @JoinColumn(name = "parameter_declaration_id")
    )
    private List<ParameterDeclaration> parameters;

    /**
     * The constructor of the constructor declaration object.
     *
     * @param modifierTypes the set of access modifiers associated with the constructor
     * @param name          name of the constructor
     * @param parameters    the parameters in the constructor
     */
    public ConstructorDeclaration(Set<ModifierType> modifierTypes, String name, List<ParameterDeclaration> parameters) {
        super(name);
        this.modifierTypes = modifierTypes;
        this.parameters = parameters;
    }
}