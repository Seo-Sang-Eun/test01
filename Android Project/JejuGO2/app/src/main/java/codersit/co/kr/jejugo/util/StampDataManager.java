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

        dtoStampPlaceArrayList.add(new DTOStampPlace(0,"126.5118814","33.5161459","제주 용두암",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(1,"126.46859300000006","33.508095","제주 도두봉",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(2,"126.34555399999999","33.4534765","애월 더럭분교",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(3,"126.289399","33.306118","오설록 티뮤지엄",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(4,"126.554414","33.246987","천지연 폭포",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(5,"126.929348","33.423586","섭지코지",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(6,"126.938460","33.459989","성산 일출봉",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(7,"126.368878","33.297256","카멜리아힐",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(8,"126.618862","33.384746","성판악",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(9,"126.943252","33.502770","우도 서빈백사",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(10,"126.771431","33.528209","만장굴",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(11,"126.267142","33.120871","마라도",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(12,"126.796260","33.555948","월정리해변",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(13,"126.623703","33.251890","쇠소깍",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(14,"126.759823","33.558220","김녕성세기해변",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(14,"126.837535","33.327854","표선해비치해변",false));
        dtoStampPlaceArrayList.add(new DTOStampPlace(14,"126.799614","33.386621","성읍민속마을",false));














    }








}
