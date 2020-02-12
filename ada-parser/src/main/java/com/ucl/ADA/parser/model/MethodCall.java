package com.ucl.ADA.parser.model;

import java.util.Map;
import java.util.Set;

public class MethodCall {

    private String calleeName;
    // TODO: change set to list
    private Set<String> arguments;

    public MethodCall(String calleeName, Set<String> arguments) {
        this.calleeName = calleeName;
        this.arguments = arguments;
    }

    public String getCalleeName() {
        return calleeName;
    }

    public void setCalleeName(String calleeName) {
        this.calleeName = calleeName;
    }

    public Set<String> getArguments() {
        return arguments;
    }

    public void setArguments(Set<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "MethodCall{" +
                "calleeName='" + calleeName + '\'' +
                ", arguments=" + arguments +
                '}';
    }
}
