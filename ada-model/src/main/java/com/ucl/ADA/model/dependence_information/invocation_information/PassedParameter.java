package com.ucl.ADA.model.dependence_information.invocation_information;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class PassedParameter {
    private String name;

    public PassedParameter(String name) {
        this.name = name;
    }

}
