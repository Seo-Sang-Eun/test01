package codersit.co.kr.jejugo.activity.food;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import codersit.co.kr.jejugo.R;
//import codersit.co.kr.jejugo.dao.DAOGeoCode;
import codersit.co.kr.jejugo.activity.MainActivity;
import codersit.co.kr.jejugo.activity.festival.JejuCultureStreetAdpater;
import codersit.co.kr.jejugo.activity.hotplace.HotplaceDetailFragment;
import codersit.co.kr.jejugo.dao.DAOArtstreetService;
import codersit.co.kr.jejugo.dao.DAOBestEating;
import codersit.co.kr.jejugo.dao.DAOGeoCode;
import codersit.co.kr.jejugo.dao.DAOImage;
import codersit.co.kr.jejugo.dao.DAOJejuWifiVisitCountInfo;
import codersit.co.kr.jejugo.dto.DTOArtstreetService;
import codersit.co.kr.jejugo.dto.DTOBestEating;
import codersit.co.kr.jejugo.dto.DTOGeoCode;
import codersit.co.kr.jejugo.dto.DTOImage;
import codersit.co.kr.jejugo.dto.DTOJejuWifiVisitCountInfo;
import codersit.co.kr.jejugo.dto.DTOStampPlace;
import codersit.co.kr.jejugo.util.ICallback;
import codersit.co.kr.jejugo.util.ImageLoaderTask;
import codersit.co.kr.jejugo.util.JejuFoodManager;
import codersit.co.kr.jejugo.util.JejuWifiDataManager;
import codersit.co.kr.jejugo.util.NMapPOIflagType;
import codersit.co.kr.jejugo.util.NMapViewerResourceProvider;
import codersit.co.kr.jejugo.util.StampDataManager;
import codersit.co.kr.jejugo.util.Util;

import static codersit.co.kr.jejugo.util.IKeyManager.NaverClientID;
import static codersit.co.kr.jejugo.util.StampDataManager.dtoStampPlaceArrayList;
import static java.lang.Math.sqrt;

/**
 * Created by P200 on 2017-06-04.
 */

public class FoodFragment extends Fragment {

    String LOG ="FoodFragment";

    String curQuery = null;
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


    @Bind(R.id.tv_fragment_food_title)
    TextView tv_fragment_food_title;
    @Bind(R.id.tv_fragment_food_tmptitle1)
    TextView tv_fragment_food_tmptitle1;
    @Bind(R.id.tv_fragment_food_tmptitle2)
    TextView tv_fragment_food_tmptitle2;
    @Bind(R.id.ll_fragment_food_for_bt_ll)
    LinearLayout ll_fragment_food_for_bt_ll;
    @Bind(R.id.tv_fragment_food_info)
    TextView tv_fragment_food_info;
    @Bind(R.id.tv_fragment_food_adress)
    TextView tv_fragment_food_adress;
    @Bind(R.id.iv_fragment_food_image)
    ImageView iv_fragment_food_image;

    final double GPS_INTERVAL_FOR_CALC = 0.01;//0.0025;

    public FoodFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_food, container, false);
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

        mNMapView = (NMapView)getView().findViewById(R.id.nmapview_frag_food);
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

        // **
        poiData.beginPOIdata( JejuFoodManager.staticDtoBestEating.getData().size());


        for(int i = 0 ; i < JejuFoodManager.staticDtoBestEating.getData().size();i++ )
        {

            poiData.addPOIitem(
                    Double.parseDouble( JejuFoodManager.staticDtoBestEating.getData().get(i).getLo() ) ,
                    Double.parseDouble( JejuFoodManager.staticDtoBestEating.getData().get(i).getLa() ),
                    JejuFoodManager.staticDtoBestEating.getData().get(i).getDataTitle(),
                    mMarkerId ,
                    0
            );
        }
        // **

        poiData.endPOIdata();

// create POI data overlay
        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);

        poiDataOverlay.setOnStateChangeListener(onStateChangeListener);


