package codersit.co.kr.jejugo.util;

import android.util.Log;

import java.util.ArrayList;

import codersit.co.kr.jejugo.dao.DAOGeoCode;
import codersit.co.kr.jejugo.dao.DAOJejuWifiVisitCountInfo;
import codersit.co.kr.jejugo.dto.DTOGeoCode;
import codersit.co.kr.jejugo.dto.DTOHotPlace;
import codersit.co.kr.jejugo.dto.DTOJejuWifiVisitCountInfo;

/**
 * Created by P200 on 2017-06-07.
 */

public class JejuWifiDataManager {

    private String LOG = "JejuWifiDataManager";

    public static ArrayList<DTOHotPlace> dtoHotPlaceArrayList = new ArrayList<>();

    public static void initData(){
        DAOJejuWifiVisitCountInfo daoJejuWifiVisitCountInfo = new DAOJejuWifiVisitCountInfo("20170101", Util.getCurrentDate(),"20","1");
        daoJejuWifiVisitCountInfo.setICallbackListener(iCallbackJejuWifiVisitCountInfo);

        daoJejuWifiVisitCountInfo.getData();
    }



    private static ICallback iCallbackJejuWifiVisitCountInfo = new ICallback() {
        @Override
        public void call(Object o) {
            DTOJejuWifiVisitCountInfo dtoJejuWifiVisitCountInfo = (DTOJejuWifiVisitCountInfo) o;


            for(int i = 0 ; i < dtoJejuWifiVisitCountInfo.getItems().size();i++)
            {

                String placeQueryString = dtoJejuWifiVisitCountInfo.getItems().get(i).getAddress();

//                Log.i(getClass().getName(), placeQueryString );

                DAOGeoCode daoGeoCode = new DAOGeoCode(placeQueryString);
                daoGeoCode.setICallbackListener(iCallbackGeoCode);

                daoGeoCode.getData();

            }
        }
    };

    private static  ICallback iCallbackGeoCode = new ICallback() {
        @Override
        public void call(Object o) {

            DTOGeoCode dtoGeoCode = (DTOGeoCode) o;

            if( o == null )
                return ;

            dtoHotPlaceArrayList.add(new DTOHotPlace(dtoGeoCode.getItem().get(0).getX() ,dtoGeoCode.getItem().get(0).getY(),dtoGeoCode.getItem().get(0).getAddress()) );

//            setMarker(dtoGeoCode.getItem().get(0).getX() ,dtoGeoCode.getItem().get(0).getY(),dtoGeoCode.getItem().get(0).getAddress());

//            poiData.addPOIitem(127.0630205, 37.5091300, "Pizza 777-111", mMarkerId, 0);

        }
    };







}
