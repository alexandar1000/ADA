CREATE TABLE CLASS_STRUCTURE
(
    id                     BIGSERIAL PRIMARY KEY,
    package_declaration_id BIGINT NOT NULL,
    class_metric_value_id  BIGINT NOT NULL
)