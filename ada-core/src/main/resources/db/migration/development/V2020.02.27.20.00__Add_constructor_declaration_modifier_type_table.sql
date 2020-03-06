CREATE TABLE CONSTRUCTOR_DECLARATION_MODIFIER_TYPE
(
    constructor_declaration_id BIGINT NOT NULL REFERENCES CONSTRUCTOR_DECLARATION (id),
    modifier_type              VARCHAR,
    UNIQUE (constructor_declaration_id, modifier_type)
)