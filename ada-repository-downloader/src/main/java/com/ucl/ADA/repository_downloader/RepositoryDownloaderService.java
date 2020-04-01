package com.ucl.ADA.repository_downloader;

import com.ucl.ADA.model.analysis_request.AnalysisRequest;
import com.ucl.ADA.model.analysis_request.AnalysisRequestRepository;
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
import java.time.OffsetDateTime;
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

    @Autowired
    private AnalysisRequestRepository analysisRequestRepository;


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

        Owner owner = saveOwner(repoOwner);

        GitRepo repo = validateRepo(owner, gitRepoInfo);

        Branch branchEntity = validateBranch(repo, gitRepoInfo);

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

    public Branch validateBranch(GitRepo repo, String branchName) {
        return branchRepository.findByRepositoryAndBranchName(repo, branchName);
    }

    public GitRepo validateRepo(Owner owner, String repoName) {
        return gitRepoRepository.findByOwnerAndRepoName(owner, repoName);
    }

    public Owner validateOwner(String userName) {

        return ownerService.getOwnerByName(userName);
    }

    public Owner saveOwner(String repoOwner) {
        Owner owner = new Owner();
        owner.setUsername(repoOwner);
        return ownerService.addOwner(owner);
    }

    public GitRepo saveRepo(Owner owner, String repoName) {
        GitRepo repo = new GitRepo();
        repo.setOwner(owner);
        repo.setRepoName(repoName);
        return gitRepoRepository.save(repo);
    }

    public Branch saveBranch(GitRepo repo, String branchName, Snapshot snapshot) {
        Branch branch = new Branch();
        branch.setBranchName(branchName);
        branch.setRepository(repo);
        branch.setLastSnapshotTimestamp(snapshot.getCommitTime());

        return branchRepository.save(branch);
    }

    public AnalysisRequest saveAnalysisRequest(Branch branch, Snapshot snapshot) {
        AnalysisRequest analysisRequest = new AnalysisRequest();
        analysisRequest.setBranch(branch);
        analysisRequest.setSnapshot(snapshot);
        analysisRequest.setTimestamp(OffsetDateTime.now());

        return analysisRequestRepository.save(analysisRequest);
    }
}
