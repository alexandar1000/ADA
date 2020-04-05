package com.ucl.ADA.model.snapshot;

import com.ucl.ADA.model.analysis_request.AnalysisRequest;
import com.ucl.ADA.model.base_entity.BaseEntity;
import com.ucl.ADA.model.branch.Branch;
import com.ucl.ADA.model.class_structure.ClassStructure;
import com.ucl.ADA.model.source_file.SourceFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SNAPSHOT")
public class Snapshot extends BaseEntity {

    /**
     * Branch entity corresponding to this snapshot
     */
    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    /**
     * Set of analysis request that retrieved this snapshot
     */
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @OneToMany(mappedBy = "snapshot", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AnalysisRequest> analysisRequests = new HashSet<>();

    /**
     * Timestamp of the commit time of the snapshot, in UTC time standard
     */
    @Column(name = "timestamp", nullable = false)
    private OffsetDateTime commitTime;

    /**
     * a map of ClassStructures, the key is qualified class name
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "SNAPSHOT_CLASS_STRUCTURE",
            joinColumns = {@JoinColumn(name = "snapshot_id")},
            inverseJoinColumns = {@JoinColumn(name = "class_structure_id")})
    @MapKeyColumn(name = "class_name")
    private Map<String, ClassStructure> classStructures = new HashMap<>();

    /**
     * a map of source files, the key is the file path
     */
    @OneToMany(mappedBy = "snapshot", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @MapKey(name = "filePath")
    private Map<String, SourceFile> sourceFiles = new HashMap<>();

    /**
     * get class structure from snapshot by name
     *
     * @param name qualified class name
     * @return the class structure object has input name
     */
    protected ClassStructure getClassStructure(String name) {
        return classStructures.get(name);
    }

    protected void addClassStructure(String className, ClassStructure classStructure) {
        classStructures.put(className, classStructure);
    }

}
