package com.ucl.ADA.parser.dependence_information.invocation_information;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class ElementInvocationInformation {
    private String name;

    public ElementInvocationInformation(String name) {
        this.name = name;
    }
}
