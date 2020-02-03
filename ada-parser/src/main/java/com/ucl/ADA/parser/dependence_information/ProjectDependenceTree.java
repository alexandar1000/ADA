package com.ucl.ADA.parser.dependence_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ProjectDependenceTree {
    private HashMap<String, ClassDependenceTree> repositoryValueMap = new HashMap<>();

    private ArrayList<String> classNames = new ArrayList<>(Arrays.asList("Aaa", "Bbb", "Ccc", "Ddd", "Eee", "Fff"));

    public ProjectDependenceTree randomParsedDataOfRepoPlaceholder () {
        for (String className : classNames) {
            repositoryValueMap.put(className, new ClassDependenceTree().randomParsedDataOfClassPlaceholder(className, classNames));
        }

        return this;
    }
}