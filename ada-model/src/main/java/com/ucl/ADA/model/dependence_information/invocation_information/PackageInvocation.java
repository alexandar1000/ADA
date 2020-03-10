package com.ucl.ADA.model.dependence_information.invocation_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PACKAGE_INVOCATION")
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
