package com.ucl.ADA.model.dependence_information.invocation_information;

import com.ucl.ADA.model.base_entity.BaseEntity;
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
@Table(name = "PASSED_PARAMETER")
public class PassedParameter extends BaseEntity {

    /**
     * the message of the passed parameter
     */
    @Column(name = "name")
    private String name;

    /**
     * Constructor of the passed parameter
     *
     * @param name message of the passed parameter
     */
    public PassedParameter(String name) {
        this.name = name;
    }

}
