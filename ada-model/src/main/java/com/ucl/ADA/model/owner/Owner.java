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

    // There are no duplicates in Github usernames, hence the uniqueness of the column
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = GitRepo.class)
    @JsonBackReference
    Set<GitRepo> repos = new LinkedHashSet<>();

    public Owner(String userName) {
        this.userName = userName;
    }

}
