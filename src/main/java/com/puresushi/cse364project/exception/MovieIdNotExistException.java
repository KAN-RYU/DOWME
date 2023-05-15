package com.puresushi.cse364project.exception;

public class MovieIdNotExistException extends RuntimeException{
    public MovieIdNotExistException(int movieId) {
        super("MovieId " + movieId + " does not exist.");
    }
}
