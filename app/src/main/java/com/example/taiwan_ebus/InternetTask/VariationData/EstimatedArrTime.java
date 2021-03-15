package com.example.taiwan_ebus.InternetTask.VariationData;

import com.example.taiwan_ebus.Database.RouteInfo;
import com.example.taiwan_ebus.Database.RouteMap;
import com.example.taiwan_ebus.InternetTask.Download_Runnable;
import com.example.taiwan_ebus.RouteMap_Interface.UpdateList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.TimerTask;

public class EstimatedArrTime extends TimerTask {
    /*搜尋依據*/
    private RouteInfo Info;
    private int Direction;

    /*建構子，取得搜尋依據*/
    public EstimatedArrTime(RouteInfo InputInfo, int InputDirection){
        Info = InputInfo;
        Direction = InputDirection;
    }

    @Override
    public void run() {
        /*分析路線地區*/
        String RouteCity = Info.getRouteUID().substring(0, 3);
        if(RouteCity.equals("TPE")){
            RouteCity = "Taipei";
        }
        else if(RouteCity.equals("NWT")){
            RouteCity = "NewTaipei";
        }

        /*取得預估到站時間*/
        String RouteName = Info.getRouteName();
        String URL = "https://ptx.transportdata.tw/MOTC/v2/Bus/EstimatedTimeOfArrival/City/" + RouteCity + "/" + RouteName + "?$filter=Direction%20eq%20" + Direction + "&$format=JSON";
        /*分析預估到站時間*/
        try{
            String JsonContent = Download_Runnable.DownloadTask(URL, "GET");
            HashMap<String, String> ArrTimeList = getArrTimeList(JsonContent);
            UpdateList.getInstance().UpdateArrTime(ArrTimeList);
        }
        catch(Exception Err){
            Err.printStackTrace();
        }
    }

    private HashMap<String, String> getArrTimeList(String Content){
        HashMap<String, String> ArrTimeList = new HashMap<String, String>();
        /*取得各站預估到站時間*/
        try {
            JSONArray JsonArr = new JSONArray(Content);
            for(int Run = 0; Run < JsonArr.length(); Run++){
                JSONObject JsonObj = JsonArr.getJSONObject(Run);

                if(JsonObj.getInt("StopStatus") == 1){
                    ArrTimeList.put(JsonObj.getString("StopUID"), "尚未發車");
                }
                else if(JsonObj.getInt("StopStatus") == 2){
                    ArrTimeList.put(JsonObj.getString("StopUID"), "交管不停");
                }
                else if(JsonObj.getInt("StopStatus") == 3){
                    ArrTimeList.put(JsonObj.getString("StopUID"), "末班已過");
                }
                else if(JsonObj.getInt("StopStatus") == 4){
                    ArrTimeList.put(JsonObj.getString("StopUID"), "今日停駛");
                }
                else{
                    int EstimatedTime = JsonObj.getInt("EstimateTime");
                    if(EstimatedTime < 60){
                        ArrTimeList.put(JsonObj.getString("StopUID"), "即將進站");
                    }
                    else{
                        ArrTimeList.put(JsonObj.getString("StopUID"), (Integer.toString(EstimatedTime / 60) + "分鐘"));
                    }
                }
            }
        }
        catch (Exception Err){
            Err.printStackTrace();
        }
        return ArrTimeList;
    }
}
