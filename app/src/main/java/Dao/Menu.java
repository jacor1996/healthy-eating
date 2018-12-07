package Dao;

import java.util.Date;
import java.util.List;

public class Menu {
    private int id;
    private User user;
    private Date date;
    private List<MealData> meals;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<MealData> getMeals() {
        return meals;
    }

    public void setMeals(List<MealData> meals) {
        this.meals = meals;
    }

    class MealData {
        private Meal meal;
        //grams of food
        private double amount;

        public Meal getMeal() {
            return meal;
        }

        public void setMeal(Meal meal) {
            this.meal = meal;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }
}
