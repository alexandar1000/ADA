package com.ucl.ADA.model.snapshot;

import com.ucl.ADA.model.source_file.SourceFile;
import com.ucl.ADA.model.source_file.SourceFileUtils;

import java.util.HashSet;
import java.util.Set;

public class SnapshotUtils {

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
     * get a set of source files that remains the same in both previous and current snapshots
     *
     * @param prev the previous snapshot in the branch
     * @param curr the current snapshot in the branch
     * @return a set of unchanged source files
     */
    public static Set<SourceFile> getSharedSourceFiles(Snapshot prev, Snapshot curr) {
        Set<SourceFile> intersection = new HashSet<>(prev.getSourceFiles());
        intersection.retainAll(curr.getSourceFiles());
        return intersection;
    }

    public static Snapshot initSnapshot(Set<String> paths) {
        Snapshot snapshot = new Snapshot();
        for (String path : paths) {
            SourceFile sourceFile = SourceFileUtils.initSourceFile(path);
            snapshot.getSourceFiles().add(sourceFile);
        }
        return snapshot;
    }



    /* ************************************************************************
     *
     *  private helper functions
     *
     **************************************************************************/

    /**
     * get the difference of source files of two snapshots
     *
     * @param s1 the first snapshot
     * @param s2 the second snapshot
     * @return a new HashSet that contains sources files only exists in the first snapshot
     */
    private static Set<SourceFile> getSnapshotsDifference(Snapshot s1, Snapshot s2) {
        Set<SourceFile> difference = new HashSet<>(s1.getSourceFiles());
        difference.removeAll(s2.getSourceFiles());
        return difference;
    }

}