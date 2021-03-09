package com.example.taiwan_ebus.Route;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

import com.example.taiwan_ebus.R;

public class RouteSearch_Interface {
    private View Interface;/*畫面控制*/
    private Activity MainInterface;

    /*建構子，讀取畫面*/
    public RouteSearch_Interface(View Input, Activity InputAct){
        Interface = Input;
        MainInterface = InputAct;
        Start();
    }

    /*介面元件*/
    private EditText RouteInput;

    public void Start(){
        /*設定搜尋框*/
        RouteInput = Interface.findViewById(R.id.Route_Text);
        SearchListener.MainInterface = MainInterface;
        SearchListener.MainContext = Interface;
        RouteInput.addTextChangedListener(SearchListener.TextChange);
    }
}
