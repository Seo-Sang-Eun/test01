package codersit.co.kr.jejugo.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static codersit.co.kr.jejugo.util.IKeyManager.GeoCodeKey1;

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



    final int RESULT_MAX_COUNT = 20;

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
//        DisplayService dd  = (DisplayService)getContext().getSystemService(Activity.DISPLAY_SERVICE);

        mNMapView = (NMapView)getView().findViewById(R.id.nmapview_frag_hotplace);
        mNMapView.setClientId(GeoCodeKey1);
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


        for(int i = 0 ; i < JejuWifiDataManager.dtoHotPlaceArrayList.size();i++ )
        {
            DTOHotPlace dtoHotPlace =  JejuWifiDataManager.dtoHotPlaceArrayList.get(i);

            poiData.addPOIitem( Double.parseDouble( dtoHotPlace.getGpsX() ) , Double.parseDouble( dtoHotPlace.getGpsY() ),dtoHotPlace.getPlaceName(),mMarkerId ,0 );
        }


        poiData.endPOIdata();

// create POI data overlay
        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);

        poiDataOverlay.setOnStateChangeListener(onStateChangeListener);

//        poiDataOverlay.showAllPOIdata(0);

    }


    NMapPOIdataOverlay.OnStateChangeListener onStateChangeListener = new NMapPOIdataOverlay.OnStateChangeListener() {
        @Override
        public void onFocusChanged(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {
            Toast.makeText(getActivity(), "onCalloutClick: " + nMapPOIitem.getTitle(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCalloutClick(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

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

    public HotplaceFragment() {

    }

}
