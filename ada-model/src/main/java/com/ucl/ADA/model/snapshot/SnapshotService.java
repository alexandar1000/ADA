package com.ucl.ADA.model.snapshot;

import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.branch.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Set;

@Service
public class SnapshotService {

    @Autowired
    private BranchService branchService;

    @Autowired
    private SnapshotRepository snapshotRepository;


    public Set<Snapshot> getSnapshotsGivenOwnerRepoAndBranch(String username, String repository, String branchName) {
        Branch branch = branchService.getBranchGivenOwnerRepoAndName(username, repository, branchName);

        return snapshotRepository.findAllByBranch(branch);
    }

    public Snapshot getSnapshotGivenOwnerRepoBranchAndTimestamp(String username, String repository, String branchName, OffsetDateTime timestamp) {
        Branch branch = branchService.getBranchGivenOwnerRepoAndName(username, repository, branchName);

        return snapshotRepository.findByBranchAndTimestamp(branch, timestamp);
    }
}
