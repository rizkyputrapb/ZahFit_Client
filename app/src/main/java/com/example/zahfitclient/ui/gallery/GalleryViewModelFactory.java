package com.example.zahfitclient.ui.gallery;

import com.example.zahfitclient.model.PlanHistory;
import com.example.zahfitclient.model.Workout;
import com.example.zahfitclient.ui.workoutstart.WorkoutstartViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class GalleryViewModelFactory implements ViewModelProvider.Factory {
    private List<PlanHistory> historyList;

    public GalleryViewModelFactory(List<PlanHistory> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GalleryViewModel.class)) {
            return (T) new GalleryViewModel(historyList);
        }
        throw new IllegalArgumentException("Viewmodel Yang Diminta WorkoutstartViewModel");
    }
}
