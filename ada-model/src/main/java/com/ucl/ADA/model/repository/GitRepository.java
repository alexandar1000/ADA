package com.ucl.ADA.model.repository;

import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.owner.Owner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "REPOSITORY")
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

    public GitRepository(String repoName, Owner owner) {
        this.repoName = repoName;
        this.owner = owner;
    }
}
