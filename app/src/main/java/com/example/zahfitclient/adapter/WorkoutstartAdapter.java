package com.example.zahfitclient.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zahfitclient.R;
import com.example.zahfitclient.databinding.ItemWorkoutBinding;
import com.example.zahfitclient.model.Workout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutstartAdapter extends RecyclerView.Adapter<WorkoutstartAdapter.WorkoutstartViewHolder>{
    private List<Workout> workoutList;
    private ArrayList<Integer> mDisabledRows;
    int selectedPosition=-1;


    public void disableRow(int index) {
        /**
         *clear array of disabled rows
         * - remove it if u wanna disable more than one row at once
         */
        mDisabledRows.clear();
        /** add disabled row to array */
        mDisabledRows.add(index);
        /** refresh view to show background changes - > call getView */
        notifyDataSetChanged();   // for my purpose
    }

    public List<Workout> getWorkoutList() {
        return workoutList;
    }

    public void setWorkoutList(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    public WorkoutstartAdapter() {
        mDisabledRows = new ArrayList<>();
    }

    @NonNull
    @Override
    public WorkoutstartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("DEBUG MENU", "onCreateViewHolder");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemWorkoutBinding binding = ItemWorkoutBinding.inflate(layoutInflater, parent, false);
        return new WorkoutstartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutstartViewHolder holder, int position) {
        Log.i("DEBUG MENU", "onBindViewHolder");
        Workout workout = workoutList.get(position);
        holder.bind(workout);
        if(selectedPosition==position)
            holder.itemView.setBackgroundResource(R.color.neon_yellow_variant);
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition=position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (workoutList != null) {
            return workoutList.size();
        } else {
            return 0;
        }
    }

    class WorkoutstartViewHolder extends RecyclerView.ViewHolder {
        private ItemWorkoutBinding binding;

        public WorkoutstartViewHolder(ItemWorkoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Workout workout) {
            binding.setWorkout(workout);
            binding.executePendingBindings();
        }
    }
}
