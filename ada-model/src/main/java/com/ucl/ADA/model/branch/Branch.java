package com.ucl.ADA.model.branch;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ucl.ADA.model.BaseEntity;
import com.ucl.ADA.model.repository.GitRepo;
import com.ucl.ADA.model.snapshot.Snapshot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "BRANCH")
public class Branch extends BaseEntity {

    /**
     * Name of branch
     */
    @Column(name = "branch_name", nullable = false)
    @Getter private String branchName;

    /**
     * Corresponding GitRepo entity
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repo_id", nullable = false)
    @JsonManagedReference
    private GitRepo repository;

    /**
     * A LinkedHashSet of all snapshots corresponding to this branch
     */
    @OneToMany(mappedBy = "branch", targetEntity = Snapshot.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @Getter private Set<Snapshot> snapshots = new LinkedHashSet<>();

}
