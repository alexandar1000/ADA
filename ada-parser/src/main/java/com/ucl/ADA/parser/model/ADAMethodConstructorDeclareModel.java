package com.ucl.ADA.parser.model;

import java.util.Map;
import java.util.Set;

public class ADAMethodConstructorDeclareModel {
    private String name;
    private String returnType;
    private Set<String> accessModifiers;
    // name->type
    private Map<String, String> parameters;
    private Map<String, String> localVariables;
    private boolean isConstructor;

    public ADAMethodConstructorDeclareModel(String name, String returnType, Set<String> accessModifiers, Map<String, String> parameters, Map<String, String> localVariables, boolean isConstructor) {
        this.name = name;
        this.returnType = returnType;
        this.accessModifiers = accessModifiers;
        this.parameters = parameters;
        this.localVariables = localVariables;
        this.isConstructor = isConstructor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public Set<String> getAccessModifiers() {
        return accessModifiers;
    }

    public void setAccessModifiers(Set<String> accessModifiers) {
        this.accessModifiers = accessModifiers;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Map<String, String> getLocalVariables() {
        return localVariables;
    }

    public void setLocalVariables(Map<String, String> localVariables) {
        this.localVariables = localVariables;
    }

    public boolean isConstructor() {
        return isConstructor;
    }

    public void setConstructor(boolean constructor) {
        isConstructor = constructor;
    }

    @Override
    public String toString() {
        return "ADAMethodConstructorDeclareModel{" +
                "name='" + name + '\'' +
                ", returnType='" + returnType + '\'' +
                ", accessModifiers=" + accessModifiers +
                ", parameters=" + parameters +
                ", localVariables=" + localVariables +
                ", isConstructor=" + isConstructor +
                '}';
    }
}
