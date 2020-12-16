package com.example.zahfitclient.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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
import com.example.zahfitclient.adapter.HistoryAdapter;
import com.example.zahfitclient.adapter.OnItemPlanListener;
import com.example.zahfitclient.adapter.PlanAdapter;
import com.example.zahfitclient.databinding.FragmentGalleryBinding;
import com.example.zahfitclient.databinding.LegsFragmentBinding;
import com.example.zahfitclient.model.Plan;
import com.example.zahfitclient.model.PlanHistory;
import com.example.zahfitclient.ui.home.HomeFragmentDirections;
import com.example.zahfitclient.ui.legs.LegsViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    FragmentGalleryBinding binding;
    private DatabaseReference mDatabase;
    private FirebaseUser userFb;
    List<PlanHistory> historyList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        userFb = FirebaseAuth.getInstance().getCurrentUser();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModelFactory galleryViewModelFactory = new GalleryViewModelFactory(historyList);
        galleryViewModel =
                new ViewModelProvider(this, galleryViewModelFactory).get(GalleryViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false);
        binding.setViewmodel(galleryViewModel);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRvHistory();
    }

    private void setupRvHistory() {
        RecyclerView recyclerView = binding.rvHistory;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mDatabase.child("history").child(userFb.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                historyList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    PlanHistory history = snapshot.getValue(PlanHistory.class);
                    historyList.add(history);
                }
                recyclerView.setLayoutManager(layoutManager);
                HistoryAdapter adapter = new HistoryAdapter();
                recyclerView.setAdapter(adapter);
                adapter.setHistoryList(historyList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}