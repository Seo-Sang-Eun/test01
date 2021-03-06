package com.example.p200.getinfotest.dao;

import com.example.p200.getinfotest.dto.DTOArtcenterShowInfoService;
import com.example.p200.getinfotest.dto.DTOArtstreetService;
import com.example.p200.getinfotest.dto.DTOBestEating;
import com.example.p200.getinfotest.dto.DTOCultureEvent;
import com.example.p200.getinfotest.dto.DTOFestivalInquiryService;
import com.example.p200.getinfotest.dto.DTOJejuWifiVisitCountInfo;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by P200 on 2017-06-03.
 */

public interface IDAO {

    @GET("openapi/service/apiservice/JejuWifiVisitCountInfo.do")
    Call<DTOJejuWifiVisitCountInfo> getJejuWifiVisitCountInfo(@Query("start_date") String start_date,
                                                              @Query("end_date") String end_date,
                                                              @Query(value = "serviceKey",encoded=true) String serviceKey,
                                                              @Query("numOfRows") String numOfRows,
                                                              @Query("pageNo") String pageNo);

    // 제주시 문화예술거리 정보
    @GET("rest/ArtstreetService/getArtstreetList")
    Call<DTOArtstreetService> getArtstreetService(@Query("startPage") String startPage,
                                                  @Query("pageSize") String pageSize,
                                                  @Query(value = "authApiKey",encoded=true) String authApiKey);

    // 서귀포시 문화예술행사 정보
    @GET("openapi/service/rest/cultureEvent")
    Call<DTOCultureEvent> getCultureEvent(@Query(value = "serviceKey",encoded=true) String serviceKey);

    // 제주아트센터공연정보
    @GET("rest/ArtcenterShowInfoService/getArtcenterShowList")
    Call<DTOArtcenterShowInfoService> getArtcenterShowInfoService(@Query("startPage") String startPage,
                                                                  @Query(value = "serviceKey",encoded=true) String serviceKey,
                                                                  @Query("pageSize") String pageSize,
                                                                  @Query(value = "authApiKey",encoded=true) String authApiKey);

    // 제주시 축제/행사 정보
    @GET("rest/FestivalInquiryService/getFestivalList")
    Call<DTOFestivalInquiryService> getFestivalInquiryService(@Query("startPage") String startPage,
                                                              @Query("pageSize") String pageSize,
                                                              @Query(value = "authApiKey",encoded=true) String authApiKey);

    // 제주 도내의 모범음식점 정보
    @GET("rest/besteating/getEatingList") // default startpage, pagesize = 1, 10
    Call<DTOBestEating> getBestEating(@Query("startPage") String startPage,
                                      @Query("pageSize") String pageSize,
                                      @Query(value = "serviceKey",encoded=true) String serviceKey,
                                      @Query("dataTitle") String dataTitle);


    public static final Retrofit RetrofitForHotplace = new Retrofit.Builder().baseUrl("http://jstp.jejutour.go.kr/")
            .addConverterFactory( SimpleXmlConverterFactory.create())
            .build();

    public static final Retrofit RetrofitForJejuArtStreet = new Retrofit.Builder().baseUrl("http://210.99.248.79/")
            .addConverterFactory( SimpleXmlConverterFactory.create())
            .build();

    public static final Retrofit RetrofitForSeogwipoCultureEvent = new Retrofit.Builder().baseUrl("http://data.seogwipo.go.kr/")
            .addConverterFactory( SimpleXmlConverterFactory.create())
            .build();

    public static final Retrofit RetrofitForJeJuArtcenterShowInfoService = new Retrofit.Builder().baseUrl("http://210.99.248.79/")
            .addConverterFactory( SimpleXmlConverterFactory.create())
            .build();

    public static final Retrofit RetrofitForFestvalInquiryService = new Retrofit.Builder().baseUrl("http://210.99.248.79/")
            .addConverterFactory( SimpleXmlConverterFactory.create())
            .build();

    public static final Retrofit RetrofitForBestEating = new Retrofit.Builder().baseUrl("http://data.jeju.go.kr/")
            .addConverterFactory( SimpleXmlConverterFactory.create())
            .build();
}
