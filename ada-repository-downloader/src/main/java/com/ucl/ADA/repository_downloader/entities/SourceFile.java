package com.ucl.ADA.repository_downloader.entities;

import javax.persistence.*;
import java.util.Objects;

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

    public SourceFile(){}

    public void setFileHash(int fileHash) {
        this.fileHash = fileHash;
    }

    public void setSnapshot(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileHash() {
        return fileHash;
    }

    public String getFileName() {
        return fileName;
    }

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
