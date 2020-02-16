package com.ucl.ADA.repository_downloader.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BRANCHES")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long branchID;


    @Column(name = "branch_name", nullable = false)
    private String branchName;

    @ManyToOne
    @JoinColumn(name = "fk_repo_id", nullable = false)
    private GitRepository repository;

    @OneToMany(mappedBy = "branch", targetEntity = Snapshot.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private
    Set<Snapshot> snapshots = new HashSet<>();

    public Branch(){}

    public Long getBranchID() {
        return branchID;
    }

    public void setBranchID(Long branchID) {
        this.branchID = branchID;
    }

    public void setRepository(GitRepository repository) {
        this.repository = repository;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Set<Snapshot> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(Set<Snapshot> snapshots) {
        this.snapshots = snapshots;
    }

}

