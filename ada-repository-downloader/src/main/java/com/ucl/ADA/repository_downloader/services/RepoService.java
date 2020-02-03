package com.ucl.ADA.repository_downloader.services;

import com.ucl.ADA.repository_downloader.helpers.RepoDownloader;
import com.ucl.ADA.repository_downloader.entities.Branch;
import com.ucl.ADA.repository_downloader.entities.GitRepository;
import com.ucl.ADA.repository_downloader.entities.Snapshot;
import com.ucl.ADA.repository_downloader.entities.User;
import com.ucl.ADA.repository_downloader.helpers.RepoDbPopulator;
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
 * Service class for downloading and storing the Git repository metadata in the DB (owner, repoName, branch, timestamp, .java files etc..)
 * @see RepoDbPopulator
 * @see RepoDownloader
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
     * Download Git repository and populate database, following the hierarchical database model of User -*> GitRepo -*> Branch -*> Snapshot
     * @param repoInfoUI - instance of RepoDbPopulator which initially holds the git URL and branch name
     *
     * */
    public void addEntry(RepoDbPopulator repoInfoUI) throws GitAPIException {

        LocalDateTime timestamp = LocalDateTime.now();

        RepoDbPopulator repoDbPopulator = RepoDownloader.downloadRepository(repoInfoUI);

        User user = initUser(repoDbPopulator);

        GitRepository repo = initRepo(user, repoDbPopulator);

        Branch branchEntity = initBranch(repo, repoDbPopulator);

        List<String> fileNames = repoDbPopulator.getFileNames();

        userRepository.save(user);
        repoEntityRepository.save(repo);
        branchRepository.save(branchEntity);

        for(String file : fileNames){
            Snapshot snapshot = initSnapshot(timestamp, branchEntity, file);
            snapshotRepository.save(snapshot);
        }
    }

    /**
     * Create and initialize a new Snapshot entity.
     * @param timestamp
     * @param branchEntity
     * @param file
     * @return
     */
    private Snapshot initSnapshot(LocalDateTime timestamp, Branch branchEntity, String file) {
        Snapshot snapshot = new Snapshot();
        Snapshot.ID snapshotID = new Snapshot.ID();

        snapshot.setID(snapshotID);

        snapshot.getID().setBranch(branchEntity);
        snapshot.getID().setTimestamp(timestamp);
        snapshot.getID().setFileName(file);
        return snapshot;
    }

    /**
     * Initialize a Branch entity by searching for existing branch within a repository, and returning it if found.
     * Otherwise, create, initialize and return a new Branch entity.
     * @param repo
     * @param downloadedRepo
     * @return an existing (or new) Branch entity.
     */
    private Branch initBranch(GitRepository repo, RepoDbPopulator downloadedRepo) {
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

    /**
     * Initialize a GitRepository entity by searching for existing repo associated with a given user, and returning it if found.
     * Otherwise, create, initialize and return a new GitRepository entity.
     * @param user
     * @param downloadedRepo
     * @return an existing (or new) GitRepository
     */
    private GitRepository initRepo(User user, RepoDbPopulator downloadedRepo) {

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

    /**
     * Initialize a User entity by searching for existing User in the database, and returning it if found.
     * Otherwise, create, initialize and return a new User entity.
     * @param repo
     * @return an existing (or new) User
     */
    private User initUser(RepoDbPopulator repo) {

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
