package com.example.jacek.healthy_eating;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import Dao.DatabaseHelper;
import Dao.Meal;

public class ManageMealsActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private static List<Meal> meals = new LinkedList<>();
    private MealsAdapter mealsAdapter;

    private RecyclerView recyclerViewMeals;
    private Button buttonAddMeal;

    private static final int ADD_MEAL_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_meals);

        db = DatabaseHelper.getInstance(getApplicationContext());
        meals = db.getAllMeals();

        recyclerViewMeals = findViewById(R.id.recyclerViewMeals);
        mealsAdapter = new MealsAdapter(db, meals);
        recyclerViewMeals.setAdapter(mealsAdapter);
        recyclerViewMeals.setLayoutManager(new LinearLayoutManager(this));

        buttonAddMeal = findViewById(R.id.buttonAddMeal);
        buttonAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMealActivity.class);
                startActivityForResult(intent, ADD_MEAL_REQUEST);
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int x = extras.getInt("MealType");

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_MEAL_REQUEST) {
            if (resultCode == RESULT_OK) {
                mealsAdapter.setMeals(db.getAllMeals());
            }
        }
    }
}
