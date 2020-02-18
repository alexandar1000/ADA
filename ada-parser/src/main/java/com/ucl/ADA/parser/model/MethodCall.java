package com.ucl.ADA.parser.model;

import lombok.Getter;

import java.util.List;

@Getter
public class MethodCall {

    private String calleeName;

    private String methodCallName;

    private List<String> arguments;

    public MethodCall(String methodCallName, String calleeName, List<String> arguments) {
        this.methodCallName = methodCallName;
        this.calleeName = calleeName;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "MethodCall{" +
                "methodCallName" + methodCallName + '\'' +
                ", calleeName='" + calleeName + '\'' +
                ", arguments=" + arguments +
                '}';
    }
}
