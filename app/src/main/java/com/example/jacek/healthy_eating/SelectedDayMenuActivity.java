package com.example.jacek.healthy_eating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import Dao.DatabaseHelper;
import Dao.MealType;

public class SelectedDayMenuActivity extends AppCompatActivity {
    private TextView textViewSelectedDate;
    private ProgressBar progressBarProteins;
    private ProgressBar progressBarFats;
    private ProgressBar progressBarCarbohydrates;
    private ProgressBar progressBarCalories;
    private Spinner spinnerMealType;
    private RecyclerView recyclerViewMeals;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private Button buttonAddMealToMenu;

    private DatabaseHelper db;
    private DateHolder dateHolder;
    private int mealType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_day_menu);

        db = DatabaseHelper.getInstance(this);

        initializeComponents();
    }

    private void initializeComponents() {
        textViewSelectedDate = findViewById(R.id.textViewSelectedDate);
        recyclerViewMeals = findViewById(R.id.recyclerViewMeals);
        buttonAddMealToMenu = findViewById(R.id.buttonAddMealToMenu);

        final Bundle extras = getIntent().getExtras();
        String selectedDate = extras.getInt("Day") + "/" + extras.getInt("Month") + "/" + extras.getInt("Year");
        textViewSelectedDate.setText("Selected date: " + selectedDate);

        progressBarProteins = findViewById(R.id.progressBarProteins);
        progressBarFats = findViewById(R.id.progressBarFats);
        progressBarCarbohydrates = findViewById(R.id.progressBarCarbohydrates);
        progressBarCalories = findViewById(R.id.progressBarCalories);



        spinnerMealType = findViewById(R.id.spinnerMealType);
        final ArrayAdapter<MealType> spinnerAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, MealType.values());
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerMealType.setAdapter(spinnerAdapter);
        spinnerMealType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mealType = ((MealType)spinnerMealType.getSelectedItem()).ordinal();
                updateRecyclerView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mealType = 0;
            }
        });

        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerViewMeals.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new MealsByMealTypeAdapter(this,
                db.getMealDataByMealType(mealType, DateHolder.getDateInMilliseconds()));

        recyclerViewMeals.setAdapter(recyclerViewAdapter);


        buttonAddMealToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browseMeals = new Intent(getApplicationContext(), AddMealToMenuActivity.class);
                MealType mealType = (MealType)spinnerMealType.getSelectedItem();
                int mealTypeValue = mealType.ordinal();
                browseMeals.putExtra("MealType", mealTypeValue);
                browseMeals.putExtra("Day", extras.getInt("Day"));
                browseMeals.putExtra("Month", extras.getInt("Month"));
                browseMeals.putExtra("Year", extras.getInt("Year"));
                startActivity(browseMeals);
            }
        });
    }

    private void updateRecyclerView() {
        recyclerViewAdapter = new MealsByMealTypeAdapter(this,
                db.getMealDataByMealType(mealType, DateHolder.getDateInMilliseconds()));

        recyclerViewMeals.setAdapter(recyclerViewAdapter);

    }
}
