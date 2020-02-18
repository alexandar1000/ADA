package com.ucl.ADA.parser.model;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ExternalInvocationInfo {

    private List<String> exMethodCalls;

    private List<String> exConstructorInvocations;

    private List<String> exFieldInvocation;

    public ExternalInvocationInfo() {
        this.exMethodCalls = new ArrayList<>();
        this.exConstructorInvocations = new ArrayList<>();
        this.exFieldInvocation = new ArrayList<>();
    }

}
