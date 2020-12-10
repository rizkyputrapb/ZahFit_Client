package com.example.zahfitclient.ui.arm;

import com.example.zahfitclient.model.Plan;
import com.example.zahfitclient.ui.abs.AbsViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ArmViewModelFactory implements ViewModelProvider.Factory {
    private List<Plan> planList;

    public ArmViewModelFactory(List<Plan> planList) {
        this.planList = planList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ArmViewModel.class)) {
            return (T) new ArmViewModel(planList);
        }
        throw new IllegalArgumentException("Viewmodel Yang Diminta ArmViewModel");
    }
}
