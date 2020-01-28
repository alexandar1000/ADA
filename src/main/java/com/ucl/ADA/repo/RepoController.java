package com.ucl.ADA.repo;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RepoController {
    @Autowired
    private RepoService repoService;

    @PostMapping("/download")
    public void download(@RequestBody Repo repo) {
        try {
              repoService.downloadRepository(repo);
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }
}
