package com.example.taiwan_ebus.Route;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.taiwan_ebus.Database.RouteInfo;
import com.example.taiwan_ebus.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultHandler extends Handler {
    private View Interface;
    private ListView RouteItems;

    public ResultHandler(View InputView){
        Interface = InputView;
    }

    @Override
    public void handleMessage(Message msg) {
        if(msg.what == 0){
            /*取得路線資訊清單*/
            SearchListener.InfoList = (List<RouteInfo>)msg.obj;
        }
        else if(msg.what == 1){
            /*路線清單顯示資訊*/
            RouteItems = Interface.findViewById(R.id.RouteItems);
            RouteItems.setOnItemClickListener(SearchListener.RouteSelector);
            /*更新路線列表*/
            UpdateListView((ArrayList<HashMap<String, String>>)msg.obj);
        }
    }

    private void UpdateListView(ArrayList<HashMap<String, String>> InputData){
        ArrayList<HashMap<String, String>> RouteResult = InputData;
        if(RouteResult != null){
            String[] ResultKey = new String[]{"RouteName", "Description"};
            int[] TextID = new int[]{android.R.id.text1, android.R.id.text2};
            ListAdapter RouteAdapter = new SimpleAdapter(Interface.getContext(), RouteResult, android.R.layout.simple_list_item_2, ResultKey, TextID);
            RouteItems.setAdapter(RouteAdapter);
        }
    }
}
