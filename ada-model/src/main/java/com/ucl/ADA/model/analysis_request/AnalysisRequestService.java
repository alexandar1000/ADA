package com.ucl.ADA.model.analysis_request;

import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.branch.BranchService;
import com.ucl.ADA.model.snapshot.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter fIn = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime odt = OffsetDateTime.parse(timestamp, fIn);
        return analysisRequestRepository.findByBranchAndTimestamp(branch, odt);
    }

    public AnalysisRequest addAnalysisRequest(AnalysisRequest analysisRequest){
        return analysisRequestRepository.save(analysisRequest);
    }

    public Snapshot getSnapshotOfAnalysisRequestByOwnerRepoBranchAndTimestamp(String owner, String repository, String branchName, String timestamp) {
        return getAnalysisRequestByBranchAndTimestamp(owner, repository, branchName, timestamp).getSnapshot();
    }
}
