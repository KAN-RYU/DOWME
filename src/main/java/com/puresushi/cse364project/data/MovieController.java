package com.puresushi.cse364project.data;

import com.puresushi.cse364project.Utils.SequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private SequenceGeneratorService sequenceGeneratorService;
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/movies/{movieId}")
    public Movie getMovie(@PathVariable int movieId) {
        return movieRepository.findByMovieId(movieId);
    }

    // Add new item
    @PostMapping("/movies")
    public Movie newMovie(@RequestBody Movie newMovie) {
        newMovie.setMovieId(sequenceGeneratorService.generateSequence(Movie.SEQUENCE_NAME));
        return movieRepository.save(newMovie);
    }
}
