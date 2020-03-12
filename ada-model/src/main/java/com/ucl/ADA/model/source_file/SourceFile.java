package com.ucl.ADA.model.source_file;

import com.ucl.ADA.model.BaseEntity;
import com.ucl.ADA.model.snapshot.Snapshot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SourceFile extends BaseEntity {

    /**
     * SHA1 file hash of this source file.
     */
    private String fileHash;

    /**
     * Snapshot corresponding to this source file
     */
    private Snapshot snapshot;

    /**
     * Fully qualified file name of this source file
     */
    private String fileName;

}
