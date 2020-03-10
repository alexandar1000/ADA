CREATE TABLE DEPENDENCE_INFO_ATTRIBUTE_INVOCATION
(
    dependence_info_id      BIGINT NOT NULL REFERENCES DEPENDENCE_INFO (id),
    attribute_invocation_id BIGINT NOT NULL REFERENCES ATTRIBUTE_INVOCATION (id),
    UNIQUE (dependence_info_id, attribute_invocation_id)
)