package com.ucl.ADA.model.dependence_information.invocation_information;

import com.ucl.ADA.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class ElementInvocation extends BaseEntity {

    /**
     * The name of the invoked element.
     */
    @Column(name = "element_name", nullable = false)
    private String name;

}
