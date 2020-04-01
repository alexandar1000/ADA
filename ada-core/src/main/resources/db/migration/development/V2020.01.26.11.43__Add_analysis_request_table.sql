CREATE TABLE ANALYSIS_REQUEST
(
    id          BIGINT                   NOT NULL GENERATED ALWAYS AS IDENTITY (START 1) PRIMARY KEY,
    "timestamp" TIMESTAMP WITH TIME ZONE NOT NULL,
    snapshot_id BIGINT                   NOT NULL REFERENCES SNAPSHOT (id),
    branch_id   BIGINT                   NOT NULL REFERENCES BRANCH (id)
);