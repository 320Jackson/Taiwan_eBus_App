package com.example.taiwan_ebus.NetworkReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

    }

    private boolean IsConnected(Context Event){
        ConnectivityManager NetworkManager = (ConnectivityManager)Event.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network NetworkContent = NetworkManager.getActiveNetwork();

        if(NetworkContent != null){
            NetworkCapabilities Connectivity = NetworkManager.getNetworkCapabilities(NetworkContent);
            if(Connectivity != null){
                if(Connectivity.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true || Connectivity.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true || Connectivity.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true){
                    return true;
                }
            }
            else{
                return false;
            }
        }
        return false;
    }
}
