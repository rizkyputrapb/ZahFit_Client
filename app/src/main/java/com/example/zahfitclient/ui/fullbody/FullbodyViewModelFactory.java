package com.example.zahfitclient.ui.fullbody;

import com.example.zahfitclient.model.Plan;
import com.example.zahfitclient.ui.arm.ArmViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FullbodyViewModelFactory implements ViewModelProvider.Factory {
    private List<Plan> planList;

    public FullbodyViewModelFactory(List<Plan> planList) {
        this.planList = planList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FullbodyViewModel.class)) {
            return (T) new FullbodyViewModel(planList);
        }
        throw new IllegalArgumentException("Viewmodel Yang Diminta ArmViewModel");
    }
}
