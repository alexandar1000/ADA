package com.ucl.ADA.model.dependence_information.invocation_information;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CONSTRUCTOR_INVOCATION")
public class ConstructorInvocation extends ElementInvocation {

    /**
     * The list of parameters which have been passes to the constructor on invocation.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "CONSTRUCTOR_INVOCATION_PASSED_PARAMETER",
            joinColumns = @JoinColumn(name = "constructor_invocation_id"),
            inverseJoinColumns = @JoinColumn(name = "passed_parameter_id")
    )
    private List<PassedParameter> passedParameters;

    /**
     * The constructor of the attribute invocation object.
     *
     * @param name             name of the constructor being invoked
     * @param passedParameters The list of parameters which the constructor has been invoked with
     */
    public ConstructorInvocation(String name, List<PassedParameter> passedParameters) {
        super(name);
        this.passedParameters = passedParameters;
    }
}
