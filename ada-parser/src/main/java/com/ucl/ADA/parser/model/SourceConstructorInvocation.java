package com.ucl.ADA.parser.model;

import lombok.Getter;

import java.util.List;

@Getter
public class SourceConstructorInvocation {

    private String constructorClassName;

    private List<String> arguments;

    public SourceConstructorInvocation(String constructorClassName, List<String> arguments) {
        this.constructorClassName = constructorClassName;
        this.arguments = arguments;
    }

}
