package com.ucl.ADA.model.project_structure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStructureRepository extends CrudRepository<ProjectStructure, Long> {
}
