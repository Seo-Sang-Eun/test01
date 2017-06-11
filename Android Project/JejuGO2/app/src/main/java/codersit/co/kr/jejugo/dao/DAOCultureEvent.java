package codersit.co.kr.jejugo.dao;

import android.os.AsyncTask;
import android.util.Log;


import java.io.IOException;

import codersit.co.kr.jejugo.dto.DTOCultureEvent;
import codersit.co.kr.jejugo.util.IKeyManager;

import retrofit2.Call;

/**
 * Created by BooHee on 2017-06-05.
 */

public class DAOCultureEvent extends DAOClass {

    private String serviceKey;

    public DAOCultureEvent()
    {
        this.serviceKey = IKeyManager.CultureEventKey;
    }

    public void getData()
    {
        new AsyncTask<Object, Void, Object>()
        {
            protected Object doInBackground(Object[] objects)
            {
                DTOCultureEvent cultureEvent =null;

                Call<DTOCultureEvent> call = IDAO.RetrofitForSeogwipoCultureEvent.create(IDAO.class).getCultureEvent(serviceKey);
                try{
                     cultureEvent = call.execute().body();
                } catch(IOException e) {
                    e.printStackTrace();
                }
                return cultureEvent;
            }

            @Override
            protected void onPostExecute(Object o) {

                iCallback.call(o);

                super.onPostExecute(o);
            }

        }.execute();
    }
}
