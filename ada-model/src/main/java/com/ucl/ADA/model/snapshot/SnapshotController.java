package com.ucl.ADA.model.snapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/{user_name}/repositories/{repo_name}/branches/{branch_name}/snapshots")
public class SnapshotController {

    @Autowired
    private SnapshotService snapshotService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public Set<Snapshot> getSnapshotsGivenOwnerRepoAndBranch(@PathVariable String user_name,
                                                             @PathVariable String repo_name,
                                                             @PathVariable String branch_name) {
        return snapshotService.getSnapshotsGivenOwnerRepoAndBranch(user_name, repo_name, branch_name);
    }

    // not sure if last parameter should be id or timestamp, set it as id for now

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/{timestamp}")
    public Snapshot getSnapshotGivenOwnerRepoBranchAndTimestamp(@PathVariable String user_name,
                                                                @PathVariable String repo_name,
                                                                @PathVariable String branch_name,
                                                                @PathVariable LocalDateTime timestamp) {
        return snapshotService.getSnapshotGivenOwnerRepoBranchAndTimestamp(user_name, repo_name, branch_name, timestamp);
    }

}
