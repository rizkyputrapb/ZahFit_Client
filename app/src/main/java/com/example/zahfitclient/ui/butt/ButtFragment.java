package com.example.zahfitclient.ui.butt;

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
import com.example.zahfitclient.databinding.ButtFragmentBinding;
import com.example.zahfitclient.model.Plan;
import com.example.zahfitclient.ui.home.HomeFragmentDirections;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ButtFragment extends Fragment {

    private ButtViewModel mViewModel;
    ButtFragmentBinding binding;
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
        ButtViewModelFactory buttViewModelFactory = new ButtViewModelFactory(planList);
        mViewModel = new ViewModelProvider(this, buttViewModelFactory).get(ButtViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.butt_fragment, container, false);
        View view = binding.getRoot();
        binding.setViewmodel(mViewModel);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRvButtBeginner();
        setupRvButtIntermediate();
        setupRvButtHard();
        setupRvButtBodyB();
    }

    private void setupRvButtBeginner() {
        RecyclerView recyclerView = binding.rvButtBeginner;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mDatabase.child("exercise_plan").orderByChild("type_level").equalTo("butt_easy").addValueEventListener(new ValueEventListener() {
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

    private void setupRvButtIntermediate() {
        RecyclerView recyclerView = binding.rvButtIntermediate;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mDatabase.child("exercise_plan").orderByChild("type_level").equalTo("butt_medium").addValueEventListener(new ValueEventListener() {
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

    private void setupRvButtHard() {
        RecyclerView recyclerView = binding.rvButtHard;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mDatabase.child("exercise_plan").orderByChild("type_level").equalTo("butt_hard").addValueEventListener(new ValueEventListener() {
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

    private void setupRvButtBodyB() {
        RecyclerView recyclerView = binding.rvButtBodyB;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mDatabase.child("exercise_plan").orderByChild("type_level").equalTo("butt_bodyb").addValueEventListener(new ValueEventListener() {
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