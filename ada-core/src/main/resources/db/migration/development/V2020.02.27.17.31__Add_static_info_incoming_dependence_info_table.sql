CREATE TABLE STATIC_INFO_INCOMING_DEPENDENCE_INFO
(
    static_info_id              BIGINT  NOT NULL REFERENCES STATIC_INFO (id),
    class_name                  VARCHAR NOT NULL,
    incoming_dependence_info_id BIGINT  NOT NULL REFERENCES INCOMING_DEPENDENCE_INFO (id),
    UNIQUE (static_info_id, incoming_dependence_info_id)
)