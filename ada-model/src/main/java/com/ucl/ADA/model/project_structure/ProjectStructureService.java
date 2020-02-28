package com.ucl.ADA.model.project_structure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectStructureService {

    @Autowired
    private ProjectStructureRepository projectStructureRepository;

    public ProjectStructure save(ProjectStructure object) {
        return projectStructureRepository.save(object);
    }

    public ProjectStructure findById(Long id) {
        return projectStructureRepository.findById(id).orElse(null);
    }

}
