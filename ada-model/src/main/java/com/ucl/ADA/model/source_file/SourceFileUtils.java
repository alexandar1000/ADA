package com.ucl.ADA.model.source_file;

import com.google.common.collect.SetMultimap;
import com.ucl.ADA.model.class_structure.ClassStructure;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import static com.ucl.ADA.model.class_structure.ClassStructureUtils.generateClassStructure;
import static com.ucl.ADA.model.class_structure.ClassStructureUtils.isClassIncomingUnchanged;
import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;

public class SourceFileUtils {

    /**
     * initialize a source file object
     *
     * @param path the path of a source file
     * @return the initialized source file object
     */
    public static SourceFile initSourceFile(String path) {
        String fileHash = null;
        try {
            fileHash = sha1Hex(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SourceFile sourceFile = new SourceFile();
        sourceFile.setFileName(path);
        sourceFile.setFileHash(fileHash);
        return sourceFile;
    }

    /**
     * check if incoming dependence info of all classes in a source file are unchanged
     *
     * @param sourceFile          source file need to be checked
     * @param incomingToDeleteMap a guava SetMultimap object, which can be viewed as HashMap<String, Set<String>, the
     *                            key is the affected class name, the value set contains all incoming dependence info
     *                            should be deleted from the affected class
     * @param incomingToAddSet    a set of class names of all class who has new incoming dependence info * @return true
     *                            if class not in both collections, false elsewhere
     * @return true only if all classes in the source file has no incoming dependence info change
     */
    public static boolean isSourceFileIncomingUnchanged(SourceFile sourceFile, SetMultimap<String, String> incomingToDeleteMap, Set<String> incomingToAddSet) {
        for (String className : sourceFile.getClassStructures().keySet()) {
            if (!isClassIncomingUnchanged(className, incomingToDeleteMap, incomingToAddSet)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param sourceFile
     * @param prevSourceFile
     * @param incomingToDeleteMap
     * @param incomingToAddSet
     * @return
     */
    public static SourceFile generateSourceFile(SourceFile sourceFile, SourceFile prevSourceFile, SetMultimap<String, String> incomingToDeleteMap, Set<String> incomingToAddSet) {
        // TODO: check if static change here
        for (String className : prevSourceFile.getClassStructures().keySet()) {
            ClassStructure prevClassStructure = prevSourceFile.getClassStructures().get(className);
            ClassStructure classStructure = generateClassStructure(true, className, prevClassStructure, incomingToDeleteMap, incomingToAddSet);
            sourceFile.getClassStructures().put(className, classStructure);
        }
        // TODO: add static changed situation
        return sourceFile;
    }


}
