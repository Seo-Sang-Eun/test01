package codersit.co.kr.jejugo.dao;

import android.os.AsyncTask;

import java.io.IOException;

import codersit.co.kr.jejugo.dto.DTOJejuWifiVisitCountInfo;
import codersit.co.kr.jejugo.util.IKeyManager;
import retrofit2.Call;

/**
 * Created by P200 on 2017-06-03.
 */

public class DAOJejuWifiVisitCountInfo extends DAOClass {

    private String start_date;
    private String end_date;
    private String serviceKey;
    private String numOfRows;
    private String pageNo;

    public DAOJejuWifiVisitCountInfo(String start_date, String end_date, String numOfRows, String pageNo) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.numOfRows = numOfRows;
        this.pageNo = pageNo;

        this.serviceKey = IKeyManager.JejuWifiVisitCountInfoKey;
    }

    public void getData()
    {
        new AsyncTask<Object, Void, Object>() {
            @Override
            protected Object doInBackground(Object[] objects) {

                DTOJejuWifiVisitCountInfo jejuWifiVisitCountInfo = null;

                Call<DTOJejuWifiVisitCountInfo> call = IDAO.RetrofitForHotplace.create(IDAO.class).getJejuWifiVisitCountInfo(start_date, end_date, serviceKey, numOfRows, pageNo);

                try {
                    jejuWifiVisitCountInfo = call.execute().body();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return jejuWifiVisitCountInfo;
            }

            @Override
            protected void onPostExecute(Object o) {

                iCallback.call(o);

                super.onPostExecute(o);
            }
        }.execute();

    }




}
