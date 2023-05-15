package com.puresushi.cse364project.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealMenuRepository extends MongoRepository<MealMenu, String> {
    List<MealMenu> findByDate(int date);
    List<MealMenu> findByDateAndTime(int date, String time);
    List<MealMenu> findByDateAndTimeAndRestaurant(int date, String time, String restaurant);

}
