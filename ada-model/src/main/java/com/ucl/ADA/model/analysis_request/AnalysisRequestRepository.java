package com.ucl.ADA.model.analysis_request;

import com.ucl.ADA.model.branch.Branch;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AnalysisRequestRepository extends CrudRepository<AnalysisRequest, Long> {
    Set<AnalysisRequest> findAllByBranch(Branch branch);

    AnalysisRequest findByBranchAndTimestamp(Branch branch, String timestamp);
}
