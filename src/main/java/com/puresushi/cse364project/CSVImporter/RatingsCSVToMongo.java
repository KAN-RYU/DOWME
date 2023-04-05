package com.puresushi.cse364project.CSVImporter;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.puresushi.cse364project.data.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class RatingsCSVToMongo {

    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;

    public RatingsCSVToMongo(MovieRepository movieRepository, RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
    }

    public void readRatingCSV() {
        String[] ratingInfo;

        try {
            CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream("/root/project/DOWME/data/ratings.csv")));

            do {
                ratingInfo = csvReader.readNext();

                if (ratingInfo != null) {
                    int userId = Integer.parseInt(ratingInfo[0]);
                    int movieId = Integer.parseInt(ratingInfo[1]);
                    int ratingInt = Integer.parseInt(ratingInfo[2]);
                    int timestamp = Integer.parseInt(ratingInfo[3]);
                    Rating rating = new Rating(userId, movieId, ratingInt, timestamp);
                    ratingRepository.save(rating);
                    Movie m = movieRepository.findByMovieId(movieId);
                    m.setNumberRate(m.getNumberRate() + 1);
                    m.setTotalRating(m.getTotalRating() + ratingInt);
                    movieRepository.save(m);
                }
            }while (ratingInfo != null);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
