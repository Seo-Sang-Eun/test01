package codersit.co.kr.jejugo.activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nhn.android.maps.NMapCompassManager;
import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

import butterknife.ButterKnife;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dto.DTOStampPlace;
import codersit.co.kr.jejugo.util.JejuWifiDataManager;
import codersit.co.kr.jejugo.util.NMapPOIflagType;
import codersit.co.kr.jejugo.util.NMapViewerResourceProvider;
import codersit.co.kr.jejugo.util.StampDataManager;

import static codersit.co.kr.jejugo.util.IKeyManager.NaverClientID;
import static codersit.co.kr.jejugo.util.StampDataManager.dtoStampPlaceArrayList;

/**
 * Created by P200 on 2017-06-04.
 */

public class StampGetFragment extends Fragment {


    final String LOG = "StampFragment";

    private NMapContext mMapContext;
    NMapView mNMapView;
    NMapViewerResourceProvider mMapViewerResourceProvider;
    NMapOverlayManager mOverlayManager;
    int mMarkerId;
    NMapPOIdata poiData;

    NMapLocationManager mMapLocationManager;
    NMapCompassManager mMapCompassManager;
    NMapMyLocationOverlay mMyLocationOverlay;
//    MapContainerView  mMapContainerView;

    final int RESULT_MAX_COUNT = 20;




    public StampGetFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_stamp_get, container, false);
        ButterKnife.bind(this,view);





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

        mNMapView = (NMapView)getView().findViewById(R.id.nmapview_frag_stamp_get);
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

        poiData.beginPOIdata(dtoStampPlaceArrayList.size());


        for(int i = 0 ; i < dtoStampPlaceArrayList.size();i++ )
        {
            DTOStampPlace tmpDtoStampPlace = dtoStampPlaceArrayList.get(i);
            poiData.addPOIitem( Double.parseDouble( tmpDtoStampPlace.getGpsX() ) , Double.parseDouble( tmpDtoStampPlace.getGpsY() ),tmpDtoStampPlace.getPlaceName(),mMarkerId ,0 );
        }

        poiData.endPOIdata();

// create POI data overlay
        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);

        poiDataOverlay.setOnStateChangeListener(onStateChangeListener);


//        poiDataOverlay.showAllPOIdata(0);

        mMapLocationManager = new NMapLocationManager(super.getActivity());
        mMapLocationManager.setOnLocationChangeListener(onLocationChangeListener);

        mMapCompassManager = new NMapCompassManager(super.getActivity());
        mMyLocationOverlay = mOverlayManager.createMyLocationOverlay(mMapLocationManager, mMapCompassManager);



    }

    NMapLocationManager.OnLocationChangeListener onLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {
        @Override
        public boolean onLocationChanged(NMapLocationManager nMapLocationManager, NGeoPoint nGeoPoint) {
            return false;
        }

        @Override
        public void onLocationUpdateTimeout(NMapLocationManager nMapLocationManager) {

        }

        @Override
        public void onLocationUnavailableArea(NMapLocationManager nMapLocationManager, NGeoPoint nGeoPoint) {

        }
    };





    NMapPOIdataOverlay.OnStateChangeListener onStateChangeListener = new NMapPOIdataOverlay.OnStateChangeListener() {
        @Override
        public void onFocusChanged(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

            if (nMapPOIitem != null) {
//                Log.i(LOG, nMapPOIitem.getHeadText());
//                Log.i(LOG, nMapPOIitem.getSnippet());
//                Log.i(LOG, nMapPOIitem.getTailText());
//                Log.i(LOG, nMapPOIitem.getTitle() + " " + nMapPOIitem.getPoint().latitude + " " + nMapPOIitem.getPoint().longitude);


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



    private void startMyLocation() {

        if (mMyLocationOverlay != null) {
            if (!mOverlayManager.hasOverlay(mMyLocationOverlay)) {
                mOverlayManager.addOverlay(mMyLocationOverlay);
            }

            if (mMapLocationManager.isMyLocationEnabled()) {

                if (!mNMapView.isAutoRotateEnabled()) {
                    mMyLocationOverlay.setCompassHeadingVisible(true);

                    mMapCompassManager.enableCompass();

                    mNMapView.setAutoRotateEnabled(true, false);

//                    mMapContainerView.requestLayout();
                    mNMapView.requestLayout();
                } else {
                    stopMyLocation();
                }

                mNMapView.postInvalidate();
            } else {
                boolean isMyLocationEnabled = mMapLocationManager.enableMyLocation(true);
                if (!isMyLocationEnabled) {

                    Intent goToSettings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(goToSettings);

                    return;
                }
            }
        }
    }

    private void stopMyLocation() {
        if (mMyLocationOverlay != null) {
            mMapLocationManager.disableMyLocation();

            if (mNMapView.isAutoRotateEnabled()) {
                mMyLocationOverlay.setCompassHeadingVisible(false);

                mMapCompassManager.disableCompass();

                mNMapView.setAutoRotateEnabled(false, false);

                mNMapView.requestLayout();
            }
        }
    }







    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StampDataManager.initData();
        initMap();


        startMyLocation();

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
