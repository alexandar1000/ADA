CREATE TABLE METHOD_DECLARATION_MODIFIER_TYPE
(
    method_declaration_id BIGINT NOT NULL REFERENCES METHOD_DECLARATION (id),
    modifier_type_id      BIGINT NOT NULL REFERENCES MODIFIER_TYPE (id),
    UNIQUE (method_declaration_id, modifier_type_id)
)