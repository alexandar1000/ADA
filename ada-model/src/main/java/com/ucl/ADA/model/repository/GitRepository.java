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
@Setter
@NoArgsConstructor
@Table(name = "REPOSITORY")
public class GitRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repo_id", nullable = false)
    @Getter private Long repoID;

    @Column(name = "repo_name", nullable = false)
    @Getter private String repoName;

    @OneToMany(mappedBy = "repository",targetEntity = Branch.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter Set<Branch> branches = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

}
