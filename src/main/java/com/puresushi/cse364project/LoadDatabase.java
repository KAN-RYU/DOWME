package com.puresushi.cse364project;

import com.puresushi.cse364project.CSVImporter.BusTimeCSVToMongo;
import com.puresushi.cse364project.CSVImporter.MealMenuCSVToMongo;
import com.puresushi.cse364project.CSVImporter.MovieCSVToMongo;
import com.puresushi.cse364project.CSVImporter.RatingsCSVToMongo;
import com.puresushi.cse364project.Utils.DatabaseSequence;
import com.puresushi.cse364project.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    private MongoOperations mongoOperations;

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
            mongoOperations.dropCollection("database_sequences");
            movieRepository.deleteAll();
            ratingRepository.deleteAll();
            log.info("Parsing Rating data start.");
            MovieCSVToMongo parser = new MovieCSVToMongo(movieRepository);
            parser.readMovieCSV();
            RatingsCSVToMongo ratingsCSVToMongo = new RatingsCSVToMongo(movieRepository, ratingRepository);
            ratingsCSVToMongo.readRatingCSV();

//            List<Rating> ratingList = ratingRepository.findByOrderByMovieIdAsc();
//            int movieIndex = 0;
//            Movie m = new Movie(0, "tmp", "tmp");
//            for (Rating rating : ratingList) {
//                if (rating.getMovieId() != movieIndex) {
//                    if (movieIndex != 0) {
//                        movieRepository.save(m);
//                    }
//                    do {
//                        movieIndex += 1;
//                        m = movieRepository.findByMovieId(movieIndex);
//                        if (movieIndex > 3952) break;
//                    } while (m == null);
//                    if (movieIndex > 3952) break;
//                }
//                m.setNumberRate(m.getNumberRate() + 1);
//                m.setTotalRating(m.getTotalRating() + rating.getRating());
//            }
//            mongoOperations.findAndModify(query(where("_id").is(Movie.SEQUENCE_NAME)), new Update().set("seq", 3952), options().returnNew(true).upsert(true), DatabaseSequence.class);
//            mongoOperations.findAndModify(query(where("_id").is(Rating.SEQUENCE_NAME)), new Update().set("seq", 1000209), options().returnNew(true).upsert(true), DatabaseSequence.class);
            log.info("Parsing Rating data done.");
        };
    }

    @Bean
    CommandLineRunner initBusTimeDB(BusTimeRepository busTimeRepository) {
        return args -> {
            log.info("Bus Timetable Initializing");
            busTimeRepository.deleteAll();
            log.info("Parsing Bus Timetable Start");
            BusTimeCSVToMongo parser = new BusTimeCSVToMongo(busTimeRepository);
            parser.readBusTimeCSV();

            log.info("Parsing Bus Timetable End");
        };
    }

    @Bean
    CommandLineRunner initMealMenuDB(MealMenuRepository mealMenuRepository) {
        return args -> {
            log.info(("Meal Menu initializing"));
            mealMenuRepository.deleteAll();
            log.info("Parsing Meal Menu Start");
            MealMenuCSVToMongo parser = new MealMenuCSVToMongo(mealMenuRepository);
            parser.readMealMenuCSV();

            log.info("Parsing Meal Menu End");
        };
    }
}
