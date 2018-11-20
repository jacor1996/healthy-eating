package com.example.jacek.healthy_eating;

public class Meal {

    private int id;
    private String name;
    private double calories;
    private double fats;
    private double proteins;
    private double carbohydrates;

    public Meal(int id, String name, double calories, double fats, double proteins, double carbohydrates) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.fats = fats;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

}
