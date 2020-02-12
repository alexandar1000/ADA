package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import com.ucl.ADA.parser.model.SourceFile;
import com.ucl.ADA.parser.parser.ADAParser;

import java.util.Set;

public class Transformer {

    public ProjectDependenceTree transform(String src_dir) {

        ProjectDependenceTree projectDependenceTree = new ProjectDependenceTree();

        SourceFileProcessor sourceFileProcessor = new SourceFileProcessor();

        // TODO: update parser to the latest version
        Set<SourceFile> sourceFiles = new ADAParser().getParsedSourceFile(src_dir);

        sourceFiles.forEach(f -> {
            sourceFileProcessor.processPackageDeclaration(projectDependenceTree, f);
            sourceFileProcessor.processAttributeDeclaration(projectDependenceTree, f);
            sourceFileProcessor.processConstructorDeclaration(projectDependenceTree, f);
            sourceFileProcessor.processMethodDeclaration(projectDependenceTree, f);
            sourceFileProcessor.processPackageInvocation(projectDependenceTree, f);
            sourceFileProcessor.processAttributeInvocation(projectDependenceTree, f);
            sourceFileProcessor.processConstructorInvocation(projectDependenceTree, f);
            sourceFileProcessor.processMethodInvocation(projectDependenceTree, f);
        });

        return projectDependenceTree;
    }

    public static void main(String[] args) {
        String src_dir = "/home/mrhmisu/UCL-MS/ADA-test-simple-JAVA-project-0/src";
        //String src_dir ="/home/mrhmisu/Downloads/CloneInterfaceSimilarityDetector-master/src";
        //String src_dir= "/home/mrhmisu/Downloads/heritrix3-master";

        new ADAParser().printParsedSourceFileInJSON(src_dir);
    }

}
