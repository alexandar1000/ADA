package com.ucl.ADA.parser.model;

import java.util.List;

public class ADAMethodCallModel {

    private String methodCallName;
    private String calleeName;
    private List<String> arguments;

    public ADAMethodCallModel(String methodCallName, String calleeName, List<String> arguments) {
        this.methodCallName = methodCallName;
        this.calleeName = calleeName;
        this.arguments = arguments;
    }

    public String getMethodCallName() {
        return methodCallName;
    }

    public void setMethodCallName(String methodCallName) {
        this.methodCallName = methodCallName;
    }

    public String getCalleeName() {
        return calleeName;
    }

    public void setCalleeName(String calleeName) {
        this.calleeName = calleeName;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "ADAMethodCallModel{" +
                "methodCallName='" + methodCallName + '\'' +
                ", calleeName='" + calleeName + '\'' +
                ", arguments=" + arguments +
                '}';
    }
}
