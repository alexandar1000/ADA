package com.ucl.ADA.model.static_information;

import com.ucl.ADA.model.dependence_information.DependenceInfo;
import com.ucl.ADA.model.dependence_information.invocation_information.AttributeInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.ConstructorInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.MethodInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.PackageInvocation;
import com.ucl.ADA.model.static_information.declaration_information.AttributeDeclaration;
import com.ucl.ADA.model.static_information.declaration_information.ConstructorDeclaration;
import com.ucl.ADA.model.static_information.declaration_information.MethodDeclaration;
import com.ucl.ADA.model.static_information.declaration_information.PackageDeclaration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class StaticInfo {

    /* ************************************************************************
     *
     *  Declaration information corresponding to this class
     *
     **************************************************************************/

    /**
     * Fully qualified class package name (including the name of the class in the end).
     */
    private PackageDeclaration currentPackage = new PackageDeclaration();

    /**
     * Attributes declared in this class.
     */
    private Set<AttributeDeclaration> attributeDeclarations = new HashSet<>();

    /**
     * Constructors declared in this class.
     */
    private Set<ConstructorDeclaration> constructorDeclarations = new HashSet<>();

    /**
     * Methods declared in this class.
     */
    private Set<MethodDeclaration> methodsDeclarations = new HashSet<>();

    /* ************************************************************************
     *
     *  Dependence relations to other classes
     *
     **************************************************************************/

    /**
     * Information about the invocations of the elements from the other classes from this class. String is the qualified
     * name of the class.
     */
    private Map<String, DependenceInfo> outgoingDependenceInfos = new HashMap<>();

    /* ************************************************************************
     *
     *  External invocations (from outside of the project)
     *
     **************************************************************************/

    /**
     * External Attribute Invocations. Includes only calls to classes which cannot be resolved within the project. These
     * include the dependencies and libraries.
     */
    private Set<PackageInvocation> externalPackageImports = new HashSet<>();

    /**
     * External Method Invocations. Includes only calls to classes which cannot be resolved within the project. These
     * include the dependencies and libraries.
     */
    private Set<MethodInvocation> externalMethodInvocations = new HashSet<>();

    /**
     * External Constructor Invocations. Includes only calls to classes which cannot be resolved within the project. These
     * include the dependencies and libraries.
     */
    private Set<ConstructorInvocation> externalConstructorInvocations = new HashSet<>();

    /**
     * External Attribute Invocations. Includes only calls to classes which cannot be resolved within the project. These
     * include the dependencies and libraries.
     */
    private Set<AttributeInvocation> externalAttributeInvocations = new HashSet<>();

}
