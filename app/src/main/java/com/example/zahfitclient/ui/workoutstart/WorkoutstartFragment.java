package com.example.zahfitclient.ui.workoutstart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zahfitclient.R;
import com.example.zahfitclient.UserMainActivity;
import com.example.zahfitclient.adapter.WorkoutAdapter;
import com.example.zahfitclient.adapter.WorkoutstartAdapter;
import com.example.zahfitclient.databinding.WorkoutstartFragmentBinding;
import com.example.zahfitclient.model.Plan;
import com.example.zahfitclient.model.PlanHistory;
import com.example.zahfitclient.model.Workout;
import com.example.zahfitclient.ui.workoutplan.WorkoutPlanFragmentArgs;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WorkoutstartFragment extends Fragment {

    private WorkoutstartViewModel mViewModel;
    private DatabaseReference mDatabase;
    FirebaseUser user;
    WorkoutstartFragmentBinding binding;
    Plan plan;
    List<Workout> workoutList;
    Workout workout;

    public static WorkoutstartFragment newInstance() {
        return new WorkoutstartFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        WorkoutstartViewModelFactory workoutstartViewModelFactory = new WorkoutstartViewModelFactory(workoutList);
        mViewModel = new ViewModelProvider(this, workoutstartViewModelFactory).get(WorkoutstartViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.workoutstart_fragment, container, false);
        binding.setViewmodel(mViewModel);
        assert getArguments() != null;
        plan = getArguments().getParcelable("plan");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(plan.getPlan_name());
        binding.btnFinishWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                mDatabase.child("history").child(user.getUid()).push().setValue(new PlanHistory(formattedDate, plan.getPlan_key(), plan.getPlan_name(), plan.getLevel_name(), plan.getType_name(), plan.getPersonal_trainer_id(), plan.getUri()));
                Intent i = new Intent(getContext(), UserMainActivity.class);
                startActivity(i);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRvWorkout();
    }

    private void setupRvWorkout() {
        RecyclerView recyclerView = binding.rvWorkoutDetails;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        mDatabase.child("exercise_workout").child("data").orderByChild("plan_id").equalTo(plan.getPlan_key()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                workoutList = new ArrayList<>();
                WorkoutstartAdapter adapter = new WorkoutstartAdapter();
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