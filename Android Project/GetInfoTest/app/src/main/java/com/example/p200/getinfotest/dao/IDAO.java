package com.example.p200.getinfotest.dao;

import com.example.p200.getinfotest.dto.DTOArtstreetService;
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

    // 제주시 문화축제 정보
    @GET("rest/ArtstreetService/getArtstreetList")
    Call<DTOArtstreetService> getArtstreetService(@Query("startPage") String startPage,
                                                  @Query("pageSize") String pageSize,
                                                  @Query(value = "authApiKey",encoded=true) String authApiKey);


    public static final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://jstp.jejutour.go.kr/")
            .addConverterFactory( SimpleXmlConverterFactory.create())
            .build();

    public static final Retrofit retrofit2 = new Retrofit.Builder().baseUrl("http://210.99.248.79/")
            .addConverterFactory( SimpleXmlConverterFactory.create())
            .build();


}
