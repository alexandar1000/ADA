package com.ucl.ADA.repository_analyser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/analyser")
public class RepositoryAnalyserController {

    @Autowired
    RepositoryAnalyserServices repositoryAnalyserServices;

    @GetMapping(produces = {"application/json"})
    @ResponseBody
    public String analyseRepository() throws FileNotFoundException {

        String parsedRepository = repositoryAnalyserServices.parseRepository();


        return "It works! :)";
    }
}


