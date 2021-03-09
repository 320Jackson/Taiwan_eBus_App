package com.example.taiwan_ebus.RouteMap_Interface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class RouteMapList_Adapter extends BaseAdapter {
    /*取得layout*/
    private LayoutInflater ListLayout;

    /*取得所需資料*/
    private ArrayList<String> StopNameList;
    private ArrayList<String> MinuteList;
    private ArrayList<String> BusList;

    /*建構子*/
    public RouteMapList_Adapter(){

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
