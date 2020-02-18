package com.ucl.ADA.parser.dependence_information.declaration_information;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor
public class ParameterDeclaration extends ElementDeclaration {
    private String type;

    public ParameterDeclaration(String name, String type) {
        super(name);
        this.type = type;
    }
}
