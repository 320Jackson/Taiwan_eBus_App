package com.example.taiwan_ebus.Database.db_Task;

import android.content.Context;
import android.os.Message;
import android.view.View;

import androidx.core.view.ActionProvider;
import androidx.fragment.app.FragmentActivity;

import com.example.taiwan_ebus.Database.DataBase;
import com.example.taiwan_ebus.Database.RouteMap;
import com.example.taiwan_ebus.RouteMap_Interface.DataHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class getRouteMap implements Runnable {
    private FragmentActivity FrameUI;
    private String RouteUID;
    private int Direction;

    /*設定傳送器及資料處理器*/
    DataHandler SendData;

    /*建構子*/
    public getRouteMap(FragmentActivity Input, String InputUID, int DirectionInput, DataHandler InputHandler){
        FrameUI = Input;
        RouteUID = InputUID;
        Direction = DirectionInput;
        SendData = InputHandler;
    }

    @Override
    public void run() {
        Context context = FrameUI.getBaseContext();
        RouteMap Map = DataBase.getInstance(context).getInterface().getMapObj(RouteUID, Direction);

        try{
            /*解析路線清單*/
            String str_StopList = Map.getStopList();

            /*取得路線站序*/
            ArrayList<HashMap<String, String>> StopList = getStationList(str_StopList);

            /*設定傳送器*/
            Message msg = Message.obtain();
            msg.what = 0;
            msg.obj = StopList;
            SendData.sendMessage(msg);
        }
        catch(Exception Err){
            Err.printStackTrace();
        }
    }

    private ArrayList<HashMap<String, String>> getStationList(String str_StopList) throws JSONException {
        ArrayList<HashMap<String, String>> Output = new ArrayList<HashMap<String, String>>();

        /*取得Json站位陣列*/
        JSONArray DataArr = new JSONArray(str_StopList);
        for(int Run = 0; Run < DataArr.length(); Run++){
            /*解析站位資料*/
            String str_StopName = DataArr.getJSONObject(Run).getJSONObject("StopName").getString("Zh_tw");
            String str_StopInfo = DataArr.getJSONObject(Run).toString();

            /*加入回傳陣列*/
            HashMap<String, String> Buffer = new HashMap<String, String>();
            Buffer.put("StopName", str_StopName);
            Buffer.put("StopInfo", str_StopInfo);
            Output.add(Buffer);
        }

        return Output;
    }
}
