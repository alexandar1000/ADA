package com.ucl.ADA.model.branch;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ucl.ADA.model.base_entity.BaseEntity;
import com.ucl.ADA.model.analysis_request.AnalysisRequest;
import com.ucl.ADA.model.repository.GitRepo;
import com.ucl.ADA.model.snapshot.Snapshot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BRANCH")
public class Branch extends BaseEntity {

    /**
     * Name of branch
     */
    @Column(name = "branch_name", nullable = false)
    private String branchName;

    /**
     * Corresponding GitRepo entity
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repo_id", nullable = false)
    @JsonManagedReference
    private GitRepo repository;

    /**
     * A LinkedHashSet of all snapshots corresponding to this branch
     */
    @OneToMany(mappedBy = "branch", targetEntity = Snapshot.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Snapshot> snapshots = new HashSet<>();

    /**
     * Set of analysis request that retrieved this snapshot
     */
    @OneToMany(mappedBy = "branch", targetEntity = AnalysisRequest.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<AnalysisRequest> analysisRequests = new LinkedHashSet<>();

    /**
     * Timestamp of last snapshot, in UTC time standard
     */
    @Column(name = "timestamp", nullable = false)
    private OffsetDateTime lastSnapshotTimestamp;

}
