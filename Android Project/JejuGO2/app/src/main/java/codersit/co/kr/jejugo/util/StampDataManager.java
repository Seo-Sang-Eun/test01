package codersit.co.kr.jejugo.util;

import java.util.ArrayList;

import codersit.co.kr.jejugo.dao.DAOGeoCode;
import codersit.co.kr.jejugo.dao.DAOJejuWifiVisitCountInfo;
import codersit.co.kr.jejugo.dto.DTOGeoCode;
import codersit.co.kr.jejugo.dto.DTOHotPlace;
import codersit.co.kr.jejugo.dto.DTOJejuWifiVisitCountInfo;
import codersit.co.kr.jejugo.dto.DTOStampPlace;

/**
 * Created by P200 on 2017-06-07.
 */

public class StampDataManager {

    private String LOG = "StampDataManager";

    public static ArrayList<DTOStampPlace> dtoStampPlaceArrayList = new ArrayList<>();

    public static void initData(){

        dtoStampPlaceArrayList.add(new DTOStampPlace(0,"126.5118814","33.5161459","제주 용두암"));
        dtoStampPlaceArrayList.add(new DTOStampPlace(1,"126.46859300000006","33.508095","제주 도두봉"));
        dtoStampPlaceArrayList.add(new DTOStampPlace(2,"126.34555399999999","33.4534765","애월 더럭분교"));













    }








}
