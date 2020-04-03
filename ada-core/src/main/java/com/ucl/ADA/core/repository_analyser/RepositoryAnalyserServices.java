package com.ucl.ADA.core.repository_analyser;

import com.google.common.collect.SetMultimap;
import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.owner.Owner;
import com.ucl.ADA.model.repository.GitRepo;
import com.ucl.ADA.model.snapshot.Snapshot;
import com.ucl.ADA.parser.ParserServices;
import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.repository_downloader.RepoDownloader;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Set;

import static com.ucl.ADA.core.transformer.ModelTransformer.*;
import static com.ucl.ADA.model.snapshot.SnapshotUtils.*;

@Service
public class RepositoryAnalyserServices {

    @Autowired
    private DatabaseUtilityService databaseUtilityService;

    @Autowired
    private ParserServices parserServices;

    public Snapshot analyseRepositoryService(String url, String branchName) {

        // Parse the repo owner and name from the url
        String[] repoInfo = RepoDownloader.parseGitUrl(url);
        String repoOwner = repoInfo[3];
        String repoName = repoInfo[4];

        // Check if the owner/repo/branch have been analyzed before
        Owner owner = databaseUtilityService.validateOwner(repoOwner);
        GitRepo repo = databaseUtilityService.validateRepo(owner, repoName);
        Branch branch = databaseUtilityService.validateBranch(repo, branchName);

        // Retrieve previous snapshot, if existing
        Snapshot prevSnapshot = null;
        if (branch != null) {
            prevSnapshot = databaseUtilityService.getLastSnapshotOfBranch(branch);
        }

        // Check last commit time through GitHub API
        // If there have been no new commits, do not re-analyze, return the previous snapshot
        OffsetDateTime lastCommitTime = RepoDownloader.getLatestCommitTime(url, branchName);
        if (lastCommitTime != null) {
            if (prevSnapshot != null && lastCommitTime.isEqual(prevSnapshot.getCommitTime())) return prevSnapshot;
        } else {
            // url OR branch name is invalid
            return null;
        }

        // if need to download and analyze again
        Snapshot snapshot;
        try {
            snapshot = getSnapshot(prevSnapshot, url, branchName);
        } catch (GitAPIException e) {
            return null;
        }

        // save entities in the database
        if (owner == null) owner = databaseUtilityService.saveOwner(repoOwner);
        if (repo == null) repo = databaseUtilityService.saveRepo(owner, repoName);
        if (branch == null) branch = databaseUtilityService.saveBranch(repo, branchName, snapshot);

        databaseUtilityService.saveSnapshot(snapshot, branch, lastCommitTime);
        databaseUtilityService.saveAnalysisRequest(branch, snapshot);

        return snapshot;
    }

    public Snapshot getSnapshot(Snapshot prevSnapshot, String url, String branchName) throws GitAPIException {

        // Downloader return {root dir path, set<file path>}, downloader automatically pick all .java files
        String rootDirPath = RepoDownloader.downloadRepository(url, branchName);
        Set<String> sourcePaths = RepoDownloader.getSourceFilePaths(rootDirPath);

        // Initialize current snapshot and source files
        Snapshot snapshot = initSnapshotAndSourceFiles(sourcePaths);

        // Get added set of SourceFile
        Set<String> addedSourceFiles = getPathsToAddedSourceFiles(snapshot, prevSnapshot);

        // Get source paths to parse from addedSourceFiles, and send {root dir path, addedSourceFiles} to Parser
        SetMultimap<String, ADAClass> filePathToClassStructuresMap = parserServices.parseRepository(rootDirPath, addedSourceFiles);

        // generate Map<String, Set<String>> pathsOfAddedSourceFilesToClassNames
        SetMultimap<String, String> pathsOfAddedSourceFilesToClassNames = getFilePathToClassNamesMap(filePathToClassStructuresMap);

        // generate Set<String> incomingToAddSet
        Set<String> incomingToAddSet = getIncomingToAddSet(filePathToClassStructuresMap);

        // reuse snapshot
        reuseClassStructuresOfSnapshot(snapshot, prevSnapshot, pathsOfAddedSourceFilesToClassNames, incomingToAddSet);

        // transform detailed information of all added class structures
        transform(snapshot, filePathToClassStructuresMap.values());

        // TODO: re-calculate all changed class structure's metric
        // this step requires change finished utility or transformer to keep track on all changed class structure
        // need to think carefully how to implement

        return snapshot;
    }

}
