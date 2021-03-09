package com.example.taiwan_ebus.RouteMap_Interface;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.taiwan_ebus.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DataHandler extends Handler {
    private FragmentActivity MainView;
    private ListView StationList;
    private View ViewElement;

    public DataHandler(FragmentActivity FrameView, View PreView){
        MainView = FrameView;
        ViewElement = PreView;
    }

    public void handleMessage(Message msg){
        /*取得資料*/
        if(msg.what == 0){
            ArrayList<HashMap<String, String>> RouteStopList = (ArrayList<HashMap<String, String>>) msg.obj;
            SetListView(RouteStopList);
        }
    }

    private void SetListView(ArrayList<HashMap<String, String>> StopList) {
        ArrayList<String> RouteNameList = new ArrayList<String>();
        ArrayList<String> RouteUIDList = new ArrayList<String>();
        /*資料分離*/
        for(int Run = 0; Run < StopList.size(); Run++){
            HashMap<String, String> Buffer = StopList.get(Run);
            /*取得站名清單*/
            RouteNameList.add(Buffer.get("StopName"));

            /*取得站位UID清單*/
            try {
                JSONObject StaObj = new JSONObject(Buffer.get("StopInfo"));
                RouteUIDList.add(StaObj.getString("StopUID"));
            }
            catch(Exception Err) {
                Err.printStackTrace();
            }
        }

        StationList = ViewElement.findViewById(R.id.RouteSta_List);
    }
}
