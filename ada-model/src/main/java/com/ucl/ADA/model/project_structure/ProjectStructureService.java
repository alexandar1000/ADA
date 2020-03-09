package com.ucl.ADA.model.project_structure;

import com.ucl.ADA.model.snapshot.Snapshot;
import com.ucl.ADA.model.snapshot.SnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ProjectStructureService {

    @Autowired
    private SnapshotService snapshotService;

    @Autowired
    private ProjectStructureRepository projectStructureRepository;


    public ProjectStructure save(ProjectStructure object) {
        return projectStructureRepository.save(object);
    }

    public ProjectStructure findByOwnerGitRepositoryBranchSnapshotTimestamp(String username, String repository, String branch, OffsetDateTime timestamp) {
        Snapshot snapshot = snapshotService.getSnapshotGivenOwnerRepoBranchAndTimestamp(username, repository, branch, timestamp);

        return projectStructureRepository.findBySnapshot(snapshot);
    }

}
