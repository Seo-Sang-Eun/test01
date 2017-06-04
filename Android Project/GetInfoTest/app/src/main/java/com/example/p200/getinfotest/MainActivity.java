package com.example.p200.getinfotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.p200.getinfotest.dao.DAOJejuWifiVisitCountInfo;
import com.example.p200.getinfotest.dto.DTOJejuWifiVisitCountInfo;
import com.example.p200.getinfotest.util.ICallback;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final String start_date = "20161201";
        final String end_date = "20161231";
        final String numOfRows = "10";
        final String pageNo = "1";

        DAOJejuWifiVisitCountInfo daoJejuWifiVisitCountInfo = new DAOJejuWifiVisitCountInfo(start_date,end_date,numOfRows,pageNo);
        daoJejuWifiVisitCountInfo.setICallbackListener(iCallback);

        daoJejuWifiVisitCountInfo.getData();


    }

    ICallback iCallback = new ICallback() {
        @Override
        public void call(Object o) {

    DTOJejuWifiVisitCountInfo dtoJejuWifiVisitCountInfo = (DTOJejuWifiVisitCountInfo)o;





        Log.i("THIS MainActivity", dtoJejuWifiVisitCountInfo.getNumOfRows());
}
    };




}