package com.example.zahfitclient.adapter;

import com.example.zahfitclient.ui.abs.AbsFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HomeFragmentAdapter extends FragmentStateAdapter {
    public HomeFragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        AbsFragment absFragment = new AbsFragment();

        switch (position) {
            case 0:
                return absFragment;
            default:
                return absFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
