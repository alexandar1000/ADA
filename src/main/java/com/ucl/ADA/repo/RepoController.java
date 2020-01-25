package com.ucl.ADA.repo;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepoController {
    @Autowired RepoServices repoServices;

    @GetMapping("/analyse")
    public String analyse(@RequestParam(value = "url") String url,
                          @RequestParam(value = "branch", defaultValue = "master") String branch) {

        try {
            repoServices.downloadRepository(url, branch);
            return "success";
        } catch (GitAPIException e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
