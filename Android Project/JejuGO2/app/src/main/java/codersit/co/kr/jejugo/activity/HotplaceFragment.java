package codersit.co.kr.jejugo.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dao.DAOJejuWifiVisitCountInfo;
import codersit.co.kr.jejugo.dto.DTOJejuWifiVisitCountInfo;
import codersit.co.kr.jejugo.util.ICallback;
import codersit.co.kr.jejugo.util.Util;

/**
 * Created by P200 on 2017-06-04.
 */

public class HotplaceFragment extends Fragment {

    final String LOG = "THIS HOTPLACE FRAGMENT";

    public HotplaceFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.i(LOG, Util.getCurrentDate("yyyy-HH-mm"));

        String start_date = "20161201";
        String end_date = "20161231";
        String numOfRows = "10";
        String pageNo = "1";

        DAOJejuWifiVisitCountInfo daoJejuWifiVisitCountInfo = new DAOJejuWifiVisitCountInfo(start_date,end_date,numOfRows,pageNo);
        daoJejuWifiVisitCountInfo.setICallbackListener(iCallback);

        daoJejuWifiVisitCountInfo.getData();

        return inflater.inflate(R.layout.activity_fragment_hotplace, container, false);
    }


    ICallback iCallback = new ICallback() {
        @Override
        public void call(Object o) {

            DTOJejuWifiVisitCountInfo dtoJejuWifiVisitCountInfo = (DTOJejuWifiVisitCountInfo)o;

            Log.i(LOG, dtoJejuWifiVisitCountInfo.getNumOfRows());
        }
    };


}
