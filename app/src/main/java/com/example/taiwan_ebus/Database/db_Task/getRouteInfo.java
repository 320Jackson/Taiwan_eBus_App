package com.example.taiwan_ebus.Database.db_Task;

import android.os.Message;
import android.view.View;

import com.example.taiwan_ebus.Database.DataBase;
import com.example.taiwan_ebus.Database.RouteInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Handler;

public class getRouteInfo implements Runnable {
    /*傳入資料*/
    private String RouteName;/*查詢字串*/
    private View InterfaceView;/*UI畫面*/

    /*傳送橋接器*/
    private Handler ResultSend;

    /*建構子*/
    public getRouteInfo(String InputName, View MainInterface, Handler InputHandler){
        RouteName = InputName;
        InterfaceView = MainInterface;
        ResultSend = InputHandler;
    }

    @Override
    public void run() {
        /*取得本地端room db資料*/
        List<RouteInfo> RouteIntro = DataBase.getInstance(InterfaceView.getContext()).getInterface().RouteList(RouteName);

        /*解析資料*/
        ArrayList<HashMap<String, String>> RouteResult = new ArrayList<>();/*路線結果*/
        for(int Run = 0; Run < RouteIntro.size(); Run++){
            HashMap<String, String> RouteData = new HashMap<>();
            String str_Name = RouteIntro.get(Run).getRouteName();
            String str_Description = RouteIntro.get(Run).getDepartureStopName() + "---" + RouteIntro.get(Run).getDestinationStopName();
            RouteData.put("RouteName", str_Name);
            RouteData.put("Description", str_Description);
            RouteResult.add(RouteData);
        }

        /*傳送另外解*/
        Message InfoResult = Message.obtain();
        InfoResult.obj = RouteIntro;
        InfoResult.what = 0;
        ResultSend.sendMessage(InfoResult);

        /*傳送結果*/
        Message ResultMsg = Message.obtain();
        ResultMsg.obj = RouteResult;
        ResultMsg.what = 1;
        ResultSend.sendMessage(ResultMsg);
    }
}
