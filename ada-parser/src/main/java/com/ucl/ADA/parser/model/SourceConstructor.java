package com.ucl.ADA.parser.model;

import lombok.Builder;

import java.util.List;
import java.util.Map;

public class SourceConstructor {

    private String name;
    private String accessModifier;
    private Map<String, String> parameters;
    // need list of method calls

    @Builder
    public SourceConstructor(String name, String accessModifier, Map<String, String> parameters) {
        this.name = name;
        this.accessModifier = accessModifier;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}


