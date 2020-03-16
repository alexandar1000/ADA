package com.ucl.ADA.model.snapshot;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.ucl.ADA.model.class_structure.ClassStructure;
import com.ucl.ADA.model.source_file.SourceFile;
import com.ucl.ADA.model.source_file.SourceFileUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.ucl.ADA.model.source_file.SourceFileUtils.isSourceFileIncomingUnchanged;

public class SnapshotUtils {

    /**
     * get the difference of source files of two snapshots
     *
     * @param s1 the first snapshot
     * @param s2 the second snapshot
     * @return a new HashSet that contains sources files only exists in the first snapshot
     */
    public static Set<SourceFile> getSnapshotsDifference(Snapshot s1, Snapshot s2) {
        Set<SourceFile> difference = new HashSet<>(s1.getSourceFiles());
        difference.removeAll(s2.getSourceFiles());
        return difference;
    }

    /**
     * get a set of source files that removed from the current snapshot compared to the previous snapshot
     *
     * @param prev the previous snapshot in the branch
     * @param curr the current snapshot in the branch
     * @return a set of removed source files
     */
    public static Set<SourceFile> getDeletedSourceFiles(Snapshot prev, Snapshot curr) {
        return getSnapshotsDifference(prev, curr);
    }

    /**
     * get a set of source files that added into the current snapshot compared to the previous snapshot
     *
     * @param prev the previous snapshot in the branch
     * @param curr the current snapshot in the branch
     * @return a set of added source files
     */
    public static Set<SourceFile> getAddedSourceFiles(Snapshot prev, Snapshot curr) {
        return getSnapshotsDifference(curr, prev);
    }

    /**
     * get a set of source files of current snapshot that remains the same in both previous and current snapshots
     *
     * @param prev the previous snapshot in the branch
     * @param curr the current snapshot in the branch
     * @return a set of unchanged source files
     */
    public static Set<SourceFile> getSharedSourceFiles(Snapshot prev, Snapshot curr) {
        Set<SourceFile> intersection = new HashSet<>(curr.getSourceFiles());
        intersection.retainAll(prev.getSourceFiles());
        return intersection;
    }

    /**
     * initialize snapshot with a set of source files
     *
     * @param paths a set of source paths
     * @return the initialized snapshot
     */
    public static Snapshot initSnapshot(Set<String> paths) {
        Snapshot snapshot = new Snapshot();
        Set<SourceFile> sourceFiles = snapshot.getSourceFiles();
        for (String path : paths) {
            SourceFile sourceFile = SourceFileUtils.initSourceFile(path);
            sourceFiles.add(sourceFile);
        }
        return snapshot;
    }

    /**
     * get information of all classes affected by deleted source files
     *
     * @param prev the previous snapshot in the branch
     * @param curr the current snapshot in the branch
     * @return a guava SetMultimap object, which can be viewed as HashMap<String, Set<String>, the key is the affected
     * class name, the value set contains all incoming dependence info should be deleted from the affected class
     */
    public static SetMultimap<String, String> getIncomingToDeleteMap(Snapshot prev, Snapshot curr) {
        Set<SourceFile> deletedSourceFiles = getDeletedSourceFiles(prev, curr);

        SetMultimap<String, String> incomingToDeleteMap = MultimapBuilder.hashKeys().hashSetValues().build();
        for (SourceFile sourceFile : deletedSourceFiles) {
            for (Map.Entry<String, ClassStructure> entry : sourceFile.getClassStructures().entrySet()) {
                String deletedClassName = entry.getKey();
                ClassStructure classStructure = entry.getValue();
                for (String affectedClassName : classStructure.getStaticInfo().getOutgoingDependenceInfos().keySet()) {
                    incomingToDeleteMap.put(affectedClassName, deletedClassName);
                }
            }
        }
        return incomingToDeleteMap;
    }

    /**
     * update the current snapshot with previous snapshot and a set of class names whose incoming dependence info shall
     * be updated
     *
     * @param prev             the previous snapshot in the branch
     * @param curr             the current snapshot in the branch
     * @param incomingToAddSet a set that contains all class names that will receive new incoming dependence info
     * @return the updated current snapshot
     */
    public static Snapshot updateCurrentSnapshotGivenPreviousSnapshotAndRelatedInfo(Snapshot prev, Snapshot curr, Set<String> incomingToAddSet) {
        SetMultimap<String, String> incomingToDeleteMap = getIncomingToDeleteMap(prev, curr);

        Set<SourceFile> currSourceFiles = curr.getSourceFiles();
        Set<SourceFile> sourceFilesToRemove = new HashSet<>();
        Set<SourceFile> sourceFilesToAdd = new HashSet<>();

        // iterate through all source files of current snapshot
        for (SourceFile sourceFile : currSourceFiles) {
            // if the static info (declarations, outgoing dependence info) of a source file is unchanged
            if (isSourceFileStaticInfoUnchanged(sourceFile, prev, curr)) {
                SourceFile prevSourceFile = getSourceFileFromSnapshotByName(sourceFile.getFileName(), prev);
                // if the incoming dependence info of a source file is unchanged,
                // record the source file and update it after finishing the for loop
                if (isSourceFileIncomingUnchanged(sourceFile, incomingToDeleteMap, incomingToAddSet)) {
                    sourceFilesToRemove.add(sourceFile);
                    sourceFilesToAdd.add(prevSourceFile);
                    continue;
                }
                // iterate through all class name, check if can reuse class structure
                for (String className : prevSourceFile.getClassStructures().keySet()) {
                    ClassStructure prevClassStructure = prevSourceFile.getClassStructures().get(className);

                }
                continue;
            }
            System.out.println();

        }
        // update source files that can be reused from previous snapshot
        currSourceFiles.removeAll(sourceFilesToRemove);
        currSourceFiles.addAll(sourceFilesToAdd);

        return curr;
    }

    /**
     * check if the static info (declarations and outgoing dependence info) remains the same between previous and
     * current snapshots
     *
     * @param prev       the previous snapshot in the branch
     * @param curr       the current snapshot in the branch
     * @param sourceFile the source file to check
     * @return true if the source file is unchanged, false if the source file needs to be parsed
     */
    public static boolean isSourceFileStaticInfoUnchanged(SourceFile sourceFile, Snapshot prev, Snapshot curr) {
        Set<SourceFile> sharedSourceFiles = getSharedSourceFiles(prev, curr);
        return sharedSourceFiles.contains(sourceFile);
    }

    /**
     * get a source file from snapshot by file name (file path)
     *
     * @param fileName file name, which is also file path
     * @param snapshot the snapshot that holds the wanted source file
     * @return the source file matches the queried file name
     */
    public static SourceFile getSourceFileFromSnapshotByName(String fileName, Snapshot snapshot) {
        for (SourceFile sourceFile : snapshot.getSourceFiles()) {
            if (sourceFile.getFileName().equals(fileName)) {
                return sourceFile;
            }
        }
        return null;
    }

}