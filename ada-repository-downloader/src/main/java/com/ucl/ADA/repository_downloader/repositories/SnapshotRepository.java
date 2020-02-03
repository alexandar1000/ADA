package com.ucl.ADA.repository_downloader.repositories;

import com.ucl.ADA.repository_downloader.entities.Snapshot;
import org.springframework.data.repository.CrudRepository;

public interface SnapshotRepository extends CrudRepository<Snapshot, Snapshot.ID> {
}
