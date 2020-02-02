package com.ucl.ADA.repository_downloader.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "REPOSITORIES")
public class GitRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repo_id", nullable = false)
    private Long repoID;

    @Column(name = "repo_name", nullable = false)
    private String repoName;

    @OneToMany(mappedBy = "ID.repository",targetEntity = Branch.class, cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Branch> branches = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;

    public GitRepository(){}

    public GitRepository(String repoName, User user) {
        this.repoName = repoName;
        this.user = user;
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

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Branch> getBranches() {
        return branches;
    }
}
