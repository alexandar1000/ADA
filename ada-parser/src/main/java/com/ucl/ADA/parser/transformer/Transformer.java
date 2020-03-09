package com.ucl.ADA.parser.transformer;

import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.parser.ada_model.ADAClass;
import com.ucl.ADA.parser.parser.ADAParser;

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

}
