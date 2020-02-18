package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.parser.model.SourceFile;
import com.ucl.ADA.parser.parser.ADAParser;

import java.util.Set;

public class Transformer {

    public ProjectStructure transform(String src_dir) {

        ProjectStructure projectStructure = new ProjectStructure();

        SourceFileProcessor sourceFileProcessor = new SourceFileProcessor();

        Set<SourceFile> sourceFiles = new ADAParser().getParsedSourceFile(src_dir);

        sourceFiles.forEach(f -> {
            sourceFileProcessor.processPackageDeclaration(projectStructure, f);
            sourceFileProcessor.processAttributeDeclaration(projectStructure, f);
            sourceFileProcessor.processConstructorDeclaration(projectStructure, f);
            sourceFileProcessor.processMethodDeclaration(projectStructure, f);
            sourceFileProcessor.processPackageInvocation(projectStructure, f);
            sourceFileProcessor.processAttributeInvocation(projectStructure, f);
            sourceFileProcessor.processConstructorInvocation(projectStructure, f);
            sourceFileProcessor.processMethodInvocation(projectStructure, f);
            sourceFileProcessor.processExternalInvocation(projectStructure, f);
        });

        return projectStructure;
    }

    public static void main(String[] args) {
//        String src_dir = "/home/mrhmisu/UCL-MS/ADA-test-simple-JAVA-project-0/src";
        //String src_dir ="/home/mrhmisu/Downloads/CloneInterfaceSimilarityDetector-master/src";
        //String src_dir= "/home/mrhmisu/Downloads/heritrix3-master";
        String src_dir = "ada-parser/src/main/resources/source_to_parse";

        new ADAParser().printParsedSourceFileInJSON(src_dir);
    }

}
