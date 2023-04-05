package com.puresushi.cse364project;

import com.puresushi.cse364project.data.Employee;
import com.puresushi.cse364project.data.EmployeeDB;
import com.puresushi.cse364project.data.Movie;
import com.puresushi.cse364project.data.MovieRepository;
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
    CommandLineRunner initMongoDB(MovieRepository movieRepository) {
        return args -> {
            log.info("" + movieRepository.save(new Movie(1, "toy", "action")));
        };
    }
}
