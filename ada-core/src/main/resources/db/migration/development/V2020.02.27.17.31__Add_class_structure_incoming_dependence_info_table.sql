CREATE TABLE CLASS_STRUCTURE_INCOMING_DEPENDENCE_INFO
(
    class_structure_id BIGINT       NOT NULL REFERENCES CLASS_STRUCTURE (id),
    class_name         VARCHAR NOT NULL,
    dependence_info_id BIGINT       NOT NULL REFERENCES DEPENDENCE_INFO (id),
    UNIQUE (class_name, dependence_info_id)
)