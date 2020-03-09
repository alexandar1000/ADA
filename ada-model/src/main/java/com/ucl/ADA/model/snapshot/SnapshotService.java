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

    /**
     * Get all snapshots given a name of a Git repository, the username of its owner and the name of the branch
     * @param username name of the owner
     * @param repository name of the Git repository
     * @param branchName name of the branch
     * @return a set of all corresponding snapshots
     */
    public Set<Snapshot> getSnapshotsGivenOwnerRepoAndBranch(String username, String repository, String branchName) {
        Branch branch = branchService.getBranchGivenOwnerRepoAndName(username, repository, branchName);

        return snapshotRepository.findAllByBranch(branch);
    }

    /**
     * Get a snapshot given a name of a Git repository, the username of its owner, the branch name and timestamp
     * @param username name of the owner
     * @param repository name of the Git repository
     * @param branchName name of the branch
     * @param timestamp timestamp of a given request
     * @return the corresponding Snapshot
     */
    public Snapshot getSnapshotGivenOwnerRepoBranchAndTimestamp(String username, String repository, String branchName, OffsetDateTime timestamp) {
        Branch branch = branchService.getBranchGivenOwnerRepoAndName(username, repository, branchName);

        return snapshotRepository.findByBranchAndTimestamp(branch, timestamp);
    }
}
