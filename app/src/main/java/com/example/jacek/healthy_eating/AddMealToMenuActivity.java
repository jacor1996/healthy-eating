package com.example.jacek.healthy_eating;

import android.content.Intent;
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

    private static final int ADD_MEAL_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_to_menu);

        db = DatabaseHelper.getInstance(this);

        recyclerViewMeals = findViewById(R.id.recyclerViewMeals);
        recyclerViewMeals.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerViewMeals.setLayoutManager(mLayoutManager);

        mAdapter = new AddMealsAdapter(this, db.getAllMeals());
        recyclerViewMeals.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_MEAL_REQUEST) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK);
                finish();
            }
        }
    }
}
