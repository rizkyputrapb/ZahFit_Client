package com.example.zahfitclient.ui.home;

import com.example.zahfitclient.model.Plan;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private List<Plan> planList;

    public HomeViewModelFactory(List<Plan> planList) {
        this.planList = planList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(HomeViewModel.class)){
            return (T) new HomeViewModel(planList);
        }
        throw new IllegalArgumentException("Viewmodel Yang Diminta UserMainActivityViewModel");
    }
}
