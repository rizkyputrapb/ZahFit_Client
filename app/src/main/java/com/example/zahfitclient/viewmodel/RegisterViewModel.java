package com.example.zahfitclient.viewmodel;

import android.app.Application;

import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private AuthAppRepository authAppRepository;
    private MutableLiveData<FirebaseUser> userLiveData;

    public RegisterViewModel(@NonNull Application application) {
        super();
        authAppRepository = new AuthAppRepository(application);
        userLiveData = authAppRepository.getUserLiveData();
    }

    public void register(String email, String password, String username, String name, int age, int height, int weight) {
        authAppRepository.register(email, password, username, name, age, height, weight);
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }
}