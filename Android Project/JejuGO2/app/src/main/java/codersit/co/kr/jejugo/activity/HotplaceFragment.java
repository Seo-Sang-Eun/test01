package codersit.co.kr.jejugo.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dao.DAOJejuWifiVisitCountInfo;
import codersit.co.kr.jejugo.dto.DTOJejuWifiVisitCountInfo;
import codersit.co.kr.jejugo.util.ICallback;
import codersit.co.kr.jejugo.util.NMapPOIflagType;
import codersit.co.kr.jejugo.util.NMapViewerResourceProvider;
import codersit.co.kr.jejugo.util.Util;

/**
 * Created by P200 on 2017-06-04.
 */

public class HotplaceFragment extends Fragment {

    final String LOG = "THIS HOTPLACE FRAGMENT";

    private NMapContext mMapContext;
    private static final String CLIENT_ID = "2iFv15YZ5WyF7Frk9_Ui";// 애플리케이션 클라이언트 아이디 값

    NMapView mNMapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {









        return inflater.inflate(R.layout.activity_fragment_hotplace, container, false);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapContext =  new NMapContext(super.getActivity());
        mMapContext.onCreate();
    }



    private void initMap()
    {
        mNMapView = (NMapView)getView().findViewById(R.id.nmapview_frag_hotplace);
        mNMapView.setClientId(CLIENT_ID);
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



    }

    private void setMarker()
    {
        NMapViewerResourceProvider mMapViewerResourceProvider = new NMapViewerResourceProvider(super.getActivity());

        NMapOverlayManager mOverlayManager = new NMapOverlayManager(super.getActivity(), mNMapView, mMapViewerResourceProvider);

        int markerId = NMapPOIflagType.PIN;

// set POI data
        NMapPOIdata poiData = new NMapPOIdata(2, mMapViewerResourceProvider);
        poiData.beginPOIdata(2);
        poiData.addPOIitem(127.0630205, 37.5091300, "Pizza 777-111", markerId, 0);
        poiData.addPOIitem(127.061, 37.51, "Pizza 123-456", markerId, 0);
        poiData.endPOIdata();

// create POI data overlay
        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);

        poiDataOverlay.showAllPOIdata(0);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initMap();

        setMarker();

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
    public void onDestroyView() {
        super.onDestroyView();
    }
    @Override
    public void onDestroy() {
        mMapContext.onDestroy();
        super.onDestroy();
    }

    public HotplaceFragment() {

    }


    // 원래 메인꺼
//        String start_date = "20161201";
//        String end_date = "20161231";
//        String numOfRows = "10";
//        String pageNo = "1";
//        DAOJejuWifiVisitCountInfo daoJejuWifiVisitCountInfo = new DAOJejuWifiVisitCountInfo(start_date,end_date,numOfRows,pageNo);
//        daoJejuWifiVisitCountInfo.setICallbackListener(iCallback);
//        daoJejuWifiVisitCountInfo.getData();

//    ICallback iCallback = new ICallback() {
//        @Override
//        public void call(Object o) {
//
//            DTOJejuWifiVisitCountInfo dtoJejuWifiVisitCountInfo = (DTOJejuWifiVisitCountInfo)o;
//
//            Log.i(LOG, dtoJejuWifiVisitCountInfo.getNumOfRows());
//        }
//    };


}
