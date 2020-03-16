package com.ucl.ADA.model.snapshot;

import com.ucl.ADA.model.branch.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class SnapshotService {

    @Autowired
    private SnapshotRepository snapshotRepository;

    /**
     * find the snapshot given the branch and commit time
     *
     * @param branch     the branch that owns the snapshot
     * @param commitTime the commit time of the snapshot
     * @return the snapshot object given the branch and commit time
     */
    public Snapshot findSnapshotByBranchAndTimestamp(Branch branch, OffsetDateTime commitTime) {
        return snapshotRepository.findSnapshotByBranchAndCommitTime(branch, commitTime);
    }

    /**
     * find the last snapshot of the given branch
     *
     * @param branch the branch that owns the snapshot
     * @return the last snapshot of the given branch
     */
    public Snapshot findLastSnapshotOfBranch(Branch branch) {
        OffsetDateTime commitTime = branch.getLastSnapshotTimestamp();
        return findSnapshotByBranchAndTimestamp(branch, commitTime);
    }

}
