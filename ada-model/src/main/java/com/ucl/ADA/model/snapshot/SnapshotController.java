package com.ucl.ADA.model.snapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("owners/{owner}/repositories/{repository}/branches/{branch}/snapshots")
public class SnapshotController {

    @Autowired
    private SnapshotService snapshotService;

    /**
     * Endpoint for getting all snapshots given the name of a Git repository, the username of
     * its owner and name of the branch
     * @param owner username of owner
     * @param repository name of Git repository
     * @param branch name of branch
     * @return set of all corresponding snapshots
     */
    @PostMapping
    public Set<Snapshot> getSnapshotsGivenOwnerRepoAndBranch(@PathVariable String owner,
                                                             @PathVariable String repository,
                                                             @PathVariable String branch) {
        return snapshotService.getSnapshotsGivenOwnerRepoAndBranch(owner, repository, branch);
    }

    /**
     * Endpoint for getting a snapshot give the name of a Git repository, the username of
     * its owner, name of branch and timestamp of request
     * @param owner username of owner
     * @param repository name of Git repository
     * @param branch name of branch
     * @param timestamp timestamp of request
     * @return the corresponding Snapshot
     * @throws DateTimeParseException if the timestamp string cannot be parsed
     */
    @PostMapping("/{timestamp}")
    public Snapshot getSnapshotGivenOwnerRepoBranchAndTimestamp(@PathVariable String owner,
                                                                @PathVariable String repository,
                                                                @PathVariable String branch,
                                                                @PathVariable String timestamp) throws DateTimeParseException {

        DateTimeFormatter fIn = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime odt = OffsetDateTime.parse(timestamp, fIn);

        return snapshotService.getSnapshotGivenOwnerRepoBranchAndTimestamp(owner, repository, branch, odt);
    }

}
