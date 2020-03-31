package com.ucl.ADA.model.dependence_information;

import com.ucl.ADA.model.class_structure.ClassStructure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "INCOMING_DEPENDENCE_INFO")
public class IncomingDependenceInfo extends DependenceInfo {

    @ManyToMany(mappedBy = "incomingDependenceInfos")
    private Set<ClassStructure> classStructures;

}
