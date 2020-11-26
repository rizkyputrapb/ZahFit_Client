package com.example.zahfitclient.adapter;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zahfitclient.databinding.ItemPlanBinding;
import com.example.zahfitclient.model.Plan;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanViewHolder> {
    private List<Plan> planList = new ArrayList<>();

    public PlanAdapter() {
    }

    public PlanAdapter(List<Plan> planList) {
        this.planList = planList;
    }

    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPlanBinding binding = ItemPlanBinding.inflate(layoutInflater, parent, false);
        return new PlanViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        Plan plan = planList.get(position);
        holder.bind(plan);
    }

    @Override
    public int getItemCount() {
        if (planList != null) {
            return planList.size();
        } else {
            return 0;
        }
    }

    class PlanViewHolder extends RecyclerView.ViewHolder {
        private ItemPlanBinding binding;

        public PlanViewHolder(ItemPlanBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Plan plan) {
            Glide.with(binding.getRoot().getContext()).load(plan.getUri()).into(binding.imageView3);
            binding.setPlan(plan);
            binding.executePendingBindings();
        }
    }
}
