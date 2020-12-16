package com.example.zahfitclient.ui.workoutstart;

import com.example.zahfitclient.model.Workout;
import com.example.zahfitclient.ui.workoutplan.WorkoutPlanViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class WorkoutstartViewModelFactory implements ViewModelProvider.Factory {
    private List<Workout> workoutList;

    public WorkoutstartViewModelFactory(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WorkoutstartViewModel.class)) {
            return (T) new WorkoutstartViewModel(workoutList);
        }
        throw new IllegalArgumentException("Viewmodel Yang Diminta WorkoutstartViewModel");
    }
}
