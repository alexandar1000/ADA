package com.ucl.ADA.model.source_file;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ucl.ADA.model.BaseEntity;
import com.ucl.ADA.model.snapshot.Snapshot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "SOURCE_FILE")
public class SourceFile extends BaseEntity {

    /**
     * SHA1 file hash of this source file.
     */
    @Column(name = "file_hash")
    @Getter
    private String fileHash;

    /**
     * Snapshot corresponding to this source file
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snapshot_id")
    @JsonManagedReference
    private Snapshot snapshot;

    /**
     * Fully qualified file name of this source file
     */
    @Column(name = "file_name", nullable = false)
    @Getter
    private String fileName;

}
