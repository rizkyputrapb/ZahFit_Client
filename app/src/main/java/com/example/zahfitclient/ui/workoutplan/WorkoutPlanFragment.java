package com.example.zahfitclient.ui.workoutplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zahfitclient.R;
import com.example.zahfitclient.model.Plan;

public class WorkoutPlanFragment extends Fragment {

    private WorkoutPlanViewModel mViewModel;
    Plan plan;

    public static WorkoutPlanFragment newInstance() {
        return new WorkoutPlanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        plan = WorkoutPlanFragmentArgs.fromBundle(getArguments()).getPlan();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(plan.getPlan_name());
        return inflater.inflate(R.layout.workout_plan_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WorkoutPlanViewModel.class);
        // TODO: Use the ViewModel
    }

}