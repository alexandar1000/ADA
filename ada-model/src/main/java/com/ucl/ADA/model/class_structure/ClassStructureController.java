package com.ucl.ADA.model.class_structure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("owners/{owner}/repositories/{repository}/branches/{branch}/requests/{timestamp}/class-structures")
public class ClassStructureController {

    @Autowired
    private ClassStructureService classStructureService;

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/{className}")
    public ClassStructure getClassStructureGivenOwnerRepoBranchTimestampAndClassName(@PathVariable String owner,
                                                                                     @PathVariable String repository,
                                                                                     @PathVariable String branch,
                                                                                     @PathVariable String timestamp,
                                                                                     @PathVariable String className) throws DateTimeParseException {
        return classStructureService.getClassStructureGivenOwnerRepoBranchTimestampAndClassName(owner, repository, branch, timestamp, className);
    }
}
