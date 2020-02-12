package com.ucl.ADA.repository_downloader.helpers;

import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RepoDownloader {

    public static RepoDbPopulator downloadRepository(RepoDbPopulator repoInfoUI) throws GitAPIException {

        if (repoInfoUI.getBranch().equals("")) {
            repoInfoUI.setBranch("master");
        }

        RepoDbPopulator repo = setup(repoInfoUI);

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String clone_path = System.getProperty("user.dir") + "/temp/"
                + repo.getOwner() + "/" + repo.getName() + "/" + repo.getBranch() + "/" + timeStamp;

        repo.setDirectoryPath(clone_path);


        File file = new File(clone_path);

        Git git = Git.cloneRepository()
                .setURI( repo.getUrl() )
                .setDirectory( file )
                .setCloneAllBranches( true )
                .call();

        if (!repo.getBranch().equals("master")) {
            Ref ref = git.checkout().
                    setCreateBranch(true).
                    setName(repo.getBranch()).
                    setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK).
                    setStartPoint("origin/" + repo.getBranch()).
                    call();
        }

        List<String> fileNames = getSourceFileNames(clone_path);
        repo.setFileNames(fileNames);
        git.close();

        return repo;
    }

    private static RepoDbPopulator setup(RepoDbPopulator repo) {
        String[] data = repo.getUrl().split("/|//");
        String owner = data[3];
        String name;
        if (data[4].indexOf(".") > 0) {
            name = data[4].substring(0, data[4].indexOf("."));
        } else {
            name = data[4];
        }
        repo.setName(name);
        repo.setOwner(owner);
        return repo;
    }


    private static List<String> getSourceFileNames(String clone_path) {
        List<String> result = null;
        try (Stream<Path> walk = Files.walk(Paths.get(clone_path))) {

            result = walk.map(Path::toString)
                    .filter(f -> f.endsWith(".java")).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
