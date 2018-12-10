package com.example.jacek.healthy_eating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import Dao.DatabaseHelper;

public class AddMealToMenuActivity extends AppCompatActivity {
    private RecyclerView recyclerViewMeals;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_to_menu);

        db = DatabaseHelper.getInstance(this);

        recyclerViewMeals = findViewById(R.id.recyclerViewMeals);
        recyclerViewMeals.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerViewMeals.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new AddMealsAdapter(this, db.getAllMeals());
        recyclerViewMeals.setAdapter(mAdapter);


    }
}
