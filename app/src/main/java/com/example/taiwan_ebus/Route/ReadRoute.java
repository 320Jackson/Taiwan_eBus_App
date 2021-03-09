package com.example.taiwan_ebus.Route;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadRoute {
    public static Context Interface;

    public static String ReadLocalFile(String Path){
        String Output = "";
        try{
            /*讀取資料流*/
            InputStreamReader InputReader = new InputStreamReader(Interface.getAssets().open(Path), "UTF-8");
            BufferedReader DataReader = new BufferedReader(InputReader);

            /*解析資料*/
            String Buffer = DataReader.readLine();
            while(Buffer != null){
                Output += Buffer;
                Buffer = DataReader.readLine();
            }
        }
        catch(Exception Err){
            Err.printStackTrace();
        }
        return Output;
    }

    public static ArrayList<String> GetRouteName(String JsonFile){
        ArrayList<String> Output = new ArrayList<String>();
        try{
            JSONArray RouteArr = new JSONArray(JsonFile);
            for(int Run = 0; Run < RouteArr.length(); Run++){
                JSONObject ReadRoute = RouteArr.getJSONObject(Run).getJSONObject("RouteName");
                String RouteName = ReadRoute.getString("Zh_tw");
                Output.add(RouteName);
            }
        }
        catch(Exception Err){
            Err.printStackTrace();
        }
        return Output;
    }

    public static ArrayList<String> GetStartEnd(String JsonFile){
        ArrayList<String> Output = new ArrayList<String>();
        try{
            JSONArray RouteArr = new JSONArray(JsonFile);
            for(int Run = 0; Run < RouteArr.length(); Run++){
                JSONObject ReadRoute = RouteArr.getJSONObject(Run);
                String ReadStr = ReadRoute.getString("DepartureStopNameZh") + "-" + ReadRoute.getString("DestinationStopNameZh");
                Output.add(ReadStr);
            }
        }
        catch(Exception Err){
            Err.printStackTrace();
        }
        return Output;
    }
}
