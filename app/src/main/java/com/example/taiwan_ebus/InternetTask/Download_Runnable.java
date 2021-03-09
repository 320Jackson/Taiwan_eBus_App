package com.example.taiwan_ebus.InternetTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

public class Download_Runnable implements Runnable {
    private static String strURL, strMethod;
    public Download_Runnable(String URL, String Method){
        strURL = URL;
        strMethod = Method;
    }

    @Override
    public void run() {
        try{
            DownloadTask(strURL, strMethod);
        }
        catch(Exception Err){
            Err.printStackTrace();
        }
    }

    public static String DownloadTask(String URL, String Method) throws IOException {
        HttpsURLConnection WebConnection;
        if(Method == "POST"){
            WebConnection = Connection.getPOST(URL);
        }
        else {
            WebConnection = Connection.getGET(URL);
        }
        WebConnection.connect();

        String str_JsonContent = ReadData(WebConnection.getInputStream(), "UTF-8");
        WebConnection.disconnect();
        return str_JsonContent;
    }

    private static String ReadData(InputStream DataStream, String CharsetName) throws IOException {
        /*資料流*/
        InputStreamReader DataReader;
        try{
            DataReader = new InputStreamReader(new GZIPInputStream(DataStream), CharsetName);
        }
        catch(Exception Err){
            DataReader = new InputStreamReader(DataStream, CharsetName);
        }
        BufferedReader DataBuffer = new BufferedReader(DataReader);
        /*讀取資料*/
        String strBuffer = DataBuffer.readLine();
        String JsonStr = "";
        while(strBuffer != null){
            JsonStr += strBuffer;
            strBuffer = DataBuffer.readLine();
        }
        return JsonStr;
    }
}
