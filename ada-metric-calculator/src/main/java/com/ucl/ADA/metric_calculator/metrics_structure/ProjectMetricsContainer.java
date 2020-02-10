package com.ucl.ADA.metric_calculator.metrics_structure;

import com.ucl.ADA.parser.dependence_information.ProjectDependenceTree;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ProjectMetricsContainer {

    private HashMap<String, ClassMetricsContainer> classMetrics = new HashMap<>();

    private ArrayList<String> classNames = new ArrayList<>();

    public void computeAllMetrics(ProjectDependenceTree projectDependenceTree) {
        for (String key : projectDependenceTree.getClassDependenceTrees().keySet()) {
        }
    }
}