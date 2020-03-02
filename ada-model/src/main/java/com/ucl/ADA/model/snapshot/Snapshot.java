package com.ucl.ADA.model.snapshot;

import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.source_file.SourceFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "SNAPSHOT")
public class Snapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snapshot_id")
    private Long snapshotID;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "snapshot", targetEntity = SourceFile.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private
    Set<Branch> sourceFiles = new HashSet<>();

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

}
