package com.puresushi.cse364project.data;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Movie {

    @Id
    private String id;
    private int movieId;
    private String title;
    private String genres;
    private Long numberRate;
    private Long totalRating;

    public Movie(int movieId, String title, String genres) {
        this.movieId = movieId;
        this.title = title;
        this.genres = genres;
    }

    public Long getAverageRating() {
        return totalRating / numberRate;
    }
}
