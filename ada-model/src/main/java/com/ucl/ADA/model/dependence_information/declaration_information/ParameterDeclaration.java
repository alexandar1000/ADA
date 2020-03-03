package com.ucl.ADA.model.dependence_information.declaration_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PARAMETER_DECLARATION")
public class ParameterDeclaration extends ElementDeclaration {

    @Column(name = "type", nullable = false)
    private String type;

    public ParameterDeclaration(String type, String name) {
        super(name);
        this.type = type;
    }
}
