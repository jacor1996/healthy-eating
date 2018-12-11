package com.example.jacek.healthy_eating;

import java.util.Calendar;

public class DateHolder {
    private int day;
    private int month;
    private int year;
    private long date;

    private static DateHolder instance;

    public static DateHolder getInstance() {
        if (instance == null) {
            instance = new DateHolder();
        }

        return instance;
    }

    public static void setDate(int day, int month, int year) {
        instance.day = day;
        instance.month = month;
        instance.year = year;
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        setDateMilliseconds(c.getTimeInMillis());
    }

    public static void setDateMilliseconds(long date) {
        instance.date = date;
    }

    public static long getDateInMilliseconds() {
        return instance.date;
    }
}
