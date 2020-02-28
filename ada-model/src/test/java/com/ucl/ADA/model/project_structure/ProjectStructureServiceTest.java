package com.ucl.ADA.model.project_structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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

    @Test
    void findById_idExists() {
        when(projectStructureRepository.findById(anyLong())).thenReturn(Optional.of(returnProjectStructure));

        ProjectStructure projectStructureFound = projectStructureService.findById(1L);

        assertThat(projectStructureFound.getId()).isEqualTo(1L);
    }

    @Test
    void findById_notFound() {
        when(projectStructureRepository.findById(anyLong())).thenReturn(Optional.empty());

        ProjectStructure projectStructureFound = projectStructureService.findById(1L);

        assertNull(projectStructureFound);
    }

}