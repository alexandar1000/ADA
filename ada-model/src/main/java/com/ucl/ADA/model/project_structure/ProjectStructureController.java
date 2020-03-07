package com.ucl.ADA.model.project_structure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("owners/{owner}/repositories/{repository}/branches/{branch}/snapshots/{timestamp}/projectStructure")
public class ProjectStructureController {

    @Autowired
    private ProjectStructureService projectStructureService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ProjectStructure getProjectStructureGivenOwnerRepoBranchAndTimestamp(@PathVariable String owner,
                                                                                @PathVariable String repository,
                                                                                @PathVariable String branch,
                                                                                @PathVariable LocalDateTime timestamp) {
        return projectStructureService.findByOwnerGitRepositoryBranchSnapshotTimestamp(owner, repository, branch, timestamp);
    }

}
