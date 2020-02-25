package com.ucl.ADA.parser.model;

import java.util.List;
import java.util.Set;

public class ADAClassModel {


    private String packageName;
    private Set<String> importedPackages;
    private String className;
    private String parentClassName;
    private Set<String> implementedInterfaces;
    private List<ADAClassAttributeModel> classAttributes;

    private List<ADAMethodCallModel> ADAMethodCallModels;
    private List<ADAConstructorCallModel> ADAConstructorCallModels;
    private List<ADAMethodConstructorDeclareModel> methodConstructorDeclaration;
    private List<String> exMethodCalls;
    private List<String> exConstructorInvocations;
    private List<String> exFieldInvocation;

    public ADAClassModel(String packageName, Set<String> importedPackages, String className, String parentClassName, Set<String> implementedInterfaces, List<ADAClassAttributeModel> classAttributes, List<ADAMethodCallModel> ADAMethodCallModels, List<ADAConstructorCallModel> ADAConstructorCallModels, List<ADAMethodConstructorDeclareModel> methodConstructorDeclaration, List<String> exMethodCalls, List<String> exConstructorInvocations, List<String> exFieldInvocation) {
        this.packageName = packageName;
        this.importedPackages = importedPackages;
        this.className = className;
        this.parentClassName = parentClassName;
        this.implementedInterfaces = implementedInterfaces;
        this.classAttributes = classAttributes;
        this.ADAMethodCallModels = ADAMethodCallModels;
        this.ADAConstructorCallModels = ADAConstructorCallModels;
        this.methodConstructorDeclaration = methodConstructorDeclaration;
        this.exMethodCalls = exMethodCalls;
        this.exConstructorInvocations = exConstructorInvocations;
        this.exFieldInvocation = exFieldInvocation;

    }


    @Override
    public boolean equals(Object sf) {
        if (sf == this)
            return true;
        if (!(sf instanceof ADAClassModel)) {
            return false;
        }
        ADAClassModel ADAClassModel = (ADAClassModel) sf;
        return ADAClassModel.packageName.equals(this.packageName)
                && ADAClassModel.importedPackages.equals(this.importedPackages)
                && ADAClassModel.className.equals(this.className)
                && ADAClassModel.parentClassName.equals(this.parentClassName)
                && ADAClassModel.implementedInterfaces.equals(this.implementedInterfaces)
                && ADAClassModel.classAttributes.equals(this.classAttributes)
                && ADAClassModel.ADAMethodCallModels.equals(this.ADAMethodCallModels)
                && ADAClassModel.ADAConstructorCallModels.equals(this.ADAConstructorCallModels)
                && ADAClassModel.methodConstructorDeclaration.equals(this.methodConstructorDeclaration)
                && ADAClassModel.exMethodCalls.equals(this.exMethodCalls)
                && ADAClassModel.exConstructorInvocations.equals(this.exConstructorInvocations)
                && ADAClassModel.exFieldInvocation.equals(this.exFieldInvocation);
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + packageName.hashCode();
        result = 31 * result + importedPackages.hashCode();
        result = 31 * result + className.hashCode();
        result = 31 * result + parentClassName.hashCode();
        result = 31 * result + implementedInterfaces.hashCode();
        result = 31 * result + classAttributes.hashCode();
        result = 31 * result + ADAMethodCallModels.hashCode();
        result = 31 * result + ADAConstructorCallModels.hashCode();
        result = 31 * result + methodConstructorDeclaration.hashCode();
        result = 31 * result + exMethodCalls.hashCode();
        result = 31 * result + exConstructorInvocations.hashCode();
        result = 31 * result + exFieldInvocation.hashCode();
        return result;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Set<String> getImportedPackages() {
        return importedPackages;
    }

    public void setImportedPackages(Set<String> importedPackages) {
        this.importedPackages = importedPackages;
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

    public List<ADAClassAttributeModel> getClassAttributes() {
        return classAttributes;
    }

    public void setClassAttributes(List<ADAClassAttributeModel> classAttributes) {
        this.classAttributes = classAttributes;
    }

    public List<ADAMethodCallModel> getADAMethodCallModels() {
        return ADAMethodCallModels;
    }

    public void setADAMethodCallModels(List<ADAMethodCallModel> ADAMethodCallModels) {
        this.ADAMethodCallModels = ADAMethodCallModels;
    }

    public List<ADAConstructorCallModel> getADAConstructorCallModels() {
        return ADAConstructorCallModels;
    }

    public void setADAConstructorCallModels(List<ADAConstructorCallModel> ADAConstructorCallModels) {
        this.ADAConstructorCallModels = ADAConstructorCallModels;
    }

    public List<ADAMethodConstructorDeclareModel> getMethodConstructorDeclaration() {
        return methodConstructorDeclaration;
    }

    public void setMethodConstructorDeclaration(List<ADAMethodConstructorDeclareModel> methodConstructorDeclaration) {
        this.methodConstructorDeclaration = methodConstructorDeclaration;
    }

    public List<String> getExMethodCalls() {
        return exMethodCalls;
    }

    public void setExMethodCalls(List<String> exMethodCalls) {
        this.exMethodCalls = exMethodCalls;
    }

    public List<String> getExConstructorInvocations() {
        return exConstructorInvocations;
    }

    public void setExConstructorInvocations(List<String> exConstructorInvocations) {
        this.exConstructorInvocations = exConstructorInvocations;
    }

    public List<String> getExFieldInvocation() {
        return exFieldInvocation;
    }

    public void setExFieldInvocation(List<String> exFieldInvocation) {
        this.exFieldInvocation = exFieldInvocation;
    }

    @Override
    public String toString() {
        return "ClassModel{" +
                "packageName='" + packageName + '\'' +
                ", importedPackages=" + importedPackages +
                ", className='" + className + '\'' +
                ", parentClassName='" + parentClassName + '\'' +
                ", implementedInterfaces=" + implementedInterfaces +
                ", classAttributes=" + classAttributes +
                ", methodCalls=" + ADAMethodCallModels +
                ", constructorInvocations=" + ADAConstructorCallModels +
                ", methodConstructorDeclaration=" + methodConstructorDeclaration +
                ", exMethodCalls=" + exMethodCalls +
                ", exConstructorInvocations=" + exConstructorInvocations +
                ", exFieldInvocation=" + exFieldInvocation +
                '}';
    }
}
