package com.ucl.ADA.repository_downloader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
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
import java.util.*;
import java.util.stream.Stream;


/**
 * Helper class for downloading a Git repository and setting up a GitRepoInfo object.
 */

public class RepoDownloader {

    /**
     * Clone a GitHub repository and save it locally in the following hierarchy:
     * <user.dir>/temp/<owner_name>/<repo_name>/<timestamp>
     * @param url url of the repository
     * @param branch branch name
     * @return the absolute path to the downloaded repository
     * @throws GitAPIException if the cloning fails
     */
    public static String downloadRepository(String url, String branch) throws GitAPIException {
        if(branch.isBlank()) branch = "master";
        String rootDirPath = setupRootPath(url, branch);

        Git.cloneRepository()
                .setURI(url)
                .setDirectory(new File(rootDirPath))
                .setBranchesToClone(Collections.singletonList("refs/heads/" + branch))
                .setBranch("refs/heads/" + branch)
                .call();

        return rootDirPath;
    }

    /**
     * Utility method to get the paths to all source files in the downloaded repository.
     * Filtered in 3 ways:
     * - only include .java files
     * - only iterate files which are in a sub-tree of a "src" folder
     * - exclude files ending with "test.java", regardless of lower/uppercase
     * @param rootDirectory path to the root directory of the downloaded repository
     * @return a set of strings containing the paths to all filtered .java files in the repo.
     */
    public static Set<String> getSourceFilePaths(String rootDirectory) {

        File dir = new File(rootDirectory);
        List<String> sourceDirectories = getSourceDirectories(dir);

        int moduleNameIndex = rootDirectory.length();

        Set<String> sourceFiles = new HashSet<>();

        for (String srcDir : sourceDirectories) {

            try (Stream<Path> walk = Files.walk(Paths.get(srcDir))) {
                walk.map(Path::toString)
                        .filter(f -> f.endsWith(".java")
                                && !f.toLowerCase().contains("/test/")
                                && !f.toLowerCase().contains("\\test\\")
                                && !f.toLowerCase().contains("/resources/")
                                && !f.toLowerCase().contains("\\resources\\"))
                        .map(s -> s.substring(moduleNameIndex))
                        .forEach(sourceFiles::add);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sourceFiles;
    }

    /**
     * Get the time of the latest (youngest) commit on a given branch of a git repository
     *
     * @param url    url of the git repository
     * @param branch name of branch
     * @return the OffsetDateTime of the latest commit time
     */
    public static OffsetDateTime getLatestCommitTime(String url, String branch) {
        String[] gitData = parseGitUrl(url);
        String owner = gitData[3];
        String name = gitData[4];

        String requestURI = "https://api.github.com/repos/" + owner + "/" + name + "/branches/" + branch;
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

        if(response.statusCode() == 404) return null;

        JSONObject json = new JSONObject(response.body());

        String commitTimeString = json.getJSONObject("commit").getJSONObject("commit").getJSONObject("author").getString("date");
        DateTimeFormatter fIn = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        return OffsetDateTime.parse(commitTimeString, fIn);
    }

    private static String setupRootPath(String url, String branch) {

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String timeStamp = offsetDateTime.format(formatter);

        String[] data = parseGitUrl(url);
        String owner = data[3];
        String repoName = data[4];


        return System.getProperty("user.dir")+"/temp/"+owner+"/"+repoName+"/"+branch+"/"+timeStamp;
    }

    private static List<String> getSourceDirectories(File rootDirectory) {
        List<String> sourceDirectories = new ArrayList<>();

        Collection<File> files = FileUtils.listFilesAndDirs(rootDirectory,
                TrueFileFilter.TRUE,
                TrueFileFilter.TRUE);

        for (File file : files) {
            if(!file.isDirectory()) continue;
            if (file.getName().toLowerCase().equals("src"))
                sourceDirectories.add(file.getPath());
        }

        return sourceDirectories;
    }

    /**
     * Parse the git url to get the name and owner of the repository
     *
     * @param url url of the git repository
     * @return a String array containing the name of the owner at [3] index and the repo name at [4] index.
     */
    public static String[] parseGitUrl(String url) {
        String[] data = url.split("/|//");

        if (data[4].indexOf(".") > 0)
            data[4] = data[4].substring(0, data[4].indexOf("."));

        return data;
    }
}
