package com.example.jacek.healthy_eating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        db = DatabaseHelper.getInstance(getApplicationContext());
        initializeComponents();

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("Id", -1);
            if (id >= 0) {
                seedData(id);
            }
        }
    }

    private void addOrEditMeal() {
        String name = editTextName.getText().toString();
        float calories = Float.parseFloat(editTextCalories.getText().toString());
        float proteins = Float.parseFloat(editTextProteins.getText().toString());
        float fats = Float.parseFloat(editTextFats.getText().toString());
        float carbohydrates = Float.parseFloat(editTextCarbohydrates.getText().toString());

        Meal meal = new Meal(name, calories, proteins, fats, carbohydrates);

        if (id >= 0) {
            Meal mealToEdit = db.getMeal(id);
            if (mealToEdit != null) {
                mealToEdit.setName(meal.getName());
                mealToEdit.setCalories(meal.getCalories());
                mealToEdit.setProteins(meal.getProteins());
                mealToEdit.setFats(meal.getFats());
                mealToEdit.setCarbohydrates(meal.getCarbohydrates());

                db.updateMeal(mealToEdit);
            }
        }
        else {
            db.insertMeal(meal);
        }

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
                addOrEditMeal();
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void seedData(int id) {
        Meal meal = db.getMeal(id);
        if (meal != null) {
            editTextName.setText(meal.getName());
            editTextCalories.setText(String.valueOf(meal.getCalories()));
            editTextFats.setText(String.valueOf(meal.getFats()));
            editTextProteins.setText(String.valueOf(meal.getProteins()));
            editTextCarbohydrates.setText(String.valueOf(meal.getCarbohydrates()));
        }
    }

}
