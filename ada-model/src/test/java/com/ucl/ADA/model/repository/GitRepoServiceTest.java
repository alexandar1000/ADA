package com.ucl.ADA.model.repository;

import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.owner.Owner;
import com.ucl.ADA.model.owner.OwnerRepository;
import com.ucl.ADA.model.owner.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GitRepoServiceTest {

    @InjectMocks
    private GitRepoService gitRepoService;

    @Mock
    private OwnerService ownerService;

    @Mock
    private GitRepoRepository repoEntityRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListRepositories(){
        List<GitRepo> repos = new ArrayList<>();
        repos.add(new GitRepo());
        repos.add(new GitRepo());

        when(repoEntityRepository.findAllByOrderByIdAsc()).thenReturn(repos);
        List<GitRepo> retrievedRepos = gitRepoService.listRepositories();
        verify(repoEntityRepository).findAllByOrderByIdAsc();

        assertThat(repos.size() == retrievedRepos.size());
        assertThat(repos.equals(retrievedRepos));
    }

    @Test
    public void testListRepoNames(){
        List<GitRepo> repos = new ArrayList<>();
        GitRepo repo1 = new GitRepo();
        repo1.setRepoName("name1");

        GitRepo repo2 = new GitRepo();
        repo2.setRepoName("name2");
        repos.add(repo1);
        repos.add(repo2);

        List<String> names = new ArrayList<>(Arrays.asList("name1", "name2"));

        when(repoEntityRepository.fetchRepoNames()).thenReturn(names);
        List<String> retrievedNames = gitRepoService.listRepoNames();
        verify(repoEntityRepository).fetchRepoNames();

        assertThat(retrievedNames.size()).isEqualTo(names.size());
        assertThat(retrievedNames).isEqualTo(names);
    }

    @Test
    public void testFindAllReposByOwner(){

        Owner owner = new Owner();
        owner.setUsername("naum97");

        Set<GitRepo> repos = new LinkedHashSet<>();
        GitRepo repo1 = new GitRepo();
        repo1.setRepoName("name1");

        GitRepo repo2 = new GitRepo();
        repo2.setRepoName("name2");
        repos.add(repo1);
        repos.add(repo2);

        owner.setRepos(repos);

        when(ownerService.getOwnerByName(any(String.class))).thenReturn(owner);
        when(ownerRepository.findByUsername(any(String.class))).thenReturn(owner);

        when(repoEntityRepository.findAllByOwner(any(Owner.class))).thenReturn(repos);

        Set<GitRepo> retrievedRepos = gitRepoService.findAllReposByOwner("naum97");

        verify(ownerService).getOwnerByName("naum97");
        verify(repoEntityRepository).findAllByOwner(any(Owner.class));

        assertThat(retrievedRepos.size()).isEqualTo(repos.size());
        assertThat(retrievedRepos).isEqualTo(repos);
    }

    @Test
    public void testFindRepoByOwnerAndName(){
        GitRepo repo = new GitRepo();
        repo.setRepoName("test_name");

        Owner owner = new Owner();
        owner.setUsername("naum97");
        repo.setOwner(owner);

        when(ownerService.getOwnerByName(any(String.class))).thenReturn(owner);
        when(ownerRepository.findByUsername(any(String.class))).thenReturn(owner);
        when(repoEntityRepository.findByOwnerAndRepoName(any(Owner.class), any(String.class))).thenReturn(repo);

        GitRepo retrievedRepo = gitRepoService.findRepoByOwnerAndRepoName("naum97", "test_name");

        verify(ownerService).getOwnerByName("naum97");
        verify(repoEntityRepository).findByOwnerAndRepoName(owner, "test_name");

        assertThat(retrievedRepo).isEqualTo(repo);
    }

    @Test
    public void testAddGitRepo(){
        GitRepo repo = new GitRepo();
        repo.setRepoName("name");

        when(repoEntityRepository.save(repo)).thenReturn(repo);
        GitRepo repo1 = gitRepoService.addGitRepo(repo);
        verify(repoEntityRepository).save(repo);

        assertThat(repo1).isEqualTo(repo);
    }

    @Test
    public void testGetRepoByOwnerAndName(){
        GitRepo repo = new GitRepo();
        repo.setRepoName("test_name");

        Owner owner = new Owner();
        owner.setUsername("naum97");
        repo.setOwner(owner);

        when(repoEntityRepository.findByOwnerAndRepoName(any(Owner.class), any(String.class))).thenReturn(repo);

        GitRepo retrievedRepo = gitRepoService.getRepoByOwnerAndName(owner, "test_name");

        verify(repoEntityRepository).findByOwnerAndRepoName(owner, "test_name");

        assertThat(retrievedRepo).isEqualTo(repo);
    }

}