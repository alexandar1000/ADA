package com.ucl.ADA.model.project_structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectStructureServiceTest {

    @Mock
    ProjectStructureRepository projectStructureRepository;

    @InjectMocks
    ProjectStructureService projectStructureService;

    ProjectStructure returnProjectStructure;

    @BeforeEach
    void setUp() {
        returnProjectStructure = new ProjectStructure();
        returnProjectStructure.setId(1L);
    }

    @Test
    void save() {
        ProjectStructure projectStructureToSave = new ProjectStructure();
        projectStructureToSave.setId(1L);

        when(projectStructureRepository.save(any())).thenReturn(returnProjectStructure);

        ProjectStructure savedProjectStructure = projectStructureService.save(projectStructureToSave);

        assertNotNull(savedProjectStructure);

        verify(projectStructureRepository).save(any());
    }



}