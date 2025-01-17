package com.puresushi.cse364project.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Rating {
    @Transient
    public static final String SEQUENCE_NAME = "ratings_sequence";

    @Id
    private String id;
    @Indexed
    private int ratingId;
    private int userId;
    private int movieId;
    private int rating;
    private int timeStamp;

    public Rating(int ratingId, int userId, int movieId, int rating, int timeStamp) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.timeStamp = timeStamp;
    }
}
