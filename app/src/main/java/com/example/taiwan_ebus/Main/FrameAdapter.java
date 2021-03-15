package com.example.taiwan_ebus.Main;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*ViewPager2 Adapter(適配器)*/
public class FrameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /*所有子視窗*/
    ArrayList<View> Pages;

    /*建構子*/
    public FrameAdapter(ArrayList<View> Input){
        Pages = Input;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*讀取目前畫面*/
        View Page = Pages.get(viewType);
        Page.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));/*設定畫面大小*/
        ViewHolder PageHolder = new ViewHolder(Page);
        return PageHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        /*頁面總數設定*/
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        /*傳送畫面類型*/
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
