package com.ucl.ADA.model.branch;

import com.ucl.ADA.model.BaseEntity;
import com.ucl.ADA.model.analysis_request.AnalysisRequest;
import com.ucl.ADA.model.repository.GitRepo;
import com.ucl.ADA.model.snapshot.Snapshot;
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
public class Branch extends BaseEntity {

    /**
     * Name of branch
     */
    private String branchName;

    /**
     * Corresponding GitRepo entity
     */
    private GitRepo repository;

    /**
     * A LinkedHashSet of all snapshots corresponding to this branch
     */
    private Set<Snapshot> snapshots = new LinkedHashSet<>();

    /**
     * Set of analysis request that retrieved this snapshot
     */
    private Set<AnalysisRequest> analysisRequests = new HashSet<>();

    /**
     * Timestamp of last snapshot, in UTC time standard
     */
    private OffsetDateTime lastSnapshotTimestamp;

}
