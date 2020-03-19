package com.ucl.ADA.model.static_information.declaration_information;

import com.ucl.ADA.model.base_entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class ElementDeclaration extends BaseEntity {

    /**
     * The name of the element declaration.
     */
    private String name;

}
