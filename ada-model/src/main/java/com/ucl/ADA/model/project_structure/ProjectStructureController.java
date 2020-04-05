package com.ucl.ADA.model.project_structure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin
@RequestMapping("owners/{owner}/repositories/{repository}/branches/{branch}/snapshots/{timestamp}/project-structure")
public class ProjectStructureController {

    @Autowired
    private ProjectStructureService projectStructureService;


    /**
     * get ProjectStructure object given username, repository name, branch name and timestamp when ADA received request for the snapshot
     *
     * @param owner      username
     * @param repository repository name
     * @param branch     branch name
     * @param timestamp  time when ADA received request for the snapshot
     * @return a ProjectStructure object
     */
    @CrossOrigin
    @PostMapping
    public ProjectStructure getProjectStructureGivenOwnerRepoBranchAndTimestamp(@PathVariable String owner,
                                                                                @PathVariable String repository,
                                                                                @PathVariable String branch,
                                                                                @PathVariable String timestamp) {

        DateTimeFormatter fIn = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime odt = OffsetDateTime.parse(timestamp, fIn);

        return projectStructureService.findByOwnerGitRepositoryBranchSnapshotTimestamp(owner, repository, branch, odt);
    }

}
