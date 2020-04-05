package com.ucl.ADA.model.snapshot;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.ucl.ADA.model.class_structure.ClassStructure;
import com.ucl.ADA.model.class_structure.ClassStructureUtils;
import com.ucl.ADA.model.dependence_information.invocation_information.ElementInvocation;
import com.ucl.ADA.model.dependence_information.invocation_information.InvocationDirection;
import com.ucl.ADA.model.dependence_information.invocation_information.InvocationType;
import com.ucl.ADA.model.source_file.SourceFile;
import com.ucl.ADA.model.static_information.declaration_information.DeclarationType;
import com.ucl.ADA.model.static_information.declaration_information.ElementDeclaration;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.ucl.ADA.model.class_structure.ClassStructureUtils.*;
import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;

@Service
public class SnapshotUtils {

    /* ************************************************************************
     *
     *  functions that initialize snapshot
     *
     **************************************************************************/

    /**
     * initialize snapshot and source files given all file paths (before parsing)
     *
     * @param filePaths the set of file paths
     * @return a initialized snapshot object
     */
    // TODO: add Branch into parameters
    public static Snapshot initSnapshotAndSourceFiles(@NonNull String rootDir, @NonNull Set<String> filePaths) {
        Snapshot snapshot = new Snapshot();
        Map<String, SourceFile> sourceFiles = snapshot.getSourceFiles();
        for (String filePath : filePaths) {
            // compute file hash
            String fileHash = null;
            try {
                fileHash = sha1Hex(new FileInputStream(rootDir + filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // create source file
            SourceFile sourceFile = new SourceFile();
            sourceFile.setFilePath(filePath);
            sourceFile.setFileHash(fileHash);
            sourceFile.setSnapshot(snapshot);
            sourceFiles.put(filePath, sourceFile);
        }
        return snapshot;
    }

    /* ************************************************************************
     *
     *  functions that reuse previous snapshot
     *
     **************************************************************************/

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
        Map<String, ClassStructure> classStructureMap = new HashMap<>();

        // initialize all class structures that are in the added source files
        initClassStructuresGivenPathToClassNamesMap(classStructureMap, snapshot, pathsOfAddedSourceFilesToClassNames);

        // there is nothing to reuse
        if (prevSnapshot == null) {
            for (Map.Entry<String, ClassStructure> entry : classStructureMap.entrySet()) {
                String key = entry.getKey();
                ClassStructure value = entry.getValue();
                snapshot.addClassStructure(key, value);
            }
            return;
        }

        // initialize all class structures that are in the shared source files
        initClassStructuresInSharedSourceFiles(classStructureMap, snapshot, prevSnapshot);

        // get information of classes affected by deleted source files
        SetMultimap<String, String> incomingToDeleteMap = getIncomingToDeleteMap(snapshot, prevSnapshot);

        // reuse class structure from previous snapshot given information of the change on incoming dependence info of a class
        Map<String, ClassStructure> prevClassStructures = prevSnapshot.getClassStructures();
        for (Map.Entry<String, ClassStructure> entry : classStructureMap.entrySet()) {
            String className = entry.getKey();
            ClassStructure initClassStructure = entry.getValue();
            ClassStructure reusedClassStructure = reuseClassStructure(initClassStructure, prevClassStructures.getOrDefault(className, null), incomingToDeleteMap, incomingToAddSet);
            snapshot.addClassStructure(className, reusedClassStructure);
        }
    }

    /**
     * initialize class structures of a initialized snapshot given a map, where the key is file paths and the value is
     * the set of class names in the source path
     *
     * @param snapshot          the initialized snapshot
     * @param pathsToClassNames a map, where the key is file paths and the value is the set of class names in the source
     *                          path
     */
    public static void initClassStructuresGivenPathToClassNamesMap(Map<String, ClassStructure> classStructureMap, @NonNull Snapshot snapshot, @NonNull SetMultimap<String, String> pathsToClassNames) {
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
                classStructureMap.put(className, classStructure);
            }
        }
    }

