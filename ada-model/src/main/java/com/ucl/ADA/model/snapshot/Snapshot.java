package com.ucl.ADA.model.snapshot;

import com.ucl.ADA.model.BaseEntity;
import com.ucl.ADA.model.analysis_request.AnalysisRequest;
import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.source_file.SourceFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
     * Set of source files contained in this snapshot
     */
    private Set<SourceFile> sourceFiles = new LinkedHashSet<>();

    /**
     * Set of analysis request that retrieved this snapshot
     */
    private Set<AnalysisRequest> analysisRequests = new HashSet<>();

    /**
     * Timestamp of the commit time of the snapshot, in UTC time standard
     */
    private OffsetDateTime timestamp;

}
