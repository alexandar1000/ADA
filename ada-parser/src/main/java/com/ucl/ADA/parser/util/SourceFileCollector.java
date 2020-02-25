package com.ucl.ADA.parser.util;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class SourceFileCollector {

    public static List<String> getJavaFilesFromSourceDirectory(File directoryPath) {
        List<String> sourceFiles = new ArrayList<>();
        new DirectoryExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
            String absolutePath = directoryPath + path;
            sourceFiles.add(absolutePath);
        }).explore(directoryPath);
        return sourceFiles;
    }

}
