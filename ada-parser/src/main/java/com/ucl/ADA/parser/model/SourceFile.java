package com.ucl.ADA.parser.model;

import java.util.Map;
import java.util.Set;

public class SourceFile {

    private String className;
    private String parentClassName;
    private Set<String> implementedInterfaces;
    private Map<String, String> staticVariables;
    private Set<SourceMethod> methods;


    public SourceFile(String className, String parentClassName, Set<String> implementedInterface, Map<String, String> staticVariables, Set<SourceMethod> methods) {
        this.className = className;
        this.parentClassName = parentClassName;
        this.implementedInterfaces = implementedInterface;
        this.staticVariables = staticVariables;
        this.methods = methods;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getParentClassName() {
        return parentClassName;
    }

    public void setParentClassName(String parentClassName) {
        this.parentClassName = parentClassName;
    }

    public Set<String> getImplementedInterfaces() {
        return implementedInterfaces;
    }

    public void setImplementedInterfaces(Set<String> implementedInterfaces) {
        this.implementedInterfaces = implementedInterfaces;
    }

    public Map<String, String> getStaticVariables() {
        return staticVariables;
    }

    public void setStaticVariables(Map<String, String> staticVariables) {
        this.staticVariables = staticVariables;
    }

    public Set<SourceMethod> getMethods() {
        return methods;
    }

    public void setMethods(Set<SourceMethod> methods) {
        this.methods = methods;
    }

    @Override
    public boolean equals(Object sf) {
        if (sf == this)
            return true;
        if (!(sf instanceof SourceFile)) {
            return false;
        }
        SourceFile sourceClass = (SourceFile) sf;
        return sourceClass.className.equals(this.className)
                && sourceClass.parentClassName.equals(this.parentClassName)
                && sourceClass.implementedInterfaces.equals(this.implementedInterfaces)
                && sourceClass.staticVariables.equals(this.staticVariables)
                && sourceClass.methods.equals(this.methods);
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + className.hashCode();
        result = 31 * result + parentClassName.hashCode();
        result = 31 * result + implementedInterfaces.hashCode();
        result = 31 * result + staticVariables.hashCode();
        result = 31 * result + methods.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.className;
    }

}
