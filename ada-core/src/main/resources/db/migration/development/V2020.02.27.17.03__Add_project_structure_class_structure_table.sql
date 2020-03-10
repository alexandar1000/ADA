CREATE TABLE PROJECT_STRUCTURE_CLASS_STRUCTURE
(
    project_structure_id BIGINT       NOT NULL REFERENCES PROJECT_STRUCTURE (id),
    class_name           VARCHAR NOT NULL,
    class_structure_id   BIGINT       NOT NULL REFERENCES CLASS_STRUCTURE (id),
    UNIQUE (class_name, class_structure_id)
)