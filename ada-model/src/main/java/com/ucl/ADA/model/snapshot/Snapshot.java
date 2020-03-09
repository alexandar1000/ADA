package com.ucl.ADA.model.snapshot;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ucl.ADA.model.BaseEntity;
import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.model.source_file.SourceFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SNAPSHOT")
public class Snapshot extends BaseEntity {

    /**
     * Branch entity corresponding to this snapshot
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    @JsonManagedReference
    private Branch branch;

    /**
     * Set of source files contained in this snapshot
     */
    @OneToMany(mappedBy = "snapshot", targetEntity = SourceFile.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<SourceFile> sourceFiles = new LinkedHashSet<>();

    /**
     * Timestamp of when the snapshot was created, in UTC time standard
     */
    @Column(name = "timestamp")
    private OffsetDateTime timestamp;

    /**
     * Project structure entity corresponding to this snapshot
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_structure_id")
    @JsonIgnore
    private ProjectStructure projectStructure;

}
