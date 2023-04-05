package com.puresushi.cse364project;

import com.puresushi.cse364project.CSVImporter.MovieCSVToMongo;
import com.puresushi.cse364project.CSVImporter.RatingsCSVToMongo;
import com.puresushi.cse364project.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeDB db) {
        return args -> {
            log.info("" + db.save(new Employee("Kwon", "Student") ));
            log.info("" + db.save(new Employee("Jun", "Student") ));
        };
    }

    @Bean
    CommandLineRunner initMongoDB(MovieRepository movieRepository, RatingRepository ratingRepository) {
        return args -> {
            log.info("Database Initializing");
            movieRepository.deleteAll();
            ratingRepository.deleteAll();
            log.info("Parsing Rating data start.");
            MovieCSVToMongo parser = new MovieCSVToMongo(movieRepository);
            parser.readMovieCSV();
            RatingsCSVToMongo ratingsCSVToMongo = new RatingsCSVToMongo(movieRepository, ratingRepository);
            ratingsCSVToMongo.readRatingCSV();

            List<Rating> ratingList = ratingRepository.findByOrderByMovieIdAsc();
            int movieIndex = 0;
            Movie m = new Movie(0, "tmp", "tmp");
            for (Rating rating : ratingList) {
                if (rating.getMovieId() != movieIndex) {
                    if (movieIndex != 0) {
                        movieRepository.save(m);
                    }
                    movieIndex += 1;
                    log.info(movieIndex + " loading");
                    m = movieRepository.findByMovieId(movieIndex);
                }
                m.setNumberRate(m.getNumberRate() + 1);
                m.setTotalRating(m.getTotalRating() + rating.getRating());
            }
            log.info("Parsing Rating data done.");
        };
    }
}
