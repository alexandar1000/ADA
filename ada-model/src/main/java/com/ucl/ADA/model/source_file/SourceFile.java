package com.ucl.ADA.model.source_file;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ucl.ADA.model.BaseEntity;
import com.ucl.ADA.model.snapshot.Snapshot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "SOURCE_FILE")
public class SourceFile extends BaseEntity {

    @Column(name = "file_hash")
    @Getter
    private String fileHash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snapshot_id")
    @JsonManagedReference
    private Snapshot snapshot;

    @Column(name = "file_name", nullable = false)
    @Getter
    private String fileName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceFile that = (SourceFile) o;
        return fileHash.equals(that.fileHash) &&
                snapshot.equals(that.snapshot) &&
                fileName.equals(that.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(snapshot, fileName);
    }
}
