package com.example.p200.getinfotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.p200.getinfotest.dao.DAOArtstreetService;
import com.example.p200.getinfotest.dao.DAOBestEating;
import com.example.p200.getinfotest.dao.DAOCultureEvent;
import com.example.p200.getinfotest.dao.DAOFestivalInquiryService;
import com.example.p200.getinfotest.dao.DAOJejuArtcenterShowInfoService;
import com.example.p200.getinfotest.dao.DAOJejuWifiVisitCountInfo;
import com.example.p200.getinfotest.dto.DTOArtcenterShowInfoService;
import com.example.p200.getinfotest.dto.DTOArtstreetService;
import com.example.p200.getinfotest.dto.DTOBestEating;
import com.example.p200.getinfotest.dto.DTOCultureEvent;
import com.example.p200.getinfotest.dto.DTOFestivalInquiryService;
import com.example.p200.getinfotest.dto.DTOJejuWifiVisitCountInfo;
import com.example.p200.getinfotest.util.ICallback;

public class BooHeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boo_hee);

        final String startPage = "1";
        final String pageSize = "10";
        final String dataTitle = "";

        DAOBestEating daoBestEating = new DAOBestEating(startPage, pageSize, dataTitle);
        daoBestEating.setICallbackListener(iCallback);

        daoBestEating.getData();
    }
    ICallback iCallback = new ICallback() {
        @Override
        public void call(Object o) {
            DTOBestEating dtoBestEating = (DTOBestEating) o;
            Log.i("THIS MainActivity", dtoBestEating.getTotalCount());
        }
    };
}
