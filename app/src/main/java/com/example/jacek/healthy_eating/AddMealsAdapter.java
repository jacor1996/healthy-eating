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

import java.util.List;

import Dao.Meal;

public class AddMealsAdapter extends RecyclerView.Adapter<AddMealsAdapter.MyViewHolder> {
    private List<Meal> mDataset;
    private Context context;
    private static final int ADD_MEAL_REQUEST = 2;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewMeal;
        public ImageButton buttonAdd;
        public Spinner spinnerMealType;
        public MyViewHolder(View itemView) {
            super(itemView);
            textViewMeal = itemView.findViewById(R.id.textViewMeal);
            buttonAdd = itemView.findViewById(R.id.buttonAdd);
        }
    }

    public AddMealsAdapter(Context context, List<Meal> myDataset) {
        mDataset = myDataset;
        this.context = context;
    }

    @Override
    public AddMealsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View mealView = inflater.inflate(R.layout.meal_add_data, parent, false);

        MyViewHolder vh = new MyViewHolder(mealView);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Meal meal = mDataset.get(position);

        holder.textViewMeal.setText(meal.getName());

        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addMealAmount = new Intent(context, AddMealAmountActivity.class);
                addMealAmount.putExtra("MealId", meal.getId());
                ((Activity)context).startActivityForResult(addMealAmount, ADD_MEAL_REQUEST);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
