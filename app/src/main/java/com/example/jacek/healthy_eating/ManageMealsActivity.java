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

import java.util.LinkedList;
import java.util.List;

import Dao.DatabaseHelper;
import Dao.Meal;

public class ManageMealsActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private static List<Meal> meals = new LinkedList<>();

    private RecyclerView recyclerViewMeals;
    private Button buttonAddMeal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_meals);

        db = new DatabaseHelper(this);
        meals.addAll(db.getAllMeals());

        recyclerViewMeals = findViewById(R.id.recyclerViewMeals);
        final MealsAdapter mealsAdapter = new MealsAdapter(meals);
        recyclerViewMeals.setAdapter(mealsAdapter);
        recyclerViewMeals.setLayoutManager(new LinearLayoutManager(this));

        buttonAddMeal = findViewById(R.id.buttonAddMeal);
        buttonAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMealActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
    }
}
