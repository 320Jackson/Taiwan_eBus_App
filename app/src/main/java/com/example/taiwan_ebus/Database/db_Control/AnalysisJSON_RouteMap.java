package com.example.taiwan_ebus.Database.db_Control;

import android.content.Context;

import com.example.taiwan_ebus.Database.DataBase;
import com.example.taiwan_ebus.Database.RouteInfo;
import com.example.taiwan_ebus.Database.RouteMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnalysisJSON_RouteMap {
    public static void Add_All(String Data, Context context) throws JSONException {
        JSONArray DataArr = new JSONArray(Data);
        for(int Run = 0; Run < DataArr.length(); Run++){
            JSONObject DataObj = DataArr.getJSONObject(Run);
            Add_Single(DataObj, context);
        }
    }

    public static void Add_Single(JSONObject MapObj, Context context) throws JSONException {
        int RouteID = MapObj.getInt("RouteID");
        String RouteUID = MapObj.getString("RouteUID");
        String StopList = MapObj.getString("Stops");
        int Direction = MapObj.getInt("Direction");
        String JsonContent = MapObj.toString();

        RouteMap MapData = new RouteMap(RouteID, RouteUID, StopList, Direction, JsonContent);
        DataBase.getInstance(context).getInterface().InsertMap(MapData);
    }
}
