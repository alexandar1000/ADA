package com.ucl.ADA.core.repository_analyser;

import com.ucl.ADA.model.analysis_request.AnalysisRequest;
import com.ucl.ADA.model.analysis_request.AnalysisRequestService;
import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.branch.BranchService;
import com.ucl.ADA.model.owner.Owner;
import com.ucl.ADA.model.owner.OwnerService;
import com.ucl.ADA.model.repository.GitRepo;
import com.ucl.ADA.model.repository.GitRepoService;
import com.ucl.ADA.model.snapshot.Snapshot;
import com.ucl.ADA.model.snapshot.SnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class DatabaseUtilityService {


    @Autowired
    private OwnerService ownerService;

    @Autowired
    private GitRepoService gitRepoService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private AnalysisRequestService analysisRequestService;

    @Autowired
    private SnapshotService snapshotService;


    public Branch validateBranch(GitRepo repo, String branchName) {
        return branchService.getBranchGivenRepoAndName(repo, branchName);
    }

    public GitRepo validateRepo(Owner owner, String repoName) {
        return gitRepoService.getRepoByOwnerAndName(owner, repoName);
    }

    public Owner validateOwner(String userName) {
        return ownerService.getOwnerByName(userName);
    }

    public Owner saveOwner(String repoOwner) {
        Owner owner = new Owner();
        owner.setUsername(repoOwner);
        return ownerService.addOwner(owner);
    }

    public GitRepo saveRepo(Owner owner, String repoName) {
        GitRepo repo = new GitRepo();
        repo.setOwner(owner);
        repo.setRepoName(repoName);
        return gitRepoService.addGitRepo(repo);
    }

    public Branch saveBranch(GitRepo repo, String branchName, OffsetDateTime lastCommitTime) {
        Branch branch = new Branch();
        branch.setBranchName(branchName);
        branch.setRepository(repo);
        branch.setLastSnapshotTimestamp(lastCommitTime);
        return branchService.addBranch(branch);
    }

    public AnalysisRequest saveAnalysisRequest(Branch branch, Snapshot snapshot) {
        AnalysisRequest analysisRequest = new AnalysisRequest();
        analysisRequest.setBranch(branch);
        analysisRequest.setSnapshot(snapshot);
        analysisRequest.setTimestamp(OffsetDateTime.now());
        return analysisRequestService.addAnalysisRequest(analysisRequest);
    }

    public Snapshot getLastSnapshotOfBranch(Branch branch) {
        return snapshotService.findLastSnapshotOfBranch(branch);
    }

    public Snapshot saveSnapshot(Snapshot snapshot, Branch branch) {
        snapshot.setBranch(branch);
        snapshot.setCommitTime(branch.getLastSnapshotTimestamp());
        return snapshotService.addSnapshot(snapshot);
    }
}