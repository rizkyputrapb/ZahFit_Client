package com.example.zahfitclient.ui.legs;

import com.example.zahfitclient.model.Plan;
import com.example.zahfitclient.ui.fullbody.FullbodyViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LegsViewModelFactory implements ViewModelProvider.Factory {
    private List<Plan> planList;

    public LegsViewModelFactory(List<Plan> planList) {
        this.planList = planList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LegsViewModel.class)) {
            return (T) new LegsViewModel(planList);
        }
        throw new IllegalArgumentException("Viewmodel Yang Diminta LegsViewModel");
    }
}
