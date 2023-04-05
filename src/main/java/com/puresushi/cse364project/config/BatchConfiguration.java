// Work in progress

package com.puresushi.cse364project.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.puresushi.cse364project.domain.Movie;
import com.puresushi.cse364project.domain.Rating;
import com.puresushi.cse364project.domain.User;
import com.puresushi.cse364project.listener.JobCompletionNotificationListener;
import com.puresushi.cse364project.model.MovieDetail;
import com.puresushi.cse364project.model.RatingDetail;
import com.puresushi.cse364project.model.UserDetail;
import com.puresushi.cse364project.processor.MovieItemProcessor;
import com.puresushi.cse364project.processor.RatingItemProcessor;
import com.puresushi.cse364project.processor.UserItemProcessor;


// Original code : https://www.tutorialsbuddy.com/spring-batch-with-mongodb-example


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Bean
    public FlatFileItemReader<MovieDetail> reader_m() {
        return new FlatFileItemReaderBuilder<MovieDetail>().name("movieItemReader")
                .resource(new ClassPathResource("movies.csv")).delimited()
                .names(new String[] {"movieId", "title", "genre"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MovieDetail>() {
                    {
                        setTargetType(MovieDetail.class);
                    }
                }).build();
    }
  
    @Bean
    public FlatFileItemReader<RatingDetail> reader_r() {
        return new FlatFileItemReaderBuilder<RatingDetail>().name("ratingItemReader")
                .resource(new ClassPathResource("ratings.csv")).delimited()
                .names(new String[] {"userId", "movieId", "rating", "timeStamp"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<RatingDetail>() {
                    {
                        setTargetType(RatingDetail.class);
                    }
                }).build();
    }
  
    @Bean
    public FlatFileItemReader<UserDetail> reader_u() {
        return new FlatFileItemReaderBuilder<UserDetail>().name("userItemReader")
                .resource(new ClassPathResource("users.csv")).delimited()
                .names(new String[] {"userId", "gender", "age", "occupation", "zipCode"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<UserDetail>() {
                    {
                        setTargetType(UserDetail.class);
                    }
                }).build();
    }

    @Bean
    public MongoItemWriter<Movie> writer(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Movie>().template(mongoTemplate).collection("movie")
                .build();
    }
  
    @Bean
    public MongoItemWriter<Rating> writer(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Rating>().template(mongoTemplate).collection("rating")
                .build();
    }
  
    @Bean
    public MongoItemWriter<User> writer(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<User>().template(mongoTemplate).collection("user")
                .build();
    }

    @Bean
    public MovieItemProcessor processor() {
        return new MovieItemProcessor();
    }
  
    @Bean
    public RatingItemProcessor processor() {
        return new RatingItemProcessor();
    }

    @Bean
    public UserItemProcessor processor() {
        return new UserItemProcessor();
    }


    @Bean
    public Step step1(FlatFileItemReader<UserDetail> itemReader, MongoItemWriter<User> itemWriter)
            throws Exception {

        return this.stepBuilderFactory.get("step1").<UserDetail, User>chunk(5).reader(itemReader)
                .processor(processor()).writer(itemWriter).build();
    }

    @Bean
    public Job updateUserJob(JobCompletionNotificationListener listener, Step step1)
            throws Exception {

        return this.jobBuilderFactory.get("updateUserJob").incrementer(new RunIdIncrementer())
                .listener(listener).start(step1).build();
    }

}
