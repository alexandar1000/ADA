package com.ucl.ADA.model.class_structure;

import com.ucl.ADA.model.base_entity.BaseEntity;
import com.ucl.ADA.model.dependence_information.DependenceInfo;
import com.ucl.ADA.model.metrics.class_metrics.ClassMetricValue;
import com.ucl.ADA.model.metrics.relation_metrics.RelationMetricValue;
import com.ucl.ADA.model.snapshot.Snapshot;
import com.ucl.ADA.model.static_information.StaticInfo;
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
public class ClassStructure extends BaseEntity {

    /* ************************************************************************
     *
     *  basic information
     *
     **************************************************************************/

    /**
     * qualified name of the class
     */
    private String className;

    /**
     * SHA1 file hash of this source file.
     */
    private String fileHash;

    /**
     * Fully qualified file name of this source file
     */
    private String fileName;

    /**
     * a set of source files that contains this class structure
     */
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
    private StaticInfo staticInfo = new StaticInfo();

    /**
     * Information about the invocations of elements from this class by the other classes. String is the qualified name
     * of the class.
     */
    private Map<String, DependenceInfo> incomingDependenceInfos = new HashMap<>();

    /**
     * All of the metric values for the link between the current class and the linking classes.
     */
    private Map<String, RelationMetricValue> relationMetricValues = new HashMap<>();

    /**
     * The metrics corresponding to the current class
     */
    private ClassMetricValue classMetricValues = new ClassMetricValue();

    // TODO: write function to update attributes
}