//        poiDataOverlay.showAllPOIdata(0);

        mMapLocationManager = new NMapLocationManager(super.getActivity());
        mMapLocationManager.setOnLocationChangeListener(onLocationChangeListener);


        mMapCompassManager = new NMapCompassManager(super.getActivity());
        mMyLocationOverlay = mOverlayManager.createMyLocationOverlay(mMapLocationManager, mMapCompassManager);


        startMyLocation();
    }


    NMapLocationManager.OnLocationChangeListener onLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {
        @Override
        public boolean onLocationChanged(NMapLocationManager nMapLocationManager, NGeoPoint nGeoPoint) {

            Log.i(LOG,  "LOCATION IS CHANGED");


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


                curQuery = nMapPOIitem.getTitle();

                tv_fragment_food_title.setText(curQuery);

                for(int i = 0; i< JejuFoodManager.staticDtoBestEating.getData().size(); i++)
                {
                    if(curQuery.equals(JejuFoodManager.staticDtoBestEating.getData().get(i).getDataTitle()) )
//                    && ( String.valueOf(nMapPOIitem.getPoint().getLatitude()).equals(JejuFoodManager.staticDtoBestEating.getData().get(i).getLa())))
                    {
                        tv_fragment_food_adress.setText("("+JejuFoodManager.staticDtoBestEating.getData().get(i).getAdres()+")");

                        String phoneNum=""; // 010-12345-1234
                        String foodType=""; // 한식 중식
                        String foodMenuWithText=""; // 통닭
                        String foodMenu="";


                        if(!(JejuFoodManager.staticDtoBestEating.getData().get(i).getMenu().equals("") || JejuFoodManager.staticDtoBestEating.getData().get(i).getMenu() == null))
                        {
                            foodMenuWithText = "주요메뉴    " + JejuFoodManager.staticDtoBestEating.getData().get(i).getMenu();
                            foodMenu = JejuFoodManager.staticDtoBestEating.getData().get(i).getMenu();
                        }

                        if(!(JejuFoodManager.staticDtoBestEating.getData().get(i).getBizcnd().equals("") || JejuFoodManager.staticDtoBestEating.getData().get(i).getBizcnd() == null))
                        {
                            foodType = "음식종류    " + JejuFoodManager.staticDtoBestEating.getData().get(i).getBizcnd();
                        }

                        if(!(JejuFoodManager.staticDtoBestEating.getData().get(i).getTelNo().equals("") || JejuFoodManager.staticDtoBestEating.getData().get(i).getTelNo() == null))
                        {
                            phoneNum = "전화번호    " + JejuFoodManager.staticDtoBestEating.getData().get(i).getTelNo();
                        }

//                        Log.i(LOG,JejuFoodManager.staticDtoBestEating.getData().get(i).getDataTitle()+"__"+foodMenu+"__"+foodType + "__"+phoneNum);
                        tv_fragment_food_info.setText( foodMenuWithText+"\n"+foodType + "\n"+phoneNum);

//                        Log.i(LOG,"MENU : " + JejuFoodManager.staticDtoBestEating.getData().get(i).getMenu());


                        // 감자,고구마     처럼 , 로되있을때
                        if(foodMenu.split(",").length>=1)
                        {
                            foodMenu = foodMenu.split(",")[0];
                        }

                        DAOImage daoImage = new DAOImage(foodMenu);
                        daoImage.setICallbackListener(iCallback);

                        daoImage.getData();


                        break;
                    }
                }

                ll_fragment_food_for_bt_ll.setVisibility(View.VISIBLE);
                tv_fragment_food_title.setVisibility(View.VISIBLE);
                tv_fragment_food_tmptitle1.setVisibility(View.GONE);
                tv_fragment_food_tmptitle2.setVisibility(View.GONE);



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


    ICallback iCallback = new ICallback() {
        @Override
        public void call(Object o) {

            DTOImage dtoImage = (DTOImage) o;

            if( dtoImage != null && dtoImage.getItem() != null) {

                iv_fragment_food_image.setVisibility(View.VISIBLE);

//                Log.i(LOG, dtoImage.getItem().get(0).getLink() );

                ImageLoaderTask imageLoaderTask = new ImageLoaderTask(iv_fragment_food_image, dtoImage.getItem().get(0).getLink() );
                imageLoaderTask.execute();
            }
            else
                iv_fragment_food_image.setVisibility(View.GONE);
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

    @OnClick(R.id.bt_fragment_food_search_bt)
    void onClick_bt_fragment_food_search_bt()
    {
        searchBrowser("https://search.naver.com/search.naver?ie=UTF-8&query=" + "제주 " + curQuery, "모범음식 검색 결과");

    }

    void searchBrowser(String url, String detailInfo)
    {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add(url); // [ url ]
        strings.add(curQuery + " " + detailInfo); // ex) [ 제주서귀포 블로그검색 결과 ]

        ((MainActivity)getActivity()).callFragmentPageWithData(new FoodDetailFragment(),strings);
    }


}
