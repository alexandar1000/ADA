package com.ucl.ADA.model.branch;

import com.ucl.ADA.model.owner.Owner;
import com.ucl.ADA.model.repository.GitRepo;
import com.ucl.ADA.model.repository.GitRepoRepository;
import com.ucl.ADA.model.repository.GitRepoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BranchServiceTest {

    @InjectMocks
    private BranchService branchService;

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private GitRepoService gitRepoService;

    @Mock
    private GitRepoRepository gitRepoRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetBranchesByOwnerAndRepo(){
        Branch branch = new Branch();
        Branch branch2 = new Branch();

        branch.setBranchName("test1");
        branch2.setBranchName("test2");

        GitRepo repo = new GitRepo();

        branch.setRepository(repo);
        branch2.setRepository(repo);

        Set<Branch> branches = new LinkedHashSet<>();
        branches.add(branch); branches.add(branch2);

        when(gitRepoService.findRepoByOwnerAndRepoName(any(), any())).thenReturn(repo);
        when(branchRepository.findAllByRepository(any())).thenReturn(branches);

        Set<Branch> retrievedBranches = branchService.getBranchesByOwnerAndRepo("naum97", "name");

        verify(gitRepoService).findRepoByOwnerAndRepoName("naum97", "name");
        verify(branchRepository).findAllByRepository(repo);

        assertThat(retrievedBranches).isEqualTo(branches);
    }

    @Test
    public void testGetBrancheByOwnerRepoAndName(){
        Branch branch = new Branch();
        branch.setBranchName("test1");

        GitRepo repo = new GitRepo();

        branch.setRepository(repo);

        when(gitRepoService.findRepoByOwnerAndRepoName(any(), any())).thenReturn(repo);
        when(branchRepository.findByRepositoryAndBranchName(any(), any())).thenReturn(branch);

        Branch retrievedBranch = branchService.getBranchGivenOwnerRepoAndName("naum97", "name", "test1");

        verify(gitRepoService).findRepoByOwnerAndRepoName("naum97", "name");
        verify(branchRepository).findByRepositoryAndBranchName(repo, "test1");

        assertThat(retrievedBranch).isEqualTo(branch);
    }

}
