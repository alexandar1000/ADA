package com.ucl.ADA.metric_calculator.metrics_structure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ProjectMetrics {

    private HashMap<String, ClassMetrics> classMetrics = new HashMap<>();

    private ArrayList<String> classNames = new ArrayList<>(Arrays.asList("Aaa", "Bbb", "Ccc", "Ddd", "Eee", "Fff"));
}