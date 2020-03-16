package com.ucl.ADA.model.class_structure;

import com.google.common.collect.SetMultimap;
import com.ucl.ADA.model.dependence_information.DependenceInfo;

import java.util.Map;
import java.util.Set;

public class ClassStructureUtils {

    /**
     * generate a class structure given information to reuse static info and incoming dependence info
     *
     * @param reuseStaticInfo     the class structure should reuse previous static info or not
     * @param className           the qualified name of class structure
     * @param prevClassStructure  the class structure exists in previous SourceFile which can be reused
     * @param incomingToDeleteMap a guava SetMultimap object, which can be viewed as HashMap<String, Set<String>, the
     *                            key is the affected class name, the value set contains all incoming dependence info
     *                            should be deleted from the affected class
     * @param incomingToAddSet    a set of class names of all class who has new incoming dependence info * @return true
     *                            if class not in both collections, false elsewhere
     * @return a class structure which reuse previous class structure or parts of class structure
     */
    public static ClassStructure generateClassStructure(boolean reuseStaticInfo, String className, ClassStructure prevClassStructure, SetMultimap<String, String> incomingToDeleteMap, Set<String> incomingToAddSet) {
        boolean reuseIncomingDependenceInfo = isClassIncomingUnchanged(className, incomingToDeleteMap, incomingToAddSet);

        // if static info and incoming dependence info is unchanged, then reuse the previous class structure
        if (reuseStaticInfo && reuseIncomingDependenceInfo) {
            return prevClassStructure;
        }

        // create new class structure
        ClassStructure classStructure = new ClassStructure();
        // reuse static info if available
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
     * check if the incoming dependence info of the class is unchanged given the collections of incoming changed
     * classes
     *
     * @param className           qualified class name
     * @param incomingToDeleteMap a guava SetMultimap object, which can be viewed as HashMap<String, Set<String>, the
     *                            key is the affected class name, the value set contains all incoming dependence info
     *                            should be deleted from the affected class
     * @param incomingToAddSet    a set of class names of all class who has new incoming dependence info
     * @return true only if class not in both collections, false elsewhere
     */
    public static boolean isClassIncomingUnchanged(String className, SetMultimap<String, String> incomingToDeleteMap, Set<String> incomingToAddSet) {
        return !incomingToAddSet.contains(className) && !incomingToDeleteMap.containsKey(className);
    }
}
