package com.ucl.ADA.model.repository;

import com.ucl.ADA.model.base_entity.BaseEntity;
import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.owner.Owner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class GitRepo extends BaseEntity {

    /**
     * Name of the git repository
     */
    private String repoName;

    /**
     * Set of branches corresponding to this repository entity
     */
    Set<Branch> branches = new LinkedHashSet<>();

    /**
     * Owner of this repository entity
     */
    private Owner owner;

}
