package com.ucl.ADA.model.static_information;

import com.ucl.ADA.model.base_entity.BaseEntity;
import com.ucl.ADA.model.class_structure.ClassStructure;
import com.ucl.ADA.model.dependence_information.DependenceInfo;
import com.ucl.ADA.model.dependence_information.invocation_information.AttributeInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.ConstructorInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.MethodInvocation;
import com.ucl.ADA.model.static_information.declaration_information.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "STATIC_INFO")
public class StaticInfo extends BaseEntity {

    /**
     * the class structures that holds this static info
     */
    @OneToMany(mappedBy = "staticInfo", cascade = CascadeType.ALL)
    private Set<ClassStructure> classStructures = new HashSet<>();


    /* ************************************************************************
     *
     *  Declaration information corresponding to this class
     *
     **************************************************************************/

    /**
     * Packages present in the class. They can be either declared or imported.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "STATIC_INFO_IMPORT_DECLARATION",
            joinColumns = @JoinColumn(name = "static_info_id"),
            inverseJoinColumns = @JoinColumn(name = "import_declaration_id")
    )
    private Set<ImportDeclaration> importDeclarations = new HashSet<>();

    /**
     * Fully qualified class package name (including the name of the class in the end).
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "package_declaration_id")
    private PackageDeclaration packageDeclaration = new PackageDeclaration();

    /**
     * Attributes declared in this class.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "STATIC_INFO_ATTRIBUTE_DECLARATION",
            joinColumns = @JoinColumn(name = "static_info_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_declaration_id")
    )
    private Set<AttributeDeclaration> attributeDeclarations = new HashSet<>();

    /**
     * Constructors declared in this class.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "STATIC_INFO_CONSTRUCTOR_DECLARATION",
            joinColumns = @JoinColumn(name = "static_info_id"),
            inverseJoinColumns = @JoinColumn(name = "constructor_declaration_id")
    )
    private Set<ConstructorDeclaration> constructorDeclarations = new HashSet<>();

    /**
     * Methods declared in this class.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "STATIC_INFO_METHOD_DECLARATION",
            joinColumns = @JoinColumn(name = "static_info_id"),
            inverseJoinColumns = @JoinColumn(name = "method_declaration_id")
    )
    private Set<MethodDeclaration> methodDeclarations = new HashSet<>();

    /* ************************************************************************
     *
     *  Dependence relations to other classes
     *
     **************************************************************************/

    /**
     * Information about the invocations of the elements from the other classes from this class. String is the qualified
     * name of the class.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "STATIC_INFO_OUTGOING_DEPENDENCE_INFO",
            joinColumns = {@JoinColumn(name = "static_info_id")},
            inverseJoinColumns = {@JoinColumn(name = "dependence_info_id")})
    @MapKeyColumn(name = "class_name")
    private Map<String, DependenceInfo> outgoingDependenceInfos = new HashMap<>();

    /* ************************************************************************
     *
     *  External invocations (from outside of the project)
     *
     **************************************************************************/

    /**
     * External Method Invocations. Includes only calls to classes which cannot be resolved within the project. These
     * include the dependencies and libraries.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "STATIC_INFO_EXTERNAL_METHOD_INVOCATION",
            joinColumns = @JoinColumn(name = "static_info_id"),
            inverseJoinColumns = @JoinColumn(name = "method_invocation_id")
    )
    private Set<MethodInvocation> externalMethodInvocations = new HashSet<>();

    /**
     * External Constructor Invocations. Includes only calls to classes which cannot be resolved within the project.
     * These include the dependencies and libraries.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "STATIC_INFO_EXTERNAL_CONSTRUCTOR_INVOCATION",
            joinColumns = @JoinColumn(name = "static_info_id"),
            inverseJoinColumns = @JoinColumn(name = "constructor_invocation_id")
    )
    private Set<ConstructorInvocation> externalConstructorInvocations = new HashSet<>();

    /**
     * External Attribute Invocations. Includes only calls to classes which cannot be resolved within the project. These
     * include the dependencies and libraries.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "STATIC_INFO_EXTERNAL_ATTRIBUTE_INVOCATION",
            joinColumns = @JoinColumn(name = "static_info_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_invocation_id")
    )
    private Set<AttributeInvocation> externalAttributeInvocations = new HashSet<>();

}
