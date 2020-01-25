package com.ucl.ADA.repo;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepoController {

    @GetMapping("/analyse")
    public String analyse(@RequestParam(value = "url") String url,
                          @RequestParam(value = "branch", defaultValue = "master") String branch) {

        RepoService repoService = new RepoService();
        try {
            repoService.downloadRepository(url, branch);
            return "success";
        } catch (GitAPIException e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
