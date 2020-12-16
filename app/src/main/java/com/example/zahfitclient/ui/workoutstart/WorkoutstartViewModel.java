package com.example.zahfitclient.ui.workoutstart;

import com.example.zahfitclient.model.Workout;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WorkoutstartViewModel extends ViewModel {
    private List<Workout> workoutList = null;
    private MutableLiveData<List<Workout>> listMutableLiveData = new MutableLiveData<List<Workout>>();

    public WorkoutstartViewModel(List<Workout> workoutList, MutableLiveData<List<Workout>> listMutableLiveData) {
        this.workoutList = workoutList;
        this.listMutableLiveData = listMutableLiveData;
    }

    public WorkoutstartViewModel(MutableLiveData<List<Workout>> listMutableLiveData) {
        this.listMutableLiveData = listMutableLiveData;
    }

    public WorkoutstartViewModel(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }


}