package com.ucl.ADA.parser.extractor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucl.ADA.parser.model.SourceFile;

import java.io.FileNotFoundException;
import java.util.Set;

public class SimpleParser {

    public String getParsedSourceInJSON(String src_dir, String file_path) throws FileNotFoundException {

        SourceParser sourceParser = new SourceParser(src_dir, file_path);
        Set<SourceFile> sourceSet = sourceParser.parseSource();
        ObjectMapper objMapper = new ObjectMapper();
        String jsonStr = "[]";
        try {
            jsonStr = objMapper.writeValueAsString(sourceSet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
}