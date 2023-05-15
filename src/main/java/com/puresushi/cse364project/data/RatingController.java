package com.puresushi.cse364project.data;


import com.puresushi.cse364project.exception.MovieIdNotExistException;
import com.puresushi.cse364project.exception.RatingRangeExceedException;
import com.puresushi.cse364project.Utils.SequenceGeneratorService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public JSONArray getRatedMovies(@PathVariable int rating) {
        List<Movie> movieList = movieRepository.findAll();
        JSONArray resultList = new JSONArray();

        if (rating <= 0 || rating > 5) {
            throw new RatingRangeExceedException(rating);
        }

        for (Movie m : movieList) {
            if (m.getAverageRating() >= rating) {
                JSONObject t = new JSONObject();
                t.appendField("genres", m.getGenres());
                t.appendField("title", m.getTitle());
                resultList.add(t);
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
        else {
            throw new MovieIdNotExistException(newRating.getMovieId());
        }
        return ratingRepository.save(newRating);
    }

    @PutMapping("/ratings/update/{ratingId}")
    Rating updatRating(@RequestBody Rating newRating, @PathVariable int ratingId) {

        Rating rating = ratingRepository.findByRatingId(ratingId);
        if (rating == null) {
            newRating.setRatingId(sequenceGeneratorService.generateSequence(Rating.SEQUENCE_NAME));
            return ratingRepository.save(newRating);
        }

        Movie m = movieRepository.findByMovieId(rating.getMovieId());
        m.setTotalRating(m.getTotalRating() - rating.getRating());
        m.setNumberRate(m.getNumberRate() - 1);

        rating.setMovieId(newRating.getMovieId());
        rating.setRating(newRating.getRating());
        rating.setUserId(newRating.getUserId());

        Movie m2 = movieRepository.findByMovieId(rating.getMovieId());
        m2.setTotalRating(m2.getTotalRating() + rating.getRating());
        m2.setNumberRate(m2.getNumberRate() + 1);

        return ratingRepository.save(rating);

    }
}
