package com.ucl.ADA.model.source_file;

import com.ucl.ADA.model.base_entity.BaseEntity;
import com.ucl.ADA.model.snapshot.Snapshot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SOURCE_FILE")
public class SourceFile extends BaseEntity {

    @Column(name = "file_path")
    private String filePath;

    /**
     * the snapshot which contains the source file
     */
    @ManyToOne
    @JoinColumn(name = "snapshot_id")
    private Snapshot snapshot;

    /**
     * the set of qualified class names of all classes in a source file
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "SOURCE_FILE_CLASS_NAME",
            joinColumns = @JoinColumn(name = "source_file_id")
    )
    @Column(name = "class_name")
    private Set<String> classNames = new HashSet<>();

    /**
     * the hash of a file to determine if the content of file is unchanged
     */
    @Column(name = "file_hash")
    private String fileHash;

}
