CREATE TABLE METHOD_DECLARATION_MODIFIER_TYPE
(
    method_declaration_id BIGINT NOT NULL REFERENCES METHOD_DECLARATION (id),
    modifier_type         VARCHAR,
    UNIQUE (method_declaration_id, modifier_type)
)