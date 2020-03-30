package com.ucl.ADA.model.repository;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ucl.ADA.model.base_entity.BaseEntity;
import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.owner.Owner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "REPOSITORY")
public class GitRepo extends BaseEntity {

    /**
     * Name of the git repository
     */
    @Column(name = "repo_name", nullable = false)
    private String repoName;

    /**
     * Set of branches corresponding to this repository entity
     */
    @OneToMany(mappedBy = "repository", targetEntity = Branch.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    Set<Branch> branches = new LinkedHashSet<>();

    /**
     * Owner of this repository entity
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonManagedReference
    private Owner owner;

}
