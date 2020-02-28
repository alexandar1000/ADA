package com.ucl.ADA.model.project_structure;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProjectStructureService {

    private final ProjectStructureRepository projectStructureRepository;

    public ProjectStructure save(ProjectStructure object) {
        return projectStructureRepository.save(object);
    }



}
