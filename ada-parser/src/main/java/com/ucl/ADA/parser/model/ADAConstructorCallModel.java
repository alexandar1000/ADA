package com.ucl.ADA.parser.model;

import java.util.List;

public class ADAConstructorCallModel {

    private String constructorClassName;
    private List<String> argumentsValues;


    public ADAConstructorCallModel(String constructorClassName, List<String> argumentsValues) {
        this.constructorClassName = constructorClassName;
        this.argumentsValues = argumentsValues;
    }

    public String getConstructorClassName() {
        return constructorClassName;
    }

    public void setConstructorClassName(String constructorClassName) {
        this.constructorClassName = constructorClassName;
    }

    public List<String> getArgumentsValues() {
        return argumentsValues;
    }

    public void setArgumentsValues(List<String> argumentsValues) {
        this.argumentsValues = argumentsValues;
    }

    @Override
    public String toString() {
        return "ADAConstructorCallModel{" +
                "constructorClassName='" + constructorClassName + '\'' +
                ", argumentsValues=" + argumentsValues +
                '}';
    }
}
