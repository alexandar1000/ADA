package com.ucl.ADA.model.class_structure;

import com.ucl.ADA.model.base_entity.BaseEntity;
import com.ucl.ADA.model.dependence_information.DependenceInfo;
import com.ucl.ADA.model.dependence_information.invocation_information.AttributeInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.ConstructorInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.MethodInvocation;
import com.ucl.ADA.model.metrics.class_metrics.ClassMetricValue;
import com.ucl.ADA.model.metrics.relation_metrics.RelationMetricValue;
import com.ucl.ADA.model.snapshot.Snapshot;
import com.ucl.ADA.model.static_information.StaticInfo;
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
@Table(name = "CLASS_STRUCTURE")
public class ClassStructure extends BaseEntity {

    /* ************************************************************************
     *
     *  basic information
     *
     **************************************************************************/

    /**
     * qualified name of the class
     */
    @Column(name = "class_name")
    private String className;

    /**
     * SHA1 file hash of this source file.
     */
    @Column(name = "file_hash")
    private String fileHash;

    /**
     * Fully qualified file name of this source file
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * a set of source files that contains this class structure
     */
    @ManyToMany(mappedBy = "classStructures", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Snapshot> snapshots = new HashSet<>();

    /* ************************************************************************
     *
     *  coupling information
     *
     **************************************************************************/

    /**
     * static information contains all declarations, outgoing dependence info and external invocation which is unchanged
     * when source file is not parsed
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "static_info_id")
    private StaticInfo staticInfo = new StaticInfo();

    /**
     * Information about the invocations of elements from this class by the other classes. String is the qualified name
     * of the class.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "CLASS_STRUCTURE_INCOMING_DEPENDENCE_INFO",
            joinColumns = {@JoinColumn(name = "class_structure_id")},
            inverseJoinColumns = {@JoinColumn(name = "dependence_info_id")})
    @MapKeyColumn(name = "class_name")
    private Map<String, DependenceInfo> incomingDependenceInfos = new HashMap<>();

    /* ************************************************************************
     *
     *  metric information
     *
     **************************************************************************/

    /**
     * All of the metric values for the link between the current class and the linking classes.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "CLASS_STRUCTURE_RELATION_METRIC_VALUE",
            joinColumns = {@JoinColumn(name = "class_structure_id")},
            inverseJoinColumns = {@JoinColumn(name = "relation_metric_value_id")})
    @MapKeyColumn(name = "class_name")
    private Map<String, RelationMetricValue> relationMetricValues = new HashMap<>();

    /**
     * The metrics corresponding to the current class
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_metric_value_id")
    private ClassMetricValue classMetricValues = new ClassMetricValue();

    /* ************************************************************************
     *
     *  functions that read or update attributes of class structure and its static info
     *
     **************************************************************************/

    protected void addExternalMethodInvocations(MethodInvocation methodInvocation) {
        staticInfo.getExternalMethodInvocations().add(methodInvocation);
    }

    protected void addExternalConstructorInvocations(ConstructorInvocation constructorInvocation) {
        staticInfo.getExternalConstructorInvocations().add(constructorInvocation);
    }

    protected void addExternalAttributeInvocations(AttributeInvocation attributeInvocation) {
        staticInfo.getExternalAttributeInvocations().add(attributeInvocation);
    }

    protected Map<String, DependenceInfo> getOutgoingDependenceInfos() {
        return staticInfo.getOutgoingDependenceInfos();
    }

    protected void setPackageDeclaration(PackageDeclaration packageDeclaration) {
        staticInfo.setPackageDeclaration(packageDeclaration);
    }

    protected void addConstructorDeclaration(ConstructorDeclaration constructorDeclaration) {
        staticInfo.getConstructorDeclarations().add(constructorDeclaration);
    }

    protected void addMethodDeclaration(MethodDeclaration methodDeclaration) {
        staticInfo.getMethodDeclarations().add(methodDeclaration);
    }

    protected void addAttributeDeclaration(AttributeDeclaration attributeDeclaration) {
        staticInfo.getAttributeDeclarations().add(attributeDeclaration);
    }

    protected void addImportDeclaration(ImportDeclaration importDeclaration) {
        staticInfo.getImportDeclarations().add(importDeclaration);
    }
}
