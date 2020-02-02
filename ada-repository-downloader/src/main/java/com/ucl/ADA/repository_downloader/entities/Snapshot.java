package com.ucl.ADA.repository_downloader.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "SNAPSHOT")
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
                @JoinColumn(name = "fk_branch_name", referencedColumnName = "branch_name"),
                @JoinColumn(name = "fk_b_repo_id", referencedColumnName = "fk_repo_id")
        })
        private Branch branch;

        @Column(name = "timestamp")
        private LocalDateTime timestamp;

        @Column(name = "file_name")
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
