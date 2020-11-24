package com.example.zahfitclient.screens;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zahfitclient.R;
import com.example.zahfitclient.databinding.RegisterFragmentBinding;
import com.example.zahfitclient.viewmodel.RegisterViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
    RegisterFragmentBinding binding;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false);
        mViewModel = new RegisterViewModel(getActivity().getApplication());
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
                mViewModel.register(binding.txtEmailregister.getText().toString(), binding.txtPasswordregister.getText().toString(), binding.txtUsernameregister.getText().toString(), binding.txtNameregister.getText().toString(), Integer.parseInt(binding.txtAgeregister.getText().toString()), Integer.parseInt(binding.txtHeightregister.getText().toString()), Integer.parseInt(binding.txtWeightregister.getText().toString()));
            }
        });
        return view;
    }

}