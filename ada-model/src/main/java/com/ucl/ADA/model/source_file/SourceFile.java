package com.ucl.ADA.model.source_file;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SourceFile {

    /**
     * the set of qualified class names of all classes in a source file
     */
    private Set<String> classNames = new HashSet<>();

    /**
     * the hash of a file to determine if the content of file is unchanged
     */
    private String fileHash;

}
