package com.ucl.ADA.parser.dependence_information.invocation_information;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PackageInvocation extends ElementInvocation {
    /**
     * The constructor of the package invocation object.
     * @param packageName the name of the package being invoked
     */
    public PackageInvocation(String packageName) {
        super(packageName);
    }
}
