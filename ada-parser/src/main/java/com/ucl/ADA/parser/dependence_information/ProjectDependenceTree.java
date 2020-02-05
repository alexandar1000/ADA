package com.ucl.ADA.parser.dependence_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ProjectDependenceTree {
    private HashMap<String, ClassDependenceTree> classDependenceTrees = new HashMap<>();

    private ArrayList<String> classNames = new ArrayList<>(Arrays.asList("Aaa", "Bbb", "Ccc", "Ddd", "Eee", "Fff"));

    public ProjectDependenceTree(ArrayList<String> classNames) {
        for (String className : classNames) {
            classDependenceTrees.put(className, null);
        }
    }

}