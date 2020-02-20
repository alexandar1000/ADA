package com.ucl.ADA.metric_calculator.metrics;

import com.ucl.ADA.model.project_structure.ProjectStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricServices {
    @Autowired
    private MetricRepository metricRepository;

    public ProjectStructure computeAllMetrics(ProjectStructure projectStructure) {
        projectStructure.computeAllMetrics();
        return projectStructure;
    }
    
    public List<Metric> getAllMetrics() {
        return metricRepository.findAll();
    }

    public Metric getMetric(Long id) {
        return metricRepository.findById(id).orElse(null);
    }
}

