package com.puresushi.cse364project;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableMongoRepositories(basePackages = {"com.puresushi.cse364project.data", "com.puresushi.cse364project.CSVImporter"})
@EnableScheduling
@SpringBootApplication()
public class RESTExerciseApplication {
    public static void main(String... args) {
        SpringApplication.run(RESTExerciseApplication.class, args);
    }
}
