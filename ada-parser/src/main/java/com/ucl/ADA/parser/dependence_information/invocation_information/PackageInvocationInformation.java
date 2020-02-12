package com.ucl.ADA.parser.dependence_information.invocation_information;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PackageInvocationInformation extends ElementInvocationInformation {
    /**
     * The constructor of the package invocation object.
     * @param packageName the name of the package being invoked
     */
    public PackageInvocationInformation(String packageName) {
        super(packageName);
    }
}
