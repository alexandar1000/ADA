package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.parser.model.ADAClass;
import com.ucl.ADA.parser.parser.ADAParser;

import java.util.Set;

public class Transformer {

    public ProjectStructure transform(String src_dir) {

        ProjectStructure projectStructure = new ProjectStructure();

        Set<ADAClass> sourceFiles = new ADAParser().getParsedSourceFile(src_dir);

        for (ADAClass sourceFile : sourceFiles) {
            SourceFileTransformer sourceFileTransformer = new SourceFileTransformer(projectStructure, sourceFile);

            sourceFileTransformer.transformPackageDeclaration();
            sourceFileTransformer.transformAttributeDeclaration();
            sourceFileTransformer.processConstructorAndMethodDeclaration();
            sourceFileTransformer.processPackageInvocation();
            sourceFileTransformer.processAttributeInvocation();
            sourceFileTransformer.processConstructorInvocation();
            sourceFileTransformer.processMethodInvocation();
            sourceFileTransformer.processExternalInvocation();
        }

        return projectStructure;
    }

    public static void main(String[] args) {
        //String src_dir = "ada-parser/src/main/resources/source_to_parse";
       //String src_dir ="/home/mrhmisu/Downloads/hadoop-trunk/";
        //String src_dir = "/home/mrhmisu/Downloads/mockito-release-3.x/";
        // String src_dir =  "/home/mrhmisu/UCL-MS/ADA-test-simple-JAVA-project-0/src";
        String src_dir = "/home/mrhmisu/Downloads/guava-master/";
        // TODO: replace parser with the new JDT parser
        new ADAParser().printParsedSourceFileInJSON(src_dir);
    }

}
