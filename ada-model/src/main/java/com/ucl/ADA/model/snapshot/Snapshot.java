package com.ucl.ADA.model.snapshot;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.project_structure.ProjectStructure;
import com.ucl.ADA.model.source_file.SourceFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SNAPSHOT")
public class Snapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snapshot_id")
    private Long snapshotID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    @JsonManagedReference
    private Branch branch;

    @OneToMany(mappedBy = "snapshot", targetEntity = SourceFile.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("file_hash ASC")
    @JsonBackReference
    private Set<SourceFile> sourceFiles = new LinkedHashSet<>();

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_structure_id")
    @JsonManagedReference
    private ProjectStructure projectStructure;

}
