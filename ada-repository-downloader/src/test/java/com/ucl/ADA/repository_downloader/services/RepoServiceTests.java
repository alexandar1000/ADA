package com.ucl.ADA.repository_downloader.services;

import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.branch.BranchRepository;
import com.ucl.ADA.model.owner.Owner;
import com.ucl.ADA.model.repository.GitRepository;
import com.ucl.ADA.model.repository.RepoEntityRepository;
import com.ucl.ADA.model.snapshot.Snapshot;
import com.ucl.ADA.model.snapshot.SnapshotRepository;
import com.ucl.ADA.model.source_file.SourceFile;
import com.ucl.ADA.model.source_file.SourceFileRepository;
import com.ucl.ADA.repository_downloader.helpers.RepoDbPopulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RepoServiceTests {
    @InjectMocks
    private RepoService repoService;

    @Mock
    private OwnerService ownerService;

    @Mock
    private RepoEntityRepository repoEntityRepository;

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private SnapshotRepository snapshotRepository;

    @Mock
    private SourceFileRepository sourceFileRepository;


    public RepoDbPopulator repoDbPopulator;

    @BeforeEach
    void setup(){
        repoDbPopulator = new RepoDbPopulator();
        repoDbPopulator.setName("test_name");
        repoDbPopulator.setOwner("naum");
        repoDbPopulator.setBranch("master");
        repoDbPopulator.setDirectoryPath("/some/dir/path");
        repoDbPopulator.setUrl("www.github.com");
        repoDbPopulator.setFileNames(new ArrayList<>(Arrays.asList("/some/dir/path/Main.java", "/some/dir/path/Test.java")));
    }
    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPopulateDatabase(){

        Owner owner = new Owner();
        owner.setUserName(repoDbPopulator.getName());

        when(ownerService.addOwner(any(Owner.class))).thenReturn(owner);

        GitRepository repo = new GitRepository();
        repo.setOwner(owner);
        repo.setRepoName(repoDbPopulator.getName());

        when(repoEntityRepository.save(any(GitRepository.class))).thenReturn(repo);

        Branch branchEntity = new Branch();
        branchEntity.setRepository(repo);
        branchEntity.setBranchName(repoDbPopulator.getBranch());

        when(branchRepository.save(any(Branch.class))).thenReturn(branchEntity);

        Snapshot snapshot = new Snapshot();
        snapshot.setBranch(branchEntity);

        when(snapshotRepository.save(any(Snapshot.class))).thenReturn(snapshot);

        SourceFile sourceFile = new SourceFile();
        sourceFile.setSnapshot(snapshot);
        sourceFile.setFileName(repoDbPopulator.getFileNames().get(0));

        when(sourceFileRepository.save(any(SourceFile.class))).thenReturn(sourceFile);

        RepoDbPopulator retDbPopulator = repoService.populateDatabase(repoDbPopulator);

        assertThat(retDbPopulator.equals(repoDbPopulator));

        verify(ownerService).listOwners();
        verify(ownerService).addOwner(any());
        verify(repoEntityRepository).save(any());
        verify(branchRepository).save(any());
        verify(snapshotRepository).save(any());
        verify(sourceFileRepository,times(repoDbPopulator.getFileNames().size())).save(any());

    }
}
