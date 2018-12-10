package com.example.jacek.healthy_eating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import Dao.DatabaseHelper;
import Dao.Meal;

public class AddMealAmountActivity extends AppCompatActivity {
    private EditText editTextAmount;
    private TextView textViewCalories;

    private DatabaseHelper db;
    private Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_amount);

        db = DatabaseHelper.getInstance(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            meal = db.getMeal(extras.getInt("MealId"));
        }

        textViewCalories = findViewById(R.id.textViewCalories);

        editTextAmount = findViewById(R.id.editTextAmount);
        editTextAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0) {
                    double amount = Double.parseDouble(s.toString());
                    double calories = meal.getCalories()/100 * amount;
                    textViewCalories.setText("Calories [kcal]: " + String.valueOf(calories));
                }

            }
        });

    }
}
