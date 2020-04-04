package com.ucl.ADA.repository_downloader;

/**
 * Custom exception - thrown if the supplied Git url and/or branch name are invalid.
 */
public class GitRepoInvalidException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */

    public GitRepoInvalidException(String message) {
        super(message);
    }
}
