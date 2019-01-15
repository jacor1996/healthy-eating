package com.example.jacek.healthy_eating;

import android.app.Activity;
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

    public static class MyViewHolder extends RecyclerView.ViewHolder {
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

    public MealsByMealTypeAdapter(Context context, List<MealData> myDataset) {
        mDataset = myDataset;
        this.context = context;
        db = DatabaseHelper.getInstance(context);
    }

    @Override
    public MealsByMealTypeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View mealView = inflater.inflate(R.layout.meal_by_meal_type, parent, false);

        MealsByMealTypeAdapter.MyViewHolder vh = new MealsByMealTypeAdapter.MyViewHolder(mealView);
        return vh;
    }

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
                ((Activity) context).setResult(Activity.RESULT_OK);
                notifyDataSetChanged();
            }
        });
    }

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
