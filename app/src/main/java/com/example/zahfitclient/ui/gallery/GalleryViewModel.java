package com.example.zahfitclient.ui.gallery;

import com.example.zahfitclient.model.PlanHistory;
import com.example.zahfitclient.model.Workout;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private List<PlanHistory> historyList = null;
    private MutableLiveData<List<PlanHistory>> listMutableLiveData = new MutableLiveData<List<PlanHistory>>();

    public GalleryViewModel() {
    }

    public GalleryViewModel(List<PlanHistory> historyList) {
        this.historyList = historyList;
    }

    public GalleryViewModel(MutableLiveData<List<PlanHistory>> listMutableLiveData) {
        this.listMutableLiveData = listMutableLiveData;
    }

    public GalleryViewModel(List<PlanHistory> historyList, MutableLiveData<List<PlanHistory>> listMutableLiveData) {
        this.historyList = historyList;
        this.listMutableLiveData = listMutableLiveData;
    }

    public List<PlanHistory> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<PlanHistory> historyList) {
        this.historyList = historyList;
    }

    public MutableLiveData<List<PlanHistory>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void setListMutableLiveData(MutableLiveData<List<PlanHistory>> listMutableLiveData) {
        this.listMutableLiveData = listMutableLiveData;
    }
}