package com.ucl.ADA.core.per_branch_analysis;

import com.google.common.collect.SetMultimap;
import com.ucl.ADA.core.transformer.Transformer;
import com.ucl.ADA.model.snapshot.Snapshot;
import com.ucl.ADA.model.snapshot.SnapshotUtils;
import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.repository_downloader.RepoDownloader;
import com.ucl.ADA.repository_downloader.RepositoryDownloaderService;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

public class RepositoryAnalyserServices {

    private RepositoryDownloaderService repositoryDownloaderService;

    public Snapshot analyseRepositoryService(String url, String branchName) {

        // parse url and branch name

        // Retrieve previous snapshot
        Snapshot prevSnapshot = new Snapshot();

        // Check last commit time through GitHub API
        OffsetDateTime lastCommitTime = RepoDownloader.getLatestCommitTime(url, branchName);
        if(lastCommitTime != null){
            if(lastCommitTime.isEqual(prevSnapshot.getCommitTime()))
                return prevSnapshot;
        }
        // if need to download and analyze again
        return getSnapshot(prevSnapshot);
    }

    public Snapshot getSnapshot(Snapshot prevSnapshot) {
        // call downloader to download the repo

        // Downloader return {root dir path, set<file path>}, downloader automatically pick all .java files
        Set<String> sourcePaths = new HashSet<>();

        // define which file paths to read?
        // write a method to remove all testing files or  (move the existing functions from parser?)

        // Initialize current snapshot and source files
        Snapshot snapshot = SnapshotUtils.initSnapshotAndSourceFiles(sourcePaths);

        // Get added set of SourceFile
        Set<String> addedSourceFiles = SnapshotUtils.getPathsToAddedSourceFiles(snapshot, prevSnapshot);

        // Get source paths to parse from addedSourceFiles, and send {root dir path, addedSourceFiles} to Parser

        // Parser return map<file path, set<ADAClass>> (use guava SetMultimap?)
        SetMultimap<String, ADAClass> filePathToClassStructuresMap = null;

        // generate Map<String, Set<String>> pathsOfAddedSourceFilesToClassNames
        SetMultimap<String, String> pathsOfAddedSourceFilesToClassNames = Transformer.getFilePathToClassNamesMap(filePathToClassStructuresMap);

        // generate Set<String> incomingToAddSet
        // TODO: START HERE!!!
        // A, B, C are parsed
        // C, D are invoked by A, B OR C
        // get all old info


        // transform detailed information of all added class structures
        // new information

        // calculate all changed class structure's metric

        return snapshot;
    }

}
