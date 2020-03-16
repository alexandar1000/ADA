package com.ucl.ADA.model.class_structure;

import com.google.common.collect.SetMultimap;
import com.ucl.ADA.model.dependence_information.DependenceInfo;

import java.util.Map;
import java.util.Set;

public class ClassStructureUtils {

    /**
     *
     * @param className
     * @param prevClassStructure
     * @param incomingToDeleteMap
     * @param incomingToAddSet
     * @return
     */
    public static ClassStructure generateClassStructure(boolean reuseStaticInfo, String className, ClassStructure prevClassStructure, SetMultimap<String, String> incomingToDeleteMap, Set<String> incomingToAddSet) {
        if (reuseStaticInfo) {
            if (isClassIncomingUnchanged(className, incomingToDeleteMap, incomingToAddSet)) {
                // if static info and incoming dependence info is unchanged, reuse class structure
                return prevClassStructure;
            } else {
                // if static info is unchanged, while incoming dependence info is changed
                // create new class structure and reuse static info
                ClassStructure classStructure = new ClassStructure();
                classStructure.setStaticInfo(prevClassStructure.getStaticInfo());
                // then clone and update incoming dependence info by incomingToDeleteMap
                if (incomingToDeleteMap.containsKey(className)) {
                    // TODO: add entry line by line???
                } else {
                    // hashmap.addAll???
                }

            }
        }


        ClassStructure classStructure = new ClassStructure();
        if (reuseStaticInfo) {

        }
        for (Map.Entry<String, DependenceInfo> entry : prevClassStructure.getIncomingDependenceInfos().entrySet()) {
            String className = entry.getKey();
            DependenceInfo incoming = entry.getValue();
            assert incomingToDeleteMap.containsKey(className);
            Set<String>
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
