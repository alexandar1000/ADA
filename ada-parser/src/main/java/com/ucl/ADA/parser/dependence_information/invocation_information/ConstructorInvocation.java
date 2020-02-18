package com.ucl.ADA.parser.dependence_information.invocation_information;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class ConstructorInvocation extends ElementInvocation {
    /**
     * The list of parameters which have been passes to the constructor on invocation.
     */
    private ArrayList<String> passedParameters = new ArrayList<>();

    /**
     * The constructor of the attribute invocation object.
     * @param name name of the constructor being invoked
     * @param passedParameters The list of parameters whit which the constructor has been invoked with
     */
    public ConstructorInvocation(String name, ArrayList<String> passedParameters) {
        super(name);
        this.passedParameters.addAll(passedParameters);
    }
}
