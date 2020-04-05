package com.ucl.ADA.model.dependence_information;

import com.ucl.ADA.model.base_entity.BaseEntity;
import com.ucl.ADA.model.class_structure.ClassStructure;
import com.ucl.ADA.model.dependence_information.invocation_information.AttributeInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.ConstructorInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.MethodInvocation;
import com.ucl.ADA.model.static_information.static_info.StaticInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "DEPENDENCE_INFO")
public class DependenceInfo extends BaseEntity {

    @ManyToMany(mappedBy = "incomingDependenceInfos", fetch = FetchType.EAGER)
    private Set<ClassStructure> classStructures = new HashSet<>();

    @ManyToOne(targetEntity = StaticInfo.class)
    private StaticInfo staticInfo;

    @Column(name = "class_name")
    private String className;

    /* ************************************************************************
     *
     *  For data and control flow coupling
     *
     **************************************************************************/

    /**
     * Attributes present in the class. They can be either declared or invoked.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "DEPENDENCE_INFO_ATTRIBUTE_INVOCATION",
            joinColumns = @JoinColumn(name = "dependence_info_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_invocation_id")
    )
    private Set<AttributeInvocation> attributeInvocations = new HashSet<>();

    /**
     * Constructors present in the class. They can be either declared or invoked.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "DEPENDENCE_INFO_CONSTRUCTOR_INVOCATION",
            joinColumns = @JoinColumn(name = "dependence_info_id"),
            inverseJoinColumns = @JoinColumn(name = "constructor_invocation_id")
    )
    private Set<ConstructorInvocation> constructorInvocations = new HashSet<>();

    /**
     * Methods present in the class. They can be either declared or invoked.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "DEPENDENCE_INFO_METHOD_INVOCATION",
            joinColumns = @JoinColumn(name = "dependence_info_id"),
            inverseJoinColumns = @JoinColumn(name = "method_invocation_id")
    )
    private Set<MethodInvocation> methodInvocations = new HashSet<>();


    /* ************************************************************************
     *
     *  public functions to update dependence info
     *
     **************************************************************************/

    /**
     * Adds a new attribute to the instance.
     *
     * @param attributeInvocationInformation an attribute invocation information object containing all of the
     *                                       corresponding information about the attribute being added
     */
    public void addAttributeInvocation(AttributeInvocation attributeInvocationInformation) {
        this.attributeInvocations.add(attributeInvocationInformation);
    }

    /**
     * Adds a new constructor to the instance.
     *
     * @param constructorInvocationInformation a constructor invocation information object containing all of the
     *                                         corresponding information about the constructor being added
     */
    public void addConstructorInvocation(ConstructorInvocation constructorInvocationInformation) {
        this.constructorInvocations.add(constructorInvocationInformation);
    }

    /**
     * Adds a new method to the instance.
     *
     * @param methodInvocationInformation a method invocation information object containing all of the corresponding
     *                                    information about the method being added
     */
    public void addMethodInvocation(MethodInvocation methodInvocationInformation) {
        this.methodInvocations.add(methodInvocationInformation);
    }

}
