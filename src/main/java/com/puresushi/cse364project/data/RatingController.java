package com.puresushi.cse364project.data;


import com.puresushi.cse364project.Utils.SequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RatingController {
    private  final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

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

    @PostMapping("/ratings")
    public Rating newRating(@RequestBody Rating newRating) {
        newRating.setRatingId(sequenceGeneratorService.generateSequence(Rating.SEQUENCE_NAME));
        Movie m = movieRepository.findByMovieId(newRating.getMovieId());
        if (m != null) {
            m.setNumberRate(m.getNumberRate() + 1);
            m.setTotalRating(m.getTotalRating() + newRating.getRating());
            movieRepository.save(m);
        }
        return ratingRepository.save(newRating);
    }

    @PutMapping("/ratings/update/{ratingId}")
    Rating updatRating(@RequestBody Rating newRating, @PathVariable int ratingId) {

        Rating m = ratingRepository.findByRatingId(ratingId);
        if (m == null) {
            newRating.setRatingId(sequenceGeneratorService.generateSequence(Rating.SEQUENCE_NAME));
            return ratingRepository.save(newRating);
        }
        m.setMovieId(newRating.getMovieId());
        m.setRating(newRating.getRating());
        m.setUserId(newRating.getUserId());
        return ratingRepository.save(m);

    }
}
