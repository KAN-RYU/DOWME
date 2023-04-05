package com.puresushi.cse364project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RESTExerciseApplication {
    public static void main(String... args) {
        SpringApplication.run(RESTExerciseApplication.class, args);
    }
}
