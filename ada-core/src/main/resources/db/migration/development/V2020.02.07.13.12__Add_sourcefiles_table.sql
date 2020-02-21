CREATE TABLE SOURCEFILE
(
    snapshot_id BIGINT NOT NULL,
    file_hash INT NOT NULL,
    file_name TEXT NOT NULL,
    CONSTRAINT source_files_pkey PRIMARY KEY (file_hash),
    CONSTRAINT source_files_fkey FOREIGN KEY (snapshot_id)
        REFERENCES SNAPSHOT (snapshot_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)