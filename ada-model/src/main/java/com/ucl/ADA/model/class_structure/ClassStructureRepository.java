package com.ucl.ADA.model.class_structure;

import com.ucl.ADA.model.snapshot.Snapshot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassStructureRepository extends CrudRepository<ClassStructure, Long> {

    ClassStructure findBySnapshotsAndClassName(Snapshot snapshot, String className);

}
