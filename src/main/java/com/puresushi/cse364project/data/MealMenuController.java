package com.puresushi.cse364project.data;

import com.puresushi.cse364project.exception.MealMenuNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MealMenuController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MealMenuRepository mealMenuRepository;

    public MealMenuController(MealMenuRepository mealMenuRepository) { this.mealMenuRepository = mealMenuRepository;}

    // Get meal with date
    @GetMapping("/meal/{date}")
    public List<MealMenu> getMealWithDate(@PathVariable int date) {
        List<MealMenu> mealMenuList = mealMenuRepository.findByDate(date);
        if (mealMenuList.isEmpty()) throw new MealMenuNotFoundException(String.valueOf(date));
        return mealMenuList;
    }

    // Get meal with date, time
    @GetMapping("/meal/{date}/{time}")
    public List<MealMenu> getMealWithDateAndTime(@PathVariable int date, @PathVariable String time) {
        List<MealMenu> mealMenuList = mealMenuRepository.findByDateAndTime(date, time);
        if (mealMenuList.isEmpty()) throw new MealMenuNotFoundException(String.valueOf(date) + " " + time);
        return mealMenuList;
    }

    // Get meal with date, time, restaurant
    @GetMapping("/meal/{date}/{time}/{restaurant}")
    public List<MealMenu> getMealWithDateAndTimeAndRestaurant(@PathVariable int date, @PathVariable String time, @PathVariable String restaurant) {
        List<MealMenu> mealMenuList = mealMenuRepository.findByDateAndTimeAndRestaurant(date, time, restaurant);
        if (mealMenuList.isEmpty()) throw new MealMenuNotFoundException(String.valueOf(date) + " " + time + " " + restaurant);
        return mealMenuList;
    }

    @GetMapping("/meal/{date}/{time}/{restaurant}/{category}")
    public MealMenu getMealWithDateAndTimeAndRestaurantAndCategory(@PathVariable int date,
                                                                   @PathVariable String time,
                                                                   @PathVariable String restaurant,
                                                                   @PathVariable String category) {
        MealMenu m = mealMenuRepository.findByDateAndTimeAndRestaurantAndCategory(date, time, restaurant, category);
        if (m == null) throw new MealMenuNotFoundException(String.valueOf(date) + " " + time + " " + restaurant + " " + category);
        return m;
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

    // Add New meal menu and update meal menu
    @PutMapping("/meal")
    public MealMenu newMealMenu(@RequestBody MealMenu newMealMenu) {
        MealMenu m = mealMenuRepository.findByDateAndTimeAndRestaurantAndCategory(newMealMenu.getDate(),
                newMealMenu.getTime(),
                newMealMenu.getRestaurant(),
                newMealMenu.getCategory());
        if (m == null) {
            return mealMenuRepository.save(newMealMenu);
        }
        m.setMenu(newMealMenu.getMenu());
        m.setDate(newMealMenu.getDate());
        m.setTime(newMealMenu.getTime());
        m.setCategory(newMealMenu.getCategory());
        m.setRestaurant(newMealMenu.getRestaurant());
        return mealMenuRepository.save(m);
    }

    @PutMapping("/meal/{mealMenuId}")
    public MealMenu updateMealMenu(@RequestBody MealMenu newMealMenu, @PathVariable String mealMenuId) {
        MealMenu m = mealMenuRepository.findById(mealMenuId).orElseThrow(() -> new MealMenuNotFoundException(mealMenuId));
        m.setMenu(newMealMenu.getMenu());
        m.setDate(newMealMenu.getDate());
        m.setTime(newMealMenu.getTime());
        m.setCategory(newMealMenu.getCategory());
        m.setRestaurant(newMealMenu.getRestaurant());
        return mealMenuRepository.save(m);
    }
}
