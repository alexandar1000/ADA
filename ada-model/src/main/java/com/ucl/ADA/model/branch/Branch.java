package com.ucl.ADA.model.branch;

import com.ucl.ADA.model.BaseEntity;
import com.ucl.ADA.model.repository.GitRepo;
import com.ucl.ADA.model.snapshot.Snapshot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Branch extends BaseEntity {

    /**
     * Name of branch
     */
    private String branchName;

    /**
     * Corresponding GitRepo entity
     */
    private GitRepo repository;

    /**
     * A LinkedHashSet of all snapshots corresponding to this branch
     */
    private Set<Snapshot> snapshots = new LinkedHashSet<>();

}
