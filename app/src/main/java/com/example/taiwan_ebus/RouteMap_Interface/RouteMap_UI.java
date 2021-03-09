package com.example.taiwan_ebus.RouteMap_Interface;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.taiwan_ebus.Database.RouteInfo;
import com.example.taiwan_ebus.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class RouteMap_UI extends FragmentActivity {
    /*畫面元件*/
    private TabLayout DirectionTags;
    private ViewPager2 UI_Frame;
    private TextView RouteTitle;

    /*元件適配器*/
    FragmentStateAdapter PageAdapter;
    RouteInfo ResultInfo;

    /*路線資訊取得*/
    public RouteInfo getRouteInfo(){
        return ResultInfo;
    }
    public int getDirection(){
        return UI_Frame.getCurrentItem();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*取得傳入資料*/
        Bundle ResultAdapter = getIntent().getExtras();
        ResultInfo = (RouteInfo) ResultAdapter.get("RouteInfo");
        setStartEndStation(ResultInfo);

        /*綁定畫面*/
        setContentView(R.layout.route_stationlist);

        /*設定元件*/
        DirectionTags = findViewById(R.id.Direction_Title);
        UI_Frame = findViewById(R.id.ListContainer);
        RouteTitle = findViewById(R.id.RouteTitle);

        /*設定適配器*/
        RouteTitle.setText(ResultInfo.getRouteName());
        PageAdapter = new DirectionChange(this, getDirection_SubUI());
        UI_Frame.setAdapter(PageAdapter);

        /*設定TabLayout*/
        TabLayoutMediator PageController = new TabLayoutMediator(DirectionTags, UI_Frame, RouteMap_Listener.TabConfig);
        PageController.attach();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*返回鍵動作*/
        if(UI_Frame.getCurrentItem() > 0){
            UI_Frame.setCurrentItem(UI_Frame.getCurrentItem() - 1);
        }
    }

    private ArrayList<Fragment> getDirection_SubUI(){
        /*取得方向子視窗*/
        ArrayList<Fragment> DirectionUI_List = new ArrayList<Fragment>();
        DirectionUI_List.add(new RouteMap_SubUI(0));
        DirectionUI_List.add(new RouteMap_SubUI(1));
        return DirectionUI_List;
    }

    private void setStartEndStation(RouteInfo ResultInfo){
        /*取得端點站*/
        String DepartureSta = ResultInfo.getDepartureStopName();
        String DestinationSta = ResultInfo.getDestinationStopName();
        /*加入端點站Title*/
        RouteMap_Listener.TitleStation.clear();
        RouteMap_Listener.TitleStation.add(DestinationSta);
        RouteMap_Listener.TitleStation.add(DepartureSta);
    }
}
