package com.ucl.ADA.model.project_structure;

import com.ucl.ADA.model.snapshot.Snapshot;
import org.springframework.data.repository.CrudRepository;

public interface ProjectStructureRepository extends CrudRepository<ProjectStructure, Long> {

    ProjectStructure findBySnapshot(Snapshot snapshot);

}
