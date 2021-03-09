package com.example.taiwan_ebus.InternetTask;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Connection {
    public static HttpsURLConnection getPOST(String strURL) throws IOException {
        URL API_URL = new URL(strURL);
        HttpsURLConnection WebConnection = (HttpsURLConnection)API_URL.openConnection();
        /*設定等候時間*/
        WebConnection.setConnectTimeout(15000);
        WebConnection.setReadTimeout(10000);
        /*設定請求*/
        WebConnection.setRequestMethod("POST");
        WebConnection.setDoInput(true);
        return WebConnection;
    }

    public static HttpsURLConnection getGET(String strURL) throws IOException {
        URL API_URL = new URL(strURL);
        HttpsURLConnection WebConnection = (HttpsURLConnection)API_URL.openConnection();
        /*設定等候時間*/
        WebConnection.setConnectTimeout(15000);
        WebConnection.setReadTimeout(10000);
        /*設定請求*/
        WebConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        WebConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        WebConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
        WebConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        WebConnection.setRequestProperty("Accept-Encoding", "gzip,deflate");
        WebConnection.setRequestMethod("GET");
        WebConnection.setDoInput(true);
        return WebConnection;
    }
}
