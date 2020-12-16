package com.example.zahfitclient.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zahfitclient.databinding.ItemHistoryBinding;
import com.example.zahfitclient.databinding.ItemWorkoutBinding;
import com.example.zahfitclient.model.PlanHistory;
import com.example.zahfitclient.model.Workout;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<PlanHistory> historyList;

    public HistoryAdapter() {
    }

    public HistoryAdapter(List<PlanHistory> historyList) {
        this.historyList = historyList;
    }

    public List<PlanHistory> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<PlanHistory> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("DEBUG MENU", "onCreateViewHolder");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemHistoryBinding binding = ItemHistoryBinding.inflate(layoutInflater, parent, false);
        return new HistoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Log.i("DEBUG MENU", "onBindViewHolder");
        PlanHistory history = historyList.get(position);
        holder.bind(history);
    }

    @Override
    public int getItemCount() {
        if (historyList != null) {
            return historyList.size();
        } else {
            return 0;
        }
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        ItemHistoryBinding binding;

        public HistoryViewHolder(ItemHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(PlanHistory history) {
            binding.setHistory(history);
            binding.executePendingBindings();
        }
    }
}
