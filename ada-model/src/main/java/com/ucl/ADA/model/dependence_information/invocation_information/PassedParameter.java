package com.ucl.ADA.model.dependence_information.invocation_information;

import com.ucl.ADA.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PASSED_PARAMETER")
public class PassedParameter extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    public PassedParameter(String name) {
        this.name = name;
    }

}
