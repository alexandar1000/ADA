package com.ucl.ADA.repository_downloader.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Snapshot")
public class Snapshot {

    @EmbeddedId
    private ID ID;

    public ID getID() {
        return ID;
    }

    public void setID(ID ID) {
        this.ID = ID;
    }

    public Snapshot(){}

    @Embeddable
    public static class ID implements Serializable {
        @ManyToOne
        @JoinColumns({
                @JoinColumn(name = "FK_branchname", referencedColumnName = "BranchName"),
                @JoinColumn(name = "FK_b_repoid", referencedColumnName = "FK_RepoID")
        })
        private Branch branch;

        @Column(name = "Timestamp")
        private LocalDateTime timestamp;

        @Column(name = "fileName")
        private String fileName;

        public ID(){}

        public void setBranch(Branch branch) {
            this.branch = branch;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ID that = (ID) o;
            return Objects.equals(branch, that.branch) &&
                    Objects.equals(timestamp, that.timestamp);
        }

        @Override
        public int hashCode() {
            return Objects.hash(branch, timestamp);
        }
    }
}
