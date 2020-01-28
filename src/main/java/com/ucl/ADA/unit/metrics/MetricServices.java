package com.ucl.ADA.unit.metrics;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricServices {
    @Autowired
    private MetricRepository metricRepository;

    private Faker faker = new Faker();

    protected void saveRandomMetric() {
        Metric m = new Metric(MetricTypes.SIMPLE_METRIC, (float) faker.number().randomDouble(6, -100000000, 10000000));
        metricRepository.save(m);
    }

    public List<Metric> getAllMetrics() {
        return metricRepository.findAll();
    }

    public Metric getMetric(Long id) {
        return metricRepository.findById(id).orElse(null);
    }
}

