package com.ucl.ADA.parser.model;


import java.util.ArrayList;
import java.util.List;

public class ExternalInvocationInfo {

    private List<String> exMethodCalls;
    private List<String> exConstructorInvocations;
    private List<String> exFieldInvocation;

    public ExternalInvocationInfo() {
        this.exMethodCalls = new ArrayList<>();
        this.exConstructorInvocations = new ArrayList<>();
        this.exFieldInvocation = new ArrayList<>();
    }

    public void addExMethodCall(String s) {
        exMethodCalls.add(s);
    }

    public void addExConstructorInvocations(String s) {
        exConstructorInvocations.add(s);
    }

    public void addExFieldInvocation(String s) {
        exFieldInvocation.add(s);
    }

    public List<String> getExMethodCalls() {
        return exMethodCalls;
    }

    public void setExMethodCalls(List<String> exMethodCalls) {
        this.exMethodCalls = exMethodCalls;
    }

    public List<String> getExConstructorInvocations() {
        return exConstructorInvocations;
    }

    public void setExConstructorInvocations(List<String> exConstructorInvocations) {
        this.exConstructorInvocations = exConstructorInvocations;
    }

    public List<String> getExFieldInvocation() {
        return exFieldInvocation;
    }

    public void setExFieldInvocation(List<String> exFieldInvocation) {
        this.exFieldInvocation = exFieldInvocation;
    }
}
