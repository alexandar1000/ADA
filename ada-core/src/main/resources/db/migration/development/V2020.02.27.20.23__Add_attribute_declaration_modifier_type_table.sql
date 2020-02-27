CREATE TABLE ATTRIBUTE_DECLARATION_MODIFIER_TYPE
(
    attribute_declaration_id BIGINT NOT NULL REFERENCES ATTRIBUTE_DECLARATION (id),
    modifier_type_id         BIGINT NOT NULL REFERENCES MODIFIER_TYPE (id),
    UNIQUE (attribute_declaration_id, modifier_type_id)
)