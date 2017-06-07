package com.example.p200.getinfotest.dao;

import android.os.AsyncTask;

import com.example.p200.getinfotest.dto.DTOJejuWifiVisitCountInfo;
import com.example.p200.getinfotest.util.IKeyManager;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by P200 on 2017-06-03.
 */

public class DAOJejuWifiVisitCountInfo extends DAOClass {

    private String start_date;
    private String end_date;
    private String serviceKey;
    private String numOfRows;
    private String pageNo;

    public DAOJejuWifiVisitCountInfo(String start_date, String end_date, String numOfRows, String pageNo) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.numOfRows = numOfRows;
        this.pageNo = pageNo;

        this.serviceKey = IKeyManager.JejuWifiVisitCountInfoKey;
    }

    public void getData()
    {
        new AsyncTask<Object, Void, Object>() {
            @Override
            protected Object doInBackground(Object[] objects) {

                Call<DTOJejuWifiVisitCountInfo> call = IDAO.RetrofitForHotplace.create(IDAO.class).getJejuWifiVisitCountInfo(start_date, end_date, serviceKey, numOfRows, pageNo);

                try {
                    DTOJejuWifiVisitCountInfo jejuWifiVisitCountInfo = call.execute().body();

                    iCallback.call(jejuWifiVisitCountInfo);


                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute();

    }




}
