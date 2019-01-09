package com.example.jacek.healthy_eating;

import java.util.List;

import Dao.DatabaseHelper;
import Dao.MealData;
import Dao.User;

public class MacroNutrientsHelper {
    private List<MealData> mealDataList;
    private double proteins;
    private double fats;
    private double carbohydrates;
    private double calories;

    private User user;
    private double proteinsLimit;
    private double fatsLimit;
    private double carbohydratesLimit;
    private double caloriesLimit;

    private final double fatsPerGram = 9;
    private final double proteinsPerGram = 4.5;
    private final double carbsPerGram = 4.5;

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

    public double getCaloriesLimit() {
        return caloriesLimit;
    }

    public double getCarbohydratesLimit() {
        return carbohydratesLimit;
    }

    public double getFatsLimit() {
        return fatsLimit;
    }

    public double getProteinsLimit() {
        return proteinsLimit;
    }

    public int getCurrentCaloriesPercentage()
    {
        return (int) (calories / caloriesLimit * 100);
    }

    public int getCurrentFatsPercentage()
    {
        return (int) (fats / fatsLimit * 100);
    }

    public int getCurrentProteinsPercentage()
    {
        return (int) (proteins / proteinsLimit * 100);
    }

    public int getCurrentCarbohydratesPercentage()
    {
        return (int) (carbohydrates / carbohydratesLimit * 100);
    }

    public MacroNutrientsHelper(List<MealData> mealDataList, User user) {
        this.mealDataList = mealDataList;
        this.user = user;
        computeMacronutrients();
        computeMacronutrientsLimits();
    }

    private void computeMacronutrients() {
        final int macroNutrientsPer100grams = 100;
        for (MealData mData :
                mealDataList) {
            proteins += mData.getMeal().getProteins() / macroNutrientsPer100grams * mData.getAmount();

            fats += mData.getMeal().getFats() / macroNutrientsPer100grams * mData.getAmount();

            carbohydrates += mData.getMeal().getCarbohydrates() / macroNutrientsPer100grams * mData.getAmount();

            calories += mData.getMeal().getCalories() / macroNutrientsPer100grams * mData.getAmount();
        }
    }

    private void computeMacronutrientsLimits() {
        double proteinsPart = 0.2;
        double carbohydratesPart = 0.5;
        double fatsPart = 0.3;

        caloriesLimit = computeUserBMR() * user.getActivity();
        proteinsLimit = caloriesLimit * proteinsPart / proteinsPerGram;
        fatsLimit = caloriesLimit * fatsPart / fatsPerGram;
        carbohydratesLimit = caloriesLimit * carbohydratesPart / carbsPerGram;
    }

    public double computeUserBMR() {
        return 66.5 + (13.75 * user.getWeight()) + (5 * user.getHeight()) - (6.75 * user.getAge());
    }


}
