package com.ucl.ADA.core.optimisation;

import com.ucl.ADA.model.snapshot.Snapshot;
import com.ucl.ADA.model.snapshot.SnapshotUtils;
import com.ucl.ADA.model.source_file.SourceFile;
import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.repository_downloader.RepositoryDownloaderService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RepositoryAnalyserServices {

    private RepositoryDownloaderService repositoryDownloaderService;

    public Snapshot analyseRepositoryService(String url, String branchName) {

        // parse url and branch name

        // Retrieve previous snapshot
        Snapshot oldSnapshot = new Snapshot();


        // Check last commit time through GitHub API

        // If needs to analyze, download the repo

        // Downloader return {root dir path, set<file path>}

        // Initialize current snapshot (with all initialized SourceFile)
        Set<String> mockPaths = new HashSet<>();

        Snapshot newSnapshot = SnapshotUtils.initSnapshot(mockPaths);

        // Get added set of SourceFile
        Set<SourceFile> addedSourceFiles = SnapshotUtils.getAddedSourceFiles(oldSnapshot, newSnapshot);

        // Get source paths to parse from addedSourceFiles, and send {root dir path, set<file path to parse>} to Parser

        // Parser return map<file path, set<ADAClass>> (use guava SetMultimap?)
        Map<String, Set<ADAClass>> sourcePathToClassStructuresMap = new HashMap<>();

        // Get deleted set of SourceFile
        Set<SourceFile> deletedSourceFiles = SnapshotUtils.getDeletedSourceFiles(oldSnapshot, newSnapshot);

        // get incoming to delete map
        SnapshotUtils.getIncomingToDeleteMap(oldSnapshot, newSnapshot);

        // get incoming to add set
        Set<String> incomingToAdd = new HashSet<>();
        sourcePathToClassStructuresMap.forEach((path, adaClasses) -> {

        });

        // Get intersection of SourceFile
        Set<SourceFile> sharedSourceFiles = SnapshotUtils.getSharedSourceFiles(oldSnapshot, newSnapshot);

        // iterate through source files in current snapshot to reuse source file, class structure or outgoings


        // iterate through ADAClass to update information

        return null;
    }

}
