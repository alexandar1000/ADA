package com.ucl.ADA.model.project_structure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("owners/{owner}/repositories/{repository}/branches/{branch}/snapshots/{timestamp}/project-structure")
public class ProjectStructureController {

    @Autowired
    private ProjectStructureService projectStructureService;


    @CrossOrigin(origins = "http://localhost:4200")
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
