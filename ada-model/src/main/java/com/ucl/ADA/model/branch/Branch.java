package com.ucl.ADA.model.branch;

import com.ucl.ADA.model.repository.GitRepository;
import com.ucl.ADA.model.snapshot.Snapshot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "BRANCH")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    @Getter private Long branchID;

    @Column(name = "branch_name", nullable = false)
    @Getter private String branchName;

    @ManyToOne
    @JoinColumn(name = "repo_id", nullable = false)
    private GitRepository repository;

    @OneToMany(mappedBy = "branch", targetEntity = Snapshot.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter private Set<Snapshot> snapshots = new HashSet<>();

}
