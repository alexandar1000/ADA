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

    /**
     * save a ProjectStructure into Database
     *
     * @param object the ProjectStructure object to save
     * @return the ProjectStructure object saved
     */
    public ProjectStructure save(ProjectStructure object) {
        return projectStructureRepository.save(object);
    }

    /**
     * get ProjectStructure object given username, repository name, branch name and timestamp when ADA received request for the snapshot
     *
     * @param username   username
     * @param repository repository name
     * @param branch     branch name
     * @param timestamp  time when ADA received request for the snapshot
     * @return a ProjectStructure object
     */
    public ProjectStructure findByOwnerGitRepositoryBranchSnapshotTimestamp(String username, String repository, String branch, OffsetDateTime timestamp) {
        Snapshot snapshot = snapshotService.getSnapshotGivenOwnerRepoBranchAndTimestamp(username, repository, branch, timestamp);

        return projectStructureRepository.findBySnapshot(snapshot);
    }

}
