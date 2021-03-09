package com.example.taiwan_ebus.Database.db_Control;

import android.content.Context;

import com.example.taiwan_ebus.Database.DataBase;
import com.example.taiwan_ebus.Database.RouteInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnalysisJSON_RouteInfo {
    public static void Add_All(String Data, Context context) throws JSONException {
        JSONArray JSONArr = new JSONArray(Data);
        for(int Run = 0; Run < JSONArr.length(); Run++) {
            JSONObject RouteObj = JSONArr.getJSONObject(Run);
            Add_Single(RouteObj, context);
        }
    }

    public static void Add_Single(JSONObject RouteObj, Context context) throws JSONException{
        int RouteID = RouteObj.getInt("RouteID");
        String RouteUID = RouteObj.getString("RouteUID");
        String RouteName = RouteObj.getJSONObject("RouteName").getString("Zh_tw");
        String DepartureSta = RouteObj.getString("DepartureStopNameZh");
        String DestinationSta = RouteObj.getString("DestinationStopNameZh");
        String JsonContent = RouteObj.toString();

        RouteInfo InfoData = new RouteInfo(RouteID, RouteUID, RouteName, DepartureSta, DestinationSta, JsonContent);
        DataBase.getInstance(context).getInterface().InsertInfo(InfoData);
    }
}
