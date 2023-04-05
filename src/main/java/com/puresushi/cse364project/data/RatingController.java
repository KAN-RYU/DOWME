package com.puresushi.cse364project.data;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RatingController {
    private  final Logger log = LoggerFactory.getLogger(getClass());

    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;

    public RatingController(RatingRepository ratingRepository, MovieRepository movieRepository) {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/ratings/{rating}")
    public List<Movie> getRatedMovies(@PathVariable int rating) {
        List<Movie> movieList = movieRepository.findAll();
        ArrayList<Movie> resultList = new ArrayList<Movie>();

        for (Movie m : movieList) {
            if (m.getAverageRating() >= rating) {
                resultList.add(m);
            }
        }

        return resultList;
    }
}
