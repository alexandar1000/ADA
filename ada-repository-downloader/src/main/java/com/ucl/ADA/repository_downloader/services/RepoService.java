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
import com.ucl.ADA.repository_downloader.helpers.RepoDownloader;
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


    @Autowired
    private OwnerService ownerService;

    @Autowired
    private RepoEntityRepository repoEntityRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private SnapshotRepository snapshotRepository;

    @Autowired
    private SourceFileRepository sourceFileRepository;

    /**
     * Download Git repository and populate database, following the hierarchical database model of
     * Owner -*> GitRepo -*> Branch -*> Snapshot -*> SourceFiles
     * @param url  url of the Git repository
     * @param branchName branch name of the Git repository
     * @return RepoDbPopulator object to be used by the parser
     * */
    public RepoDbPopulator downloadAndStoreRepo(String url, String branchName) throws GitAPIException {

        RepoDbPopulator repoDbPopulator = RepoDownloader.downloadRepository(url,branchName);

        return populateDatabase(repoDbPopulator);
    }

    /**
     * Populate database with the metadata of the git repository.
     * @param repoDbPopulator helper object containing the name, owner, branch and path to source files
     *                        of the downloaded Git Repository
     * @return the same RepoDbPopulator object, to be used by the the parser.
     */
    RepoDbPopulator populateDatabase(RepoDbPopulator repoDbPopulator) {

        LocalDateTime timestamp = LocalDateTime.now();

        Owner owner = initOwner(repoDbPopulator);

        GitRepository repo = initRepo(owner, repoDbPopulator);

        Branch branchEntity = initBranch(repo, repoDbPopulator);

        Snapshot snapshot = initSnapshot(timestamp, branchEntity);

        initSourceFiles(repoDbPopulator, snapshot);

        return repoDbPopulator;
    }

    /**
     * Create, initalize and store a new SourceFile entity in the DB.
     * @param repoDbPopulator helper object containing the source file names of the downloaded repository
     * @see RepoDbPopulator#getFileNames()
     * @param snapshot corresponding Snapshot entity
     */

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
    }

    /**
     * Create, initalize and save a new Snapshot entity in the DB.
     * @param timestamp timestamp of request
     * @param branchEntity corresponding Branch entity
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
     * Otherwise, create, initialize and save a new Branch entity in the DB.
     * @param repo corresponding Git repository
     * @param downloadedRepo object containing metadata of Git repository
     * @see RepoDbPopulator#getBranch()
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
     * Initialize a GitRepository entity by searching for existing repo associated with a given owner, and returning it if found.
     * Otherwise, create, initialize and return a new GitRepository entity.
     * @param owner corresponding owner
     * @param downloadedRepo object containing metadata of Git repository
     * @return an existing (or new) GitRepository
     */
    private GitRepository initRepo(Owner owner, RepoDbPopulator downloadedRepo) {

        Set<GitRepository> repos = owner.getRepos();
        String repoName = downloadedRepo.getName();
        for(GitRepository r : repos){
            if(r.getRepoName().equals(repoName)){
                return r;
            }
        }
        GitRepository repo = new GitRepository();
        repo.setOwner(owner);
        repo.setRepoName(repoName);
        return repoEntityRepository.save(repo);
    }

    /**
     * Initialize a Owner entity by searching for existing Owner in the database, and returning it if found.
     * Otherwise, create, initialize and return a new Owner entity.
     * @param downloadedRepo object containining metadata of Git repository
     * @return an existing (or new) Owner
     */
    private Owner initOwner(RepoDbPopulator downloadedRepo) {

        List<Owner> owners = ownerService.listOwners();
        String testUserName = downloadedRepo.getOwner();

        // Search and return existing user, if found
        for(Owner u : owners){
            if(u.getUserName().equals(testUserName)){
                return u;
            }
        }

        // If not found, create and return a new user
        Owner owner = new Owner();
        owner.setUserName(downloadedRepo.getOwner());
        return ownerService.addOwner(owner);
    }

    public List<GitRepository> listRepositories(){
        return repoEntityRepository.findAllByOrderByRepoIDAsc();
    }

    public List<String> listRepoNames(){
        return repoEntityRepository.fetchRepoNames();
    }
}
