package com.example.p200.getinfotest.dao;

import android.os.AsyncTask;

import com.example.p200.getinfotest.dto.DTOArtstreetService;
import com.example.p200.getinfotest.util.IKeyManager;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by BooHee on 2017-06-04.
 */

public class DAOArtstreetService extends DAOClass {

    private String startPage;
    private String pageSize;
    private String authApiKey;

    public DAOArtstreetService(String startPage, String pageSize)
    {
        this.startPage = startPage;
        this.pageSize = pageSize;
        this.authApiKey = IKeyManager.ArtstreetServiceKey;
    }

    public void getData()
    {
        new AsyncTask<Object, Void, Object>()
        {
            protected Object doInBackground(Object[] objects)
            {
                Call<DTOArtstreetService> call = IDAO.RetrofitForJejuArtStreet.create(IDAO.class).getArtstreetService(startPage, pageSize, authApiKey);
                try{
                    DTOArtstreetService artstreetService = call.execute().body();
                    iCallback.call(artstreetService);
                } catch(IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
