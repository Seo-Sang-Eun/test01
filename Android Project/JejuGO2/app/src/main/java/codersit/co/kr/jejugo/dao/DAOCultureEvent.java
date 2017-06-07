package codersit.co.kr.jejugo.dao;

import android.os.AsyncTask;



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
                Call<DTOCultureEvent> call = IDAO.RetrofitForSeogwipoCultureEvent.create(IDAO.class).getCultureEvent(serviceKey);
                try{
                    DTOCultureEvent cultureEvent = call.execute().body();
                    iCallback.call(cultureEvent);
                } catch(IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
