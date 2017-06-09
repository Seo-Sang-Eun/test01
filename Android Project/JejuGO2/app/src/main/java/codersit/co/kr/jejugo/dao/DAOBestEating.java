package codersit.co.kr.jejugo.dao;

import android.os.AsyncTask;

import java.io.IOException;

import codersit.co.kr.jejugo.dto.DTOBestEating;
import codersit.co.kr.jejugo.util.IKeyManager;
import retrofit2.Call;

/**
 * Created by BooHee on 2017-06-09.
 */

public class DAOBestEating extends DAOClass {
    private String startPage;
    private String pageSize;
    private String serviceKey;
    private String dataTitle;

    public DAOBestEating(String startPage, String pageSize)
    {
        this.startPage = startPage;
        this.pageSize = pageSize;
        this.serviceKey = IKeyManager.BesteatingKey;
    }

    public void getData()
    {
        new AsyncTask<Object, Void, Object>()
        {
            protected Object doInBackground(Object[] objects)
            {
                DTOBestEating bestEating = null;
                Call<DTOBestEating> call = IDAO.RetrofitForBestEating.create(IDAO.class).getBestEating(serviceKey, startPage, pageSize);
                try{
                    bestEating = call.execute().body();
                } catch(IOException e) {
                    e.printStackTrace();
                }
                return bestEating;
            }

            @Override
            protected void onPostExecute(Object o) {

                iCallback.call(o);

                super.onPostExecute(o);
            }

        }.execute();
    }
}
