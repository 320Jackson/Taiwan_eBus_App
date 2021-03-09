package com.example.taiwan_ebus.Main;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class UpdateFinished extends Handler {
    private Context Frame_Context;

    public UpdateFinished(Context InputContext){
        Frame_Context = InputContext;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        if(msg.what == 0){
            Toast.makeText(Frame_Context, "Update Finished", Toast.LENGTH_LONG).show();
        }
        else if(msg.what == 1){
            Toast.makeText(Frame_Context, "Newest Version", Toast.LENGTH_LONG).show();
        }
    }
}
