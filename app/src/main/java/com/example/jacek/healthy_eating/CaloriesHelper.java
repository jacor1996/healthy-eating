package com.example.jacek.healthy_eating;

import Dao.Meal;

public class CaloriesHelper {
    public static double getCaloriesForMeal(Meal meal, double amount) {
        return meal.getCalories() / 100 * amount;
    }

}
