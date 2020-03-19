package com.ucl.ADA.model.snapshot;

import com.ucl.ADA.model.branch.Branch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface SnapshotRepository extends CrudRepository<Snapshot, Long> {

    Snapshot findSnapshotByBranchAndCommitTime(Branch branch, OffsetDateTime commitTime);

}
