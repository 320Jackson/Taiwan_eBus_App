package com.example.taiwan_ebus.Database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "RouteInfo")
public class RouteInfo implements Serializable {
    @PrimaryKey(autoGenerate = false)
    protected int RouteID;
    protected String RouteUID;
    protected String RouteName;
    protected String DepartureStopName;
    protected String DestinationStopName;
    protected boolean IsFavorite;
    protected String JsonContent;

    public RouteInfo(){}

    @Ignore
    public RouteInfo(int InputID, String InputUID, String InputName, String InputDeparture, String InputDestination, String InputObject){
        RouteID = InputID;
        RouteUID = InputUID;
        RouteName = InputName;
        DepartureStopName = InputDeparture;
        DestinationStopName = InputDestination;
        IsFavorite = false;
        JsonContent = InputObject;
    }

    public String getRouteUID(){
        return RouteUID;
    }

    public String getRouteName(){
        return RouteName;
    }

    public String getDepartureStopName(){
        return DepartureStopName;
    }

    public String getDestinationStopName(){
        return DestinationStopName;
    }

    public String getJsonContent(){
        return JsonContent;
    }
}
