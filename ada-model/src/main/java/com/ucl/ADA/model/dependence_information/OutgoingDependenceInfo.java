package com.ucl.ADA.model.dependence_information;

import com.ucl.ADA.model.static_information.StaticInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OUTGOING_DEPENDENCE_INFO")
public class OutgoingDependenceInfo extends DependenceInfo {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "static_info_id")
    private StaticInfo staticInfo;

}
