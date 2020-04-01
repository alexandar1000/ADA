CREATE TABLE CLASS_STRUCTURE
(
    id                    BIGINT  NOT NULL GENERATED ALWAYS AS IDENTITY (START 1) PRIMARY KEY,
    class_metric_value_id BIGINT  NOT NULL REFERENCES CLASS_METRIC_VALUE (id),
    class_name            VARCHAR NOT NULL,
    file_hash             VARCHAR NOT NULL,
    file_path             VARCHAR NOT NULL
)