package com.ucl.ADA.model.snapshot;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.ucl.ADA.model.class_structure.ClassStructure;
import com.ucl.ADA.model.class_structure.ClassStructureUtils;
import com.ucl.ADA.model.source_file.SourceFile;
import lombok.NonNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static com.ucl.ADA.model.class_structure.ClassStructureUtils.reuseClassStructure;
import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;

public class SnapshotUtils {

    /**
     * initialize snapshot and source files given all file paths (before parsing)
     *
     * @param filePaths the set of file paths
     * @return a initialized snapshot object
     */
    // TODO: add Branch into parameters
    public static Snapshot initSnapshotAndSourceFiles(Set<String> filePaths) {
        Snapshot snapshot = new Snapshot();
        Map<String, SourceFile> sourceFiles = snapshot.getSourceFiles();
        for (String filePath : filePaths) {
            // compute file hash
            String fileHash = null;
            try {
                fileHash = sha1Hex(new FileInputStream(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // create source file
            SourceFile sourceFile = new SourceFile();
            sourceFile.setFileHash(fileHash);
            sourceFiles.put(filePath, sourceFile);
        }
        return snapshot;
    }

    /**
     * generate a snapshot given a snapshot that can be reused and the set of names of the affected class structures
     * (after parsing)
     *
     * @param snapshot                            the current snapshot
     * @param prevSnapshot                        the previous snapshot to be reused
     * @param pathsOfAddedSourceFilesToClassNames a map that key is the file path and the value is a set of class names
     *                                            that belongs to the file path
     * @param incomingToAddSet                    the set of names of the affected class structures
     */
    public static void reuseClassStructuresOfSnapshot(@NonNull Snapshot snapshot, Snapshot prevSnapshot, @NonNull SetMultimap<String, String> pathsOfAddedSourceFilesToClassNames, @NonNull Set<String> incomingToAddSet) {
        // there is no previous snapshot to reuse
        if (prevSnapshot == null) {
            return;
        }
        // the exception can be removed when working on per-repo optimisation
        if (snapshot.getBranch().getBranchName().equals(prevSnapshot.getBranch().getBranchName())) {
            throw new IllegalArgumentException("the two input snapshots mus tbe on the same branch");
        }
        if (snapshot.getCommitTime().isBefore(prevSnapshot.getCommitTime())) {
            throw new IllegalArgumentException("the commit time of the previous snapshot to reuse should be earlier than the initialized one");
        }

        // initialize all class structures that are in the added source files
        initClassStructuresGivenPathToClassNamesMap(snapshot, pathsOfAddedSourceFilesToClassNames);

        // initialize all class structures that are in the shared source files
        initClassStructuresInSharedSourceFiles(snapshot, prevSnapshot);

        // get information of classes affected by deleted source files
        SetMultimap<String, String> incomingToDeleteMap = getIncomingToDeleteMap(snapshot, prevSnapshot);

        // reuse class structure from previous snapshot given information of the change on incoming dependence info of a class
        Map<String, ClassStructure> prevClassStructures = prevSnapshot.getClassStructures();
        Map<String, ClassStructure> reuseUpdatedClassStructures = new HashMap<>();
        for (Map.Entry<String, ClassStructure> entry : snapshot.getClassStructures().entrySet()) {
            String className = entry.getKey();
            ClassStructure initClassStructure = entry.getValue();
            ClassStructure reuseUpdatedClassStructure = reuseClassStructure(initClassStructure, prevClassStructures.getOrDefault(className, null), incomingToDeleteMap, incomingToAddSet);
            reuseUpdatedClassStructures.put(className, reuseUpdatedClassStructure);
        }
        snapshot.setClassStructures(reuseUpdatedClassStructures);
    }

    /**
     * initialize class structures of a initialized snapshot given a map, where the key is file paths and the value is
     * the set of class names in the source path
     *
     * @param snapshot          the initialized snapshot
     * @param pathsToClassNames a map, where the key is file paths and the value is the set of class names in the source
     *                          path
     */
    public static void initClassStructuresGivenPathToClassNamesMap(@NonNull Snapshot snapshot, @NonNull SetMultimap<String, String> pathsToClassNames) {
        Map<String, SourceFile> sourceFiles = snapshot.getSourceFiles();
        for (String filePath : pathsToClassNames.keySet()) {
            Set<String> classNames = pathsToClassNames.get(filePath);

            // add class names into source file
            SourceFile sourceFile = sourceFiles.get(filePath);
            for (String className : classNames) {
                sourceFile.getClassNames().add(className);
            }

            // initialize all class structures in the snapshot
            for (String className : classNames) {
                ClassStructure classStructure = ClassStructureUtils.initClassStructureWithFileNameAndFileHash(className, filePath, sourceFile.getFileHash());
                snapshot.addClassStructure(className, classStructure);
            }
        }
    }

    /**
     * initialize class structures of all shared source files between previous and current snapshot
     *
     * @param snapshot     the initialized current snapshot
     * @param prevSnapshot the previous snapshot to get class information from
     */
    public static void initClassStructuresInSharedSourceFiles(@NonNull Snapshot snapshot, Snapshot prevSnapshot) {
        Map<String, SourceFile> sourceFiles = snapshot.getSourceFiles();
        Map<String, SourceFile> prevSourceFiles = prevSnapshot.getSourceFiles();
        Map<String, ClassStructure> prevClassStructures = prevSnapshot.getClassStructures();

        // get paths to the shared source files
        Set<String> pathsToSharedSourceFiles = getPathsToShardSourceFiles(snapshot, prevSnapshot);
        for (String filePath : pathsToSharedSourceFiles) {
            // reuse the source file object from previous snapshot for class names information
            SourceFile prevSourceFile = prevSourceFiles.get(filePath);
            sourceFiles.put(filePath, prevSourceFile);

            // initialize all class structures that in are shared
            Set<String> classNames = prevSourceFile.getClassNames();
            for (String className : classNames) {
                ClassStructure prevClassStructure = prevClassStructures.get(className);
                String fileHash = prevClassStructure.getFileHash();
                ClassStructure classStructure = ClassStructureUtils.initClassStructureWithFileNameAndFileHash(className, filePath, fileHash);
                snapshot.addClassStructure(className, classStructure);
            }
        }
    }

    /**
     * generate a guava SetMultimap object that can be viewed as Map<String, Set<String>>. The key is the name of an
     * affected class structure, while the value is a set of class names should be deleted from affected class
     * structure's incoming dependence info
     *
     * @param snapshot     the initialized snapshot
     * @param prevSnapshot the previous snapshot to be (partially) reused
     * @return a guava SetMultimap object that can be viewed as Map<String, Set<String>>. The key is the name of an
     * affected class structure, while the value is a set of class names should be deleted from affected class
     * structure's incoming dependence info
     */
    public static SetMultimap<String, String> getIncomingToDeleteMap(@NonNull Snapshot snapshot, @NonNull Snapshot prevSnapshot) {
        Set<String> deletedSourceFiles = getPathsToDifferentSourceFiles(prevSnapshot, snapshot);
        Map<String, SourceFile> prevSourceFiles = prevSnapshot.getSourceFiles();
        Map<String, ClassStructure> prevClassStructures = prevSnapshot.getClassStructures();

        SetMultimap<String, String> incomingToDeleteMap = MultimapBuilder.hashKeys().hashSetValues().build();
        for (String fileName : deletedSourceFiles) {
            Set<String> classNames = prevSourceFiles.get(fileName).getClassNames();
            for (String className : classNames) {
                ClassStructure classStructure = prevClassStructures.get(className);
                for (String affectedClassName : classStructure.getStaticInfo().getOutgoingDependenceInfos().keySet()) {
                    incomingToDeleteMap.put(affectedClassName, className);
                }
            }
        }

        return incomingToDeleteMap;
    }

    /**
     * get the paths to all newly added source files in current snapshot compared to the previous one
     *
     * @param snapshot     the current snapshot
     * @param prevSnapshot the previous snapshot
     * @return a set of source paths to the source files in current snapshot but not in the previous one
     */
    public static Set<String> getPathsToAddedSourceFiles(@NonNull Snapshot snapshot, @NonNull Snapshot prevSnapshot) {
        return getPathsToDifferentSourceFiles(snapshot, prevSnapshot);
    }

    /**
     * find all different source files between snapshots by comparing fle name and file hash and return the set of file
     * paths
     *
     * @param snapshot1 the first input snapshot
     * @param snapshot2 the second input snapshot
     * @return a set of file paths to source files that only exists in the first snapshot and not in the second one
     */
    public static Set<String> getPathsToDifferentSourceFiles(@NonNull Snapshot snapshot1, @NonNull Snapshot snapshot2) {
        Map<String, SourceFile> sourceFiles1 = snapshot1.getSourceFiles();
        Map<String, SourceFile> sourceFiles2 = snapshot2.getSourceFiles();

        // get the set of deleted source files by file names
        Set<String> deletedSourceFiles = new HashSet<>(sourceFiles1.keySet());
        deletedSourceFiles.removeAll(sourceFiles2.keySet());

        // update the set of deleted source files by file hashes
        Set<String> sourceFilesWithSameName = new HashSet<>(sourceFiles1.keySet());
        sourceFilesWithSameName.retainAll(sourceFiles2.keySet());
        for (String fileName : sourceFilesWithSameName) {
            if (!sourceFiles2.get(fileName).getFileHash().equals(sourceFiles1.get(fileName).getFileHash())) {
                deletedSourceFiles.add(fileName);
            }
        }

        return deletedSourceFiles;
    }

    /**
     * find the same source files between snapshots by comparing fle name and file hash and return the set of file
     * paths
     *
     * @param snapshot1 the first input snapshot
     * @param snapshot2 the second input snapshot
     * @return a set of file paths to source files that are shared by two snapshots
     */
    public static Set<String> getPathsToShardSourceFiles(@NonNull Snapshot snapshot1, @NonNull Snapshot snapshot2) {
        Map<String, SourceFile> sourceFiles1 = snapshot1.getSourceFiles();
        Map<String, SourceFile> sourceFiles2 = snapshot2.getSourceFiles();

        // get the source files with same name
        Set<String> shardSourceFiles = new HashSet<>(sourceFiles1.keySet());
        shardSourceFiles.retainAll(sourceFiles2.keySet());

        // filter all files that have different file hash
        Set<String> res = new HashSet<>();
        for (String fileName : shardSourceFiles) {
            if (sourceFiles2.get(fileName).getFileHash().equals(sourceFiles1.get(fileName).getFileHash())) {
                res.add(fileName);
            }
        }

        return res;
    }

}