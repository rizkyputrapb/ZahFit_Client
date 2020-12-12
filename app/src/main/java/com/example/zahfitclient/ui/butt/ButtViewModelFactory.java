package com.example.zahfitclient.ui.butt;

import com.example.zahfitclient.model.Plan;
import com.example.zahfitclient.ui.arm.ArmViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ButtViewModelFactory implements ViewModelProvider.Factory {
    private List<Plan> planList;

    public ButtViewModelFactory(List<Plan> planList) {
        this.planList = planList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ButtViewModel.class)) {
            return (T) new ButtViewModel(planList);
        }
        throw new IllegalArgumentException("Viewmodel Yang Diminta ButtViewModel");
    }
}
