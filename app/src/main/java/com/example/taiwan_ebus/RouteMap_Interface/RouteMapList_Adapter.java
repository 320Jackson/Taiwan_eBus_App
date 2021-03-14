package com.example.taiwan_ebus.RouteMap_Interface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taiwan_ebus.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RouteMapList_Adapter extends BaseAdapter {
    /*取得layout*/
    private LayoutInflater ListLayout;

    /*取得所需資料*/
    private ArrayList<String> StopNameList;
    private ArrayList<String> StopUIDList;
    private ArrayList<String> ArrMinList;
    private ArrayList<String> BusList;

    /*避免重新加載*/
    static class AdapterHolder{
        LinearLayout Container;
        TextView ArrTime, StaName;
        Button BusNum;
    }

    /*建構子, 初始化取得資料*/
    public RouteMapList_Adapter(LayoutInflater inflater, ArrayList<String> StopList, ArrayList<String> StaUIDList, ArrayList<String> Bus){
        ListLayout = inflater;
        this.StopNameList = StopList;
        this.StopUIDList = StaUIDList;
        this.BusList = Bus;
    }

    @Override
    public int getCount() {
        return StopNameList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterHolder holder;
        /*避免重複加載*/
        if(convertView == null){
            holder = new AdapterHolder();
            convertView = ListLayout.inflate(R.layout.routelist_style, null);
            holder.ArrTime = convertView.findViewById(R.id.MinuteDisplay);
            holder.StaName = convertView.findViewById(R.id.StationName);
            holder.BusNum = convertView.findViewById(R.id.BusMoving);
            convertView.setTag(holder);
        }
        else{
            holder = (AdapterHolder) convertView.getTag();
        }

        /*置入資料*/
        holder.StaName.setText(StopNameList.get(position));
        holder.ArrTime.setText(StopUIDList.get(position));
        return convertView;
    }
}
