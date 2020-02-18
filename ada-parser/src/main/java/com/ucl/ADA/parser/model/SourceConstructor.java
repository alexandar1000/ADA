package com.ucl.ADA.parser.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Getter
@NoArgsConstructor
public class SourceConstructor {

    private String name;
    private Set<String> modifiers;
    private Map<String, String> parameters; // name -> type
    // TODO: need list of method calls

    @Builder
    public SourceConstructor(String name, Set<String> modifiers, Map<String, String> parameters) {
        this.name = name;
        this.modifiers = modifiers;
        this.parameters = parameters;
    }
}


