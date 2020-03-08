package com.ucl.ADA.model.snapshot;

import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.branch.BranchRepository;
import com.ucl.ADA.model.branch.BranchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SnapshotServiceTest {

    @InjectMocks
    private SnapshotService snapshotService;

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private BranchService branchService;

    @Mock
    private SnapshotRepository snapshotRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSnapshotsGivenOwnerRepoAndBranch(){
        Snapshot snapshot = new Snapshot();
        Snapshot snapshot1 = new Snapshot();

        Branch branch = new Branch();
        branch.setBranchName("master");

        snapshot.setBranch(branch);
        snapshot1.setBranch(branch);

        Set<Snapshot> snapshots = new LinkedHashSet<>();
        snapshots.add(snapshot); snapshots.add(snapshot1);

        when(branchService.getBranchGivenOwnerRepoAndName(any(), any(), any())).thenReturn(branch);
        when(snapshotRepository.findAllByBranch(any())).thenReturn(snapshots);

        Set<Snapshot> retrievedSnapshots = snapshotService.getSnapshotsGivenOwnerRepoAndBranch("naum97", "name", "master");

        verify(branchService).getBranchGivenOwnerRepoAndName("naum97", "name", "master");
        verify(snapshotRepository).findAllByBranch(branch);

        assertThat(retrievedSnapshots).isEqualTo(snapshots);
    }

    @Test
    public void testGetSnapshotGivenOwnerRepoBranchAndTimestamp(){
        Snapshot snapshot = new Snapshot();

        Branch branch = new Branch();
        branch.setBranchName("master");

        snapshot.setBranch(branch);

        when(branchService.getBranchGivenOwnerRepoAndName(any(), any(), any())).thenReturn(branch);
        when(snapshotRepository.findByBranchAndTimestamp(any(), any())).thenReturn(snapshot);

        OffsetDateTime time = OffsetDateTime.now();

        Snapshot retrievedSnapshot = snapshotService.getSnapshotGivenOwnerRepoBranchAndTimestamp("naum97", "name", "master", time);

        verify(branchService).getBranchGivenOwnerRepoAndName("naum97", "name", "master");
        verify(snapshotRepository).findByBranchAndTimestamp(branch, time);

        assertThat(retrievedSnapshot).isEqualTo(snapshot);
    }

}
