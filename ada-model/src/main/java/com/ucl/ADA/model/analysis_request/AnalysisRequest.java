package com.ucl.ADA.model.analysis_request;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ucl.ADA.model.base_entity.BaseEntity;
import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.snapshot.Snapshot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ANALYSIS_REQUEST")
public class AnalysisRequest extends BaseEntity {

    /**
     * the snapshot retrieved or newly-analyzed by the analysis request
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snapshot_id", nullable = false)
    @JsonManagedReference
    private Snapshot snapshot;

    /**
     * the branch where the analysis request exists
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    @JsonManagedReference
    private Branch branch;

    /**
     * Timestamp of when the analysis request was received, in UTC time standard
     */
    @Column(name = "timestamp", nullable = false)
    private OffsetDateTime timestamp;

}
