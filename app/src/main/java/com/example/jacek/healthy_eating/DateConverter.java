package com.example.jacek.healthy_eating;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateConverter {

    public static String getDateFromMilliseconds(long dateInMilliseconds) {
        String dateFormat = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateInMilliseconds);
        String date = simpleDateFormat.format(calendar.getTime());

        return date;
    }
}
