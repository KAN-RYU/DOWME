package com.puresushi.cse364project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RatingDomain {
    private @Id Long userId;
    private @Id Long movieId;
    private int rating;
    private Long timeStamp;

    RatingDomain() {
    }

    public RatingDomain(Long userId, Long movieId, int rating, Long timeStamp) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.timeStamp = timeStamp;
    }

    public Long getuserId() {
        return this.userId;
    }

    public void setuserId(Long userId) {
        this.userId = userId;
    }

    public Long getmovieId() {
        return this.movieId;
    }

    public void setmovieId(Long movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }    

    public Long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }    

  
}
