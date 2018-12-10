package com.example.jacek.healthy_eating;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import Dao.Meal;

public class AddMealsAdapter extends RecyclerView.Adapter<AddMealsAdapter.MyViewHolder> {
    private List<Meal> mDataset;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewMeal;
        public Button buttonAdd;
        public MyViewHolder(View itemView) {
            super(itemView);
            textViewMeal = itemView.findViewById(R.id.textViewMeal);
            buttonAdd = itemView.findViewById(R.id.buttonAdd);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AddMealsAdapter(Context context, List<Meal> myDataset) {
        mDataset = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AddMealsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View mealView = inflater.inflate(R.layout.meal_add_data, parent, false);
        // create a new view

        MyViewHolder vh = new MyViewHolder(mealView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Meal meal = mDataset.get(position);

        holder.textViewMeal.setText(meal.getName());

        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addMealAmount = new Intent(context, AddMealAmountActivity.class);
                addMealAmount.putExtra("MealId", meal.getId());
                context.startActivity(addMealAmount);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
