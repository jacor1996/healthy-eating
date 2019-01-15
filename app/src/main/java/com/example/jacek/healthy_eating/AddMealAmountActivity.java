package com.example.jacek.healthy_eating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

import Dao.DatabaseHelper;
import Dao.Meal;
import Dao.MealData;
import Dao.MealType;

public class AddMealAmountActivity extends AppCompatActivity {
    private EditText editTextAmount;
    private TextView textViewCalories;
    private Spinner spinnerMealType;
    private Button buttonConfirm;

    private DatabaseHelper db;
    private Meal meal;
    private CaloriesHelper caloriesHelper;
    private DateHolder dateHolder;

    private double amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_amount);

        db = DatabaseHelper.getInstance(this);
        caloriesHelper = new CaloriesHelper();
        dateHolder = DateHolder.getInstance();

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
                    amount = Double.parseDouble(s.toString());
                    double calories = CaloriesHelper.getCaloriesForMeal(meal, amount);
                    textViewCalories.setText(String.format("Calories [kcal]: %.2f", calories));
                } else {
                    textViewCalories.setText("Specify valid value!");
                }

            }
        });

        spinnerMealType = findViewById(R.id.spinnerMealType);

        ArrayAdapter<MealType> mealTypeArrayAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, MealType.values());

        mealTypeArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerMealType.setAdapter(mealTypeArrayAdapter);

        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MealData mealData = new MealData();
                mealData.setMealId(meal.getId());
                mealData.setAmount(amount);
                MealType mealType = (MealType) spinnerMealType.getSelectedItem();

                mealData.setMealType(mealType.ordinal());

                long milliseconds = DateHolder.getDateInMilliseconds();
                mealData.setDate(milliseconds);

                db.insertMealData(mealData);

                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
