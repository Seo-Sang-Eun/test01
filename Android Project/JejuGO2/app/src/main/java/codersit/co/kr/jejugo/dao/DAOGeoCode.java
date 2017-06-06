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

        this.serviceKey1 = IKeyManager.GeoCodeKey1;
        this.serviceKey2 = IKeyManager.GeoCodeKey2;
    }

    public void getData()
    {
        new AsyncTask<Object, Void, Object>() {
            @Override
            protected Object doInBackground(Object[] objects) {


                Map<String, String> map = new HashMap<>();
                map.put("User-Agent", "curl/7.43.0");
                map.put("Accept", "*/*");
                map.put("Content-Type", "text/xml;charset=utf-8");
                map.put("X-Naver-Client-Id", serviceKey1);
                map.put("X-Naver-Client-Secret", serviceKey2);

                Call<DTOGeoCode> call = IDAO.RetrofitForGeoCode.create(IDAO.class).getGeoCode(placeName, map);

                try {
                    DTOGeoCode geoCode = call.execute().body();

                    iCallback.call(geoCode);


                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute();

    }




}
