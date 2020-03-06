package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.parser.parser.ADAParser;
import com.ucl.ADA.parser.parser.ExecutorParser;

import java.util.Set;

public class Transformer {

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

    public static void main(String[] args) {
        //String src_dir = "ada-parser/src/main/resources/source_to_parse";
        //String src_dir ="/home/mrhmisu/Downloads/hadoop-trunk/";
        //String src_dir = "/home/mrhmisu/Downloads/mockito-release-3.x/";
        //String src_dir = "/home/mrhmisu/UCL-MS/ADA-test-simple-JAVA-project-0/src";
        // String src_dir = "/home/mrhmisu/Downloads/guava-master/";
        String src_dir = "/home/mrhmisu/UCL-MS/ADA";
        // new ADAParser().printParsedSourceFileInJSON(src_dir);

        new ExecutorParser().printParsedSourceFileInJSON(src_dir);


    }

}
