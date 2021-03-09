package com.example.taiwan_ebus.Main;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.taiwan_ebus.Database.db_Control.CheckUpdate;
import com.example.taiwan_ebus.NetworkReceiver.NetworkReceiver;
import com.example.taiwan_ebus.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class Main_Interface extends AppCompatActivity {
    /*框架及容器*/
    private ViewPager2 Frame;
    private ArrayList<View> Pages;

    /*介面元件*/
    private TabLayout PageTags;

    /*網路狀態偵測*/
    NetworkReceiver Receiver;/*網路監聽器*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*綁定介面*/
        setContentView(R.layout.main_interface);

        /*設定框架*/
        Frame = findViewById(R.id.ListContainer);
        setPageList();
        Frame.setOffscreenPageLimit(2);
        Frame.setAdapter(new FrameAdapter(Pages));
        Frame.registerOnPageChangeCallback(Main_Listener.PageChange);

        PageTags = findViewById(R.id.Title_List);
        /*綁定ViewPager2及TabLayout*/
        TabLayoutMediator FrameController = new TabLayoutMediator(PageTags, Frame, Main_Listener.TabConfig);
        FrameController.attach();

        /*檢查資料庫*/
        Thread CheckDBUpdate = new Thread(new CheckUpdate(Main_Interface.this));
        CheckDBUpdate.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter EventFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        /*註冊網路監聽器*/
        Receiver = new NetworkReceiver();
        registerReceiver(Receiver, EventFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*程式結束，釋放監聽器*/
        unregisterReceiver(Receiver);
    }

    private void setPageList(){
        /*建立可顯示介面清單*/
        Pages = new ArrayList<View>();
        Pages.add(getLayoutInflater().inflate(R.layout.route_search, null));
        Pages.add(getLayoutInflater().inflate(R.layout.station_interface, null));
        Pages.add(getLayoutInflater().inflate(R.layout.bus_trace, null));
        Pages.add(getLayoutInflater().inflate(R.layout.ubike_interface, null));
        Main_Listener.Pages = Pages;
        Main_Listener.MainInterface = Main_Interface.this;
    }
}
