package com.ucl.ADA.model.dependence_information.declaration_information;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParameterDeclaration extends ElementDeclaration {
    private String type;

    @Builder
    public ParameterDeclaration(String name, String type) {
        super(name);
        this.type = type;
    }
}
