package com.ucl.ADA.core.per_branch_analysis;

import com.google.common.collect.SetMultimap;
import com.ucl.ADA.model.snapshot.Snapshot;
import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.repository_downloader.RepoDownloader;
import com.ucl.ADA.repository_downloader.RepositoryDownloaderService;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.ucl.ADA.core.transformer.ModelTransformer.*;
import static com.ucl.ADA.model.snapshot.SnapshotUtils.*;

public class RepositoryAnalyserServices {

    private RepositoryDownloaderService repositoryDownloaderService;

    public Snapshot analyseRepositoryService(String url, String branchName) {

        // TODO: parse url and branch name

        // TODO: validate owner, repo and branch

        // Retrieve previous snapshot
        Snapshot prevSnapshot = new Snapshot();

        // Check last commit time through GitHub API
        OffsetDateTime lastCommitTime = RepoDownloader.getLatestCommitTime(url, branchName);
        if (lastCommitTime != null) {
            if (lastCommitTime.isEqual(prevSnapshot.getCommitTime()))
                return prevSnapshot;
        }

        // if need to download and analyze again
        Snapshot snapshot = getSnapshot(prevSnapshot);

        // TODO: record analysis request

        // TODO: store everything

        // TODO: pack the owner, repo and branch info into snapshot (through private class?)
        return snapshot;
    }

    public Snapshot getSnapshot(Snapshot prevSnapshot) {
        // TODO: call downloader to download the repo

        // Downloader return {root dir path, set<file path>}, downloader automatically pick all .java files
        Set<String> sourcePaths = new HashSet<>(); // all java file path

        // TODO: define which file paths to read?
        // write a method to remove all testing files or (move the existing functions from parser?)
        // parse the file path into root and set of file paths (divided by snapshot timestamp)
        // all valid java file path (.mvwn /test)

        // Initialize current snapshot and source files
        Snapshot snapshot = initSnapshotAndSourceFiles(sourcePaths);

        // Get added set of SourceFile
        Set<String> addedSourceFiles = getPathsToAddedSourceFiles(snapshot, prevSnapshot);

        // all valid java file path that need to parse

        // TODO: Get source paths to parse from addedSourceFiles, and send {root dir path, addedSourceFiles} to Parser

        // TODO: Parser return map<file path, set<ADAClass>> (use guava SetMultimap?)
        SetMultimap<String, ADAClass> filePathToClassStructuresMap = null;

        // generate Map<String, Set<String>> pathsOfAddedSourceFilesToClassNames
        SetMultimap<String, String> pathsOfAddedSourceFilesToClassNames = getFilePathToClassNamesMap(filePathToClassStructuresMap);

        // generate Set<String> incomingToAddSet
        Set<String> incomingToAddSet = getIncomingToAddSet(filePathToClassStructuresMap);

        // reuse snapshot
        reuseClassStructuresOfSnapshot(snapshot, prevSnapshot, pathsOfAddedSourceFilesToClassNames, incomingToAddSet);

        // transform detailed information of all added class structures
        transform(snapshot, (Set<ADAClass>) filePathToClassStructuresMap.values());

        // TODO: re-calculate all changed class structure's metric
        // this step requires change finished utility or transformer to keep track on all changed class structure
        // need to think carefully how to implement

        return snapshot;
    }

}
