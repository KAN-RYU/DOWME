package com.puresushi.cse364project;

public class MovieIdNotExistException extends RuntimeException{
    public MovieIdNotExistException(int movieId) {
        super("MovieId " + movieId + " does not exist.");
    }
}
