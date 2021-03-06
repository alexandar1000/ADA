CREATE TABLE DEPENDENCE_INFO_PACKAGE_INVOCATION
(
    dependence_info_id    BIGINT NOT NULL REFERENCES DEPENDENCE_INFO (id),
    package_invocation_id BIGINT NOT NULL REFERENCES PACKAGE_INVOCATION (id),
    UNIQUE (dependence_info_id, package_invocation_id)
)