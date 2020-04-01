CREATE TABLE SNAPSHOT_CLASS_STRUCTURE
(
    snapshot_id        BIGINT  NOT NULL REFERENCES SNAPSHOT (id),
    class_name         VARCHAR NOT NULL,
    class_structure_id BIGINT  NOT NULL REFERENCES CLASS_STRUCTURE (id),
    UNIQUE (snapshot_id, class_structure_id)
)