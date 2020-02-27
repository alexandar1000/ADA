package com.ucl.ADA.model.dependence_information.declaration_information;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MODIFIER_TYPE")
public enum ModifierType {
    PUBLIC,
    PROTECTED,
    DEFAULT,
    PRIVATE,
    STATIC,
    FINAL,
    ABSTRACT,
    UNRESOLVED;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
