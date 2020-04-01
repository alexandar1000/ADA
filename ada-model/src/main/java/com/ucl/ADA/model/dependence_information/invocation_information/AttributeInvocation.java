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
@Table(name = "ATTRIBUTE_INVOCATION")
public class AttributeInvocation extends ElementInvocation {

    /**
     * The constructor of the attribute invocation object.
     *
     * @param name name of the attribute being invoked
     */
    public AttributeInvocation(String name) {
        super(name);
    }
}
