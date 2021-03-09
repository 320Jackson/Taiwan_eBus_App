package com.example.taiwan_ebus.RouteMap_Interface;

import androidx.annotation.NonNull;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class RouteMap_Listener {
    public static ArrayList<String> TitleStation = new ArrayList<String>();

    /*去返程切換器*/
    public static TabLayoutMediator.TabConfigurationStrategy TabConfig = new TabLayoutMediator.TabConfigurationStrategy() {
        @Override
        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            tab.setText(TitleStation.get(position));
        }
    };
}
