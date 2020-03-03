package com.ucl.ADA.model.snapshot;

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

@Entity
@Setter
@NoArgsConstructor
@Table(name = "SNAPSHOT")
public class Snapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snapshot_id")
    @Getter private Long snapshotID;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "snapshot", targetEntity = SourceFile.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("file_hash ASC")
    @Getter private Set<SourceFile> sourceFiles = new LinkedHashSet<>();

    @Column(name = "timestamp")
    @Getter private LocalDateTime timestamp;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_structure_id")
    @Getter private ProjectStructure projectStructure;

    public void setProjectStructure(ProjectStructure projectStructure) {
        this.projectStructure = projectStructure;
    }

}
