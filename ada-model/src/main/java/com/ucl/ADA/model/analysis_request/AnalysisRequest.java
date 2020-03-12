package com.ucl.ADA.model.analysis_request;

import com.ucl.ADA.model.BaseEntity;
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
     * the snapshot retrieved by analysis request
     */
    private Snapshot snapshot;

    /**
     * Timestamp of when the analysis request was received, in UTC time standard
     */
    private OffsetDateTime timestamp;

}
