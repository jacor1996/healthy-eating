package Dao;

public class Meal {
    public static final String TABLE_NAME = "Meals";

    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_CALORIES = "Calories";
    public static final String COLUMN_FATS = "Fats";
    public static final String COLUMN_PROTEINS = "Proteins";
    public static final String COLUMN_CARBOHYDRATES = "Carbohydrates";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_CALORIES + " DECIMAL(18,2),"
                + COLUMN_FATS + " DECIMAL(18,2),"
                + COLUMN_PROTEINS + " DECIMAL(18,2),"
                + COLUMN_CARBOHYDRATES + " DECIMAL(18,2) "
                + ")";

    public static final String INSERT_DEFAULT_DATA =
        "INSERT INTO " + TABLE_NAME +
                "(" + COLUMN_NAME + ", " + COLUMN_CALORIES + ", " + COLUMN_PROTEINS + ", " + COLUMN_FATS + ", " + COLUMN_CARBOHYDRATES + ")" +
                " VALUES ('Apple', 50, 2, 0, 14.2), " +
                "('Cottage cheese', 97, 15.5, 3.5, 2)";

    private int id;
    private String name;
    private double calories;
    private double fats;
    private double proteins;
    private double carbohydrates;

    public Meal() {};

    public Meal(String name, double calories, double proteins, double fats, double carbohydrates) {
        this.name = name;
        this.calories = calories;
        this.fats = fats;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
    }

    public Meal(int id, String name, double calories, double fats, double proteins, double carbohydrates) {
        this(name, calories, proteins, fats, carbohydrates);
        this.id = id;
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
