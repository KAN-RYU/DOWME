package com.puresushi.cse364project.CSVImporter;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.puresushi.cse364project.data.MealMenu;
import com.puresushi.cse364project.data.MealMenuRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MealMenuCSVToMongo {
    private final MealMenuRepository mealMenuRepository;

    public MealMenuCSVToMongo(MealMenuRepository mealMenuRepository) {this.mealMenuRepository = mealMenuRepository;}

    public void readMealMenuCSV() {
        try {
            CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream("/root/project/DOWME/data/menu.csv")));

            do {
                String[] mealMenuTable = csvReader.readNext();

                if (mealMenuTable != null) {
                    MealMenu mealMenu = new MealMenu(Integer.parseInt(mealMenuTable[0]), mealMenuTable[1], mealMenuTable[2], mealMenuTable[3], mealMenuTable[4]);
                    mealMenuRepository.save(mealMenu);
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
