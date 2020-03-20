package com.ucl.ADA.model.snapshot;

import com.ucl.ADA.model.analysis_request.AnalysisRequest;
import com.ucl.ADA.model.base_entity.BaseEntity;
import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.class_structure.ClassStructure;
import com.ucl.ADA.model.source_file.SourceFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Snapshot extends BaseEntity {

    /**
     * Branch entity corresponding to this snapshot
     */
    private Branch branch;

    /**
     * a map of ClassStructures, the key is qualified class name
     */
    private Map<String, ClassStructure> classStructures = new HashMap<>();

    /**
     * Set of analysis request that retrieved this snapshot
     */
    private Set<AnalysisRequest> analysisRequests = new HashSet<>();

    /**
     * Timestamp of the commit time of the snapshot, in UTC time standard
     */
    private OffsetDateTime commitTime;

    /**
     * a map of source files, the key is the file path
     */
    private Map<String, SourceFile> sourceFiles = new HashMap<>();

    // TODO: write functions to update attributes
}
