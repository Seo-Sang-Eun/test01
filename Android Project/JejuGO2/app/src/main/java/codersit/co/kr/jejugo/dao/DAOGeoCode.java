package codersit.co.kr.jejugo.dao;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import codersit.co.kr.jejugo.dto.DTOGeoCode;
import codersit.co.kr.jejugo.dto.DTOJejuWifiVisitCountInfo;
import codersit.co.kr.jejugo.util.IKeyManager;
import retrofit2.Call;
import retrofit2.http.Header;

/**
 * Created by P200 on 2017-06-03.
 */

public class DAOGeoCode extends DAOClass {

    private String placeName;
    private String serviceKey1;
    private String serviceKey2;

    public DAOGeoCode(String placeName){
        this.placeName = placeName;

        this.serviceKey1 = IKeyManager.NaverClientID;
        this.serviceKey2 = IKeyManager.NaverClientSecret;
    }

    public void getData()
    {
        new AsyncTask<Object, Void, Object>() {
            @Override
            protected Object doInBackground(Object[] objects) {

                DTOGeoCode geoCode = null;

                Call<DTOGeoCode> call = IDAO.RetrofitForGeoCode.create(IDAO.class).getGeoCode(placeName, serviceKey1,serviceKey2);

                try {
                    geoCode = call.execute().body();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return geoCode;
            }

            @Override
            protected void onPostExecute(Object o) {

                iCallback.call(o);

                super.onPostExecute(o);
            }

        }.execute();

    }




}
