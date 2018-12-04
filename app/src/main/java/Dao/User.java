package Dao;

public class User {
    public static final String TABLE_NAME = "Users";

    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_AGE = "Age";
    public static final String COLUMN_HEIGHT = "Height";
    public static final String COLUMN_WEIGHT = "Weight";
    public static final String COLUMN_ACTIVITY = "Activity";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_AGE + " INTEGER,"
                    + COLUMN_HEIGHT + " DECIMAL(18,2),"
                    + COLUMN_WEIGHT + " DECIMAL(18,2),"
                    + COLUMN_ACTIVITY + " DECIMAL(18,2) "
                    + ")";

    public static final String INSERT_DEFAULT_DATA =
            "INSERT INTO " + TABLE_NAME +
                    "(" + COLUMN_AGE + ", " + COLUMN_HEIGHT + ", " + COLUMN_WEIGHT + ", " + COLUMN_ACTIVITY + ")" +
                    " VALUES (20, 1.85, 82, 1.3)";

    private int id;
    private int age;
    private double height;
    private double weight;
    private double activity;

    public User() {}

    public User(int age, double height, double weight, double activity) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.activity = activity;
    }

    public User(int id, int age, double height, double weight, double activity) {
        this(age, height, weight, activity);
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setActivity(double activity) {
        this.activity = activity;
    }

    public double getActivity() {
        return activity;
    }

}
