package com.example.p200.getinfotest.dao;

import android.os.AsyncTask;

import com.example.p200.getinfotest.dto.DTOArtcenterShowInfoService;
import com.example.p200.getinfotest.dto.DTOArtstreetService;
import com.example.p200.getinfotest.util.IKeyManager;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by BooHee on 2017-06-05.
 */

public class DAOJejuArtcenterShowInfoService extends DAOClass{
    private String startPage;
    private String pageSize;
    private String authApiKey;
    private String serviceKey;

    public DAOJejuArtcenterShowInfoService(String startPage, String pageSize)
    {
        this.startPage = startPage;
        this.pageSize = pageSize;
        this.authApiKey = IKeyManager.ArtcenterShowInfoServiceKey;
        this.serviceKey = IKeyManager.ArtcenterShowInfoServiceKey;
    }

    public void getData()
    {
        new AsyncTask<Object, Void, Object>()
        {
            protected Object doInBackground(Object[] objects)
            {
                Call<DTOArtcenterShowInfoService> call = IDAO.RetrofitForJeJuArtcenterShowInfoService.create(IDAO.class).getArtcenterShowInfoService(startPage, serviceKey, pageSize, authApiKey);
                try{
                    DTOArtcenterShowInfoService artcenterShowInfoService = call.execute().body();
                    iCallback.call(artcenterShowInfoService);
                } catch(IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
