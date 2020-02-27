CREATE TABLE CONSTRUCTOR_DECLARATION_MODIFIER_TYPE
(
    constructor_declaration_id BIGINT NOT NULL REFERENCES CONSTRUCTOR_DECLARATION (id),
    modifier_type_id           BIGINT NOT NULL REFERENCES MODIFIER_TYPE (id),
    UNIQUE (constructor_declaration_id, modifier_type_id)
)