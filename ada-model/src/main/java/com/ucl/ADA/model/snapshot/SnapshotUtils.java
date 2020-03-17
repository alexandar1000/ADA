package com.ucl.ADA.model.snapshot;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.ucl.ADA.model.class_structure.ClassStructure;
import com.ucl.ADA.model.class_structure.ClassStructureUtils;
import com.ucl.ADA.model.source_file.SourceFile;
import lombok.NonNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.ucl.ADA.model.class_structure.ClassStructureUtils.reuseClassStructure;
import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;

public class SnapshotUtils {

    /**
     * initialize a new snapshot object, also initialized the class structures with given information
     *
     * @param filePathToClassNamesMap a map that key is the file path and the value is a set of class names that belongs
     *                                to the file path
     * @return a new created snapshot object with initialized class structures
     */
    // TODO: the parameter is the return value of quick transform which only transform class names
    // TODO: add Branch into parameters
    public static Snapshot initSnapshot(Map<String, Set<String>> filePathToClassNamesMap) {
        Snapshot snapshot = new Snapshot();
        Map<String, SourceFile> sourcePathToSourceFiles = snapshot.getSourceFiles();
        for (Map.Entry<String, Set<String>> entry : filePathToClassNamesMap.entrySet()) {
            String filePath = entry.getKey();
            Set<String> classNames = entry.getValue();
            // compute file hash
            String fileHash = null;
            try {
                fileHash = sha1Hex(new FileInputStream(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // create source file if not exists
            if (!sourcePathToSourceFiles.containsKey(filePath)) {
                SourceFile sourceFile = new SourceFile();
                sourceFile.setFileHash(fileHash);
                sourcePathToSourceFiles.put(filePath, sourceFile);
            }
            SourceFile sourceFile = sourcePathToSourceFiles.get(filePath);
            // add class names into source file
            for (String className : classNames) {
                sourceFile.getClassNames().add(className);
            }
            // initialize all class structures in the snapshot
            for (String className : classNames) {
                snapshot.addClassStructures(className, ClassStructureUtils.initClassStructureWithFileNameAndFileHash(filePath, fileHash));
            }
        }
        return snapshot;
    }

    /**
     * generate a snapshot given a snapshot that can be reused and the set of names of the affected class structures
     *
     * @param prevSnapshot     the previous snapshot to be reused
     * @param incomingToAddSet the set of names of the affected class structures
     * @return a snapshot which have reuse (at least parts of) the previous snapshot
     */
    // TODO: need to build a quick transform that only transform affected class names of new parsed ADAClass
    public static Snapshot reuseClassStructuresOfSnapshot(@NonNull Snapshot snapshot, Snapshot prevSnapshot, @NonNull Set<String> incomingToAddSet) {
        // if there is no previous snapshot to reuse, return the initialized one
        if (prevSnapshot == null) {
            return snapshot;
        }
        // the exception can be removed when working on per-repo optimisation
        if (snapshot.getBranch().getBranchName().equals(prevSnapshot.getBranch().getBranchName())) {
            throw new IllegalArgumentException("the two input snapshots mus tbe on the same branch");
        }
        if (snapshot.getCommitTime().isBefore(prevSnapshot.getCommitTime())) {
            throw new IllegalArgumentException("the commit time of the previous snapshot to reuse should be earlier than the initialized one");
        }

        Map<String, ClassStructure> prevClassStructures = prevSnapshot.getClassStructures();
        SetMultimap<String, String> incomingToDeleteMap = getIncomingToDeleteMap(snapshot, prevSnapshot);

        Map<String, ClassStructure> reuseUpdatedClassStructures = new HashMap<>();
        for (Map.Entry<String, ClassStructure> entry : snapshot.getClassStructures().entrySet()) {
            String className = entry.getKey();
            ClassStructure classStructure = entry.getValue();
            ClassStructure reuseUpdatedClassStructure = reuseClassStructure(classStructure, prevClassStructures.getOrDefault(className, null), incomingToDeleteMap, incomingToAddSet);
            reuseUpdatedClassStructures.put(className, reuseUpdatedClassStructure);
        }

        snapshot.setClassStructures(reuseUpdatedClassStructures);

        return snapshot;
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
        Map<String, SourceFile> sourceFiles = snapshot.getSourceFiles();
        Map<String, SourceFile> prevSourceFiles = prevSnapshot.getSourceFiles();
        Map<String, ClassStructure> prevClassStructures = prevSnapshot.getClassStructures();

        // get the set of deleted source files by file names
        Set<String> deletedSourceFiles = new HashSet<>(prevSourceFiles.keySet());
        deletedSourceFiles.removeAll(sourceFiles.keySet());

        // update the set of deleted source files by file hashes
        Set<String> sourceFilesWithSameName = new HashSet<>(prevSourceFiles.keySet());
        sourceFilesWithSameName.retainAll(sourceFiles.keySet());
        for (String fileName : sourceFilesWithSameName) {
            if (!sourceFiles.get(fileName).getFileHash().equals(prevSourceFiles.get(fileName).getFileHash())) {
                deletedSourceFiles.add(fileName);
            }
        }

        // generate incoming to delete map
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


}