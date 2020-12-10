package com.example.zahfitclient.ui.fullbody;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zahfitclient.R;
import com.example.zahfitclient.adapter.OnItemPlanListener;
import com.example.zahfitclient.adapter.PlanAdapter;
import com.example.zahfitclient.databinding.FullbodyFragmentBinding;
import com.example.zahfitclient.model.Plan;
import com.example.zahfitclient.ui.arm.ArmViewModel;
import com.example.zahfitclient.ui.home.HomeFragmentDirections;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FullbodyFragment extends Fragment {

    private FullbodyViewModel mViewModel;
    FullbodyFragmentBinding binding;
    private DatabaseReference mDatabase;
    List<Plan> planList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FullbodyViewModelFactory fullbodyViewModelFactory = new FullbodyViewModelFactory(planList);
        mViewModel = new ViewModelProvider(this, fullbodyViewModelFactory).get(FullbodyViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fullbody_fragment, container, false);
        binding.setViewmodel(mViewModel);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRvFullBBeginner();
        setupRvFullBMedium();
        setupRvFullBHard();
        setupRvFullBBodyB();
        // TODO: Use the ViewModel
    }

    private void setupRvFullBBodyB() {
        RecyclerView recyclerView = binding.rvFullBBodyB;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mDatabase.child("exercise_plan").orderByChild("type_level").equalTo("fullbody_bodyb").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                planList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Plan plan = snapshot.getValue(Plan.class);
                    plan.setPlan_key(snapshot.getKey());
                    planList.add(plan);
                }
                recyclerView.setLayoutManager(layoutManager);
                PlanAdapter adapter = new PlanAdapter(new OnItemPlanListener() {
                    @Override
                    public void OnPlanClicked(Plan plan) {
                        mViewModel.onPlanClicked(plan);
                    }
                });
                recyclerView.setAdapter(adapter);
                adapter.setPlanList(planList);
                mViewModel.navigateToWorkout().observe(getViewLifecycleOwner(), new Observer<Plan>() {
                    @Override
                    public void onChanged(Plan plan) {
                        if (plan != null) {
                            NavDirections action = HomeFragmentDirections.actionNavHomeToWorkoutPlanFragment(plan);
                            Navigation.findNavController(requireView()).navigate(action);
                            mViewModel.onWorkoutNavigated();
                        }
                    }
                });
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setupRvFullBHard() {
        RecyclerView recyclerView = binding.rvFullBHard;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mDatabase.child("exercise_plan").orderByChild("type_level").equalTo("fullbody_hard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                planList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Plan plan = snapshot.getValue(Plan.class);
                    plan.setPlan_key(snapshot.getKey());
                    planList.add(plan);
                }
                recyclerView.setLayoutManager(layoutManager);
                PlanAdapter adapter = new PlanAdapter(new OnItemPlanListener() {
                    @Override
                    public void OnPlanClicked(Plan plan) {
                        mViewModel.onPlanClicked(plan);
                    }
                });
                recyclerView.setAdapter(adapter);
                adapter.setPlanList(planList);
                mViewModel.navigateToWorkout().observe(getViewLifecycleOwner(), new Observer<Plan>() {
                    @Override
                    public void onChanged(Plan plan) {
                        if (plan != null) {
                            NavDirections action = HomeFragmentDirections.actionNavHomeToWorkoutPlanFragment(plan);
                            Navigation.findNavController(requireView()).navigate(action);
                            mViewModel.onWorkoutNavigated();
                        }
                    }
                });
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setupRvFullBMedium() {
        RecyclerView recyclerView = binding.rvFullBIntermediate;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mDatabase.child("exercise_plan").orderByChild("type_level").equalTo("fullbody_medium").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                planList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Plan plan = snapshot.getValue(Plan.class);
                    plan.setPlan_key(snapshot.getKey());
                    planList.add(plan);
                }
                recyclerView.setLayoutManager(layoutManager);
                PlanAdapter adapter = new PlanAdapter(new OnItemPlanListener() {
                    @Override
                    public void OnPlanClicked(Plan plan) {
                        mViewModel.onPlanClicked(plan);
                    }
                });
                recyclerView.setAdapter(adapter);
                adapter.setPlanList(planList);
                mViewModel.navigateToWorkout().observe(getViewLifecycleOwner(), new Observer<Plan>() {
                    @Override
                    public void onChanged(Plan plan) {
                        if (plan != null) {
                            NavDirections action = HomeFragmentDirections.actionNavHomeToWorkoutPlanFragment(plan);
                            Navigation.findNavController(requireView()).navigate(action);
                            mViewModel.onWorkoutNavigated();
                        }
                    }
                });
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setupRvFullBBeginner() {
        RecyclerView recyclerView = binding.rvFullBBeginner;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mDatabase.child("exercise_plan").orderByChild("type_level").equalTo("fullbody_easy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                planList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Plan plan = snapshot.getValue(Plan.class);
                    plan.setPlan_key(snapshot.getKey());
                    planList.add(plan);
                }
                recyclerView.setLayoutManager(layoutManager);
                PlanAdapter adapter = new PlanAdapter(new OnItemPlanListener() {
                    @Override
                    public void OnPlanClicked(Plan plan) {
                        mViewModel.onPlanClicked(plan);
                    }
                });
                recyclerView.setAdapter(adapter);
                adapter.setPlanList(planList);
                mViewModel.navigateToWorkout().observe(getViewLifecycleOwner(), new Observer<Plan>() {
                    @Override
                    public void onChanged(Plan plan) {
                        if (plan != null) {
                            NavDirections action = HomeFragmentDirections.actionNavHomeToWorkoutPlanFragment(plan);
                            Navigation.findNavController(requireView()).navigate(action);
                            mViewModel.onWorkoutNavigated();
                        }
                    }
                });
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}