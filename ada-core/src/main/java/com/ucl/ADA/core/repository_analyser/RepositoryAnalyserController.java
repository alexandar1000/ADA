package com.ucl.ADA.core.repository_analyser;

import com.ucl.ADA.repository_downloader.GitRepoInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analyser")
public class RepositoryAnalyserController {
    @Autowired
    RepositoryAnalyserServices repositoryAnalyserServices;

    /**
     * A top level endpoint which will analyse the entire repository branch which is provided, at the current time
     * point. Currently awaiting other modules to be developed.
     * @return Currently only a string, but in the future something which links to the resulting data.
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> analyseRepository(@RequestParam(value = "url", defaultValue = "") String url,
                                               @RequestParam(value = "branch", defaultValue = "") String branchName) {

        try {
            return ResponseEntity.ok(repositoryAnalyserServices.analyseRepositoryService(url, branchName));
        } catch (GitRepoInvalidException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

    }
}