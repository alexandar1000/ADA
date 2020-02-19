package com.ucl.ADA.parser.model;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ExternalInvocationInfo {

    private List<String> exImports;

    // TODO: replace String with methodcall object ???
    private List<String> exMethodCalls;

    private List<String> exConstructorInvocations;

    private List<String> exFieldInvocation;

    // TODO: refactor with addAll
    public ExternalInvocationInfo() {
        this.exImports = new ArrayList<>();
        this.exMethodCalls = new ArrayList<>();
        this.exConstructorInvocations = new ArrayList<>();
        this.exFieldInvocation = new ArrayList<>();
    }

}
