package com.ucl.ADA.parser.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SourceFile {

    private String packageName;
    private String className;
    private String parentClassName;
    private Set<String> implementedInterfaces;
    private List<SourceAttribute> classAttributes;
    private Set<SourceMethod> methods;
    private Set<String> importedPackages;
    private List<SourceConstructor> declaredSourceConstructors;
    private ExternalInvocationInfo externalInvocationInfo;

    @Builder
    public SourceFile(String packageName, String className, String parentClassName, Set<String> implementedInterfaces, List<SourceAttribute> classAttributes, Set<SourceMethod> methods, Set<String> importedPackages, List<SourceConstructor> declaredSourceConstructors, ExternalInvocationInfo externalInvocationInfo) {
        this.packageName = packageName;
        this.className = className;
        this.parentClassName = parentClassName;
        this.implementedInterfaces = implementedInterfaces;
        this.classAttributes = classAttributes;
        this.methods = methods;
        this.importedPackages = importedPackages;
        this.declaredSourceConstructors = declaredSourceConstructors;
        this.externalInvocationInfo = externalInvocationInfo;
    }

    @Override
    public boolean equals(Object sf) {
        if (sf == this)
            return true;
        if (!(sf instanceof SourceFile)) {
            return false;
        }
        SourceFile sourceClass = (SourceFile) sf;
        return sourceClass.importedPackages.equals(this.importedPackages)
                && sourceClass.packageName.equals(this.packageName)
                && sourceClass.className.equals(this.className)
                && sourceClass.parentClassName.equals(this.parentClassName)
                && sourceClass.implementedInterfaces.equals(this.implementedInterfaces)
                && sourceClass.methods.equals(this.methods)
                && sourceClass.declaredSourceConstructors.equals(this.declaredSourceConstructors)
                && sourceClass.classAttributes.equals(this.classAttributes)
                && sourceClass.externalInvocationInfo.equals(this.externalInvocationInfo);
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + importedPackages.hashCode();
        result = 31 * result + packageName.hashCode();
        result = 31 * result + className.hashCode();
        result = 31 * result + parentClassName.hashCode();
        result = 31 * result + implementedInterfaces.hashCode();
        result = 31 * result + methods.hashCode();
        result = 31 * result + declaredSourceConstructors.hashCode();
        result = 31 * result + classAttributes.hashCode();
        result = 31 * result + externalInvocationInfo.hashCode();

        return result;
    }


    @Override
    public String toString() {
        return this.className;
    }

}
