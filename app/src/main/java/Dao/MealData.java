package Dao;

import java.time.Instant;
import java.util.Date;

public class MealData {
    public static final String TABLE_NAME = "MealsData";

    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_MEAL_ID = "MealId";
    public static final String COLUMN_AMOUNT = "Amount";
    public static final String COLUMN_MEAL_TYPE = "MealType";
    public static final String COLUMN_DATE = "Date";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_MEAL_ID + " INTEGER, "
                    + COLUMN_AMOUNT + " DECIMAL(18,2), "
                    + COLUMN_MEAL_TYPE + " INTEGER, "
                    + COLUMN_DATE + " INTEGER, "
                    + "FOREIGN KEY (" + COLUMN_MEAL_TYPE + ") REFERENCES " + Meal.TABLE_NAME + "(" + Meal.COLUMN_ID + ")"
                    + ")";

    private int id;
    private int mealId;
    //grams of food
    private double amount;
    private int mealType;
    private long date;

    public MealData() {}

    public MealData(int mealId, double amount, int mealType, int date) {
        this.mealId = mealId;
        this.amount = amount;
        this.mealType = mealType;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getMealType() {
        return mealType;
    }

    public void setMealType(int mealType) {
        this.mealType = mealType;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getDate() { return  date; }
}
