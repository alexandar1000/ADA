package com.ucl.ADA.model.static_information.declaration_information;

import lombok.Getter;

@Getter
public enum ModifierType {
    PUBLIC,
    PROTECTED,
    DEFAULT,
    PRIVATE,
    STATIC,
    FINAL,
    ABSTRACT,
    UNRESOLVED;
}
