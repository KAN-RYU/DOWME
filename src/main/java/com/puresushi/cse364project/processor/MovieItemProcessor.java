package com.puresushi.cse364project.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import com.puresushi.cse364project.domain.Movie;
import com.puresushi.cse364project.model.MovieDetail;


public class MovieItemProcessor implements ItemProcessor<MovieDetail, Movie> {
    private static final Logger log = LoggerFactory.getLogger(MovieItemProcessor.class);

    @Override
    public Movie process(MovieDetail item) throws Exception {

        log.info("processing movie data.....{}", item);

        Movie transformedMovie = new Movie();
        transformedMovie.setMovieId(item.getMovieId());
        transformedMovie.setGenre(item.getGenre());
        transformedMovie.setTitle(item.getTitle());
        return transformedMovie;
    }
    
}
