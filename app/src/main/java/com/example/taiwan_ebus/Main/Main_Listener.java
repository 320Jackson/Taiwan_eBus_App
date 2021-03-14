package com.example.taiwan_ebus.Main;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.taiwan_ebus.R;
import com.example.taiwan_ebus.Route.RouteSearch_Interface;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class Main_Listener {
    public static ArrayList<View> Pages;
    private static RouteSearch_Interface RS_Controller;
    public static Activity MainInterface;

    /*設定分頁籤*/
    public static TabLayoutMediator.TabConfigurationStrategy TabConfig = new TabLayoutMediator.TabConfigurationStrategy() {
        String[] TagTitle = new String[]{"路線搜尋", "站牌搜尋", "公車追蹤", "公共自行車"};
        @Override
        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            tab.setText(TagTitle[position]);
        }
    };

    /*設定換頁事件*/
    public static ViewPager2.OnPageChangeCallback PageChange = new ViewPager2.OnPageChangeCallback() {
        View PageRead;/*讀取頁面*/
        @Override
        public void onPageSelected(int position) {
            PageRead = Pages.get(position);

            switch(position){
                case 0:
                    if(RS_Controller == null){
                        RS_Controller = new RouteSearch_Interface(PageRead, MainInterface);
                    }
                    else{
                        RS_Controller.setMainActivity(PageRead, MainInterface);
                    }
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:

                    break;
            }
        }
    };
}
