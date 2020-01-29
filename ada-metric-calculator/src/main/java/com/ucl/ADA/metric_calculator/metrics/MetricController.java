package com.ucl.ADA.metric_calculator.metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metrics")
public class MetricController {

    @Autowired
    MetricServices metricServices;

    /**
     * Saves a new random metric into the database
     * */
    @PostMapping
    public void saveNewRandomMetric() {
        metricServices.saveRandomMetric();
    }

    /**
     * Retrieves all metrics stored in the database.
     * @return a list of all of the metrics present in the database.
     */
    @GetMapping(produces = {"application/json"})
    @ResponseBody
    public List<Metric> retrieveAllMetrics() {
        return metricServices.getAllMetrics();
    }

    /**
     * Returns a specific metric value from the database
     * @param id the id of the metric value which is retrieved
     * @return the metric value with the associated id
     */
    @GetMapping(value = "/{id}", produces = {"application/json"})
    @ResponseBody
    public Metric retrieveMetricById(@PathVariable Long id) {
        return metricServices.getMetric(id);
    }
}
