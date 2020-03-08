package com.ucl.ADA.model.snapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("owners/{owner}/repositories/{repository}/branches/{branch}/snapshots")
public class SnapshotController {

    @Autowired
    private SnapshotService snapshotService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public Set<Snapshot> getSnapshotsGivenOwnerRepoAndBranch(@PathVariable String owner,
                                                             @PathVariable String repository,
                                                             @PathVariable String branch) {
        return snapshotService.getSnapshotsGivenOwnerRepoAndBranch(owner, repository, branch);
    }

    // not sure if last parameter should be id or timestamp, set it as id for now

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/{timestamp}")
    public Snapshot getSnapshotGivenOwnerRepoBranchAndTimestamp(@PathVariable String owner,
                                                                @PathVariable String repository,
                                                                @PathVariable String branch,
                                                                @PathVariable String timestamp) throws ParseException {

        DateTimeFormatter fIn = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        OffsetDateTime odt = OffsetDateTime.parse( timestamp , fIn );

        return snapshotService.getSnapshotGivenOwnerRepoBranchAndTimestamp(owner, repository, branch, odt);
    }

}
