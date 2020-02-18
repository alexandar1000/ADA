package com.ucl.ADA.model.dependence_information.invocation_information;

import lombok.Getter;

@Getter
public abstract class ElementInvocation {
    /**
     * The name of the invoked element.
     */
    private String name;

    public ElementInvocation(String name) {
        this.name = name;
    }
}
