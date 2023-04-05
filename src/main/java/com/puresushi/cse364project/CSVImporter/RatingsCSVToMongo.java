package com.puresushi.cse364project.CSVImporter;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import com.puresushi.cse364project.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RatingsCSVToMongo {

    private  final Logger log = LoggerFactory.getLogger(getClass());
    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;

    public RatingsCSVToMongo(MovieRepository movieRepository, RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
    }

    public void readRatingCSV() {
        try {
            CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream("/root/project/DOWME/data/ratings.csv")));
            List<String[]> ratings = csvReader.readAll();
            ArrayList<Rating> newRatings = new ArrayList<Rating>();
            int index = 0;
            int length = ratings.size();

            for (String[] ratingInfo : ratings) {
                if (index % 100000 == 0) log.info(index + " / " + length + " loading");
                if (ratingInfo != null) {
                    int userId = Integer.parseInt(ratingInfo[0]);
                    int movieId = Integer.parseInt(ratingInfo[1]);
                    int ratingInt = Integer.parseInt(ratingInfo[2]);
                    int timestamp = Integer.parseInt(ratingInfo[3]);
                    Rating rating = new Rating(index+1, userId, movieId, ratingInt, timestamp);
                    newRatings.add(rating);
//                    ratingRepository.save(rating);
//                    Movie m = movieRepository.findByMovieId(movieId);
//                    m.setNumberRate(m.getNumberRate() + 1);
//                    m.setTotalRating(m.getTotalRating() + ratingInt);
//                    movieRepository.save(m);
                }
                index += 1;
            }
            ratingRepository.insert(newRatings);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