    /**
     * initialize class structures of all shared source files between previous and current snapshot
     *
     * @param snapshot     the initialized current snapshot
     * @param prevSnapshot the previous snapshot to get class information from
     */
    public static void initClassStructuresInSharedSourceFiles(Map<String, ClassStructure> classStructureMap, @NonNull Snapshot snapshot, Snapshot prevSnapshot) {
        Map<String, SourceFile> sourceFiles = snapshot.getSourceFiles();
        Map<String, SourceFile> prevSourceFiles = prevSnapshot.getSourceFiles();
        Map<String, ClassStructure> prevClassStructures = prevSnapshot.getClassStructures();

        // get paths to the shared source files
        Set<String> pathsToSharedSourceFiles = getPathsToShardSourceFiles(snapshot, prevSnapshot);
        for (String filePath : pathsToSharedSourceFiles) {
            // reuse the source file object from previous snapshot for class names information
            SourceFile prevSourceFile = prevSourceFiles.get(filePath);
            sourceFiles.put(filePath, prevSourceFile);

            // initialize all class structures that are in shared source files
            Set<String> classNames = prevSourceFile.getClassNames();
            for (String className : classNames) {
                ClassStructure prevClassStructure = prevClassStructures.get(className);
                String fileHash = prevClassStructure.getFileHash();
                ClassStructure classStructure = ClassStructureUtils.initClassStructureWithFileNameAndFileHash(className, filePath, fileHash);
                classStructureMap.put(className, classStructure);
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
     * @param snapshot     the initialized snapshot
     * @param prevSnapshot the previous snapshot
     * @return a set of source paths to the source files in current snapshot but not in the previous one
     */
    public static Set<String> getPathsToAddedSourceFiles(@NonNull Snapshot snapshot, Snapshot prevSnapshot) {
        if (prevSnapshot == null) {
            return snapshot.getSourceFiles().keySet();
        }
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

    /* ************************************************************************
     *
     *  functions that update snapshot with new declaration and invocation info
     *
     **************************************************************************/

    /**
     * Add a declaration element into the snapshot given the declaring class name, and the type of declaration
     *
     * @param snapshot        the snapshot to update declaration information
     * @param declaringClass  the qualified name of the class where it declares
     * @param declaration     the declaration object
     * @param declarationType the type of declaration
     */
    public static void addDeclarationToSnapshot(@NonNull Snapshot snapshot, @NonNull String declaringClass, @NonNull ElementDeclaration declaration, @NonNull DeclarationType declarationType) {
        validateClassNameInSnapshot(snapshot, declaringClass);
        addDeclarationToClassStructure(snapshot.getClassStructure(declaringClass), declaration, declarationType);
    }

    /**
     * Add an internal invocation into the snapshot given the consuming and declaring class names, and the type of
     * invocation
     *
     * @param snapshot           the snapshot to update invocation information
     * @param consumingClassName the qualified name of the caller
     * @param declaringClassName the qualified name of the callee
     * @param invocationType     the type of the invocation
     * @param invocation         the invocation object
     */
    public static void addInternalInvocationToSnapshot(@NonNull Snapshot snapshot, @NonNull String consumingClassName, @NonNull String declaringClassName, @NonNull InvocationType invocationType, @NonNull ElementInvocation invocation) {
        validateClassNameInSnapshot(snapshot, consumingClassName);
        validateClassNameInSnapshot(snapshot, declaringClassName);
        addInternalInvocationToClassStructure(snapshot.getClassStructure(consumingClassName), declaringClassName, InvocationDirection.OUTGOING, invocationType, invocation);
        addInternalInvocationToClassStructure(snapshot.getClassStructure(declaringClassName), consumingClassName, InvocationDirection.INCOMING, invocationType, invocation);
    }

    /**
     * Add an external invocation into the snapshot given the consuming class names, and the type of invocation
     *
     * @param snapshot       the snapshot to update invocation information
     * @param consumingClass the qualified name of the caller
     * @param invocationType the type of the invocation
     * @param invocation     the invocation object
     */
    public static void addExternalInvocationToSnapshot(@NonNull Snapshot snapshot, @NonNull String consumingClass, @NonNull InvocationType invocationType, @NonNull ElementInvocation invocation) {
        validateClassNameInSnapshot(snapshot, consumingClass);
        addExternalInvocationToClassStructure(snapshot.getClassStructure(consumingClass), invocationType, invocation);
    }

    /**
     * validate if a class name is included in the class structures of a snapshot
     *
     * @param snapshot  the snapshot that should contain the class structure
     * @param className the qualified class name to be validated
     */
    public static void validateClassNameInSnapshot(@NonNull Snapshot snapshot, @NonNull String className) {
        Map<String, ClassStructure> classStructures = snapshot.getClassStructures();
        if (!classStructures.containsKey(className)) {
            throw new IllegalArgumentException("Class structure \"" + className + "\" is not initialized correctly");
        }
    }

    /* ************************************************************************
     *
     *  functions that compute metrics for each class structure in snapshot
     *
     **************************************************************************/

//    /**
//     * Computes both the class and the relation metrics for the project structure
//     */
//    public void computeAllMetrics() {
//        for (ClassStructure classStructure : classStructures.values()) {
//            classStructure.computeAllClassMetrics();
//            classStructure.computeAllRelationMetrics();
//        }
//    }

}