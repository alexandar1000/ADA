package com.ucl.ADA.metric_calculator.metrics;

import com.ucl.ADA.parser.extractor.SimpleParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
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


    @GetMapping(value = "/pt", produces = {"application/json"})
    @ResponseBody
    public String genParseTree() throws FileNotFoundException {

    String SRC_DIRECTORY_PATH="./ada-parser//src/main/resources/source_to_parse/abc";
    String SRC_FILE_PATH = SRC_DIRECTORY_PATH + "/ServiceCentre.java";

        SimpleParser sp = new SimpleParser();
        return sp.getParsedSourceInJSON(SRC_DIRECTORY_PATH, SRC_FILE_PATH);
    }
}
