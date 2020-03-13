package com.ucl.ADA.model.source_file;

import java.io.FileInputStream;
import java.io.IOException;

import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;

public class SourceFileUtils {

    public static SourceFile initSourceFile(String path) {
        String fileHash = null;
        try {
            fileHash = sha1Hex(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SourceFile sourceFile = new SourceFile();
        sourceFile.setFileName(path);
        sourceFile.setFileHash(fileHash);
        return sourceFile;
    }



}
