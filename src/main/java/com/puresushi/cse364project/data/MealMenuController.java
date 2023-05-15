package com.puresushi.cse364project.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MealMenuController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MealMenuRepository mealMenuRepository;

    public MealMenuController(MealMenuRepository mealMenuRepository) { this.mealMenuRepository = mealMenuRepository;}

    // Get meal with date
    @GetMapping("/meal/{date}")
    public List<MealMenu> getMealWithDate(@PathVariable int date) {
        return mealMenuRepository.findByDate(date);
    }

    // Get meal with date, time
    @GetMapping("/meal/{date}/{time}")
    public List<MealMenu> getMealWithDateAndTime(@PathVariable int date, @PathVariable String time) {
        return mealMenuRepository.findByDateAndTime(date, time);
    }

    // Get meal with date, time, restaurant
    @GetMapping("/meal/{date}/{time}/{restaurant}")
    public List<MealMenu> getMealWithDateAndTimeAndRestaurant(@PathVariable int date, @PathVariable String time, @PathVariable String restaurant) {
        return mealMenuRepository.findByDateAndTimeAndRestaurant(date, time, restaurant);
    }

    @GetMapping("/meal/search/{menu}")
    public List<MealMenu> searchMealWithMenu(@PathVariable String menu) {
        List<MealMenu> allMeal = mealMenuRepository.findAll();
        List<MealMenu> result = new ArrayList<>();
        for (MealMenu mealMenu: allMeal) {
            if (mealMenu.getMenu().toLowerCase().contains(menu.toLowerCase().replace('+', ' '))) {
                result.add(mealMenu);
            }
        }

        return result;
    }
}
