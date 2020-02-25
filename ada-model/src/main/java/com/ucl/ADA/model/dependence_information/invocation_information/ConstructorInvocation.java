package com.ucl.ADA.model.dependence_information.invocation_information;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ConstructorInvocation extends ElementInvocation {
    /**
     * The list of parameters which have been passes to the constructor on invocation.
     */
    private List<PassedParameter> passedParameters = new ArrayList<>();

    /**
     * The constructor of the attribute invocation object.
     * @param name name of the constructor being invoked
     * @param passedParameters The list of parameters whit which the constructor has been invoked with
     */
    @Builder
    public ConstructorInvocation(String name, List<PassedParameter> passedParameters) {
        super(name);
        this.passedParameters.addAll(passedParameters);
    }
}
