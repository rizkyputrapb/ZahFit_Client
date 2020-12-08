package com.example.zahfitclient.ui.abs;

import com.example.zahfitclient.model.Plan;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AbsViewModelFactory implements ViewModelProvider.Factory {
    private List<Plan> planList;

    public AbsViewModelFactory(List<Plan> planList) {
        this.planList = planList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AbsViewModel.class)) {
            return (T) new AbsViewModel(planList);
        }
        throw new IllegalArgumentException("Viewmodel Yang Diminta UserMainActivityViewModel");
    }
}
