package com.example.zahfitclient.ui.workoutplan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.zahfitclient.R;
import com.example.zahfitclient.adapter.WorkoutAdapter;
import com.example.zahfitclient.databinding.WorkoutPlanFragmentBinding;
import com.example.zahfitclient.model.Plan;
import com.example.zahfitclient.model.Workout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutPlanFragment extends Fragment {

    private WorkoutPlanViewModel mViewModel;
    private WorkoutPlanFragmentBinding binding;
    private DatabaseReference mDatabase;
    Plan plan;
    List<Workout> workoutList;
    Workout workout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        WorkoutPlanViewModelFactory workoutPlanViewModelFactory = new WorkoutPlanViewModelFactory(workoutList);
        mViewModel = new ViewModelProvider(this, workoutPlanViewModelFactory).get(WorkoutPlanViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.workout_plan_fragment, container, false);
        binding.setViewmodel(mViewModel);
        View view = binding.getRoot();
        assert getArguments() != null;
        plan = WorkoutPlanFragmentArgs.fromBundle(getArguments()).getPlan();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(plan.getPlan_name());
        Glide.with(binding.getRoot().getContext()).load(plan.getUri()).into(binding.imageView2);
        binding.btnStartWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("plan", plan);
                Navigation.findNavController(view).navigate(R.id.action_workoutPlanFragment_to_workoutstartFragment, bundle);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setPlan(plan);
        setupRvWorkout();
    }

    public void setupRvWorkout() {
        RecyclerView recyclerView = binding.workoutRV;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        mDatabase.child("exercise_workout").child("data").orderByChild("plan_id").equalTo(plan.getPlan_key()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                workoutList = new ArrayList<>();
                WorkoutAdapter adapter = new WorkoutAdapter();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String workout_id = snapshot.child("workout_id").getValue(String.class);
                    String count = snapshot.child("count").getValue(String.class);
                    String time = snapshot.child("time").getValue(String.class);
                    mDatabase.child("workout").child(workout_id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            workout = snapshot.getValue(Workout.class);
                            workout.setCount(count);
                            workout.setTime(time);
                            workout.setWorkout_id(workout_id);
                            Log.d("GET", "workout_name: " + workout.getWorkout_name() + " " + workout.getWorkout_id());
                            workoutList.add(workout);
                            adapter.setWorkoutList(workoutList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}