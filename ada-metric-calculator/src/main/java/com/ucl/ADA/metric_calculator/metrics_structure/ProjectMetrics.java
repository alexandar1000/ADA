package com.ucl.ADA.metric_calculator.metrics_structure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ProjectMetrics {
    private HashMap<String, ClassMetrics> repositoryValueMap = new HashMap<>();

    private ArrayList<String> classNames = new ArrayList<>(Arrays.asList("Aaa", "Bbb", "Ccc", "Ddd", "Eee", "Fff"));

    public ProjectMetrics randomParsedDataOfRepoPlaceholder () {
        for (String className : classNames) {
            repositoryValueMap.put(className, new ClassMetrics().randomParsedDataOfClassPlaceholder(className, classNames));
        }

        return this;
    }

    @Override
    public String toString() {
        return "\nParsedDataOfRepoPlaceholder{" +
                "\n\trepositoryValueMap=" + repositoryValueMap +
                ",\n\t classNames=" + classNames +
                "\n}";
    }
}