package com.ucl.ADA.core.transformer;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.parser.parser.ADAParser;

import java.util.Set;

public class Transformer {

    /**
     * transform a project into a ProjectStructure object given its directory path
     *
     * @param src_dir directory path of the project
     * @return a ProjectStructure object that contains all information of a project
     */
    public ProjectStructure transform(String src_dir) {

        ProjectStructure projectStructure = new ProjectStructure();

        Set<ADAClass> sourceClasses = new ADAParser().getParsedSourceFile(src_dir);

        Set<String> classNames = SourceClassTransformer.getClassNames(sourceClasses);

        PackageBreaker packageBreaker = new PackageBreaker(classNames);

        for (ADAClass sourceFile : sourceClasses) {
            SourceClassTransformer sourceClassTransformer = new SourceClassTransformer(projectStructure, sourceFile, classNames, packageBreaker);

            sourceClassTransformer.transformPackageDeclaration();
            sourceClassTransformer.transformAttributeDeclaration();
            sourceClassTransformer.transformConstructorAndMethodDeclaration();
            sourceClassTransformer.transformInAndExPackageInvocation();
            sourceClassTransformer.transformAttributeInvocation();
            sourceClassTransformer.transformConstructorInvocation();
            sourceClassTransformer.transformMethodInvocation();
            sourceClassTransformer.transformExternalInvocation();
        }
        return projectStructure;
    }


    /**
     * transform the ADAClass into the qualified class name of the ADAClass for the given data structure returned by
     * parsing service
     *
     * @param filePathToClassStructuresMap a map where the key is the file path, and the value is a set of ADAClass
     *                                     which is parsed from the key source file
     * @return a map where the kay is the file path, and the value is the set of qualified class names in the source
     * file
     */
    public static SetMultimap<String, String> getFilePathToClassNamesMap(SetMultimap<String, ADAClass> filePathToClassStructuresMap) {
        SetMultimap<String, String> filePathToClassNamesMap = MultimapBuilder.hashKeys().hashSetValues().build();
        for (String filePath : filePathToClassStructuresMap.keySet()) {
            Set<ADAClass> adaClasses = filePathToClassStructuresMap.get(filePath);
            for (ADAClass adaClass : adaClasses) {
                filePathToClassNamesMap.put(filePath, adaClass.getClassName());
            }
        }
        return filePathToClassNamesMap;
    }

}
