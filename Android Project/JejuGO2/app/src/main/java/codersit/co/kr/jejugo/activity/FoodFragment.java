package codersit.co.kr.jejugo.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dao.DAOGeoCode;
import codersit.co.kr.jejugo.dao.DAOJejuWifiVisitCountInfo;
import codersit.co.kr.jejugo.dto.DTOGeoCode;
import codersit.co.kr.jejugo.dto.DTOJejuWifiVisitCountInfo;
import codersit.co.kr.jejugo.util.ICallback;
import codersit.co.kr.jejugo.util.Util;

/**
 * Created by P200 on 2017-06-04.
 */

public class FoodFragment extends Fragment {

    String LOG ="FoodFragment";

    public FoodFragment() {

    }

    TextView tvFood;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View curView = inflater.inflate(R.layout.activity_fragment_food, container, false);

//        tvFood = (TextView)curView.findViewById(R.id.tv_food);
//
//
//        DAOJejuWifiVisitCountInfo daoJejuWifiVisitCountInfo = new DAOJejuWifiVisitCountInfo("20170101", Util.getCurrentDate(),"20","1");
//        daoJejuWifiVisitCountInfo.setICallbackListener(iCallbackJejuWifiVisitCountInfo);
//
//        daoJejuWifiVisitCountInfo.getData();

        return curView;

    }

//    ICallback iCallbackGeoCode = new ICallback() {
//        @Override
//        public void call(Object o) {
//
//            DTOGeoCode dtoGeoCode = (DTOGeoCode) o;
//
//            if(o==null)
//                return ;
//
//            Log.i(LOG, dtoGeoCode.getItem().get(0).getY() +" , " + dtoGeoCode.getItem().get(0).getX() );
//
//        }
//    };
//
//
//    ICallback iCallbackJejuWifiVisitCountInfo = new ICallback() {
//        @Override
//        public void call(Object o) {
//            DTOJejuWifiVisitCountInfo dtoJejuWifiVisitCountInfo = (DTOJejuWifiVisitCountInfo) o;
//
//
//
//            Log.i(LOG, dtoJejuWifiVisitCountInfo.getNumOfRows() );
//            tvFood.setText(dtoJejuWifiVisitCountInfo.getNumOfRows());
//
////            for(int i = 0 ; i < dtoJejuWifiVisitCountInfo.getItems().size();i++)
////            {
////                String placeQueryString = dtoJejuWifiVisitCountInfo.getItems().get(i).getAddress();
////
////                Log.i(LOG, placeQueryString );
////
////                DAOGeoCode daoGeoCode = new DAOGeoCode(placeQueryString);
////                daoGeoCode.setICallbackListener(iCallbackGeoCode);
////
////                daoGeoCode.getData();
////            }
//
//
//
//
//        }
//    };

}
