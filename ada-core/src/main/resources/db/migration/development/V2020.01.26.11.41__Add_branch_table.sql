CREATE TABLE BRANCH
(
    id          BIGINT                   NOT NULL GENERATED ALWAYS AS IDENTITY (START 1) PRIMARY KEY,
    branch_name VARCHAR                  NOT NULL,
    "timestamp" TIMESTAMP                WITH TIME ZONE NOT NULL,
    repo_id     BIGINT                   NOT NULL REFERENCES REPOSITORY (id)
)