package com.ucl.ADA.metric_calculator.metrics_structure;

import com.ucl.ADA.model.dependence_information.ProjectStructure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter @NoArgsConstructor
public class ProjectMetricsContainer {

    /**
     * All of the class metrics for the given project
     */
    private HashMap<String, ClassMetricsContainer> classMetrics = new HashMap<>();

    /**
     * Computes both the class and relation metrics when provided a ProjectDependenceTree of the corresponding parsed
     * repo
     * @param projectStructure corresponding parsed repository stored in the ProjectDependenceTree
     */
    public void computeAllMetrics(ProjectStructure projectStructure) {
        for (String key : projectStructure.getClassStructures().keySet()) {
            if (!classMetrics.containsKey(key)) {
                ClassMetricsContainer classMetricsContainer = new ClassMetricsContainer();
                classMetrics.put(key, classMetricsContainer);
            }
            classMetrics.get(key).computeAllClassMetrics(key, projectStructure);
            classMetrics.get(key).computeAllRelationMetrics(key, projectStructure);
        }
    }
}
