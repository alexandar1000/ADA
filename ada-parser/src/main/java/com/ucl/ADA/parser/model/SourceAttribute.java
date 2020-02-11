package com.ucl.ADA.parser.model;

import lombok.Builder;

import java.util.Set;

public class SourceAttribute {

    private String name;
    private Set<String> modifiers;
    private String type;
    private String assignedValue;


    @Builder
    public SourceAttribute(Set<String> modifiers, String name, String type, String value) {
        this.modifiers = modifiers;
        this.name = name;
        this.type = type;
        this.assignedValue = value;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(Set<String> modifiers) {
        this.modifiers = modifiers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAssignedValue() {
        return assignedValue;
    }

    public void setAssignedValue(String assignedValue) {
        this.assignedValue = assignedValue;
    }

    @Override
    public String toString() {
        return "SourceAttribute{" +
                "name='" + name + '\'' +
                ", modifiers=" + modifiers +
                ", type='" + type + '\'' +
                ", assignedValue='" + assignedValue + '\'' +
                '}';
    }
}
