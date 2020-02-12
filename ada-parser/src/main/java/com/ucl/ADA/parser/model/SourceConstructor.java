package com.ucl.ADA.parser.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class SourceConstructor {

    private String name;
    private String accessModifier;
    private Map<String, String> parameters;// name -> type
    // need list of method calls

    @Builder
    public SourceConstructor(String name, String accessModifier, Map<String, String> parameters) {
        this.name = name;
        this.accessModifier = accessModifier;
        this.parameters = parameters;
    }
}


