package com.ucl.ADA.model.project_structure;

import com.ucl.ADA.model.snapshot.Snapshot;
import com.ucl.ADA.model.snapshot.SnapshotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectStructureServiceTest {

    @Mock
    private ProjectStructureRepository projectStructureRepository;

    @InjectMocks
    private ProjectStructureService projectStructureService;

    @Mock
    private SnapshotService snapshotService;


    private ProjectStructure returnProjectStructure;

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
    void findByOwnerGitRepositoryBranchSnapshotTimestamp() {
        ProjectStructure projectStructure = new ProjectStructure();

        Snapshot snapshot = new Snapshot();

        projectStructure.setSnapshot(snapshot);

        when(snapshotService.getSnapshotGivenOwnerRepoBranchAndTimestamp(any(), any(), any(), any())).thenReturn(snapshot);
        when(projectStructureRepository.findBySnapshot(snapshot)).thenReturn(projectStructure);

        OffsetDateTime timestamp = OffsetDateTime.now();

        ProjectStructure retrievedProjectStructure = projectStructureService.findByOwnerGitRepositoryBranchSnapshotTimestamp("bzq", "ada", "master", timestamp);

        verify(snapshotService).getSnapshotGivenOwnerRepoBranchAndTimestamp("bzq", "ada", "master", timestamp);
        verify(projectStructureRepository).findBySnapshot(snapshot);

        assertThat(retrievedProjectStructure).isEqualTo(projectStructure);

    }
}