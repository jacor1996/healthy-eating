package com.example.jacek.healthy_eating;

import java.util.List;

import Dao.MealData;

public class MacroNutrientsHelper {
    private List<MealData> mealDataList;
    private double proteins;
    private double fats;
    private double carbohydrates;
    private double calories;

    public double getCalories() {
        return calories;
    }

    public double getProteins() {
        return proteins;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public double getFats() {
        return fats;
    }

    public MacroNutrientsHelper(List<MealData> mealDataList) {
        this.mealDataList = mealDataList;
        ComputeMacronutrients();
    }

    private void ComputeMacronutrients() {
        final int macroNutrientsPer100grams = 100;
        for (MealData mData :
                mealDataList) {
            proteins += mData.getMeal().getProteins()/ macroNutrientsPer100grams * mData.getAmount();

            fats += mData.getMeal().getFats()/ macroNutrientsPer100grams * mData.getAmount();

            carbohydrates += mData.getMeal().getCarbohydrates()/ macroNutrientsPer100grams * mData.getAmount();

            calories += mData.getMeal().getCalories() / macroNutrientsPer100grams * mData.getAmount();
        }
    }


}
