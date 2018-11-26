package com.example.jacek.healthy_eating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Dao.DatabaseHelper;
import Dao.Meal;

public class AddMealActivity extends AppCompatActivity {
    private TextView textViewName;
    private EditText editTextName;
    private TextView textViewCalories;
    private EditText editTextCalories;
    private TextView textViewProteins;
    private EditText editTextProteins;
    private TextView textViewFats;
    private EditText editTextFats;
    private TextView textViewCarbohydrates;
    private EditText editTextCarbohydrates;
    private Button buttonAddMeal;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        db = new DatabaseHelper(this);

        initializeComponents();

    }

    private void addMealToDb() {
        String name = editTextName.getText().toString();
        float calories = Float.parseFloat(editTextCalories.getText().toString());
        float proteins = Float.parseFloat(editTextProteins.getText().toString());
        float fats = Float.parseFloat(editTextFats.getText().toString());
        float carbohydrates = Float.parseFloat(editTextCarbohydrates.getText().toString());

        Meal meal = new Meal(name, calories, proteins, fats, carbohydrates);
        db.insertMeal(meal);
    }

    private void initializeComponents() {
        textViewName = findViewById(R.id.textViewName);
        editTextName = findViewById(R.id.editTextName);
        textViewCalories = findViewById(R.id.textViewCalories);
        editTextCalories = findViewById(R.id.editTextCalories);
        textViewProteins = findViewById(R.id.textViewProteins);
        editTextProteins = findViewById(R.id.editTextProteins);
        textViewFats = findViewById(R.id.textViewFats);
        editTextFats = findViewById(R.id.editTextFats);
        textViewCarbohydrates = findViewById(R.id.textViewCarbohydrates);
        editTextCarbohydrates = findViewById(R.id.editTextCarbohydrates);
        buttonAddMeal = findViewById(R.id.buttonAddMeal);
        buttonAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMealToDb();
            }
        });
    }
}
