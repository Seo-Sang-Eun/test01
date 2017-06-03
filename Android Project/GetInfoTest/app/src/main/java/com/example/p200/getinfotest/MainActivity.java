package com.example.p200.getinfotest;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final String start_date = "20161201";
        final String end_date = "20161231";
        final String numOfRows = "10";
        final String pageNo = "1";

        DAOJejuWifiVisitCountInfo daoJejuWifiVisitCountInfo = new DAOJejuWifiVisitCountInfo(start_date,end_date,numOfRows,pageNo);
        daoJejuWifiVisitCountInfo.setICallbackListener(iCallback);

        daoJejuWifiVisitCountInfo.getData();


    }

    ICallback iCallback = new ICallback() {
        @Override
        public void call(Object o) {

        DTOJejuWifiVisitCountInfo dtoJejuWifiVisitCountInfo = (DTOJejuWifiVisitCountInfo)o;


        Log.i("THIS MainActivity", dtoJejuWifiVisitCountInfo.getNumOfRows());
        }
    };




}