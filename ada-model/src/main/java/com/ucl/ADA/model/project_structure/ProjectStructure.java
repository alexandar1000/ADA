package com.ucl.ADA.model.project_structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ucl.ADA.model.BaseEntity;
import com.ucl.ADA.model.class_structure.ClassStructure;
import com.ucl.ADA.model.dependence_information.declaration_information.AttributeDeclaration;
import com.ucl.ADA.model.dependence_information.declaration_information.ConstructorDeclaration;
import com.ucl.ADA.model.dependence_information.declaration_information.MethodDeclaration;
import com.ucl.ADA.model.dependence_information.declaration_information.PackageDeclaration;
import com.ucl.ADA.model.dependence_information.invocation_information.*;
import com.ucl.ADA.model.snapshot.Snapshot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PROJECT_STRUCTURE")
public class ProjectStructure extends BaseEntity {

    /**
     * a map of ClassStructures, the key is qualified class name
     */
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "PROJECT_STRUCTURE_CLASS_STRUCTURE",
            joinColumns = {@JoinColumn(name = "project_structure_id")},
            inverseJoinColumns = {@JoinColumn(name = "class_structure_id")})
    @MapKeyColumn(name = "class_name")
    private Map<String, ClassStructure> classStructures = new HashMap<>();

    /**
     * the snapshot object that it belong to
     */
    @JsonIgnore
    @OneToOne(mappedBy = "projectStructure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Snapshot snapshot;

    /**
     * basic information on the snapshot
     */
    @Transient
    private GitRepoInfo gitRepoInfo;


    /**
     * Adds package declaration for the corresponding declaring class where it is declared.
     *
     * @param declaringClass     Class where the package is declared
     * @param packageDeclaration The package declaration object
     */
    public void addPackageDeclaration(String declaringClass, PackageDeclaration packageDeclaration) {
        if (this.classStructures.containsKey(declaringClass)) {
            this.classStructures.get(declaringClass).setCurrentPackage(packageDeclaration);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.setCurrentPackage(packageDeclaration);
            this.classStructures.put(declaringClass, classStructure);
        }
    }

    /**
     * Adds attribute declaration for the corresponding declaring class where it is declared.
     *
     * @param declaringClass       Class where the attribute is declared
     * @param attributeDeclaration The attribute declaration object
     */
    public void addAttributeDeclaration(String declaringClass, AttributeDeclaration attributeDeclaration) {
        if (this.classStructures.containsKey(declaringClass)) {
            this.classStructures.get(declaringClass).addAttributeDeclaration(attributeDeclaration);
        } else {
            ClassStructure classStructure = getNewClassStructure(declaringClass);
            classStructure.addAttributeDeclaration(attributeDeclaration);
            this.classStructures.put(declaringClass, classStructure);
        }
    }

    /**
     * Adds constructor declaration for the corresponding declaring class where it is declared.
     *
     * @param declaringClass         Class where the constructor is declared
     * @param constructorDeclaration The attribute declaration object
     */
    public void addConstructorDeclaration(String declaringClass, ConstructorDeclaration constructorDeclaration) {
        if (this.classStructures.containsKey(declaringClass)) {
            this.classStructures.get(declaringClass).addConstructorDeclaration(constructorDeclaration);
        } else {
            ClassStructure classStructure = getNewClassStructure(declaringClass);
            classStructure.addConstructorDeclaration(constructorDeclaration);
            this.classStructures.put(declaringClass, classStructure);
        }
    }

    /**
     * Adds method declaration for the corresponding declaring class where it is declared.
     *
     * @param declaringClass    Class where the method is declared
     * @param methodDeclaration The method declaration object
     */
    public void addMethodDeclaration(String declaringClass, MethodDeclaration methodDeclaration) {
        if (this.classStructures.containsKey(declaringClass)) {
            this.classStructures.get(declaringClass).addMethodDeclaration(methodDeclaration);
        } else {
            ClassStructure classStructure = getNewClassStructure(declaringClass);
            classStructure.addMethodDeclaration(methodDeclaration);
            this.classStructures.put(declaringClass, classStructure);
        }
    }


    /**
     * Adds the package invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     *
     * @param consumingClassName class where the package has been invoked from
     * @param declaringClassName class where the package has been declared in
     * @param packageInvocation  The package declaration object
     */
    public void addPackageInvocation(String consumingClassName, String declaringClassName, PackageInvocation packageInvocation) {
        if (this.classStructures.containsKey(consumingClassName)) {
            this.classStructures.get(consumingClassName).addPackageInvocationElement(declaringClassName, InvocationType.OUTGOING, packageInvocation);
        } else {
            ClassStructure classStructure = getNewClassStructure(consumingClassName);
            classStructure.addPackageInvocationElement(declaringClassName, InvocationType.OUTGOING, packageInvocation);
            this.classStructures.put(consumingClassName, classStructure);
        }

        if (this.classStructures.containsKey(declaringClassName)) {
            this.classStructures.get(declaringClassName).addPackageInvocationElement(consumingClassName, InvocationType.INCOMING, packageInvocation);
        } else {
            ClassStructure classStructure = getNewClassStructure(declaringClassName);
            classStructure.addPackageInvocationElement(consumingClassName, InvocationType.INCOMING, packageInvocation);
            this.classStructures.put(declaringClassName, classStructure);
        }
    }

    /**
     * Adds the attribute invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     *
     * @param consumingClassName  class where the attribute has been invoked from
     * @param declaringClassName  class where the attribute has been declared in
     * @param attributeInvocation The package declaration object
     */
    public void addAttributeInvocation(String consumingClassName, String declaringClassName, AttributeInvocation attributeInvocation) {
        if (this.classStructures.containsKey(consumingClassName)) {
            this.classStructures.get(consumingClassName).addAttributeInvocationElement(declaringClassName, InvocationType.OUTGOING, attributeInvocation);
        } else {
            ClassStructure classStructure = getNewClassStructure(consumingClassName);
            classStructure.addAttributeInvocationElement(declaringClassName, InvocationType.OUTGOING, attributeInvocation);
            this.classStructures.put(consumingClassName, classStructure);
        }

        if (this.classStructures.containsKey(declaringClassName)) {
            this.classStructures.get(declaringClassName).addAttributeInvocationElement(consumingClassName, InvocationType.INCOMING, attributeInvocation);
        } else {
            ClassStructure classStructure = getNewClassStructure(declaringClassName);
            classStructure.addAttributeInvocationElement(consumingClassName, InvocationType.INCOMING, attributeInvocation);
            this.classStructures.put(declaringClassName, classStructure);
        }
    }

    /**
     * Adds the constructor invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     *
     * @param consumingClassName    class where the constructor has been invoked from
     * @param declaringClassName    class where the constructor has been declared in
     * @param constructorInvocation The package declaration object
     */
    public void addConstructorInvocation(String consumingClassName, String declaringClassName, ConstructorInvocation constructorInvocation) {
        if (this.classStructures.containsKey(consumingClassName)) {
            this.classStructures.get(consumingClassName).addConstructorInvocationElement(declaringClassName, InvocationType.OUTGOING, constructorInvocation);
        } else {
            ClassStructure classStructure = getNewClassStructure(consumingClassName);
            classStructure.addConstructorInvocationElement(declaringClassName, InvocationType.OUTGOING, constructorInvocation);
            this.classStructures.put(consumingClassName, classStructure);
        }

        if (this.classStructures.containsKey(declaringClassName)) {
            this.classStructures.get(declaringClassName).addConstructorInvocationElement(consumingClassName, InvocationType.INCOMING, constructorInvocation);
        } else {
            ClassStructure classStructure = getNewClassStructure(declaringClassName);
            classStructure.addConstructorInvocationElement(consumingClassName, InvocationType.INCOMING, constructorInvocation);
            this.classStructures.put(declaringClassName, classStructure);
        }
    }

    /**
     * Adds the method invocation for the corresponding declaring class where it is declared, and the corresponding
     * consuming class where it is consumed.
     *
     * @param consumingClassName class where the method has been invoked from
     * @param declaringClassName class where the method has been declared in
     * @param methodInvocation   The package declaration object
     */
    public void addMethodInvocation(String consumingClassName, String declaringClassName, MethodInvocation methodInvocation) {
        if (this.classStructures.containsKey(consumingClassName)) {
            this.classStructures.get(consumingClassName).addMethodInvocationElement(declaringClassName, InvocationType.OUTGOING, methodInvocation);
        } else {
            ClassStructure classStructure = getNewClassStructure(consumingClassName);
            classStructure.addMethodInvocationElement(declaringClassName, InvocationType.OUTGOING, methodInvocation);
            this.classStructures.put(consumingClassName, classStructure);
        }

        if (this.classStructures.containsKey(declaringClassName)) {
            this.classStructures.get(declaringClassName).addMethodInvocationElement(consumingClassName, InvocationType.INCOMING, methodInvocation);
        } else {
            ClassStructure classStructure = getNewClassStructure(declaringClassName);
            classStructure.addMethodInvocationElement(consumingClassName, InvocationType.INCOMING, methodInvocation);
            this.classStructures.put(declaringClassName, classStructure);
        }
    }

    /**
     * Adds the external package invocation to the corresponding class.
     *
     * @param consumingClass        the class which consumes the external package
     * @param externalPackageImport the external package being imported
     */
    public void addExternalPackageImport(String consumingClass, PackageInvocation externalPackageImport) {
        if (this.classStructures.containsKey(consumingClass)) {
            this.classStructures.get(consumingClass).addExternalPackageImport(externalPackageImport);
        } else {
            ClassStructure classStructure = getNewClassStructure(consumingClass);
            classStructure.addExternalPackageImport(externalPackageImport);
            this.classStructures.put(consumingClass, classStructure);
        }
    }

    /**
     * Adds the external attribute invocation to the corresponding class.
     *
     * @param consumingClass                the class which consumes the external attribute
     * @param externalAttributeDeclarations the external attribute being consumed
     */
    public void addExternalAttributeDeclarations(String consumingClass, AttributeInvocation externalAttributeDeclarations) {
        if (this.classStructures.containsKey(consumingClass)) {
            this.classStructures.get(consumingClass).addExternalAttributeInvocation(externalAttributeDeclarations);
        } else {
            ClassStructure classStructure = getNewClassStructure(consumingClass);
            classStructure.addExternalAttributeInvocation(externalAttributeDeclarations);
            this.classStructures.put(consumingClass, classStructure);
        }
    }

    /**
     * Adds the external constructor invocation to the corresponding class.
     *
     * @param consumingClass                 the class which consumes the external constructor
     * @param externalConstructorInvocations the external constructor being consumed
     */
    public void addExternalConstructorInvocations(String consumingClass, ConstructorInvocation externalConstructorInvocations) {
        if (this.classStructures.containsKey(consumingClass)) {
            this.classStructures.get(consumingClass).addExternalConstructorInvocation(externalConstructorInvocations);
        } else {
            ClassStructure classStructure = new ClassStructure();
            classStructure.addExternalConstructorInvocation(externalConstructorInvocations);
            this.classStructures.put(consumingClass, classStructure);
        }
    }

    /**
     * Adds the external method invocation to the corresponding class.
     *
     * @param consumingClass            the class which consumes the external method
     * @param externalMethodInvocations the external method being consumed
     */
    public void addExternalMethodInvocations(String consumingClass, MethodInvocation externalMethodInvocations) {
        if (this.classStructures.containsKey(consumingClass)) {
            this.classStructures.get(consumingClass).addExternalMethodInvocation(externalMethodInvocations);
        } else {
            ClassStructure classStructure = getNewClassStructure(consumingClass);
            classStructure.addExternalMethodInvocation(externalMethodInvocations);
            this.classStructures.put(consumingClass, classStructure);
        }
    }

    /**
     * Computes both the class and the relation metrics for the project structure
     */
    public void computeAllMetrics() {
        for (ClassStructure classStructure : classStructures.values()) {
            classStructure.computeAllClassMetrics();
            classStructure.computeAllRelationMetrics();
        }
    }

    /**
     * create a new ClassStructure object
     *
     * @param className qualified name of class
     * @return a newly-created ClassStructure object
     */
    private ClassStructure getNewClassStructure(String className) {
        ClassStructure classStructure = new ClassStructure();
        classStructure.setCurrentPackage(new PackageDeclaration(getDefaultPackageName(className)));
        return classStructure;
    }

    /**
     * get default package name given qualified class name
     *
     * @param className qualified name of class
     * @return a package name, return an empty String if the class is in root package
     */
    private String getDefaultPackageName(String className) {
        int p = className.lastIndexOf(".");
        return (p == -1 ? "" : className.substring(0, p));
    }

}
