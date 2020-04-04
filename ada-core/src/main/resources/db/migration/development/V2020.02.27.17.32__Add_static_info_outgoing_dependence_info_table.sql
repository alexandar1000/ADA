CREATE TABLE STATIC_INFO_OUTGOING_DEPENDENCE_INFO
(
    static_info_id     BIGINT  NOT NULL REFERENCES STATIC_INFO (id),
    class_name         VARCHAR NOT NULL,
    dependence_info_id BIGINT  NOT NULL REFERENCES DEPENDENCE_INFO (id),
    UNIQUE (static_info_id, dependence_info_id)
)