package com.ucl.ADA.parser.ada_model;

import lombok.Getter;

import java.util.List;

@Getter
public class ADAConstructorInvocation {

    private String constructorClassName;
    private List<String> arguments;


    public ADAConstructorInvocation(String constructorClassName, List<String> arguments) {
        this.constructorClassName = constructorClassName;
        this.arguments = arguments;
    }


    @Override
    public String toString() {
        return "ADAConstructorCallModel{" +
                "constructorClassName='" + constructorClassName + '\'' +
                ", argumentsValues=" + arguments +
                '}';
    }
}
