package com.ucl.ADA.parser.ada_model;

import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class ADAClass {

    private String packageName;
    private Set<String> importedInternalClasses;
    private Set<String> importedExternalClasses;
    private String className;

    private boolean isInterface;
    private boolean isEnum;


    private String parentClassName;
    private Set<String> implementedInterfaces;
    private List<ADAClassAttribute> adaClassAttributes;

    private List<String> declaredEnums;

    private List<ADAMethodInvocation> ADAMethodInvocations;
    private List<ADAConstructorInvocation> ADAConstructorInvocations;
    private List<ADAMethodOrConstructorDeclaration> ADAMethodOrConstructorDeclaration;
    private List<String> exMethodCalls;
    private List<String> exConstructorInvocations;
    private List<String> exFieldInvocation;


    public ADAClass(String packageName, Set<String> importedInternalClasses, Set<String> importedExternalClasses,
                    String className, boolean isInterface, boolean isEnum, String parentClassName,
                    Set<String> implementedInterfaces, List<ADAClassAttribute> adaClassAttributes,
                    List<String> declaredEnums, List<ADAMethodInvocation> ADAMethodInvocations,
                    List<ADAConstructorInvocation> ADAConstructorInvocations,
                    List<ADAMethodOrConstructorDeclaration> ADAMethodOrConstructorDeclaration,
                    List<String> exMethodCalls, List<String> exConstructorInvocations, List<String> exFieldInvocation) {
        this.packageName = packageName;
        this.importedInternalClasses = importedInternalClasses;
        this.importedExternalClasses = importedExternalClasses;
        this.className = className;
        this.isInterface = isInterface;
        this.isEnum = isEnum;
        this.parentClassName = parentClassName;
        this.implementedInterfaces = implementedInterfaces;
        this.adaClassAttributes = adaClassAttributes;
        this.declaredEnums = declaredEnums;
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
                && ADAClass.importedInternalClasses.equals(this.importedInternalClasses)
                && ADAClass.importedExternalClasses.equals(this.importedExternalClasses)
                && ADAClass.className.equals(this.className)
                && ADAClass.parentClassName.equals(this.parentClassName)
                && ADAClass.implementedInterfaces.equals(this.implementedInterfaces)
                && ADAClass.adaClassAttributes.equals(this.adaClassAttributes)
                && ADAClass.declaredEnums.equals(this.declaredEnums)
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
        result = 31 * result + importedInternalClasses.hashCode();
        result = 31 * result + importedExternalClasses.hashCode();
        result = 31 * result + className.hashCode();
        result = 31 * result + parentClassName.hashCode();
        result = 31 * result + implementedInterfaces.hashCode();
        result = 31 * result + adaClassAttributes.hashCode();
        result = 31 * result + declaredEnums.hashCode();
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
        return "ADAClass{" +
                "packageName='" + packageName + '\'' +
                ", importedInternalClasses=" + importedInternalClasses +
                ", importedExternalClasses=" + importedExternalClasses +
                ", className='" + className + '\'' +
                ", isInterface=" + isInterface +
                ", isEnum=" + isEnum +
                ", parentClassName='" + parentClassName + '\'' +
                ", implementedInterfaces=" + implementedInterfaces +
                ", adaClassAttributes=" + adaClassAttributes +
                ", declaredEnums=" + declaredEnums +
                ", ADAMethodInvocations=" + ADAMethodInvocations +
                ", ADAConstructorInvocations=" + ADAConstructorInvocations +
                ", ADAMethodOrConstructorDeclaration=" + ADAMethodOrConstructorDeclaration +
                ", exMethodCalls=" + exMethodCalls +
                ", exConstructorInvocations=" + exConstructorInvocations +
                ", exFieldInvocation=" + exFieldInvocation +
                '}';
    }
}
