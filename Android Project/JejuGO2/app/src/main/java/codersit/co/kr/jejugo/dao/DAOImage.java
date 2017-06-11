package codersit.co.kr.jejugo.dao;

import android.os.AsyncTask;

import java.io.IOException;

import codersit.co.kr.jejugo.dto.DTOGeoCode;
import codersit.co.kr.jejugo.dto.DTOImage;
import codersit.co.kr.jejugo.util.IKeyManager;
import retrofit2.Call;

/**
 * Created by P200 on 2017-06-03.
 */

public class DAOImage extends DAOClass {

    private String searchQuery;
    private String serviceKey1;
    private String serviceKey2;
    public DAOImage(String searchQuery){

        this.searchQuery = searchQuery;

        this.serviceKey1 = IKeyManager.NaverClientID;
        this.serviceKey2 = IKeyManager.NaverClientSecret;
    }
//17.6.8 12시 에러나서 주석처리.
    public void getData()
    {
     new AsyncTask<Object, Void, Object>() {
            @Override
            protected Object doInBackground(Object[] objects) {

                DTOImage geoCode = null;

                Call<DTOImage> call = IDAO.RetrofitForImage.create(IDAO.class).getImage(searchQuery, "1","medium",serviceKey1,serviceKey2);

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
