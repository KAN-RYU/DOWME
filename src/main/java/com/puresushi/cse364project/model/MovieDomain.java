package com.puresushi.cse364project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MovieDomain {
    private @Id Long movieId;
    private String title;
    private String genre;

    MovieDomain() {
    }

    public MovieDomain(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public Long getMovieId() {
        return this.movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
