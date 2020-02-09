package com.ucl.ADA.parser.model;

import java.util.Map;
import java.util.Set;

public class SourceFile {

    private String packageName;
    private String className;
    private String parentClassName;
    private Set<String> implementedInterfaces;
    private Map<String, String> staticFields;
    private Map<String, String> publicFields;
    private Set<SourceMethod> methods;


    public SourceFile(String packageName, String className, String parentClassName, Set<String> implementedInterface, Map<String, String> staticFields, Map<String, String> publicFields, Set<SourceMethod> methods) {
        this.packageName = packageName;
        this.className = className;
        this.parentClassName = parentClassName;
        this.implementedInterfaces = implementedInterface;
        this.staticFields = staticFields;
        this.publicFields = publicFields;
        this.methods = methods;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
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

    public Map<String, String> getStaticFields() {
        return staticFields;
    }

    public void setStaticFields(Map<String, String> staticFields) {
        this.staticFields = staticFields;
    }

    public Map<String, String> getPublicFields() {
        return publicFields;
    }

    public void setPublicFields(Map<String, String> publicFields) {
        this.publicFields = publicFields;
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
        return sourceClass.packageName.equals(this.packageName)
                &&sourceClass.className.equals(this.className)
                && sourceClass.parentClassName.equals(this.parentClassName)
                && sourceClass.implementedInterfaces.equals(this.implementedInterfaces)
                && sourceClass.staticFields.equals(this.staticFields)
                && sourceClass.publicFields.equals(this.publicFields)
                && sourceClass.methods.equals(this.methods);
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + packageName.hashCode();
        result = 31 * result + className.hashCode();
        result = 31 * result + parentClassName.hashCode();
        result = 31 * result + implementedInterfaces.hashCode();
        result = 31 * result + staticFields.hashCode();
        result = 31 * result + publicFields.hashCode();
        result = 31 * result + methods.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.className;
    }

}
