package com.example.taiwan_ebus.RouteMap_Interface;

import android.app.Activity;
import android.content.Context;
import android.icu.util.Output;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.taiwan_ebus.Database.RouteInfo;
import com.example.taiwan_ebus.Database.db_Task.getRouteMap;
import com.example.taiwan_ebus.R;

public class RouteMap_SubUI extends Fragment {
    /*橋接器及內容*/
    private RouteInfo Info;
    int Direction;

    /*建構子*/
    public RouteMap_SubUI(int DirectionInput){
        Direction = DirectionInput;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*載入子視窗畫面*/
        View OutputDirection = inflater.inflate(R.layout.direction, container, false);
        /*取得路線站序*/
        FragmentActivity FrameUI = this.getActivity();
        DataHandler UI_Updater = new DataHandler(FrameUI, OutputDirection);
        String RouteUID = Info.getRouteUID();
        Thread getRouteMap = new Thread(new getRouteMap(FrameUI, RouteUID, Direction, UI_Updater));
        getRouteMap.start();
        return OutputDirection;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        /*取得現在選取的路線資訊*/
        super.onAttach(context);
        RouteMap_UI FrameActivity = (RouteMap_UI) context;
        Info = FrameActivity.getRouteInfo();
    }
}
