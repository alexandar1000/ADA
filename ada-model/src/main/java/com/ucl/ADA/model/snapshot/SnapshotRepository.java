package com.ucl.ADA.model.snapshot;

import com.ucl.ADA.model.branch.Branch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Set;

@Repository
public interface SnapshotRepository extends CrudRepository<Snapshot, Long> {

    Set<Snapshot> findAllByBranch(Branch branch);

    Snapshot findByBranchAndTimestamp(Branch branch, OffsetDateTime timestamp);

}
