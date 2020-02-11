package com.ucl.ADA.parser.model;

import lombok.Builder;

import java.util.List;

public class ConstructorInvocation {

    private String constructorClassName;
    private List<String> argumentsValues;

    @Builder
    public ConstructorInvocation(String constructorClassName, List<String> argumentsValues) {
        this.constructorClassName = constructorClassName;
        this.argumentsValues = argumentsValues;
    }

    public String getConstructorClassName() {
        return constructorClassName;
    }

    public List<String> getArgumentsValues() {
        return argumentsValues;
    }
}
