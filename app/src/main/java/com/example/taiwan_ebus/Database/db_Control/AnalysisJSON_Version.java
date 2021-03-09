package com.example.taiwan_ebus.Database.db_Control;

import android.content.Context;

import com.example.taiwan_ebus.Database.DataBase;
import com.example.taiwan_ebus.Database.DataVersion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class AnalysisJSON_Version {
    /*解析所有資料，存入資料庫*/
    public static void Add_All(String Data, int CityID, Context context) throws JSONException {
        /*解析Json*/
        JSONObject JSONContent = new JSONObject(Data);
        int VersionID = JSONContent.getInt("VersionID");
        Date UpdateTime = new Date();
        String JSONstr = Data.toString();

        /*存入資料庫*/
        DataVersion DataVer = new DataVersion(CityID, VersionID, UpdateTime, JSONstr);
        DataBase.getInstance(context).getInterface().InsertVersion(DataVer);
    }

    /*取得版本號*/
    public static int Analysis_ID(String Data) throws JSONException{
        JSONObject DataObj = new JSONObject(Data);
        int VersionID = DataObj.getInt("VersionID");
        return VersionID;
    }
}
