package com.ucl.ADA.repository_downloader;

import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Helper class for downloading a Git repository and setting up a GitRepoInfo object.
 */

public class RepoDownloader {

    /**
     * Download a Git repository given a URL and a branch name and initialize a GitRepoInfo object.
     * @see GitRepoInfo
     * @param url Git url of the repository
     * @param branch Branch name
     * @return Initialized GitRepoInfo object
     * @throws GitAPIException if download fails
     */
    public static GitRepoInfo downloadRepository(String url, String branch) throws GitAPIException {

        if (branch.equals("")) {
            branch = "master";
        }

        GitRepoInfo repo = setup(url, branch);

        File repoDir = new File(repo.getDirectoryPath());

        Git git = Git.cloneRepository()
                .setURI( repo.getUrl() )
                .setDirectory( repoDir )
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

        List<String> fileNames = getSourceFileNames(repo.getDirectoryPath());
        repo.setFileNames(fileNames);
        git.close();

        return repo;
    }

    /**
     * Parse the url string to get the owner and name of the Git repository and construct a GitRepoInfo object.
     * @param url of the Git repository
     * @param branch name
     * @return initialized GitRepoInfo object
     */
    private static GitRepoInfo setup(String url, String branch) {

        GitRepoInfo gitRepoInfo = new GitRepoInfo();
        gitRepoInfo.setUrl(url);

        String[] data = parseGitUrl(url);
        String owner = data[3];
        String name = data[4];

        gitRepoInfo.setRepository(name);
        gitRepoInfo.setOwner(owner);
        gitRepoInfo.setBranch(branch);

        OffsetDateTime offsetDateTime = OffsetDateTime.now();

        gitRepoInfo.setTimestamp(offsetDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

        String timeStamp = offsetDateTime.format(formatter);

        String clone_path = System.getProperty("user.dir") + "/temp/"
                + owner + "/" + name + "/" + branch + "/" + timeStamp;

        gitRepoInfo.setDirectoryPath(clone_path);

        return gitRepoInfo;
    }

    /**
     * Utility method to list all source files (.java) in a given repository.
     * @param directoryPath path to the source directory
     * @return a list containing all file names ending with .java
     */
    private static List<String> getSourceFileNames(String directoryPath) {
        List<String> result = null;
        try (Stream<Path> walk = Files.walk(Paths.get(directoryPath))) {

            result = walk.map(Path::toString)
                    .filter(f -> f.endsWith(".java")).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Parse the git url to get the name and owner of the repository
     * @param url url of the git repository
     * @return a String array containing the name of the owner at [3] index and the repo name at [4] index.
     */
    private static String[] parseGitUrl(String url){
        String[] data = url.split("/|//");

        if (data[4].indexOf(".") > 0)
            data[4] = data[4].substring(0, data[4].indexOf("."));

        return data;
    }

    /**
     * Get the time of the latest (youngest) commit on a given branch of a git repository
     * @param url url of the git repository
     * @param branch name of branch
     * @return the OffsetDateTime of the latest commit time
     */
    public static OffsetDateTime getLatestCommitTime(String url, String branch){
        String[] gitData = parseGitUrl(url);
        String owner = gitData[3];
        String name = gitData[4];

        String requestURI = "https://api.github.com/repos/"+owner+"/"+name+"/branches/"+branch;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestURI))
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return null;
        }

        JSONObject json = new JSONObject(response.body());

        String commitTimeString = json.getJSONObject("commit").getJSONObject("commit").getJSONObject("author").getString("date");
        DateTimeFormatter fIn = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        return OffsetDateTime.parse(commitTimeString, fIn);
    }
}
