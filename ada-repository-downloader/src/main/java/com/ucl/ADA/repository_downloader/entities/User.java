package com.ucl.ADA.repository_downloader.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userID;

    // There are no duplicates in Github usernames, hence the uniqueness of the column
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = GitRepository.class)
    Set<GitRepository> repos = new HashSet<>();

    public User(){};

    public User(String userName) {
        this.userName = userName;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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
