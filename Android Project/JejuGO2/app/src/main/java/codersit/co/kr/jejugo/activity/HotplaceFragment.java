package codersit.co.kr.jejugo.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dao.DAOGeoCode;
import codersit.co.kr.jejugo.dao.DAOJejuWifiVisitCountInfo;
import codersit.co.kr.jejugo.dto.DTOGeoCode;
import codersit.co.kr.jejugo.dto.DTOHotPlace;
import codersit.co.kr.jejugo.dto.DTOJejuWifiVisitCountInfo;
import codersit.co.kr.jejugo.util.JejuWifiDataManager;
import codersit.co.kr.jejugo.util.ICallback;
import codersit.co.kr.jejugo.util.NMapPOIflagType;
import codersit.co.kr.jejugo.util.NMapViewerResourceProvider;
import codersit.co.kr.jejugo.util.Util;

import static codersit.co.kr.jejugo.util.IKeyManager.NaverClientID;

/**
 * Created by P200 on 2017-06-04.
 */

public class HotplaceFragment extends Fragment {

    final String LOG = "THIS HOTPLACE FRAGMENT";

    private NMapContext mMapContext;
    NMapView mNMapView;
    NMapViewerResourceProvider mMapViewerResourceProvider;
    NMapOverlayManager mOverlayManager;
    int mMarkerId;
    NMapPOIdata poiData;

    String curQuery = null;

    final int RESULT_MAX_COUNT = 20;

    @Bind(R.id.tv_fragment_hotplace_title)
    TextView tv_fragment_hotplace_title;
    @Bind(R.id.tv_fragment_hotplace_tmptitle1)
    TextView tv_fragment_hotplace_tmptitle1;
    @Bind(R.id.tv_fragment_hotplace_tmptitle2)
    TextView tv_fragment_hotplace_tmptitle2;
    @Bind(R.id.ll_fragment_hotplace_for_bt_ll)
    LinearLayout ll_fragment_hotplace_for_bt_ll;

    void searchBrowser(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @OnClick(R.id.ll_fragment_hotplace_search_ll)
    void onClick_ll_fragment_hotplace_search_ll()
    {
        searchBrowser("https://search.naver.com/search.naver?ie=UTF-8&query="+curQuery);
    }

    @OnClick(R.id.ll_fragment_hotplace_blog_ll)
    void onClick_ll_fragment_hotplace_blog_ll()
    {
        searchBrowser("https://search.naver.com/search.naver?where=post&sm=tab_jum&ie=utf8&query="+curQuery);
    }

    @OnClick(R.id.ll_fragment_hotplace_image_ll)
    void onClick_ll_fragment_hotplace_image_ll()
    {
        searchBrowser("https://search.naver.com/search.naver?where=image&sm=tab_jum&ie=utf8&query="+curQuery);
    }

    @OnClick(R.id.ll_fragment_hotplace_news_ll)
    void onClick_ll_fragment_hotplace_news_ll()
    {
        searchBrowser("https://search.naver.com/search.naver?where=news&sm=tab_jum&ie=utf8&query="+curQuery);
    }

    @OnClick(R.id.ll_fragment_hotplace_place_ll)
    void onClick_ll_fragment_hotplace_place_ll()
    {
        searchBrowser("http://map.naver.com/index.nhn?query="+curQuery);
    }

    @OnClick(R.id.ll_fragment_hotplace_cafe_ll)
    void onClick_ll_fragment_hotplace_cafe_ll()
    {
        searchBrowser("https://search.naver.com/search.naver?where=article&sm=tab_jum&ie=utf8&query=" + curQuery);
    }

    public HotplaceFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_hotplace, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapContext =  new NMapContext(super.getActivity());
        mMapContext.onCreate();
    }

