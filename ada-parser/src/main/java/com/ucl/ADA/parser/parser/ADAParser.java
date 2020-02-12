package com.ucl.ADA.parser.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucl.ADA.parser.model.SourceFile;
import com.ucl.ADA.parser.util.SourceFileCollector;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ADAParser {

    public Set<SourceFile> getParsedSourceFile(String src_dir) {
        Set<SourceFile> allParsedFile = new HashSet<>();
        SourceParser sourceParser = new SourceParser(src_dir);
        List<String> filePaths = SourceFileCollector.getJavaFilesFromSourceDirectory(new File(src_dir));
        filePaths.forEach(f -> {
            //System.out.println(f.toString());
            Set<SourceFile> sourceSet = sourceParser.parseSource(f);
            allParsedFile.addAll(sourceSet);
        });
        return allParsedFile;
    }

    public void printParsedSourceFileInJSON(String src_dir) {
        SourceParser sourceParser = new SourceParser(src_dir);
        List<String> filePaths = SourceFileCollector.getJavaFilesFromSourceDirectory(new File(src_dir));
        filePaths.forEach(f -> {
           // System.out.println(f.toString());
            Set<SourceFile> sourceSet = sourceParser.parseSource(f);
            ObjectMapper objMapper = new ObjectMapper();
            String jsonStr = "[]";
            try {
                jsonStr = objMapper.writeValueAsString(sourceSet);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            System.out.println(jsonStr);
        });
    }
}
