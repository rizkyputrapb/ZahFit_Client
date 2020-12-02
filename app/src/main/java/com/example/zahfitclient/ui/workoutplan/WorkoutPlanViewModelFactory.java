package com.example.zahfitclient.ui.workoutplan;

import com.example.zahfitclient.model.Plan;
import com.example.zahfitclient.model.Workout;
import com.example.zahfitclient.ui.home.HomeViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class WorkoutPlanViewModelFactory implements ViewModelProvider.Factory {
    private List<Workout> workoutList;

    public WorkoutPlanViewModelFactory(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WorkoutPlanViewModel.class)) {
            return (T) new WorkoutPlanViewModel(workoutList);
        }
        throw new IllegalArgumentException("Viewmodel Yang Diminta UserMainActivityViewModel");
    }
}