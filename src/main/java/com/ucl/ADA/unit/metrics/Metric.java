package com.ucl.ADA.unit.metrics;

import javax.persistence.*;

@Entity(name = "METRIC")
public class Metric {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "TYPE")
    private MetricTypes type;

    @Column(nullable = false, name = "VALUE")
    private Float value;

    public Metric() {
    }

    public Metric(MetricTypes type, Float value) {
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

    public MetricTypes getType() {
        return type;
    }

    public void setType(MetricTypes type) {
        this.type = type;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
