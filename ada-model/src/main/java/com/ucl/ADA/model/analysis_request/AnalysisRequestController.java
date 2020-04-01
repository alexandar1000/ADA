package com.ucl.ADA.model.analysis_request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("owners/{owner}/repositories/{repository}/branches/{branch}/requests")
public class AnalysisRequestController {

    @Autowired
    private AnalysisRequestService analysisRequestService;

    /**
     * Endpoint for getting all analysis requests given a Git repository, its owner and branch
     * @param owner username of the owner
     * @param repository name of the Git repository
     * @param branch name of the branch
     * @return a set of all corresponding analysis requests
     */
    @CrossOrigin("http://localhost:4200")
    @PostMapping
    public Set<AnalysisRequest> getAnalysisRequestsByOwnerRepoAndBranch(@PathVariable String owner,
                                                                        @PathVariable String repository,
                                                                        @PathVariable String branch) {
        return analysisRequestService.getAnalysisRequestsByOwnerRepoAndBranch(owner, repository, branch);
    }

    /**
     * Endpoint for getting an analysis request entity given a Git repository,
     * its owner, name of the branch and timestamp
     * @param owner name of the Git repository owner
     * @param repository name of the Git repository
     * @param branch name of the branch
     * @param timestamp timestamp of the analysis request
     * @return the corresponding analysis request
     */
    @CrossOrigin("http://localhost:4200")
    @PostMapping("/{timestamp}")
    public AnalysisRequest getAnalysisRequestByOwnerRepoBranchAndTimestamp(@PathVariable String owner,
                                                 @PathVariable String repository,
                                                 @PathVariable String branch,
                                                 @PathVariable String timestamp) {
        return analysisRequestService.getAnalysisRequestByBranchAndTimestamp(owner, repository, branch, timestamp);
    }
}

