package com.ucl.ADA.model.project_structure;

import com.ucl.ADA.model.snapshot.Snapshot;
import org.springframework.data.repository.CrudRepository;

public interface ProjectStructureRepository extends CrudRepository<ProjectStructure, Long> {

    /**
     * find ProjectStructure object by the snapshot it belongs to
     *
     * @param snapshot a Snapshot object
     * @return a ProjectStructure object
     */
    ProjectStructure findBySnapshot(Snapshot snapshot);

}
