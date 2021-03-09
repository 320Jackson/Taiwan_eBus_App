package com.example.taiwan_ebus.Database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "DataVersion")
public class DataVersion {
    @PrimaryKey(autoGenerate = false)
    protected int CityID;
    protected int Version;
    protected Date UpdateTime;
    protected String JsonContent;

    public DataVersion(){}

    @Ignore
    public DataVersion(int Input_City, int Input_Ver, Date Input_UT, String InputContent){
        CityID = Input_City;
        Version = Input_Ver;
        UpdateTime = Input_UT;
        JsonContent = InputContent;
    }

    public int getCityID(){
        return CityID;
    }

    public int getVersion(){
        return Version;
    }

    public Date getUpdateTime(){
        return UpdateTime;
    }

    public String getJsonContent(){
        return JsonContent;
    }
}
