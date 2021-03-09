package com.example.taiwan_ebus.RouteMap_Interface;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class DirectionChange extends FragmentStateAdapter {
    /*方向子視窗清單*/
    private List<Fragment> Direction_SubUI;

    /*建構子*/
    public DirectionChange(@NonNull FragmentActivity fa, List<Fragment> Input_SubUI) {
        super(fa);
        Direction_SubUI = Input_SubUI;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        /*設定現正畫面*/
        return Direction_SubUI.get(position);
    }

    @Override
    public int getItemCount() {
        /*取得頁面總數*/
        return Direction_SubUI.size();
    }
}
