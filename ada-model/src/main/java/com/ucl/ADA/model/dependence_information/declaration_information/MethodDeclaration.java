package com.ucl.ADA.model.dependence_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
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
    @Transient
    private Set<ModifierType> modifierTypes = new HashSet<>();

    /**
     * The parameters which the method accepts.
     */
    @Transient
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
