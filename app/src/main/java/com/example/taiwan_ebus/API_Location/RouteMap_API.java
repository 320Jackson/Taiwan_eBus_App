package com.example.taiwan_ebus.API_Location;

import java.util.ArrayList;

public class RouteMap_API {
    private static String InterCity = "https://ptx.transportdata.tw/MOTC/v2/Bus/DisplayStopOfRoute/InterCity?$format=JSON";
    private static String Taipei = "https://ptx.transportdata.tw/MOTC/v2/Bus/DisplayStopOfRoute/City/Taipei?$format=JSON";
    private static String NewTaipei = "https://ptx.transportdata.tw/MOTC/v2/Bus/DisplayStopOfRoute/City/NewTaipei?$format=JSON";

    public static ArrayList<String> APIList = new ArrayList<String>(){{add(InterCity); add(Taipei); add(NewTaipei);}};
}
