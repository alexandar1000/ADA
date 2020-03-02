CREATE TABLE ATTRIBUTE_DECLARATION_MODIFIER_TYPE
(
    attribute_declaration_id BIGINT NOT NULL REFERENCES ATTRIBUTE_DECLARATION (id),
    modifier_type            VARCHAR(255),
    UNIQUE (attribute_declaration_id, modifier_type)
)