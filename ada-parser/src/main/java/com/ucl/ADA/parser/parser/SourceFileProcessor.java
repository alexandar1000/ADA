package com.ucl.ADA.parser.parser;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SourceFileProcessor {


    /**
     * Default constructor for SourceFileProcessor
     */
    protected SourceFileProcessor() {
    }


    /**
     * It takes list of file paths and reads the file contents
     * and forms a map of paths->contents for each list.
     *
     * @param listsOfFilePaths : A list of string containing the *.java source file paths
     * @return A list of map where each contains (source file path-> contents
     */

    protected List<Map<String, String>> getSourceContentsInChunks(List<List<String>> listsOfFilePaths) {
        List<Map<String, String>> allFileContents = new ArrayList<>();
        for (List<String> listOfFilePaths : listsOfFilePaths) {
            Map<String, String> fileContents = new HashMap<>();
            fileContents.putAll(getAllSourceContent(listOfFilePaths));
            allFileContents.add(fileContents);
        }
        return allFileContents;
    }


    /**
     * It takes a list of *.java source file paths and read all the contents of these files.
     *
     * @param paths : A list of *.java source file paths
     * @return : A map containing all *java source file path and its content
     * in a form of source (file path-> file contents) pair.
     */


    protected Map<String, String> getAllSourceContent(List<String> paths) {
        Map<String, String> sourceContents = new HashMap<>();
        for (String path : paths) {
            String code = getSourceCodeFromSourcePath(path);
            sourceContents.put(path, code);
        }
        return sourceContents;
    }


    /**
     * This method reads the contents from a given source file path.
     * If the file is empty or cannot be found in the given file path it return a empty string
     *
     * @param sourceFilePath : Source file path that content has to be read from the path.
     * @return A single string containing the source code written in that file.
     * And containing empty string if the file is empty or cannot be found in the specified location.
     */

    protected String getSourceCodeFromSourcePath(String sourceFilePath) {
        String sourceCode = "";
        File sourceFile = new File(sourceFilePath);
        if (sourceFile.exists()) {
            try {
                sourceCode = FileUtils.readFileToString(sourceFile, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sourceCode;
    }

    /**
     * It helps to filter all the *.java files inside source repository.
     * However, it only considers the *.java files that are placed inside a parent src directory.
     * Besides, it ignores all the files that ends with the suffix *Test.java or *Tests.java.
     *
     * @param rootDirectory: : root directory path for source repository
     * @return: A list of string containing file path of all *.java files except the *Test.java and *Tests.java files
     */

    protected List<String> getSourceFilePaths(String rootDirectory) {
        String[] sourceDirectories = getSourceDirectories(new File(rootDirectory));
        List<String> sourceFiles = new ArrayList<>();
        final String[] suffix = {"java"};
        for (String srcDir : sourceDirectories) {
            File rootDir = new File(srcDir);
            Collection<File> files = FileUtils.listFiles(rootDir, suffix, true);
            for (File file : files) {
                if (!file.getPath().endsWith("Test.java") && !file.getPath().endsWith("Tests.java")) {
                    {
                        sourceFiles.add(file.getAbsolutePath());
                    }
                }
            }
        }
        return sourceFiles;
    }


    /**
     * This method helps to identify all the src directories inside the repository.
     * It utilizes a method from Apache Commons IO FileUtils.listFilesAndDirs.
     *
     * @param rootDirectory : root directory path for source repository
     * @return An array of string containing all the src directories.
     */

    protected String[] getSourceDirectories(File rootDirectory) {
        List<String> sourceDirectories = new ArrayList<>();
        Collection<File> files = FileUtils.listFilesAndDirs(rootDirectory,
                TrueFileFilter.INSTANCE,
                TrueFileFilter.INSTANCE);
        for (File file : files) {
            if (file.isDirectory()) {
                if (file.getName().equals("src") || file.getName().equals("Src"))
                    sourceDirectories.add(file.getAbsolutePath());
            }
        }
        String[] srcDirs = sourceDirectories.stream().toArray(String[]::new);
        return srcDirs;
    }

}
