package com.ucl.ADA.repository_downloader;

import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.branch.BranchRepository;
import com.ucl.ADA.model.owner.Owner;
import com.ucl.ADA.model.owner.OwnerService;
import com.ucl.ADA.model.project_structure.GitRepoInfo;
import com.ucl.ADA.model.repository.GitRepo;
import com.ucl.ADA.model.repository.GitRepoRepository;
import com.ucl.ADA.model.snapshot.Snapshot;
import com.ucl.ADA.model.snapshot.SnapshotRepository;
import com.ucl.ADA.model.source_file.SourceFile;
import com.ucl.ADA.model.source_file.SourceFileRepository;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Service class for downloading and storing the Git repository metadata in the DB (owner, repoName, branch, timestamp, .java files etc..)
 *
 * @see GitRepoInfo
 * @see RepoDownloader
 */
@Service
public class RepositoryDownloaderService {


    @Autowired
    private OwnerService ownerService;

    @Autowired
    private GitRepoRepository gitRepoRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private SnapshotRepository snapshotRepository;

    @Autowired
    private SourceFileRepository sourceFileRepository;

    /**
     * Download Git repository and populate database, following the hierarchical database model of
     * Owner -*> GitRepo -*> Branch -*> Snapshot -*> SourceFiles
     *
     * @param url        url of the Git repository
     * @param branchName branch name of the Git repository
     * @return GitRepoInfo object to be used by the parser
     */
    public GitRepoInfo downloadAndStoreRepo(String url, String branchName) throws GitAPIException {

        GitRepoInfo gitRepoInfo = RepoDownloader.downloadRepository(url, branchName);

        return populateDatabase(gitRepoInfo);
    }

    /**
     * Populate database with the metadata of the git repository.
     *
     * @param gitRepoInfo helper object containing the name, owner, branch and path to source files
     *                        of the downloaded Git Repository
     * @return the same GitRepoInfo object, to be used by the the parser.
     */
    GitRepoInfo populateDatabase(GitRepoInfo gitRepoInfo) {

        Owner owner = initOwner(gitRepoInfo);

        GitRepo repo = initRepo(owner, gitRepoInfo);

        Branch branchEntity = initBranch(repo, gitRepoInfo);

        Snapshot snapshot = initSnapshot(gitRepoInfo, branchEntity);

        gitRepoInfo.setSnapshot(snapshot);

        initSourceFiles(gitRepoInfo, snapshot);

        return gitRepoInfo;
    }

    /**
     * Create, initalize and store a new SourceFile entity in the DB.
     *
     * @param gitRepoInfo helper object containing the source file names of the downloaded repository
     * @param snapshot        corresponding Snapshot entity
     * @see GitRepoInfo#getFileNames()
     */

    private void initSourceFiles(GitRepoInfo gitRepoInfo, Snapshot snapshot) {
        List<String> fileNames = gitRepoInfo.getFileNames();

        for (String file : fileNames) {

            SourceFile sourceFile = new SourceFile();
            sourceFile.setSnapshot(snapshot);
            sourceFile.setFileName(file);

            // Not sure if this is the right way to hash file names!
            sourceFile.setFileHash(sourceFile.hashCode());

            sourceFileRepository.save(sourceFile);
        }
    }

    /**
     * Create, initalize and save a new Snapshot entity in the DB.
     *
     * @param gitRepoInfo helper object containing the source file names of the downloaded repository
     * @param branchEntity    corresponding Branch entity
     * @return saved Snapshot entity
     */
    private Snapshot initSnapshot(GitRepoInfo gitRepoInfo, Branch branchEntity) {
        Snapshot snapshot = new Snapshot();

        snapshot.setBranch(branchEntity);
        snapshot.setTimestamp(gitRepoInfo.getTimestamp());

        return snapshotRepository.save(snapshot);
    }

    /**
     * Initialize a Branch entity by searching for existing branch within a repository, and returning it if found.
     * Otherwise, create, initialize and save a new Branch entity in the DB.
     *
     * @param repo           corresponding Git repository
     * @param downloadedRepo object containing metadata of Git repository
     * @return an existing (or new) Branch entity.
     * @see GitRepoInfo#getBranch()
     */
    private Branch initBranch(GitRepo repo, GitRepoInfo downloadedRepo) {
        Set<Branch> branches = repo.getBranches();
        String branchName = downloadedRepo.getBranch();

        for (Branch b : branches) {

            if (b.getBranchName().equals(branchName)) {
                return b;
            }
        }

        Branch branchEntity = new Branch();
        branchEntity.setRepository(repo);
        branchEntity.setBranchName(branchName);

        return branchRepository.save(branchEntity);
    }

    /**
     * Initialize a GitRepository entity by searching for existing repo associated with a given owner, and returning it if found.
     * Otherwise, create, initialize and return a new GitRepository entity.
     *
     * @param owner          corresponding owner
     * @param downloadedRepo object containing metadata of Git repository
     * @return an existing (or new) GitRepository
     */
    private GitRepo initRepo(Owner owner, GitRepoInfo downloadedRepo) {

        Set<GitRepo> repos = owner.getRepos();
        String repoName = downloadedRepo.getRepository();
        for (GitRepo r : repos) {
            if (r.getRepoName().equals(repoName)) {
                return r;
            }
        }
        GitRepo repo = new GitRepo();
        repo.setOwner(owner);
        repo.setRepoName(repoName);
        return gitRepoRepository.save(repo);
    }

    /**
     * Initialize a Owner entity by searching for existing Owner in the database, and returning it if found.
     * Otherwise, create, initialize and return a new Owner entity.
     *
     * @param downloadedRepo object containining metadata of Git repository
     * @return an existing (or new) Owner
     */
    private Owner initOwner(GitRepoInfo downloadedRepo) {

        List<Owner> owners = ownerService.listOwners();
        String testUserName = downloadedRepo.getOwner();

        // Search and return existing user, if found
        for (Owner u : owners) {
            if (u.getUserName().equals(testUserName)) {
                return u;
            }
        }

        // If not found, create and return a new user
        Owner owner = new Owner();
        owner.setUserName(downloadedRepo.getOwner());
        return ownerService.addOwner(owner);
    }

    // TODO: this method is temporarily setup to connect snapshot with projectstructure
    public Snapshot getSnapshotById(Long id) {
        return snapshotRepository.findById(id).orElse(null);
    }
}
