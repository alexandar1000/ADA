package com.ucl.ADA.model.owner;

import com.ucl.ADA.model.BaseEntity;
import com.ucl.ADA.model.repository.GitRepo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Owner extends BaseEntity {

    /**
     * Name of the user or organisation. Has to be unique since GitHub usernames are unique
     */
    private String username;

    /**
     * Set of Git repositories owned by this owner
     */
    Set<GitRepo> repos = new LinkedHashSet<>();

    /**
     * Constructor used for testing purposes
     * @param username name of the owner
     */
    public Owner(String username) {
        this.username = username;
    }

}
