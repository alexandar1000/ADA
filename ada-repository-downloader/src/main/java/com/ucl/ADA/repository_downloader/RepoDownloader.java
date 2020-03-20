package com.ucl.ADA.repository_downloader;

import org.json.JSONObject;

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
     * Utility method to list all source files (.java) in a given repository.
     *
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
     *
     * @param url url of the git repository
     * @return a String array containing the name of the owner at [3] index and the repo name at [4] index.
     */
    private static String[] parseGitUrl(String url) {
        String[] data = url.split("/|//");

        if (data[4].indexOf(".") > 0)
            data[4] = data[4].substring(0, data[4].indexOf("."));

        return data;
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

        JSONObject json = new JSONObject(response.body());

        String commitTimeString = json.getJSONObject("commit").getJSONObject("commit").getJSONObject("author").getString("date");
        DateTimeFormatter fIn = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        return OffsetDateTime.parse(commitTimeString, fIn);
    }
}
