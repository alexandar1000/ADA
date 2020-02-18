package com.ucl.ADA.model.dependence_information.invocation_information;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class MethodInvocation extends ElementInvocation {
    /**
     * The list of parameters which have been passes to the method on invocation.
     */
    private List<PassedParameter> passedParameters = new ArrayList<>();

    /**
     * The constructor of the method invocation object.
     * @param name name of the constructor being invoked
     * @param passedParameters The list of parameters whit which the constructor has been invoked with
     */
    public MethodInvocation(String name, ArrayList<PassedParameter> passedParameters) {
        super(name);
        this.passedParameters.addAll(passedParameters);
    }
}
