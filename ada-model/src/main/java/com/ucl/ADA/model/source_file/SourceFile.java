package com.ucl.ADA.model.source_file;

import com.ucl.ADA.model.base_entity.BaseEntity;
import com.ucl.ADA.model.class_structure.ClassStructure;
import com.ucl.ADA.model.snapshot.Snapshot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SourceFile extends BaseEntity {

    /** SHA1 file hash of this source file. */
    private String fileHash;

    /** Set of snapshots contains this source file */
    private Set<Snapshot> snapshots;

    /** Fully qualified file name of this source file */
    private String fileName;

    /** a map of ClassStructures, the key is qualified class name */
    private Map<String, ClassStructure> classStructures = new HashMap<>();


    @Override
    public int hashCode() {
        int result = fileHash.hashCode();
        result = 31 * result + fileName.hashCode();
        return result;
    }
}
