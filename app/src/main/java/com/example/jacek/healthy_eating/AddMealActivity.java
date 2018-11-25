package com.example.jacek.healthy_eating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class AddMealActivity extends AppCompatActivity {
    private TextView textViewName;
    private EditText editTextName;
    private TextView textViewCalories;
    private EditText editTextCalories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        textViewName = findViewById(R.id.textViewName);
        editTextName = findViewById(R.id.editTextName);
        textViewCalories = findViewById(R.id.textViewCalories);
        editTextCalories = findViewById(R.id.editTextCalories);
    }
}
