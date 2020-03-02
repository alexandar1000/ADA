package com.ucl.ADA.model.source_file;

import com.ucl.ADA.model.snapshot.Snapshot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SOURCEFILE")
public class SourceFile {

    @Id
    @Column(name = "file_hash")
    private int fileHash;

    @ManyToOne
    @JoinColumn(name = "snapshot_id")
    private Snapshot snapshot;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceFile that = (SourceFile) o;
        return fileHash == that.fileHash &&
                snapshot.equals(that.snapshot) &&
                fileName.equals(that.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(snapshot, fileName);
    }
}
