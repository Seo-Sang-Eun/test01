package com.example.p200.getinfotest.dao;

import android.os.AsyncTask;

import com.example.p200.getinfotest.dto.DTOArtstreetService;
import com.example.p200.getinfotest.dto.DTOFestivalInquiryService;
import com.example.p200.getinfotest.util.IKeyManager;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by BooHee on 2017-06-05.
 */

public class DAOFestivalInquiryService extends DAOClass {

    private String startPage;
    private String pageSize;
    private String authApiKey;

    public DAOFestivalInquiryService(String startPage, String pageSize)
    {
        this.startPage = startPage;
        this.pageSize = pageSize;
        this.authApiKey = IKeyManager.FestivalInquiryServiceKey;
    }

    public void getData()
    {
        new AsyncTask<Object, Void, Object>()
        {
            protected Object doInBackground(Object[] objects)
            {
                Call<DTOFestivalInquiryService> call = IDAO.RetrofitForFestvalInquiryService.create(IDAO.class).getFestivalInquiryService(startPage, pageSize, authApiKey);
                try{
                    DTOFestivalInquiryService festivalInquiryService = call.execute().body();
                    iCallback.call(festivalInquiryService);
                } catch(IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
