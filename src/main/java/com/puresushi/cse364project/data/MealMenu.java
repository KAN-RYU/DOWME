package com.puresushi.cse364project.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class MealMenu {

    @Id
    private Long id;
    private int date;
    private String time;
    private String category;
    private String restaurant;
    private String menu;

    public MealMenu(int date, String time, String category, String restaurant, String menu) {
        this.date = date;
        this.time = time;
        this.category = category;
        this.restaurant = restaurant;
        this.menu = menu;
    }
}
