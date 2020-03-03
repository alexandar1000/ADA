CREATE TABLE SNAPSHOT
(
    snapshot_id          BIGINT                      NOT NULL GENERATED ALWAYS AS IDENTITY (START 1),
    branch_id            BIGINT                      NOT NULL,
    "timestamp"          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT snapshot_pkey PRIMARY KEY (snapshot_id),
    CONSTRAINT snapshot_fkey FOREIGN KEY (branch_id)
        REFERENCES BRANCH (branch_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)