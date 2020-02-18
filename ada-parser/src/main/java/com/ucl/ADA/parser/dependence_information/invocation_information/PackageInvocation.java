package com.ucl.ADA.parser.dependence_information.invocation_information;

import lombok.Getter;

@Getter
public class PackageInvocation extends ElementInvocation {
    /**
     * The constructor of the package invocation object.
     * @param name the name of the package being invoked
     */
    public PackageInvocation(String name) {
        super(name);
    }
}
