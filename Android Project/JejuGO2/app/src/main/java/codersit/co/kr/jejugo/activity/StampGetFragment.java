package codersit.co.kr.jejugo.activity;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dto.DTOStampPlace;
import codersit.co.kr.jejugo.util.JejuWifiDataManager;
import codersit.co.kr.jejugo.util.NMapPOIflagType;
import codersit.co.kr.jejugo.util.NMapViewerResourceProvider;
import codersit.co.kr.jejugo.util.SaveDataManager;
import codersit.co.kr.jejugo.util.StampDataManager;
import codersit.co.kr.jejugo.util.Util;

import static codersit.co.kr.jejugo.util.IKeyManager.NaverClientID;
import static codersit.co.kr.jejugo.util.StampDataManager.dtoStampPlaceArrayList;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * Created by P200 on 2017-06-04.
 */

public class StampGetFragment extends Fragment {


    final String LOG = "StampGetFragment";

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

    final double GPS_INTERVAL_FOR_CALC = 0.01;//0.0025;

    int mCurArrayPos;
    String mCurPlace;
    Boolean isEnable;


    @Bind(R.id.tv_fragment_stam_get_place)
    TextView tv_fragment_stam_get_place;

    @Bind(R.id.iv_fragment_stam_get_stamp)
    ImageView iv_fragment_stam_get_stamp;

