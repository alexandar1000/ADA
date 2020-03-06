package com.ucl.ADA.model.project_structure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/{user_name}/repositories/{repo_name}/branches/{branch_name}/snapshots/{timestamp}/project_structure")
public class ProjectStructureController {

    @Autowired
    private ProjectStructureService projectStructureService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ProjectStructure getProjectStructureGivenOwnerRepoBranchAndTimestamp(@PathVariable String user_name,
                                                                                @PathVariable String repo_name,
                                                                                @PathVariable String branch_name,
                                                                                @PathVariable LocalDateTime timestamp) {
        return projectStructureService.findByOwnerGitRepositoryBranchSnapshotTimestamp(user_name, repo_name, branch_name, timestamp);
    }

}
