package com.ucl.ADA.model.snapshot;

import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.branch.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class SnapshotService {

    @Autowired
    private BranchService branchService;

    @Autowired
    private SnapshotRepository snapshotRepository;


    public Set<Snapshot> getSnapshotsGivenOwnerRepoAndBranch(String user_name, String repo_name, String branch_name) {
        Branch branch = branchService.getBranchGivenOwnerRepoAndName(user_name, repo_name, branch_name);

        return snapshotRepository.findAllByBranch(branch);
    }

    public Snapshot getSnapshotGivenOwnerRepoBranchAndTimestamp(String user_name, String repo_name, String branch_name, LocalDateTime timestamp) {
        Branch branch = branchService.getBranchGivenOwnerRepoAndName(user_name, repo_name, branch_name);

        return snapshotRepository.findByBranchAndTimestamp(branch, timestamp);
    }
}
