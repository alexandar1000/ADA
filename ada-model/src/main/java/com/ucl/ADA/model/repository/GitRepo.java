package com.ucl.ADA.model.repository;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ucl.ADA.model.BaseEntity;
import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.owner.Owner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Setter
@NoArgsConstructor
@Table(name = "REPOSITORY")
public class GitRepo extends BaseEntity {

    @Column(name = "repo_name", nullable = false)
    @Getter private String repoName;

    @OneToMany(mappedBy = "repository", targetEntity = Branch.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @Getter Set<Branch> branches = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonManagedReference
    private Owner owner;

}
