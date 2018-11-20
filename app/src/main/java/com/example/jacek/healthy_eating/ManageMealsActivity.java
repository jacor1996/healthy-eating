package com.example.jacek.healthy_eating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

public class ManageMealsActivity extends AppCompatActivity {
    private static List<Meal> meals = initializeList();

    private RecyclerView recyclerViewMeals;

    private static List<Meal> initializeList() {
         List<Meal> meals = new LinkedList<Meal>();
         meals.add(new Meal(1, "Woda", 0,0,0,0));

         return  meals;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_meals);

        recyclerViewMeals = findViewById(R.id.recyclerViewMeals);


        Intent intent = getIntent();
    }
}
