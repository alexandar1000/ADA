CREATE TABLE SOURCE_FILE_CLASS_NAME
(
    source_file_id BIGINT  NOT NULL REFERENCES SNAPSHOT (id),
    class_name     VARCHAR NOT NULL,
    UNIQUE (source_file_id, class_name)
)