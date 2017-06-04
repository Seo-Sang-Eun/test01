package com.example.p200.getinfotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.p200.getinfotest.dao.DAOArtstreetService;
import com.example.p200.getinfotest.dao.DAOJejuWifiVisitCountInfo;
import com.example.p200.getinfotest.dto.DTOArtstreetService;
import com.example.p200.getinfotest.dto.DTOJejuWifiVisitCountInfo;
import com.example.p200.getinfotest.util.ICallback;

public class BooHeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boo_hee);

        final String startPage = "1";
        final String pageSize= "10";

        DAOArtstreetService daoArtstreetService = new DAOArtstreetService(startPage, pageSize);
        daoArtstreetService.setICallbackListener(iCallback);

        daoArtstreetService.getData();
    }
    ICallback iCallback = new ICallback() {
        @Override
        public void call(Object o) {
            DTOArtstreetService dtoArtstreetService = (DTOArtstreetService) o;
            Log.i("THIS MainActivity", dtoArtstreetService.getTotalCount());
        }
    };
}
