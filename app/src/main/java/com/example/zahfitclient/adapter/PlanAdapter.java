package com.example.zahfitclient.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.zahfitclient.databinding.ItemPlanBinding;
import com.example.zahfitclient.model.Plan;
import com.example.zahfitclient.ui.home.OnItemPlanListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanViewHolder> {
    private List<Plan> planList = new ArrayList<>();
    private OnItemPlanListener itemPlanListener;

    public PlanAdapter() {
    }

    public PlanAdapter(OnItemPlanListener itemPlanListener) {
        this.itemPlanListener = itemPlanListener;
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
        Log.i("DEBUG MENU", "onCreateViewHolder");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPlanBinding binding = ItemPlanBinding.inflate(layoutInflater, parent, false);
        return new PlanViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        Log.i("DEBUG MENU", "onBindViewHolder");
        Plan plan = planList.get(position);
        holder.bind(plan, itemPlanListener);
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

        public void bind(Plan plan, OnItemPlanListener itemPlanListener) {
            Glide.with(binding.getRoot().getContext()).load(plan.getUri()).into(binding.imageView3);
            binding.setPlan(plan);
            binding.setClicklistener(itemPlanListener);
            binding.executePendingBindings();
        }
    }
}
