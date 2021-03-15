package com.example.taiwan_ebus.RouteMap_Interface;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import androidx.room.Update;

import com.example.taiwan_ebus.R;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateList {
    /*元件*/
    private ListView InfoList;
    private RouteMapList_Adapter ListAdapter;

    /*Singleton Mode*/
    private static UpdateList Instance;
    private UpdateList(){};

    public static UpdateList getInstance(){
        if(Instance == null){
            Instance = new UpdateList();
        }
        return Instance;
    }

    /*初始化元件*/
    public void Initialization(View ContainerView, LayoutInflater inflater){
        InfoList = ContainerView.findViewById(R.id.RouteSta_List);
        ListAdapter = new RouteMapList_Adapter(inflater);
        InfoList.setAdapter(ListAdapter);
    }

    /*更新內容*/
    public void UpdateStation(ArrayList<String> StopList, ArrayList<String> StopUIDList){
        if(ListAdapter != null){
            ListAdapter.UpdateStation(StopList, StopUIDList);
            ListAdapter.notifyDataSetChanged();
        }
    }

    public void UpdateArrTime(HashMap<String, String> ArrTimeList){
        if(ListAdapter != null){
            ListAdapter.UpdateArrTime(ArrTimeList);
            ListAdapter.notifyDataSetChanged();
        }
    }
}
