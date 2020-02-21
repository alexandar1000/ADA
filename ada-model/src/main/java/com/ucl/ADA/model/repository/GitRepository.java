package com.ucl.ADA.model.repository;

import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.owner.Owner;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "REPOSITORY   ")
public class GitRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repo_id", nullable = false)
    private Long repoID;

    @Column(name = "repo_name", nullable = false)
    private String repoName;

    @OneToMany(mappedBy = "repository",targetEntity = Branch.class, cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Branch> branches = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    public GitRepository(){}

    public GitRepository(String repoName, Owner owner) {
        this.repoName = repoName;
        this.owner = owner;
    }

    public Long getRepoID() {
        return repoID;
    }

    public void setRepoID(Long repoID) {
        this.repoID = repoID;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Set<Branch> getBranches() {
        return branches;
    }
}
