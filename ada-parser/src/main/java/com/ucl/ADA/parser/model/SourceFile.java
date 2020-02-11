package com.ucl.ADA.parser.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
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

    @Builder
    public SourceFile(Set<String> importedPackages, String packageName, String className, String parentClassName, Set<String> implementedInterface, Set<SourceMethod> methods, List<SourceConstructor> declaredSourceConstructors, List<SourceAttribute> classAttributes) {
        this.importedPackages = importedPackages;
        this.packageName = packageName;
        this.className = className;
        this.parentClassName = parentClassName;
        this.implementedInterfaces = implementedInterface;
        this.methods = methods;
        this.declaredSourceConstructors = declaredSourceConstructors;
        this.classAttributes = classAttributes;
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
                && sourceClass.classAttributes.equals(this.classAttributes);
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

        return result;
    }

    @Override
    public String toString() {
        return this.className;
    }

}
