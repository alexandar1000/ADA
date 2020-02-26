CREATE TABLE ATTRIBUTE_DECLARATION
(
    id           BIGSERIAL PRIMARY KEY,
    element_name VARCHAR(255) NOT NULL,
    type         VARCHAR(255) NOT NULL,
    value        VARCHAR(255)
)