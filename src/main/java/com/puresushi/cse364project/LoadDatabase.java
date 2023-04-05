package com.puresushi.cse364project;

import com.puresushi.cse364project.CSVImporter.MovieCSVToMongo;
import com.puresushi.cse364project.CSVImporter.RatingsCSVToMongo;
import com.puresushi.cse364project.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
            log.info("Parsing Rating data stat.");
            MovieCSVToMongo parser = new MovieCSVToMongo(movieRepository);
            parser.readMovieCSV();
            RatingsCSVToMongo ratingsCSVToMongo = new RatingsCSVToMongo(movieRepository, ratingRepository);
            ratingsCSVToMongo.readRatingCSV();
            log.info("Parsing Rating data done.");
        };
    }
}
