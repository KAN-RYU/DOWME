package com.puresushi.cse364project.CSVImporter;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.puresushi.cse364project.data.BusTime;
import com.puresushi.cse364project.data.BusTimeController;
import com.puresushi.cse364project.data.BusTimeRepository;
import com.puresushi.cse364project.data.Movie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class BusTimeCSVToMongo {
    private final BusTimeRepository busTimeRepository;

    public BusTimeCSVToMongo(BusTimeRepository busTimeRepository) {this.busTimeRepository = busTimeRepository;}

    public void readBusTimeCSV() {
        try {
            CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream("/root/project/DOWME/data/bustime.csv")));

            do {
                String[] busTimetable = csvReader.readNext();

                if (busTimetable != null) {
                    BusTime busTime = new BusTime(Integer.parseInt(busTimetable[0]), busTimetable[1]);
                    busTimeRepository.save(busTime);
                }
                else {
                    break;
                }
            }while (true);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
