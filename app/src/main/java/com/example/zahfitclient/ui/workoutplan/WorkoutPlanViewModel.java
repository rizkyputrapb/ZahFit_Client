package com.example.zahfitclient.ui.workoutplan;

import com.example.zahfitclient.model.Workout;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WorkoutPlanViewModel extends ViewModel {
    private List<Workout> workoutList = null;
    private MutableLiveData<List<Workout>> listMutableLiveData = new MutableLiveData<List<Workout>>();

    public WorkoutPlanViewModel() {
    }

    public WorkoutPlanViewModel(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    public WorkoutPlanViewModel(MutableLiveData<List<Workout>> listMutableLiveData) {
        this.listMutableLiveData = listMutableLiveData;
    }

    public WorkoutPlanViewModel(List<Workout> workoutList, MutableLiveData<List<Workout>> listMutableLiveData) {
        this.workoutList = workoutList;
        this.listMutableLiveData = listMutableLiveData;
    }

    public MutableLiveData<List<Workout>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void setListMutableLiveData(MutableLiveData<List<Workout>> listMutableLiveData) {
        this.listMutableLiveData = listMutableLiveData;
    }

    public LiveData<List<Workout>> listLiveData() {
        return listMutableLiveData;
    }
}