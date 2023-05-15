package com.puresushi.cse364project.exception;

public class MealMenuNotFoundException extends RuntimeException{
    public MealMenuNotFoundException(String mealMenuId) { super("That Meal menu ID is not existed: "+mealMenuId);}
}
