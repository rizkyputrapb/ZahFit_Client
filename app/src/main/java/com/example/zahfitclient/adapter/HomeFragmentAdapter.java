package com.example.zahfitclient.adapter;

import com.example.zahfitclient.ui.abs.AbsFragment;
import com.example.zahfitclient.ui.arm.ArmFragment;
import com.example.zahfitclient.ui.fullbody.FullbodyFragment;

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
        ArmFragment armFragment = new ArmFragment();
        FullbodyFragment fullbodyFragment = new FullbodyFragment();
        switch (position) {
            case 0:
                return absFragment;

            case 1:
                return armFragment;

            case 2:
                return fullbodyFragment;

            default:
                return absFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
