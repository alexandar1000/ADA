package com.ucl.repostitoryDownloader.repo;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RepoController {

    @Autowired
    private RepoService repoService;

    @PostMapping("/download")
    public Repo download(@RequestBody Repo repo) {
        try {
            repoService.downloadRepository(repo);
            return repoService.repository;
        } catch (GitAPIException e) {
            e.printStackTrace();
            return null;
        }
    }
}
