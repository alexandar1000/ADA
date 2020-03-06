package com.ucl.ADA.model.project_structure;

import com.ucl.ADA.model.snapshot.Snapshot;
import com.ucl.ADA.model.snapshot.SnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProjectStructureService {

    @Autowired
    private SnapshotService snapshotService;

    @Autowired
    private ProjectStructureRepository projectStructureRepository;


    public ProjectStructure save(ProjectStructure object) {
        return projectStructureRepository.save(object);
    }

    public ProjectStructure findByOwnerGitRepositoryBranchSnapshotTimestamp(String user_name, String repo_name, String branch_name, LocalDateTime timestamp) {
        Snapshot snapshot = snapshotService.getSnapshotGivenOwnerRepoBranchAndTimestamp(user_name, repo_name, branch_name, timestamp);

        return projectStructureRepository.findBySnapshot(snapshot);
    }

}
