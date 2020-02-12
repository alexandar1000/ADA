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
    private Map<String, String> localVariables;
    // class name->method name
    private List<MethodCall> methodCalls;
    private List<ConstructorInvocation> constructorInvocations;

    @Builder
    public SourceMethod(String name, String returnType, Set<String> accessModifiers, Map<String, String> parameters,
                        List<MethodCall> methodCalls, Map<String, String> localVariables,List<ConstructorInvocation> constructorInvocations) {
        this.name = name;
        this.returnType = returnType;
        this.accessModifiers = accessModifiers;
        this.parameters = parameters;
        this.methodCalls = methodCalls;
        this.localVariables = localVariables;
        this.constructorInvocations=constructorInvocations;
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
                && method.localVariables.equals(this.localVariables)
                && method.methodCalls.equals(this.methodCalls)
                && method.constructorInvocations.equals(this.constructorInvocations);
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + this.name.hashCode();
        result = 31 * result + this.returnType.hashCode();
        result = 31 * result + this.accessModifiers.hashCode();
        result = 31 * result + this.parameters.hashCode();
        result = 31 * result + this.localVariables.hashCode();
        result = 31 * result + this.methodCalls.hashCode();
        result = 31 * result + this.constructorInvocations.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.returnType + " " + this.name + " " + this.parameters;
    }

}
