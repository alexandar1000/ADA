package com.ucl.ADA.model.analysis_request;

import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.branch.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AnalysisRequestService {

    @Autowired
    private BranchService branchService;
    @Autowired
    private AnalysisRequestRepository analysisRequestRepository;

    public Set<AnalysisRequest> getAnalysisRequestsByOwnerRepoAndBranch(String owner, String repository, String branchName) {

        Branch branch = branchService.getBranchGivenOwnerRepoAndName(owner, repository, branchName);
        return analysisRequestRepository.findAllByBranch(branch);

    }

    public AnalysisRequest getAnalysisRequestByBranchAndTimestamp(String owner, String repository, String branchName, String timestamp) {

        Branch branch = branchService.getBranchGivenOwnerRepoAndName(owner, repository, branchName);

        return analysisRequestRepository.findByBranchAndTimestamp(branch, timestamp);
    }

    public AnalysisRequest addAnalysisRequest(AnalysisRequest analysisRequest){
        return analysisRequestRepository.save(analysisRequest);
    }
}
