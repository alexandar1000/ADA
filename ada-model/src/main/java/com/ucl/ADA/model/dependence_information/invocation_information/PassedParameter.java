package com.ucl.ADA.model.dependence_information.invocation_information;

import com.ucl.ADA.model.base_entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PassedParameter extends BaseEntity {

    /**
     * the message of the passed parameter
     */
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
