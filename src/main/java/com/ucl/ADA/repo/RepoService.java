package com.ucl.ADA.repo;

import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RepoService {
    protected Repo repository;

    @Autowired
    public RepoService() {
        repository = new Repo();
    }

    public void downloadRepository(Repo repo) throws GitAPIException {
        String branch = repo.getBranch();
        String url = repo.getUrl();

        if (branch.equals("")) {
            branch = "master";
        }

        setup(url, branch);

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String clone_path = System.getProperty("user.dir") + "/temp/"
                + repository.getOwner() + "/" + repository.getName() + "/" + branch + "/" + timeStamp;
        File file = new File(clone_path);

        Git git = Git.cloneRepository()
                .setURI( url )
                .setDirectory( file )
                .setCloneAllBranches( true )
                .call();

        if (!branch.equals("master")) {
            Ref ref = git.checkout().
                    setCreateBranch(true).
                    setName(branch).
                    setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK).
                    setStartPoint("origin/" + branch).
                    call();
        }
        git.close();
        saveRepository();
    }

    private void setup(String url, String branch) {
        String[] data = url.split("/|//");
        String owner = data[3];
        String name = data[4].substring(0, data[4].indexOf("."));
        repository.setUrl(url);
        repository.setName(name);
        repository.setOwner(owner);
        repository.setBranch(branch);
    }

    private void saveRepository() {
    }
}
