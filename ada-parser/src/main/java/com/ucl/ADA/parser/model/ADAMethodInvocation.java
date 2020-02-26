package com.ucl.ADA.parser.model;

import lombok.Getter;

import java.util.List;

@Getter
public class ADAMethodInvocation {

    private String methodCallName;
    private String calleeName;
    private List<String> arguments;

    public ADAMethodInvocation(String methodCallName, String calleeName, List<String> arguments) {
        this.methodCallName = methodCallName;
        this.calleeName = calleeName;
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
