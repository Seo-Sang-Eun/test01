package codersit.co.kr.jejugo.dao;

import android.os.AsyncTask;

import codersit.co.kr.jejugo.dto.DTOArtcenterShowInfoService;
import codersit.co.kr.jejugo.dto.DTOArtcenterShowInfoService_List;
import codersit.co.kr.jejugo.util.IKeyManager;

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
            {DTOArtcenterShowInfoService artcenterShowInfoService =null;
                Call<DTOArtcenterShowInfoService> call = IDAO.RetrofitForJeJuArtcenterShowInfoService.create(IDAO.class).getArtcenterShowInfoService(startPage, serviceKey, pageSize, authApiKey);
                try{
                    artcenterShowInfoService = call.execute().body();
                } catch(IOException e) {
                    e.printStackTrace();
                }
                return artcenterShowInfoService;
            }


            @Override
            protected void onPostExecute(Object o) {

                iCallback.call(o);

                super.onPostExecute(o);
            }

        }.execute();
    }
}
