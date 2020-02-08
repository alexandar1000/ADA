package com.ucl.ADA.parser.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Getter
@NoArgsConstructor
public class SourceFile {

    private String packageName;
    private String className;
    private String parentClassName;
    private Set<String> implementedInterfaces;
    private Map<String, String> staticFields;
    private Map<String, String> publicFields;
    private Set<SourceMethod> methods;

    @Builder
    public SourceFile(String packageName, String className, String parentClassName, Set<String> implementedInterface, Map<String, String> staticFields, Map<String, String> publicFields, Set<SourceMethod> methods) {
        this.packageName = packageName;
        this.className = className;
        this.parentClassName = parentClassName;
        this.implementedInterfaces = implementedInterface;
        this.staticFields = staticFields;
        this.publicFields = publicFields;
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
                && sourceClass.className.equals(this.className)
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
