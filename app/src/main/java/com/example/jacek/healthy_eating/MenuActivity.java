package com.example.jacek.healthy_eating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Intent selectedDayActivity = new Intent(getApplicationContext(), SelectedDayMenuActivity.class);
                selectedDayActivity.putExtra("Day", dayOfMonth);
                selectedDayActivity.putExtra("Month", month);
                selectedDayActivity.putExtra("Year", year);
                startActivity(selectedDayActivity);
            }
        });
    }
}
