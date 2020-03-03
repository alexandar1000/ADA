package com.ucl.ADA.parser.ada_model;

import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Getter
public class ADAMethodOrConstructorDeclaration {

    private String name;
    private String returnType;
    private Set<String> modifiers;
    // name->type
    private Map<String, String> parameters;
    private Map<String, String> localVariables;
    private boolean isConstructor;


    public ADAMethodOrConstructorDeclaration(String name, String returnType, Set<String> modifiers, Map<String, String> parameters, Map<String, String> localVariables, boolean isConstructor) {
        this.name = name;
        this.returnType = returnType;
        this.modifiers = modifiers;
        this.parameters = parameters;
        this.localVariables = localVariables;
        this.isConstructor = isConstructor;
    }


    @Override
    public String toString() {
        return "ADAMethodConstructorDeclareModel{" +
                "name='" + name + '\'' +
                ", returnType='" + returnType + '\'' +
                ", accessModifiers=" + modifiers +
                ", parameters=" + parameters +
                ", localVariables=" + localVariables +
                ", isConstructor=" + isConstructor +
                '}';
    }
}
