package com.ucl.ADA.parser.model;

import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class ADAClass {

    private String packageName;
    private Set<String> importedClasses;
    private String className;
    private String parentClassName;
    private Set<String> implementedInterfaces;
    private List<ADAClassAttribute> adaClassAttributes;

    private List<ADAMethodInvocation> ADAMethodInvocations;
    private List<ADAConstructorInvocation> ADAConstructorInvocations;
    private List<ADAMethodOrConstructorDeclaration> ADAMethodOrConstructorDeclaration;
    private List<String> exMethodCalls;
    private List<String> exConstructorInvocations;
    private List<String> exFieldInvocation;

    public ADAClass(String packageName, Set<String> importedClasses, String className, String parentClassName, Set<String> implementedInterfaces, List<ADAClassAttribute> adaClassAttributes, List<ADAMethodInvocation> ADAMethodInvocations, List<ADAConstructorInvocation> ADAConstructorInvocations, List<ADAMethodOrConstructorDeclaration> ADAMethodOrConstructorDeclaration, List<String> exMethodCalls, List<String> exConstructorInvocations, List<String> exFieldInvocation) {
        this.packageName = packageName;
        this.importedClasses = importedClasses;
        this.className = className;
        this.parentClassName = parentClassName;
        this.implementedInterfaces = implementedInterfaces;
        this.adaClassAttributes = adaClassAttributes;
        this.ADAMethodInvocations = ADAMethodInvocations;
        this.ADAConstructorInvocations = ADAConstructorInvocations;
        this.ADAMethodOrConstructorDeclaration = ADAMethodOrConstructorDeclaration;
        this.exMethodCalls = exMethodCalls;
        this.exConstructorInvocations = exConstructorInvocations;
        this.exFieldInvocation = exFieldInvocation;
    }


    @Override
    public boolean equals(Object sf) {
        if (sf == this)
            return true;
        if (!(sf instanceof ADAClass)) {
            return false;
        }
        ADAClass ADAClass = (ADAClass) sf;
        return ADAClass.packageName.equals(this.packageName)
                && ADAClass.importedClasses.equals(this.importedClasses)
                && ADAClass.className.equals(this.className)
                && ADAClass.parentClassName.equals(this.parentClassName)
                && ADAClass.implementedInterfaces.equals(this.implementedInterfaces)
                && ADAClass.adaClassAttributes.equals(this.adaClassAttributes)
                && ADAClass.ADAMethodInvocations.equals(this.ADAMethodInvocations)
                && ADAClass.ADAConstructorInvocations.equals(this.ADAConstructorInvocations)
                && ADAClass.ADAMethodOrConstructorDeclaration.equals(this.ADAMethodOrConstructorDeclaration)
                && ADAClass.exMethodCalls.equals(this.exMethodCalls)
                && ADAClass.exConstructorInvocations.equals(this.exConstructorInvocations)
                && ADAClass.exFieldInvocation.equals(this.exFieldInvocation);
    }


    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + packageName.hashCode();
        result = 31 * result + importedClasses.hashCode();
        result = 31 * result + className.hashCode();
        result = 31 * result + parentClassName.hashCode();
        result = 31 * result + implementedInterfaces.hashCode();
        result = 31 * result + adaClassAttributes.hashCode();
        result = 31 * result + ADAMethodInvocations.hashCode();
        result = 31 * result + ADAConstructorInvocations.hashCode();
        result = 31 * result + ADAMethodOrConstructorDeclaration.hashCode();
        result = 31 * result + exMethodCalls.hashCode();
        result = 31 * result + exConstructorInvocations.hashCode();
        result = 31 * result + exFieldInvocation.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return "ClassModel{" +
                "packageName='" + packageName + '\'' +
                ", importedPackages=" + importedClasses +
                ", className='" + className + '\'' +
                ", parentClassName='" + parentClassName + '\'' +
                ", implementedInterfaces=" + implementedInterfaces +
                ", classAttributes=" + adaClassAttributes +
                ", methodCalls=" + ADAMethodInvocations +
                ", constructorInvocations=" + ADAConstructorInvocations +
                ", methodConstructorDeclaration=" + ADAMethodOrConstructorDeclaration +
                ", exMethodCalls=" + exMethodCalls +
                ", exConstructorInvocations=" + exConstructorInvocations +
                ", exFieldInvocation=" + exFieldInvocation +
                '}';
    }
}
