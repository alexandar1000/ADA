package com.ucl.ADA.metric_calculator.metrics_structure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ProjectMetricsContainer {

    private HashMap<String, ClassMetricsContainer> classMetrics = new HashMap<>();

    private ArrayList<String> classNames = new ArrayList<>();

    public ProjectMetricsContainer(ArrayList<String> classNames) {
        this.classNames.addAll(classNames);
    }
}