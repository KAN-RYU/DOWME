package com.puresushi.cse364project.CSVImporter;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.puresushi.cse364project.Utils.SequenceGeneratorService;
import com.puresushi.cse364project.data.Movie;
import com.puresushi.cse364project.data.MovieRepository;
import org.springframework.core.io.UrlResource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class MovieCSVToMongo {
    private final MovieRepository movieRepository;

    public MovieCSVToMongo(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void readMovieCSV() {

        String[] movieInfo;

        try {
            CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream("/root/project/DOWME/data/movies.csv")));

            do {
                movieInfo = csvReader.readNext();

                if (movieInfo != null) {
                    if (movieRepository.findByMovieId(Integer.parseInt(movieInfo[0])) != null) {
                        continue;
                    }
                    else {
                        Movie movie = new Movie(Integer.parseInt(movieInfo[0]), movieInfo[1], movieInfo[2]);
                        movieRepository.save(movie);
                    }
                }
            }while (movieInfo != null);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
