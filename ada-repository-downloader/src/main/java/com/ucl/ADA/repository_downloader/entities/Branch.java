package com.ucl.ADA.repository_downloader.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Branches")
public class Branch {

    @EmbeddedId
    private ID ID;

    @OneToMany(mappedBy = "ID.branch", targetEntity = Snapshot.class, cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Branch> snapshots = new HashSet<>();

    public Branch(){}

    public ID getID() {
        return ID;
    }

    public void setID(ID ID) {
        this.ID = ID;
    }

    public Set<Branch> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(Set<Branch> snapshots) {
        this.snapshots = snapshots;
    }

    @Embeddable
    public static class ID implements Serializable {

        @Column(name = "BranchName")
        private String branchName;

        @ManyToOne
        @JoinColumn(name = "FK_RepoID")
        private GitRepository repository;

        public ID(){}

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public void setRepository(GitRepository gitRepository) {
            this.repository = gitRepository;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ID ID = (ID) o;
            return Objects.equals(branchName, ID.branchName) &&
                    Objects.equals(repository, ID.repository);
        }

        @Override
        public int hashCode() {
            return Objects.hash(branchName, repository);
        }
    }
}

