package com.ucl.ADA.repository_downloader.services;

import com.ucl.ADA.repository_downloader.helpers.RepoDownloader;
import com.ucl.ADA.repository_downloader.entities.Branch;
import com.ucl.ADA.repository_downloader.entities.GitRepository;
import com.ucl.ADA.repository_downloader.entities.Snapshot;
import com.ucl.ADA.repository_downloader.entities.User;
import com.ucl.ADA.repository_downloader.helpers.RepoHelper;
import com.ucl.ADA.repository_downloader.repositories.BranchRepository;
import com.ucl.ADA.repository_downloader.repositories.RepoEntityRepository;
import com.ucl.ADA.repository_downloader.repositories.SnapshotRepository;
import com.ucl.ADA.repository_downloader.repositories.UserRepository;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Service class for storing the repo metadata in the database.
 *
 */
@Service
public class RepoService {

    //TODO: refactor this class.

    @Autowired private UserRepository userRepository;
    @Autowired private RepoEntityRepository repoEntityRepository;
    @Autowired private BranchRepository branchRepository;
    @Autowired private SnapshotRepository snapshotRepository;

    /**
     * Add an entry in the database, following the hierarchical database model of User -*> GitRepo -*> Branch -*> Snapshot

     */
    public void addEntry(RepoHelper repoInfoUI) {

        LocalDateTime timestamp = LocalDateTime.now();

        RepoHelper repoHelper = null;
        try {
            repoHelper = RepoDownloader.downloadRepository(repoInfoUI);
        } catch (GitAPIException e) {
            e.printStackTrace();
        }

        assert repoHelper != null;
        User user = initUser(repoHelper);

        GitRepository repo = initRepo(user, repoHelper);

        Branch branchEntity = initBranch(repo, repoHelper);

        List<String> fileNames = repoHelper.getFileNames();

        userRepository.save(user);
        repoEntityRepository.save(repo);
        branchRepository.save(branchEntity);

        for(String file : fileNames){
            Snapshot snapshot = initSnapshot(timestamp, branchEntity, file);
            snapshotRepository.save(snapshot);
        }
    }

    private Snapshot initSnapshot(LocalDateTime timestamp, Branch branchEntity, String file) {
        Snapshot snapshot = new Snapshot();
        Snapshot.ID snapshotID = new Snapshot.ID();

        snapshot.setID(snapshotID);

        snapshot.getID().setBranch(branchEntity);
        snapshot.getID().setTimestamp(timestamp);
        snapshot.getID().setFileName(file);
        return snapshot;
    }

    private Branch initBranch(GitRepository repo, RepoHelper downloadedRepo) {
        Set<Branch> branches = repo.getBranches();
        String branchName = downloadedRepo.getBranch();

        for(Branch b : branches){

            if(b.getID().getBranchName().equals(branchName)){
                return b;
            }
        }

        Branch branchEntity = new Branch();
        Branch.ID ID = new Branch.ID();

        branchEntity.setID(ID);

        branchEntity.getID().setRepository(repo);
        branchEntity.getID().setBranchName(branchName);

        return branchEntity;
    }

    private GitRepository initRepo(User user, RepoHelper downloadedRepo) {

        Set<GitRepository> repos = user.getRepos();
        String repoName = downloadedRepo.getName();
        for(GitRepository r : repos){
            if(r.getRepoName().equals(repoName)){
                return r;
            }
        }
        GitRepository repo = new GitRepository();
        repo.setUser(user);
        repo.setRepoName(repoName);
        return repo;
    }

    private User initUser(RepoHelper repo) {

        List<User> users = (List<User>) userRepository.findAll();
        String testUserName = repo.getOwner();

        // Search and return existing user, if found
        for(User u : users){
            if(u.getUserName().equals(testUserName)){
                return u;
            }
        }

        // If not found, create and return a new user
        User user = new User();
        user.setUserName(repo.getOwner());
        return user;
    }
}
