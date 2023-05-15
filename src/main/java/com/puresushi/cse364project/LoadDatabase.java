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
            log.info(System.getProperty("user.dir"));
        };
    }

//    @Bean
//    CommandLineRunner initMongoDB(MovieRepository movieRepository, RatingRepository ratingRepository) {
//        return args -> {
//            log.info("Database Initializing");
//            mongoOperations.dropCollection("database_sequences");
//            movieRepository.deleteAll();
//            ratingRepository.deleteAll();
//            log.info("Parsing Rating data start.");
//            MovieCSVToMongo parser = new MovieCSVToMongo(movieRepository);
//            parser.readMovieCSV();
//            RatingsCSVToMongo ratingsCSVToMongo = new RatingsCSVToMongo(movieRepository, ratingRepository);
//            ratingsCSVToMongo.readRatingCSV();
//
//            log.info("Parsing Rating data done.");
//        };
//    }

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

    @Bean
    CommandLineRunner initAttendanceUserDB(AttendanceUserRepository attendanceUserRepository) {
        return args -> {
            attendanceUserRepository.deleteAll();
        };
    }
}
