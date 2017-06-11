package codersit.co.kr.jejugo.util;

import android.util.Log;

import java.util.ArrayList;

import codersit.co.kr.jejugo.dto.DTOPartnerStore;

/**
 * Created by BooHee on 2017-06-10.
 */

public class PartnerStoreManager {

    private String LOG = "PartnerStoreManager";

    public static ArrayList<DTOPartnerStore> dtoStoreList = new ArrayList<>();

    public static void InitData()
    {
        dtoStoreList.add(new DTOPartnerStore("자매국수","064-746-2222", "제주시 월랑로 4길" ));
        dtoStoreList.add(new DTOPartnerStore("영주말가든","064-748-4343", "제주시 선덕로5길 13" ));
        dtoStoreList.add(new DTOPartnerStore("제주흑돼지와전복","064-739-7171", "서귀포시 이어도로 193" ));
        dtoStoreList.add(new DTOPartnerStore("제주해풍횟집","064-762-1008", "서귀포시 칠십리로 41" ));
        dtoStoreList.add(new DTOPartnerStore("섭지바당","064-784-1888", "서귀포시 성산읍 섭지코지로26번길 31" ));
        dtoStoreList.add(new DTOPartnerStore("오설록티뮤지엄","064-794-5312", "서귀포시 안덕면 신화역사로 15 오설록" ));
        dtoStoreList.add(new DTOPartnerStore("제주에코랜드","064-802-8020", "제주시 번영로 1278-169" ));
        dtoStoreList.add(new DTOPartnerStore("제주아쿠아플라넷","064-780-0900", "서귀포시 성산읍 섭지코지로 95" ));
        dtoStoreList.add(new DTOPartnerStore("카멜리아힐","064-792-0088", "서귀포시 안덕면 병악로 166" ));
        dtoStoreList.add(new DTOPartnerStore("메이즈랜드","064-784-3838", "제주시 구좌읍 비자림로 2134-47 메이즈랜드" ));


    }
}
