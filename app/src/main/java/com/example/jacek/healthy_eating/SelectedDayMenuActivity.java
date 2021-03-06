package com.example.jacek.healthy_eating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
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

import java.util.LinkedList;
import java.util.List;

import Dao.DatabaseHelper;
import Dao.MealData;
import Dao.MealType;

public class SelectedDayMenuActivity extends AppCompatActivity {
    private TextView textViewSelectedDate;
    private TextView textViewCalories, textViewFats, textViewProteins, getTextViewCarbohydrates;
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
    private MacroNutrientsHelper macroNutrientsHelper;
    private List<MealData> mealDataList;

    private final static int ADD_MEAL_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_day_menu);

        db = DatabaseHelper.getInstance(this);
        mealDataList = new LinkedList<>();
        macroNutrientsHelper = new MacroNutrientsHelper(db.getMealDataByDate(DateHolder.getDateInMilliseconds()), db.getUser(1));

        initializeComponents();
        setProgressBars();
        setTextViews();
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

        textViewCalories = findViewById(R.id.textViewCalories);
        textViewFats = findViewById(R.id.textViewFats);
        textViewProteins = findViewById(R.id.textViewProteins);
        getTextViewCarbohydrates = findViewById(R.id.textViewCarbohydrates);

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

                updateMacroNutrientsHelper();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mealType = 0;
            }
        });

        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerViewMeals.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewMeals.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewAdapter = new MealsByMealTypeAdapter(this, mealDataList);
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
                startActivityForResult(browseMeals, ADD_MEAL_REQUEST);
            }
        });
    }

    private void updateRecyclerView() {
        mealDataList.clear();
        mealDataList.addAll(db.getMealDataByMealType(mealType, DateHolder.getDateInMilliseconds()));
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void setProgressBars() {
        progressBarFats.setProgress(macroNutrientsHelper.getCurrentFatsPercentage());
        progressBarProteins.setProgress(macroNutrientsHelper.getCurrentProteinsPercentage());
        progressBarCarbohydrates.setProgress(macroNutrientsHelper.getCurrentCarbohydratesPercentage());
        progressBarCalories.setProgress(macroNutrientsHelper.getCurrentCaloriesPercentage());
    }

    private void setTextViews() {
        String calories = String.format("Calories: %.2f / %.2f", macroNutrientsHelper.getCalories(), macroNutrientsHelper.getCaloriesLimit());
        String proteins = String.format("Proteins: %.2f / %.2f", macroNutrientsHelper.getProteins(), macroNutrientsHelper.getProteinsLimit());
        String fats = String.format("Fats: %.2f / %.2f", macroNutrientsHelper.getFats(), macroNutrientsHelper.getFatsLimit());
        String carbohydrates = String.format("Carbs: %.2f / %.2f", macroNutrientsHelper.getCarbohydrates(), macroNutrientsHelper.getCarbohydratesLimit());

        textViewCalories.setText(calories);
        textViewProteins.setText(proteins);
        textViewFats.setText(fats);
        getTextViewCarbohydrates.setText(carbohydrates);
    }

    private void updateMacroNutrientsHelper() {
        macroNutrientsHelper.updateMealDataList(db.getMealDataByDate(DateHolder.getDateInMilliseconds()));
        setTextViews();
        setProgressBars();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_MEAL_REQUEST) {
            if (resultCode == RESULT_OK) {
                updateRecyclerView();
                updateMacroNutrientsHelper();
            }
        }
    }
}
