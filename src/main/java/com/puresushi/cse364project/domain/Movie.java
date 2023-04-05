package com.puresushi.cse364project.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movie")
public class Movie {
    @Id
    private Long movieId;
    private String title;
    private String genre;

    public Long getMovieId() {
        return this.movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}
