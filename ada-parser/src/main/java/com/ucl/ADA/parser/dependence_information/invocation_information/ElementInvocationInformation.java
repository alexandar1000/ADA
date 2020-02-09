package com.ucl.ADA.parser.dependence_information.invocation_information;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class ElementInvocationInformation {
    /**
     * The name of the invoked element.
     */
    private String name;

    public ElementInvocationInformation(String name) {
        this.name = name;
    }
}
