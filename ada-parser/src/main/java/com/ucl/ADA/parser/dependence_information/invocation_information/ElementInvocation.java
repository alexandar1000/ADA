package com.ucl.ADA.parser.dependence_information.invocation_information;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class ElementInvocation {
    /**
     * The name of the invoked element.
     */
    private String name;

    public ElementInvocation(String name) {
        this.name = name;
    }
}
