package com.ucl.ADA.parser.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@NoArgsConstructor
public class SourceMethod {

    private String name;
    private String returnType;
    private Set<String> accessModifiers;
    // name->type
    private Map<String, String> parameters;
    private Map<String, String> usedVariables;
    // class name->method name
    private List<String> methodCalls;

    @Builder
    public SourceMethod(String name, String returnType, Set<String> accessModifiers, Map<String, String> parameters,
                        List<String> methodCalls, Map<String, String> usedVariables) {
        this.name = name;
        this.returnType = returnType;
        this.accessModifiers = accessModifiers;
        this.parameters = parameters;
        this.methodCalls = methodCalls;
        this.usedVariables = usedVariables;
    }

    @Override
    public boolean equals(Object m) {
        if (m == this)
            return true;
        if (!(m instanceof SourceMethod)) {
            return false;
        }
        SourceMethod method = (SourceMethod) m;
        return method.name.equals(this.name)
                && method.returnType.equals(this.returnType)
                && method.accessModifiers.equals(this.accessModifiers)
                && method.parameters.equals(this.parameters)
                && method.usedVariables.equals(this.usedVariables)
                && method.methodCalls.equals(this.methodCalls);
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + this.name.hashCode();
        result = 31 * result + this.returnType.hashCode();
        result = 31 * result + this.accessModifiers.hashCode();
        result = 31 * result + this.parameters.hashCode();
        result = 31 * result + this.usedVariables.hashCode();
        result = 31 * result + this.methodCalls.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.returnType + " " + this.name + " " + this.parameters;
    }

}
