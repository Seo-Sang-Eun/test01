package com.example.p200.getinfotest.dao;

import android.os.AsyncTask;

import com.example.p200.getinfotest.dto.DTOArtstreetService;
import com.example.p200.getinfotest.dto.DTOBestEating;
import com.example.p200.getinfotest.util.IKeyManager;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by BooHee on 2017-06-06.
 */

public class DAOBestEating extends DAOClass {
    private String startPage;
    private String pageSize;
    private String serviceKey;
    private String dataTitle;

    public DAOBestEating(String startPage, String pageSize, String dataTitle)
    {
        this.startPage = startPage;
        this.pageSize = pageSize;
        this.dataTitle = dataTitle;
        this.serviceKey = IKeyManager.BesteatingKey;
    }

    public void getData()
    {
        new AsyncTask<Object, Void, Object>()
        {
            protected Object doInBackground(Object[] objects)
            {
                Call<DTOBestEating> call = IDAO.RetrofitForBestEating.create(IDAO.class).getBestEating(startPage, pageSize, serviceKey, dataTitle);
                try{
                    DTOBestEating bestEating = call.execute().body();
                    iCallback.call(bestEating);
                } catch(IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
