package com.example.zahfitclient.ui.legs;

import com.example.zahfitclient.model.Plan;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LegsViewModel extends ViewModel {
    private List<Plan> planList = null;
    private MutableLiveData<List<Plan>> listMutableLiveData = new MutableLiveData<>();

    public LegsViewModel() {
    }

    public LegsViewModel(List<Plan> planList) {
        this.planList = planList;
    }

    public LegsViewModel(MutableLiveData<List<Plan>> listMutableLiveData) {
        this.listMutableLiveData = listMutableLiveData;
    }

    public LegsViewModel(List<Plan> planList, MutableLiveData<List<Plan>> listMutableLiveData) {
        this.planList = planList;
        this.listMutableLiveData = listMutableLiveData;
    }

    public LiveData<List<Plan>> listLiveData() {
        return listMutableLiveData;
    }

    private MutableLiveData<Plan> _navigateToWorkout = new MutableLiveData<>();

    public LiveData<Plan> navigateToWorkout() {
        return _navigateToWorkout;
    }

    public void onPlanClicked(Plan plan) {
        _navigateToWorkout.setValue(plan);
    }

    public void onWorkoutNavigated() {
        _navigateToWorkout.setValue(null);
    }
}