package codersit.co.kr.jejugo.dao;

import android.os.AsyncTask;


import java.io.IOException;

import codersit.co.kr.jejugo.dto.DTOArtstreetService;
import codersit.co.kr.jejugo.util.IKeyManager;
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
                DTOArtstreetService artstreetService = null;
                Call<DTOArtstreetService> call = IDAO.RetrofitForJejuArtStreet.create(IDAO.class).getArtstreetService(startPage, pageSize, authApiKey);
                try{
                    artstreetService = call.execute().body();
                } catch(IOException e) {
                    e.printStackTrace();
                }
                return artstreetService;
            }

            @Override
            protected void onPostExecute(Object o) {

                iCallback.call(o);

                super.onPostExecute(o);
            }

        }.execute();
    }
}
