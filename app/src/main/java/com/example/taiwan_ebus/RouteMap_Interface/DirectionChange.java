package com.example.taiwan_ebus.RouteMap_Interface;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class DirectionChange extends FragmentStateAdapter {
    /*方向子視窗清單*/
    private ArrayList<Fragment> Direction_SubUI;

    /*建構子*/
    public DirectionChange(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ArrayList<Fragment> InputUI) {
        super(fragmentManager, lifecycle);
        Direction_SubUI = InputUI;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return Direction_SubUI.get(position);
    }

    @Override
    public int getItemCount() {
        return Direction_SubUI.size();
    }
}
