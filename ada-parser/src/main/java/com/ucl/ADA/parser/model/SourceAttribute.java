package com.ucl.ADA.parser.model;

import lombok.Getter;

import java.util.Set;

@Getter
public class SourceAttribute {

    private String name;

    private Set<String> modifiers;

    private String type;

    private String value;

    public SourceAttribute(Set<String> modifiers, String name, String type, String value) {
        this.modifiers = modifiers;
        this.name = name;
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "SourceAttribute{" +
                "name='" + name + '\'' +
                ", modifiers=" + modifiers +
                ", type='" + type + '\'' +
                ", assignedValue='" + value + '\'' +
                '}';
    }
}
