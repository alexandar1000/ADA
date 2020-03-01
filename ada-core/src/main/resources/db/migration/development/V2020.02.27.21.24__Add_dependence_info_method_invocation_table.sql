CREATE TABLE DEPENDENCE_INFO_METHOD_INVOCATION
(
    dependence_info_id   BIGINT NOT NULL REFERENCES DEPENDENCE_INFO (id),
    method_invocation_id BIGINT NOT NULL REFERENCES METHOD_INVOCATION (id),
    UNIQUE (dependence_info_id, method_invocation_id)
)