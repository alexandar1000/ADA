package com.ucl.ADA.parser.test_resources;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {


    public static void main(String[] args) {
        String path = "WaterAnimal.java";
        System.out.println(getSourceCodeFromSourcePath(path));
    }

    /**
     * This method reads the contents from a given source file path.
     * If the file is empty or cannot be found in the given file path it return a empty string
     *
     * @param sourceFilePath Source file path that content has to be read from the path.
     * @return A single string containing the source code written in that file.
     * And containing empty string if the file is empty or cannot be found in the specified location.
     * @IOException if errors occur in IO operation
     */
    public static String getSourceCodeFromSourcePath(String sourceFilePath) {
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

}
