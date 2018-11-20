package com.example.jacek.healthy_eating;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewCalories;
        private TextView textViewFats;
        private TextView textViewProteins;
        private TextView textViewCarbohydrates;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewCalories = itemView.findViewById(R.id.textViewCalories);
            textViewFats = itemView.findViewById(R.id.textViewFats);
            textViewProteins = itemView.findViewById(R.id.textViewProteins);
            textViewCarbohydrates = itemView.findViewById(R.id.textViewCarbohydrates);
        }
    }

    private List<Meal> meals;

    public MealsAdapter(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public MealsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View mealView = inflater.inflate(R.layout.meal_data, parent, false);

        ViewHolder viewHolder = new ViewHolder(mealView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MealsAdapter.ViewHolder viewHolder, int position) {
        Meal meal = meals.get(position);

        TextView textViewName = viewHolder.textViewName;
        textViewName.setText(meal.getName());

        TextView textViewCalories = viewHolder.textViewCalories;
        textViewCalories.setText("Calories: " + meal.getCalories());

        TextView textViewFats = viewHolder.textViewFats;
        textViewFats.setText("Fats: " + meal.getFats());

        TextView textViewProteins = viewHolder.textViewProteins;
        textViewProteins.setText("Proteins: " + meal.getProteins());

        TextView textViewCarbohydrates = viewHolder.textViewCarbohydrates;
        textViewCarbohydrates.setText("Carbohydrates: " + meal.getCarbohydrates());


    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}
