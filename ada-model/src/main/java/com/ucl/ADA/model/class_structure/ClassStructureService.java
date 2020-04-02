package com.ucl.ADA.model.class_structure;

import com.ucl.ADA.model.analysis_request.AnalysisRequestService;
import com.ucl.ADA.model.snapshot.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeParseException;

@Service
public class ClassStructureService {

    @Autowired
    private AnalysisRequestService analysisRequestService;

    @Autowired
    private ClassStructureRepository classStructureRepository;


    public ClassStructure getClassStructureGivenOwnerRepoBranchTimestampAndClassName(String owner,
                                                                                     String repository,
                                                                                     String branch,
                                                                                     String timestamp,
                                                                                     String className) throws DateTimeParseException {
        Snapshot snapshot = analysisRequestService.getSnapshotOfAnalysisRequestByOwnerRepoBranchAndTimestamp(owner, repository, branch, timestamp);
        return classStructureRepository.findBySnapshotsAndClassName(snapshot, className);
    }
}
