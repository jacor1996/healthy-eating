package com.example.jacek.healthy_eating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;

import Dao.DatabaseHelper;
import Dao.Meal;

public class MainActivity extends AppCompatActivity {
    private Button buttonManageMeals;
    private Button buttonManageMenus;
    private Button buttonEditUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonManageMeals = findViewById(R.id.buttonManageMeals);
        buttonManageMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManageMealsActivity.class);
                startActivity(intent);
            }
        });

        buttonManageMenus = findViewById(R.id.buttonManageMenus);
        buttonManageMenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });

        buttonEditUser = findViewById(R.id.buttonEditUser);
        buttonEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserData.class);
                startActivity(intent);
            }
        });

    }
}
