package com.example.jacek.healthy_eating;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import Dao.DatabaseHelper;
import Dao.Meal;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewCalories;
        private TextView textViewFats;
        private TextView textViewProteins;
        private TextView textViewCarbohydrates;

        private Button buttonRemoveMeal;
        private Button buttonEditMeal;

        private static final int ADD_MEAL_REQUEST = 1;

        public ViewHolder(final View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewCalories = itemView.findViewById(R.id.textViewCalories);
            textViewFats = itemView.findViewById(R.id.textViewFats);
            textViewProteins = itemView.findViewById(R.id.textViewProteins);
            textViewCarbohydrates = itemView.findViewById(R.id.textViewCarbohydrates);

            buttonEditMeal = itemView.findViewById(R.id.buttonEditMeal);
            buttonEditMeal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();

                     Intent editIntent = new Intent(itemView.getContext(), AddMealActivity.class);
                     editIntent.putExtra("Id", getMealId(position));

                    ((Activity) context).startActivityForResult(editIntent, ADD_MEAL_REQUEST);


                }
            });

            buttonRemoveMeal = itemView.findViewById(R.id.buttonRemoveMeal);
            buttonRemoveMeal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    deleteMeal(position);
                }
            });

        }
    }

    private List<Meal> meals;
    private DatabaseHelper db;
    public Context context;

    public MealsAdapter(DatabaseHelper db, List<Meal> meals, Context context) {

        this.meals = meals;
        this.db = db;
        this.context = context;
    }

    @Override
    public MealsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View mealView = inflater.inflate(R.layout.meal_data, parent, false);

        ViewHolder viewHolder = new ViewHolder(mealView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MealsAdapter.ViewHolder viewHolder, int position) {
        Meal meal = meals.get(position);

        TextView textViewName = viewHolder.textViewName;
        textViewName.setText(meal.getName());

        TextView textViewCalories = viewHolder.textViewCalories;
        textViewCalories.setText(String.format("Calories: %2.2f", meal.getCalories()));

        TextView textViewFats = viewHolder.textViewFats;
        textViewFats.setText(String.format("Fats: %2.2f", meal.getFats()));

        TextView textViewProteins = viewHolder.textViewProteins;
        textViewProteins.setText(String.format("Proteins: %2.2f", meal.getProteins()));

        TextView textViewCarbohydrates = viewHolder.textViewCarbohydrates;
        textViewCarbohydrates.setText(String.format("Carbohydrates: %2.2f", meal.getCarbohydrates()));

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void setMeals() {
        this.meals = DatabaseHelper.getInstance(context.getApplicationContext()).getAllMeals();
        notifyDataSetChanged();
    }

    public void deleteMeal(int position) {
        Meal mealToDelete = meals.get(position);

        db.deleteMeal(mealToDelete);
        meals.remove(mealToDelete);
        notifyDataSetChanged();
    }

    public int getMealId(int position) {
        Meal meal = meals.get(position);
        return meal.getId();
    }
}
