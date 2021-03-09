package com.example.taiwan_ebus.Database.db_Control;

import android.content.Context;
import android.icu.util.VersionInfo;
import android.widget.Toast;

import com.example.taiwan_ebus.API_Location.RouteInfo_API;
import com.example.taiwan_ebus.API_Location.RouteMap_API;
import com.example.taiwan_ebus.API_Location.Version_API;
import com.example.taiwan_ebus.Database.DataBase;
import com.example.taiwan_ebus.InternetTask.Download_Runnable;

import io.reactivex.annotations.Nullable;

public class CheckUpdate implements Runnable {
    private Context MainContext;/*畫面Context*/

    /*建構子*/
    public CheckUpdate(Context context){
        MainContext = context;
    }

    @Override
    public void run() {
        /*檢查資料庫是否有資料*/
        int int_VersionCount = DataBase.getInstance(MainContext).getInterface().getVersionCount();
        if(int_VersionCount == 0){
            for(int RunCity = 1; RunCity <= 2; RunCity++){
                GetUpdate(RunCity, null);
            }
            return;
        }

        /*有資料，檢查更新*/
        for(int RunCity = 1; RunCity <= 2; RunCity++){
            try{
                /*取得網路版本*/
                String str_Version = Download_Runnable.DownloadTask(Version_API.APIList.get(RunCity), "GET");

                /*解析版本，網路版號及本地版號*/
                int NetworkVersion = AnalysisJSON_Version.Analysis_ID(str_Version);
                int LocalVersion = DataBase.getInstance(MainContext).getInterface().getVersionoObj(RunCity).getVersion();
                /*比對兩者號碼，進行更新*/
                if(NetworkVersion != LocalVersion){
                    GetUpdate(RunCity, str_Version);
                }
            }
            catch(Exception Err) {
                Err.printStackTrace();
            }
        }
    }

    private void GetUpdate(int CityID, String Ver_Input){
        try{
            /*網路版本*/
            String str_Version;
            if(Ver_Input == null){
                /*初始化使用*/
                str_Version = Download_Runnable.DownloadTask(Version_API.APIList.get(CityID), "GET");
            }
            else{
                str_Version = Ver_Input;
            }
            /*加入資料庫*/
            AnalysisJSON_Version.Add_All(str_Version, CityID, MainContext);

            /*取得路線資訊*/
            String str_RouteInfo = Download_Runnable.DownloadTask(RouteInfo_API.APIList.get(CityID), "GET");
            AnalysisJSON_RouteInfo.Add_All(str_RouteInfo, MainContext);

            /*取得路線線型*/
            String str_RouteMap = Download_Runnable.DownloadTask(RouteMap_API.APIList.get(CityID), "GET");
            AnalysisJSON_RouteMap.Add_All(str_RouteMap, MainContext);
        }
        catch (Exception Err){
            Err.printStackTrace();
        }
    }
}
