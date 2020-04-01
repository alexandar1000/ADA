package com.ucl.ADA.repository_downloader;

import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.branch.BranchRepository;
import com.ucl.ADA.model.owner.Owner;
import com.ucl.ADA.model.owner.OwnerService;
import com.ucl.ADA.model.repository.GitRepo;
import com.ucl.ADA.model.repository.GitRepoRepository;
import com.ucl.ADA.model.snapshot.Snapshot;
import com.ucl.ADA.model.snapshot.SnapshotRepository;
import com.ucl.ADA.model.source_file.SourceFile;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;

/**
 * Service class for downloading and storing the Git repository metadata in the DB (owner, repoName, branch, timestamp, .java files etc..)
 *
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

        Owner owner = findOwnerByName(gitRepoInfo);

        GitRepo repo = findRepoByOwnerAndName(owner, gitRepoInfo);

        Branch branchEntity = findBranchByRepoAndName(repo, gitRepoInfo);

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

            String filehash;
            try {
                filehash = sha1Hex(new FileInputStream(file));
            } catch (IOException e) {
                filehash = null;
            }

            sourceFile.setFileHash(filehash);

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
    private Branch findBranchByRepoAndName(GitRepo repo, String branchName) {
        return branchRepository.findByRepositoryAndBranchName(repo, branchName);
    }

    /**
     * Initialize a GitRepository entity by searching for existing repo associated with a given owner, and returning it if found.
     * Otherwise, create, initialize and return a new GitRepository entity.
     *
     * @param owner          corresponding owner
     * @param downloadedRepo object containing metadata of Git repository
     * @return an existing (or new) GitRepository
     */
    private GitRepo findRepoByOwnerAndName(Owner owner, String repoName) {
        return gitRepoRepository.findByOwnerAndRepoName(owner, repoName);
    }

    /**
     * Initialize a Owner entity by searching for existing Owner in the database, and returning it if found.
     * Otherwise, create, initialize and return a new Owner entity.
     *
     * @param downloadedRepo object containining metadata of Git repository
     * @return an existing (or new) Owner
     */
    private Owner findOwnerByName(String userName) {

        return ownerService.getOwnerByName(userName);
    }

    //todo: check if the null checks work
    /**
     * Validate if owner, git repository and branch are present in the DB. If they are, return the branch entity.
     * @param repoOwner username of the owner of the repository
     * @param repoName name of the repository
     * @param branchName name of the branch
     * @return the corresponding branch entity, or null if non-existant
     */
    public Branch validate(String repoOwner, String repoName, String branchName) {

        Owner owner = findOwnerByName(repoOwner);
        if(owner == null) return null;
        GitRepo repo = findRepoByOwnerAndName(owner, repoName);
        if(repo == null) return null;

        return findBranchByRepoAndName(repo, branchName);
    }
}
