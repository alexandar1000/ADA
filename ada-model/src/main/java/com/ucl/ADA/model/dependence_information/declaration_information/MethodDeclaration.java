package com.ucl.ADA.model.dependence_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "METHOD_DECLARATION")
public class MethodDeclaration extends ElementDeclaration {
    /**
     * The return type of the method.
     */
    @Column(name = "return_type", nullable = false)
    private String returnType;

    /**
     * The access modifier assigned to the method.
     */
    @ElementCollection(targetClass = ModifierType.class)
    @CollectionTable(
            name = "METHOD_DECLARATION_MODIFIER_TYPE",
            joinColumns = @JoinColumn(name = "method_declaration_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "modifier_type")
    private Set<ModifierType> modifierTypes = new HashSet<>();

    /**
     * The parameters which the method accepts.
     */
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "METHOD_DECLARATION_PARAMETER_DECLARATION",
            joinColumns = @JoinColumn(name = "method_declaration_id"),
            inverseJoinColumns = @JoinColumn(name = "parameter_declaration_id")
    )
    private List<ParameterDeclaration> parameters = new ArrayList<>();

    /**
     * The constructor of the method declaration object.
     *
     * @param modifierTypes the access modifier associated with the method
     * @param returnType    the method return type
     * @param name          name of the method
     * @param parameters    the parameters which the method accepts
     */
    public MethodDeclaration(Set<ModifierType> modifierTypes, String returnType, String name, List<ParameterDeclaration> parameters) {
        super(name);
        if (modifierTypes != null) {
            this.modifierTypes.addAll(modifierTypes);
        }
        this.returnType = returnType;
        if (parameters != null) {
            this.parameters.addAll(parameters);
        }
    }
}
