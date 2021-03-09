package com.example.taiwan_ebus.Route;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;

import com.example.taiwan_ebus.Database.RouteInfo;
import com.example.taiwan_ebus.Database.db_Task.getRouteInfo;
import com.example.taiwan_ebus.RouteMap_Interface.RouteMap_SubUI;
import com.example.taiwan_ebus.RouteMap_Interface.RouteMap_UI;

import java.io.Serializable;
import java.util.List;

public class SearchListener {
    public static View MainContext;
    public static Activity MainInterface;
    public static List<RouteInfo> InfoList;

    /*路線點擊監聽器*/
    public static AdapterView.OnItemClickListener RouteSelector = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            RouteInfo SelectionRoute = InfoList.get(position);
            /*啟動子視窗*/
            Intent ActivityIntent = new Intent();
            ActivityIntent.setClass(MainInterface, RouteMap_UI.class);
            Bundle SendData = new Bundle();
            SendData.putSerializable("RouteInfo", SelectionRoute);
            ActivityIntent.putExtras(SendData);
            MainInterface.startActivity(ActivityIntent);
        }
    };

    /*搜尋文字監聽器*/
    public static TextWatcher TextChange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            /*取得路線搜尋字串*/
            String str_Route = s.toString();
            str_Route = "%" + str_Route + "%";

            /*設定UI更新處理*/
            Handler UI_Handler = new ResultHandler(MainContext);
            /*取得資料*/
            Thread getRouteTask = new Thread(new getRouteInfo(str_Route, MainContext, UI_Handler));
            try{
                getRouteTask.start();
            }
            catch(Exception Err){
                Err.printStackTrace();
            }
        }
    };
}
