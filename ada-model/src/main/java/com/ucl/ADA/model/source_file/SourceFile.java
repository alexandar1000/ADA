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

    private Set<String> classNames = new HashSet<>();

    private String fileHash;

}
