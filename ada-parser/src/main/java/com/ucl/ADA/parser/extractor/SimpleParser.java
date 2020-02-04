package com.ucl.ADA.parser.extractor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucl.ADA.parser.model.SourceFile;
import com.ucl.ADA.parser.util.SourceFileCollector;

import java.io.File;
import java.util.List;
import java.util.Set;

public class SimpleParser {

    // change this path for further parsing
//    static final String src_dir = "/home/mrhmisu/UCL-MS/IRDM-Project/src/";
    static final String src_dir = "ada-parser/src/main/java/com/ucl/ADA/parser";
    public static void main(String[] args) {

        SourceParser sourceParser = new SourceParser(src_dir);
        List<String> filePaths = new SourceFileCollector().getJavaFilesFromSourceDirectory(new File(src_dir));

        filePaths.forEach(f -> {
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
