package com.ucl.ADA.model.snapshot;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SnapshotRepository extends CrudRepository<Snapshot, Long> {
        List<Snapshot> findAllByOrderBySnapshotIDAsc();
}
