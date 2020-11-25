package com.example.zahfitclient.screens;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zahfitclient.R;
import com.example.zahfitclient.UserMainActivity;
import com.example.zahfitclient.databinding.RegisterFragmentBinding;
import com.example.zahfitclient.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment {

    RegisterFragmentBinding binding;
    private User user;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.intro_bg);
        binding.videoView3.setVideoURI(uri);
        binding.videoView3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        binding.videoView3.start();
        View view = binding.getRoot();
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.txtEmailregister.getText().toString();
                String password = binding.txtPasswordregister.getText().toString();
                String username = binding.txtUsernameregister.getText().toString();
                String name = binding.txtNameregister.getText().toString();
                int age = Integer.parseInt(binding.txtAgeregister.getText().toString());
                int height = Integer.parseInt(binding.txtHeightregister.getText().toString());
                int weight = Integer.parseInt(binding.txtWeightregister.getText().toString());
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    user = new User(email, username, name, age, height, weight);
                                    mDatabase.child("user").child(task.getResult().getUser().getUid()).setValue(user);
                                    Log.w("RegisterActivity", "Register Success " + firebaseAuth.getCurrentUser().getEmail());
                                    startActivity(new Intent(getContext(), UserMainActivity.class));
                                    getActivity().finish();
                                } else {
                                    Toast.makeText(getActivity(), "Registration Failure: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        return view;
    }
}