    @OnClick(R.id.iv_fragment_stam_get_stamp)
    void OnClickIv_fragment_stam_get_stamp()
    {
        if(isEnable==false)
            return;

//        mCurArrayPos
//        mCurPlace
        SaveDataManager saveDataManager = new SaveDataManager(getActivity().getApplicationContext());

        String tmpStr = "stamp";

        if( mCurArrayPos <10)
            tmpStr += "0" + mCurArrayPos;
        else
            tmpStr +=  "" + mCurArrayPos;

        saveDataManager.putData(tmpStr,"true");

        int tmpInt = Integer.parseInt( saveDataManager.getData("stampCount") );
        tmpInt++;
        saveDataManager.putData("stampCount",tmpInt+"");

        String tmpRstMsg = "";

        if(tmpInt <= 2)
        {
            tmpRstMsg = "3개 중 "+ tmpInt +"개 스템프 획득!";
        }
        else if (tmpInt == 3)
        {
            tmpRstMsg = "스템프 3개 획득!\n제휴 5% 할인쿠폰 획득";

            saveDataManager.putData("stampInfo1", Util.getCurrentDate("yyyy-MM-dd"));
            saveDataManager.putData("stampInfo2", Util.getCurrentDate("yyyy-MM-dd"));
            saveDataManager.putData("stampInfo3", Util.getCurrentDate("yyyy-MM-dd"));


        }
        else if(tmpInt <= 5)
        {
            tmpRstMsg = "6개 중 "+ tmpInt +"개 스템프 획득!";
        }
        else if (tmpInt == 6)
        {
            tmpRstMsg = "스템프 6개 획득!\n제휴 10% 할인쿠폰 획득";
            saveDataManager.putData("stampInfo4", Util.getCurrentDate("yyyy-MM-dd"));
            saveDataManager.putData("stampInfo5", Util.getCurrentDate("yyyy-MM-dd"));
            saveDataManager.putData("stampInfo6", Util.getCurrentDate("yyyy-MM-dd"));
        }
        else if(tmpInt <= 8)
        {
            tmpRstMsg = "9개 중 "+ tmpInt +"개 스템프 획득!";
        }
        else if (tmpInt == 9)
        {
            tmpRstMsg = "스템프 9개 획득!\n제휴 15% 할인쿠폰 획득";
            saveDataManager.putData("stampInfo7", Util.getCurrentDate("yyyy-MM-dd"));
            saveDataManager.putData("stampInfo8", Util.getCurrentDate("yyyy-MM-dd"));
            saveDataManager.putData("stampInfo9", Util.getCurrentDate("yyyy-MM-dd"));
        }

        AlertDialog alertDialog = new AlertDialog.Builder( getActivity()).create();

        alertDialog.setTitle("스템프 모으기");
        alertDialog.setMessage( tmpRstMsg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        ((MainActivity)getActivity()).callFragmentPage(new StampBookFragment());

                    }
                });
        alertDialog.show();






        // 마커 뜨는게 false 인부분 즉 스탬프 안찍은 마커만 뜨게했고
        // 마커 받았을떄 세어드프리퍼런스로 true만들고
        // 북페이지로 화면전환
        // 다시 일로와도 방금받은 스탬프마커는 업어짐

    }

    @OnClick(R.id.bt_test)
    void OnClickbt_test()
    {
        Log.i(LOG,"BT_TEST");

//        mCurArrayPos
//        mCurPlace
        SaveDataManager saveDataManager = new SaveDataManager(getActivity().getApplicationContext());

        String tmpStr = "stamp";

        if( mCurArrayPos <10)
            tmpStr += "0" + mCurArrayPos;
        else
            tmpStr +=  "" + mCurArrayPos;

        saveDataManager.putData(tmpStr,"true");

        int tmpInt = Integer.parseInt( saveDataManager.getData("stampCount") );


        tmpInt++;
        saveDataManager.putData("stampCount",tmpInt+"");

        String tmpRstMsg = "";

        if(tmpInt <= 2)
        {
            tmpRstMsg = "3개 중 "+ tmpInt +"개 스템프 획득!";
        }
        else if (tmpInt == 3)
        {
            tmpRstMsg = "스템프 3개 획득!\n제휴 5% 할인쿠폰 획득";

            saveDataManager.putData("stampInfo1", Util.getCurrentDate("yyyy-MM-dd"));
            saveDataManager.putData("stampInfo2", Util.getCurrentDate("yyyy-MM-dd"));
            saveDataManager.putData("stampInfo3", Util.getCurrentDate("yyyy-MM-dd"));


        }
        else if(tmpInt <= 5)
        {
            tmpRstMsg = "6개 중 "+ tmpInt +"개 스템프 획득!";
        }
        else if (tmpInt == 6)
        {
            tmpRstMsg = "스템프 6개 획득!\n제휴 10% 할인쿠폰 획득";
            saveDataManager.putData("stampInfo4", Util.getCurrentDate("yyyy-MM-dd"));
            saveDataManager.putData("stampInfo5", Util.getCurrentDate("yyyy-MM-dd"));
            saveDataManager.putData("stampInfo6", Util.getCurrentDate("yyyy-MM-dd"));
        }
        else if(tmpInt <= 8)
        {
            tmpRstMsg = "9개 중 "+ tmpInt +"개 스템프 획득!";
        }
        else if (tmpInt == 9)
        {
            tmpRstMsg = "스템프 9개 획득!\n제휴 15% 할인쿠폰 획득";
            saveDataManager.putData("stampInfo7", Util.getCurrentDate("yyyy-MM-dd"));
            saveDataManager.putData("stampInfo8", Util.getCurrentDate("yyyy-MM-dd"));
            saveDataManager.putData("stampInfo9", Util.getCurrentDate("yyyy-MM-dd"));
        }

        AlertDialog alertDialog = new AlertDialog.Builder( getActivity()).create();

        alertDialog.setTitle("스템프 모으기");
        alertDialog.setMessage( tmpRstMsg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        ((MainActivity)getActivity()).callFragmentPage(new StampBookFragment());

                    }
                });
        alertDialog.show();






        // 마커 뜨는게 false 인부분 즉 스탬프 안찍은 마커만 뜨게했고
        // 마커 받았을떄 세어드프리퍼런스로 true만들고
        // 북페이지로 화면전환
        // 다시 일로와도 방금받은 스탬프마커는 업어짐

    }
    void disableStamp()
    {
        isEnable=false;
        tv_fragment_stam_get_place.setTextColor(Color.rgb(157,157,157));
        tv_fragment_stam_get_place.setText("마커 근처에 가면 스템프가 활성화 됩니다");
        iv_fragment_stam_get_stamp.setImageResource(R.drawable.stamp_off);
    }

    void enableStamp(String stampInfo)
    {
        isEnable=true;
        tv_fragment_stam_get_place.setTextColor(Color.rgb(189,1,2));
        tv_fragment_stam_get_place.setText("[ " + stampInfo+" ] 스템프를 획득가능");
        iv_fragment_stam_get_stamp.setImageResource(R.drawable.stamp_on);
    }


    public StampGetFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_stamp_get, container, false);
        ButterKnife.bind(this,view);

        disableStamp();

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


        SaveDataManager saveDataManager = new SaveDataManager(getActivity().getApplicationContext());




        for(int i = 0 ; i < dtoStampPlaceArrayList.size();i++ )
        {

            String tmpStr = "stamp";

            if( mCurArrayPos <10)
                tmpStr += "0" + i;
            else
                tmpStr +=  "" + i;

            if(saveDataManager.getData(tmpStr).compareTo("false")==0)
            {
                DTOStampPlace tmpDtoStampPlace = dtoStampPlaceArrayList.get(i);
                poiData.addPOIitem( Double.parseDouble( tmpDtoStampPlace.getGpsX() ) , Double.parseDouble( tmpDtoStampPlace.getGpsY() ),tmpDtoStampPlace.getPlaceName(),mMarkerId ,0 );
            }

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

    boolean isArriveCheck(NGeoPoint nGeoPoint, String lon, String lat)
    {
        //     *(다른부분)
        // 33.4535   ,   126.346 = > 130.6998407736
        // 33.4435   ,   126.346 = > 130.6972815641
        //
        // 차이 : 0.0025 (인식률떨어짐 ) -> 0.01

        double doubleLon = Double.parseDouble(lon);
        double doubleLat = Double.parseDouble(lat);

//        Log.i(LOG,nGeoPoint.longitude + " " + nGeoPoint.latitude );
//        Log.i(LOG,doubleLon + " " + doubleLat );

        if( sqrt((nGeoPoint.longitude - doubleLon) * (nGeoPoint.longitude - doubleLon )
                + (nGeoPoint.latitude - doubleLat )*(nGeoPoint.latitude - doubleLat ) )
                < GPS_INTERVAL_FOR_CALC )
        {
            return true;
        }
        return false;
    }

    NMapLocationManager.OnLocationChangeListener onLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {
        @Override
        public boolean onLocationChanged(NMapLocationManager nMapLocationManager, NGeoPoint nGeoPoint) {

            Log.i(LOG,  "LOCATION IS CHANGED");

            mCurArrayPos= -1;

            for (int i = 0; i < StampDataManager.dtoStampPlaceArrayList.size(); i++)
            {

                if( StampDataManager.dtoStampPlaceArrayList.get(i).getGet() == true ) continue;

                boolean isChecked = isArriveCheck(nGeoPoint,   StampDataManager.dtoStampPlaceArrayList.get(i).getGpsX() , StampDataManager.dtoStampPlaceArrayList.get(i).getGpsY() );

                if(isChecked == true)
                {
                    mCurArrayPos=i;
                    break;
                }
            }

            if( mCurArrayPos == -1)
            {
                disableStamp();
            }
            else
            {
                mCurPlace = StampDataManager.dtoStampPlaceArrayList.get(mCurArrayPos).getPlaceName();
                enableStamp(mCurPlace);
            }


            return true;
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
        initMap();
        startMyLocation();

        disableStamp();
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
        stopMyLocation();
        super.onDestroy();
    }


}
