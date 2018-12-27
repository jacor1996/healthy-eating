package com.example.jacek.healthy_eating;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Dao.DatabaseHelper;
import Dao.Meal;
import Dao.MealData;

public class MealsByMealTypeAdapter extends RecyclerView.Adapter<MealsByMealTypeAdapter.MyViewHolder> {
    private List<MealData> mDataset;
    private Context context;
    private DatabaseHelper db;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewMeal;
        public TextView textViewAmount;
        public ImageButton buttonRemove;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewMeal = itemView.findViewById(R.id.textViewMeal);
            textViewAmount = itemView.findViewById(R.id.textViewAmount);
            buttonRemove = itemView.findViewById(R.id.buttonRemove);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MealsByMealTypeAdapter(Context context, List<MealData> myDataset) {
        mDataset = myDataset;
        this.context = context;
        db = DatabaseHelper.getInstance(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MealsByMealTypeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View mealView = inflater.inflate(R.layout.meal_by_meal_type, parent, false);
        // create a new view

        MealsByMealTypeAdapter.MyViewHolder vh = new MealsByMealTypeAdapter.MyViewHolder(mealView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MealsByMealTypeAdapter.MyViewHolder holder, final int position) {
        final MealData mealData = mDataset.get(position);
        Meal meal = db.getMeal(mealData.getMealId());
        mealData.setMeal(meal);

        holder.textViewMeal.setText(mealData.getMeal().getName());
        holder.textViewAmount.setText(String.valueOf(mealData.getAmount()));
        holder.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataset.remove(mealData);
                db.deleteMealData(mealData);
                notifyDataSetChanged();
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setMealsData(List<MealData> myData) {
        mDataset = myData;
        notifyDataSetChanged();
    }

    public int getMealDataId(int position) {
        MealData mealData = mDataset.get(position);
        return mealData.getId();
    }

}
