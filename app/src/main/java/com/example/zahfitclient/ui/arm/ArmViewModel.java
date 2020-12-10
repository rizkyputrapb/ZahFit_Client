package com.example.zahfitclient.ui.arm;

import com.example.zahfitclient.model.Plan;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ArmViewModel extends ViewModel {
    private List<Plan> planList = null;
    private MutableLiveData<List<Plan>> listMutableLiveData = new MutableLiveData<>();

    public ArmViewModel() {
    }

    public ArmViewModel(List<Plan> planList) {
        this.planList = planList;
    }

    public ArmViewModel(MutableLiveData<List<Plan>> listMutableLiveData) {
        this.listMutableLiveData = listMutableLiveData;
    }

    public ArmViewModel(List<Plan> planList, MutableLiveData<List<Plan>> listMutableLiveData) {
        this.planList = planList;
        this.listMutableLiveData = listMutableLiveData;
    }

    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

    public MutableLiveData<List<Plan>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void setListMutableLiveData(MutableLiveData<List<Plan>> listMutableLiveData) {
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