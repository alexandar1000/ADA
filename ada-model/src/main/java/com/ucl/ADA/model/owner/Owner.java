package com.ucl.ADA.model.owner;

import com.ucl.ADA.model.repository.GitRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OWNER")
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

    public Owner(String userName) {
        this.userName = userName;
    }

}
