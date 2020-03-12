package com.ucl.ADA.model.owner;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ucl.ADA.model.BaseEntity;
import com.ucl.ADA.model.repository.GitRepo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OWNER")
public class Owner extends BaseEntity {

    /**
     * Name of the user or organisation. Has to be unique since GitHub usernames are unique
     */
    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    /**
     * Set of Git repositories owned by this owner
     */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST, targetEntity = GitRepo.class)
    @JsonBackReference
    Set<GitRepo> repos = new LinkedHashSet<>();

    /**
     * Constructor used for testing purposes
     * @param username name of the owner
     */
    public Owner(String username) {
        this.username = username;
    }

}