    private void initMap()
    {
//        DisplayService dd  = (DisplayService)getContext().getSystemService(Activity.DISPLAY_SERVICE);

        mNMapView = (NMapView)getView().findViewById(R.id.nmapview_frag_hotplace);
        mNMapView.setClientId(NaverClientID);
        mNMapView.setClickable(true);
        mNMapView.setEnabled(true);
        mNMapView.setFocusable(true);
        mNMapView.setFocusableInTouchMode(true);
        mNMapView.requestFocus();

        mMapContext.setupMapView(mNMapView);
        NMapController nMapController =  mNMapView.getMapController();
        NGeoPoint jejuGeoPoint = new NGeoPoint(126.57401159999995,33.439524);
        nMapController.setMapCenter(jejuGeoPoint,6);

//          맵회전(전체[세로]크기떄 사용하면 조을듯)
//        mNMapView.setAutoRotateEnabled(true,false);
//        mNMapView.setRotateAngle(90);

        mMapViewerResourceProvider = new NMapViewerResourceProvider(super.getActivity());

        mOverlayManager = new NMapOverlayManager(super.getActivity(), mNMapView, mMapViewerResourceProvider);
        mMarkerId = NMapPOIflagType.PIN;
        poiData = new NMapPOIdata(RESULT_MAX_COUNT, mMapViewerResourceProvider);

        poiData.beginPOIdata(JejuWifiDataManager.dtoHotPlaceArrayList.size());

        ArrayList<Double> tmpListForCheckCnt = new ArrayList<>();

        for(int i = 0 ; i < JejuWifiDataManager.dtoHotPlaceArrayList.size();i++ )
        {
            DTOHotPlace dtoHotPlace =  JejuWifiDataManager.dtoHotPlaceArrayList.get(i);

            boolean isFind=false;

            for(int j = 0 ; j < tmpListForCheckCnt.size();j++)
            {
                if(tmpListForCheckCnt.get(j)== Double.parseDouble( dtoHotPlace.getGpsX()) )
                {
                    isFind=true;
                    break;
                }
            }

            if(isFind == false)
                tmpListForCheckCnt.add( Double.parseDouble( dtoHotPlace.getGpsX() ) );

            poiData.addPOIitem( Double.parseDouble( dtoHotPlace.getGpsX() ) , Double.parseDouble( dtoHotPlace.getGpsY() ),dtoHotPlace.getPlaceName(),mMarkerId ,0 );
        }

        tv_fragment_hotplace_tmptitle1.setText("스마트관광플랫폼\n(http://jstp.jejutour.go.kr)\n에서 제공하는 제주도에서\n가장 많이 방문하는 "+
                tmpListForCheckCnt.size() +"개 지역입니다");

        poiData.endPOIdata();

// create POI data overlay
        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);

        poiDataOverlay.setOnStateChangeListener(onStateChangeListener);


//        poiDataOverlay.showAllPOIdata(0);
    }


    NMapPOIdataOverlay.OnStateChangeListener onStateChangeListener = new NMapPOIdataOverlay.OnStateChangeListener() {
        @Override
        public void onFocusChanged(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

            if (nMapPOIitem != null) {
//                Log.i(LOG, nMapPOIitem.getHeadText());
//                Log.i(LOG, nMapPOIitem.getSnippet());
//                Log.i(LOG, nMapPOIitem.getTailText());
//                Log.i(LOG, nMapPOIitem.getTitle() + " " + nMapPOIitem.getPoint().latitude + " " + nMapPOIitem.getPoint().longitude);

                if(curQuery==null)
                {
                    curQuery = nMapPOIitem.getTitle();

                    tv_fragment_hotplace_title.setText(curQuery);

                    ll_fragment_hotplace_for_bt_ll.setVisibility(View.VISIBLE);
                    tv_fragment_hotplace_title.setVisibility(View.VISIBLE);
                    tv_fragment_hotplace_tmptitle1.setVisibility(View.GONE);
                    tv_fragment_hotplace_tmptitle2.setVisibility(View.GONE);
                }

            } else {
                Log.i(LOG, "onFocusChanged: ");
            }
        }

        @Override
        public void onCalloutClick(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

            // Log.i(LOG, "onCalloutClick: " + nMapPOIitem.getTitle());
            // 말풍선 클릭시 호출되는 콜백

        }
    };




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initMap();
    }

    @Override
    public void onStart(){
        super.onStart();
        mMapContext.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapContext.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mMapContext.onPause();
    }
    @Override
    public void onStop() {
        mMapContext.onStop();
        super.onStop();
    }
    @Override
    public void onDestroy() {
        mMapContext.onDestroy();
        super.onDestroy();
    }


}
