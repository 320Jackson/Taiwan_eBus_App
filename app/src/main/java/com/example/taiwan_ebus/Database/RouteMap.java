package com.example.taiwan_ebus.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "RouteMap", primaryKeys = {"RouteUID", "Direction"})
public class RouteMap {
    @NonNull
    protected String RouteUID;
    protected int RouteId;
    protected String StopList;
    protected int Direction;
    protected String JsonContent;

    public RouteMap(){}

    @Ignore
    public RouteMap(int InputID, String InputUID, String StopInput, int DirectionInput, String InputContent) {
        RouteId = InputID;
        RouteUID = InputUID;
        StopList = StopInput;
        Direction = DirectionInput;
        JsonContent = InputContent;
    }

    public String getStopList() {
        return StopList;
    }
}
