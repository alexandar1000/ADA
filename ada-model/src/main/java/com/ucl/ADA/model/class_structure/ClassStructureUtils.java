package com.ucl.ADA.model.class_structure;

import com.google.common.collect.SetMultimap;
import com.ucl.ADA.model.dependence_information.DependenceInfo;
import lombok.NonNull;

import java.util.Map;
import java.util.Set;

public class ClassStructureUtils {

    /**
     * initialize a new class structure given the file name and file hash
     *
     * @param fileName name of the file, which is also file path
     * @param fileHash hash of the file
     * @return the new created class structure
     */
    public static ClassStructure initClassStructureWithFileNameAndFileHash(@NonNull String fileName, @NonNull String fileHash) {
        ClassStructure classStructure = new ClassStructure();
        classStructure.setFileName(fileName);
        classStructure.setFileHash(fileHash);
        return classStructure;
    }

    /**
     * generate a class structure given initialized class structure, the previous class structure to reuse, information
     * to reuse static info and incoming dependence info
     *
     * @param classStructure      the initialized class structure
     * @param prevClassStructure  the class structure exists in previous snapshot that has same qualified name
     * @param incomingToDeleteMap a guava SetMultimap object that can be viewed as Map<String, Set<String>>. The key is
     *                            the name of an affected class structure, while the value is a set of class names
     *                            should be deleted from affected class structure's incoming dependence info
     * @param incomingToAddSet    a set of class names of all class who has new incoming dependence info
     * @return a class structure which reuse (at least parts of) previous class structure
     */
    public static ClassStructure reuseClassStructure(@NonNull ClassStructure classStructure, ClassStructure prevClassStructure, @NonNull SetMultimap<String, String> incomingToDeleteMap, @NonNull Set<String> incomingToAddSet) {
        // if previous class structure is null, then nothing to reuse
        if (prevClassStructure == null) {
            return classStructure;
        }
        // if the class names are different, then should not be reused
        if (!classStructure.getClassName().equals(prevClassStructure.getClassName())) {
            throw new IllegalArgumentException("initialized and previous class structure must have the same class name");
        }

        String className = classStructure.getClassName();

        // check which parts of class structure can be reused
        boolean reuseStaticInfo = isClassStaticInfoEqual(classStructure, prevClassStructure);
        boolean reuseIncomingDependenceInfo = isClassIncomingUnchanged(className, incomingToDeleteMap, incomingToAddSet);

        // if both static info and incoming dependence info are unchanged,
        // then reuse the previous class structure object
        if (reuseStaticInfo && reuseIncomingDependenceInfo) {
            return prevClassStructure;
        }

        // if cannot reuse the whole class structure,
        // then reuse static info and incoming dependence info if available
        if (reuseStaticInfo) {
            classStructure.setStaticInfo(prevClassStructure.getStaticInfo());
        }
        if (reuseIncomingDependenceInfo) {
            // reuse all incoming dependence info if available
            classStructure.setIncomingDependenceInfos(prevClassStructure.getIncomingDependenceInfos());
        } else {
            // reuse incoming dependence info excludes incomingToDeleteMap from previous class structure
            Map<String, DependenceInfo> incomingDependenceInfo = classStructure.getIncomingDependenceInfos();
            if (incomingToDeleteMap.containsKey(className)) {
                // if there are incoming dependence info to delete, query from incomingToDeleteMap
                Set<String> incomingToDeleteSet = incomingToDeleteMap.get(className);
                for (Map.Entry<String, DependenceInfo> entry : prevClassStructure.getIncomingDependenceInfos().entrySet()) {
                    String incomingClass = entry.getKey();
                    DependenceInfo dependenceInfo = entry.getValue();
                    if (!incomingToDeleteSet.contains(incomingClass)) {
                        incomingDependenceInfo.put(incomingClass, dependenceInfo);
                    }
                }
            } else {
                // if there is no deletion in incomingToDelete, reuse all incoming dependence info from previous class structure
                incomingDependenceInfo.putAll(prevClassStructure.getIncomingDependenceInfos());
            }
        }
        return classStructure;
    }

    /**
     * check if the incoming dependence info of the class is unchanged by searching it from two collections of changed
     * incoming dependence info
     *
     * @param className           qualified class name
     * @param incomingToDeleteMap a guava SetMultimap object that can be viewed as Map<String, Set<String>>. The key is
     *                            the name of an affected class structure, while the value is a set of class names
     *                            should be deleted from affected class structure's incoming dependence info
     * @param incomingToAddSet    a set of class names of all class who has new incoming dependence info
     * @return true only if the class has no incoming dependence info change
     */
    public static boolean isClassIncomingUnchanged(@NonNull String className, @NonNull SetMultimap<String, String> incomingToDeleteMap, @NonNull Set<String> incomingToAddSet) {
        return !incomingToAddSet.contains(className) && !incomingToDeleteMap.containsKey(className);
    }

    /**
     * check if two class structures have the same static info by comparing the file name and file hash
     *
     * @param m the first input class structure object
     * @param n the second input class structure object
     * @return true only if two class structures have the same static info
     */
    public static boolean isClassStaticInfoEqual(@NonNull ClassStructure m, @NonNull ClassStructure n) {
        if (!m.getClassName().equals(n.getClassName())) {
            throw new IllegalArgumentException("the two input class structures must have the same class name");
        }
        return m.getFileName().equals(n.getFileName()) && m.getFileHash().equals(n.getFileHash());
    }
}
