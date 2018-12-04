package Dao;

public enum ActivityLevel {
    LAZY(1.1f, "No activity to 1 activity per week."),
    MODERATE(1.3f, "Activity 1 - 3 times per week."),
    ACTIVE(1.5f, "Activity 4 - 5 times per week."),
    VERY_ACTIVE(1.7, "Activity every day and hard labour work.");

    private double constant;
    private String string;
    ActivityLevel(double constant, String string)
    {
        this.constant = constant;
        this.string = string;
    }

    @Override
    public String toString()
    {
        return string;
    }
}
