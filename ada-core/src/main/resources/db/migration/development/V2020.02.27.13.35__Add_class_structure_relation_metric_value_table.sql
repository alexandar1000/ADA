CREATE TABLE CLASS_STRUCTURE_RELATION_METRIC_VALUE
(
    class_structure_id       BIGINT       NOT NULL REFERENCES CLASS_STRUCTURE (id),
    class_name               VARCHAR NOT NULL,
    relation_metric_value_id BIGINT       NOT NULL REFERENCES RELATION_METRIC_VALUE (id),
    UNIQUE (class_name, relation_metric_value_id)
)