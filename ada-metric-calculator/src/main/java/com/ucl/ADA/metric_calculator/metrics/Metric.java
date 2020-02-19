package com.ucl.ADA.metric_calculator.metrics;

import com.ucl.ADA.model.metrics.class_metrics.ClassMetricType;

import javax.persistence.*;

@Entity(name = "METRIC")
public class Metric {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "TYPE")
    private ClassMetricType type;

    @Column(nullable = false, name = "VALUE")
    private Float value;

    public Metric() {
    }

    public Metric(ClassMetricType type, Float value) {
        this.id = null;
        this.type = type;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassMetricType getType() {
        return type;
    }

    public void setType(ClassMetricType type) {
        this.type = type;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
