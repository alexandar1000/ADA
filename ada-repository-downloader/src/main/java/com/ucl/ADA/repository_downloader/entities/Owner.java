package com.ucl.ADA.repository_downloader.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "OWNERS")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long ownerID;

    // There are no duplicates in Github usernames, hence the uniqueness of the column
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = GitRepository.class)
    Set<GitRepository> repos = new HashSet<>();

    public Owner(){};

    public Owner(String userName) {
        this.userName = userName;
    }

    public Long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Long ownerID) {
        this.ownerID = ownerID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<GitRepository> getRepos() {
        return repos;
    }

    public void setRepos(Set<GitRepository> repos) {
        this.repos = repos;
    }

}
