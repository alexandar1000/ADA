package com.ucl.ADA.repository_downloader.services;

import com.ucl.ADA.repository_downloader.entities.*;
import com.ucl.ADA.repository_downloader.helpers.RepoDownloader;
import com.ucl.ADA.repository_downloader.helpers.RepoDbPopulator;
import com.ucl.ADA.repository_downloader.repositories.*;
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
    @Autowired private SourceFileRepository sourceFileRepository;

    /**
     * Download Git repository and populate database, following the hierarchical database model of User -*> GitRepo -*> Branch -*> Snapshot
     * @param repoInfoUI - instance of RepoDbPopulator which initially holds the git URL and branch name
     *
     * */
    public User addEntry(RepoDbPopulator repoInfoUI) throws GitAPIException {

        LocalDateTime timestamp = LocalDateTime.now();

        RepoDbPopulator repoDbPopulator = RepoDownloader.downloadRepository(repoInfoUI);

        User user = initUser(repoDbPopulator);

        GitRepository repo = initRepo(user, repoDbPopulator);

        Branch branchEntity = initBranch(repo, repoDbPopulator);

        Snapshot snapshot = initSnapshot(timestamp, branchEntity);

        initSourceFiles(repoDbPopulator, snapshot);

        return user;
    }

    private void initSourceFiles(RepoDbPopulator repoDbPopulator, Snapshot snapshot) {
        List<String> fileNames = repoDbPopulator.getFileNames();

        for(String file : fileNames){

            SourceFile sourceFile = new SourceFile();
            sourceFile.setSnapshot(snapshot);
            sourceFile.setFileName(file);

            // Not sure if this is the right way to hash file names!
            sourceFile.setFileHash(sourceFile.hashCode());

            sourceFileRepository.save(sourceFile);
        }

        return user;
    }

    /**
     * Create and initialize a new Snapshot entity.
     * @param timestamp timestamp of request
     * @param branchEntity corresponding branch
     * @return saved Snapshot entity
     */
    private Snapshot initSnapshot(LocalDateTime timestamp, Branch branchEntity) {
        Snapshot snapshot = new Snapshot();

        snapshot.setBranch(branchEntity);
        snapshot.setTimestamp(timestamp);

        return snapshotRepository.save(snapshot);
    }

    /**
     * Initialize a Branch entity by searching for existing branch within a repository, and returning it if found.
     * Otherwise, create, initialize and return a new Branch entity.
     * @param repo corresponding Git repository
     * @param downloadedRepo object containing metadata of Git repository
     * @return an existing (or new) Branch entity.
     */
    private Branch initBranch(GitRepository repo, RepoDbPopulator downloadedRepo) {
        Set<Branch> branches = repo.getBranches();
        String branchName = downloadedRepo.getBranch();

        for(Branch b : branches){

            if(b.getBranchName().equals(branchName)){
                return b;
            }
        }

        Branch branchEntity = new Branch();
        branchEntity.setRepository(repo);
        branchEntity.setBranchName(branchName);

        return branchRepository.save(branchEntity);
    }

    /**
     * Initialize a GitRepository entity by searching for existing repo associated with a given user, and returning it if found.
     * Otherwise, create, initialize and return a new GitRepository entity.
     * @param user corresponding user
     * @param downloadedRepo object containing metadata of Git repository
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
        return repoEntityRepository.save(repo);
    }

    /**
     * Initialize a User entity by searching for existing User in the database, and returning it if found.
     * Otherwise, create, initialize and return a new User entity.
     * @param downloadedRepo object containining metadata of Git repository
     * @return an existing (or new) User
     */
    private User initUser(RepoDbPopulator downloadedRepo) {

        List<User> users = (List<User>) userRepository.findAll();
        String testUserName = downloadedRepo.getOwner();

        // Search and return existing user, if found
        for(User u : users){
            if(u.getUserName().equals(testUserName)){
                return u;
            }
        }

        // If not found, create and return a new user
        User user = new User();
        user.setUserName(downloadedRepo.getOwner());
        return userRepository.save(user);
    }
}
