package com.ucl.ADA.model.dependence_information.invocation_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PackageInvocation extends ElementInvocation {

    /**
     * The constructor of the package invocation object.
     *
     * @param name the name of the package being invoked
     */
    public PackageInvocation(String name) {
        super(name);
    }
}
