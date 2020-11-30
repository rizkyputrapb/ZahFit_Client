package com.example.zahfitclient.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zahfitclient.R;
import com.example.zahfitclient.adapter.PlanAdapter;
import com.example.zahfitclient.databinding.FragmentHomeBinding;
import com.example.zahfitclient.databinding.NavHeaderMainBinding;
import com.example.zahfitclient.model.Plan;
import com.example.zahfitclient.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    List<Plan> planList;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModelFactory homeViewModelFactory = new HomeViewModelFactory(planList);
        homeViewModel = new ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setViewmodel(homeViewModel);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRvPlan();
    }

    private void setupRvPlan() {
        RecyclerView recyclerView = binding.rvPlan;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mDatabase.child("exercise_plan").orderByChild("level_name").equalTo("Easy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                planList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Plan plan = snapshot.getValue(Plan.class);
                    planList.add(plan);
                }
                recyclerView.setLayoutManager(layoutManager);
                PlanAdapter adapter = new PlanAdapter(new OnItemPlanListener() {
                    @Override
                    public void OnPlanClicked(Plan plan) {
                        homeViewModel.onPlanClicked(plan);
                    }
                });
                recyclerView.setAdapter(adapter);
                adapter.setPlanList(planList);
                homeViewModel.navigateToWorkout().observe(getViewLifecycleOwner(), new Observer<Plan>() {
                    @Override
                    public void onChanged(Plan plan) {
                        if (plan != null) {
                            NavDirections action = HomeFragmentDirections.actionNavHomeToWorkoutPlanFragment(plan);
                            Navigation.findNavController(requireView()).navigate(action);
                            homeViewModel.onWorkoutNavigated();
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