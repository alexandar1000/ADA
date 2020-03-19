package com.ucl.ADA.model.analysis_request;

import com.ucl.ADA.model.base_entity.BaseEntity;
import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.snapshot.Snapshot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AnalysisRequest extends BaseEntity {

    /**
     * the snapshot retrieved or newly-analyzed by the analysis request
     */
    private Snapshot snapshot;

    /**
     * the branch where the analysis request exists
     */
    private Branch branch;

    /**
     * Timestamp of when the analysis request was received, in UTC time standard
     */
    private OffsetDateTime timestamp;

}
