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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    @JsonManagedReference
    private Branch branch;

    @OneToMany(mappedBy = "snapshot", targetEntity = SourceFile.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("file_hash ASC")
    @JsonBackReference
    private Set<SourceFile> sourceFiles = new LinkedHashSet<>();

    @Column(name = "timestamp")
    private OffsetDateTime timestamp;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_structure_id")
    @JsonIgnore
    private ProjectStructure projectStructure;

}
