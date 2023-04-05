package com.puresushi.cse364project.CSVImporter;

import com.puresushi.cse364project.data.Movie;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.MalformedURLException;

@EnableBatchProcessing
@Configuration
public class MovieCSVToMongo {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public Job readMovieCSVFile() {
        return new JobBuilder("readMovieCSVFile").incrementer(new RunIdIncrementer()).start(step1()).build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1").<Movie, Movie>chunk(10).reader(reader()).writer(writer()).build();
    }

    @Bean
    public FlatFileItemReader<Movie> reader() {
        FlatFileItemReader<Movie> reader = new FlatFileItemReader<>();
        try {
            reader.setResource(new UrlResource("file:~/DOWME/data/movies.csv"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        reader.setLineMapper(new DefaultLineMapper<Movie>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("id", "title", "genres");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Movie>() {{
                setTargetType(Movie.class);
            }});
        }});
        return reader;
    }

    @Bean
    public MongoItemWriter<Movie> writer() {
        MongoItemWriter<Movie> writer = new MongoItemWriter<Movie>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("movie");
        return writer;
    }

}
