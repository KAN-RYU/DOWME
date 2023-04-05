package com.puresushi.cse364project.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    public List<Rating> findByOrderByMovieIdAsc();

    Rating findByRatingId(int ratingId);
}
