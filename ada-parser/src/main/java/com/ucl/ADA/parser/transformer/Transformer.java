package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.parser.model.SourceFile;
import com.ucl.ADA.parser.parser.ADAParser;

import java.util.Set;

public class Transformer {

    public ProjectStructure transform(String src_dir) {

        ProjectStructure projectStructure = new ProjectStructure();

        // TODO: replace parser with the new JDT parser
        Set<SourceFile> sourceFiles = new ADAParser().getParsedSourceFile(src_dir);

        for (SourceFile sourceFile : sourceFiles) {
            SourceFileTransformer sourceFileTransformer = new SourceFileTransformer(projectStructure, sourceFile);

            sourceFileTransformer.transformPackageDeclaration();
            sourceFileTransformer.transformAttributeDeclaration();
            sourceFileTransformer.processConstructorDeclaration();
            sourceFileTransformer.processMethodDeclaration();
            sourceFileTransformer.processPackageInvocation();
            sourceFileTransformer.processAttributeInvocation();
            sourceFileTransformer.processConstructorInvocation();
            sourceFileTransformer.processMethodInvocation();
            sourceFileTransformer.processExternalInvocation();
        }

        return projectStructure;
    }

    public static void main(String[] args) {
//        String src_dir = "/home/mrhmisu/UCL-MS/ADA-test-simple-JAVA-project-0/src";
        //String src_dir ="/home/mrhmisu/Downloads/CloneInterfaceSimilarityDetector-master/src";
        //String src_dir= "/home/mrhmisu/Downloads/heritrix3-master";
        String src_dir = "ada-parser/src/main/resources/source_to_parse";
        // TODO: replace parser with the new JDT parser
        new ADAParser().printParsedSourceFileInJSON(src_dir);
    }

}
