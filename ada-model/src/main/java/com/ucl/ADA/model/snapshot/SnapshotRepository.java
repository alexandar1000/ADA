package com.ucl.ADA.model.snapshot;

import com.ucl.ADA.model.branch.Branch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Set;

@Repository
public interface SnapshotRepository extends CrudRepository<Snapshot, Long> {

    /**
     * Get all snapshots given a branch entity
     * @param branch branch entity
     * @return a set of all corresponding snapshots
     */
    Set<Snapshot> findAllByBranch(Branch branch);

    /**
     * Get a snapshot given a branch and a timestamp
     * @param branch branch entity
     * @param timestamp timestamp of request
     * @return the corresponding snapshot
     */
    Snapshot findByBranchAndTimestamp(Branch branch, OffsetDateTime timestamp);

}
