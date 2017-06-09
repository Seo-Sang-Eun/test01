package codersit.co.kr.jejugo.activity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.activity.festival.FestivalFragment;
import codersit.co.kr.jejugo.activity.food.FoodFragment;
import codersit.co.kr.jejugo.activity.hotplace.HotplaceFragment;
//import codersit.co.kr.jejugo.util.GPSTracker;

/**
 * Created by P200 on 2017-06-04.
 */

public class MainFragment extends Fragment {

    private String LOG = "THIS MainFragment";

    public static int RENEW_GPS = 1;
    public static int SEND_PRINT = 2;

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what==RENEW_GPS){
                makeNewGpsService();
            }
            if(msg.what==SEND_PRINT){
                logPrint((String)msg.obj);
            }
        }

    };

//    GPSTracker gps = null;

    Context mContext;


    public MainFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_fragment_main, null);
        ButterKnife.bind(this, view);

        mContext = MainActivity.mContext;


//        if(gps == null) {
//            gps = new GPSTracker(mContext,mHandler);
//        }else{
//            gps.Update();
//        }

        // check if GPS enabled
//        if(gps.canGetLocation()){
//            double latitude = gps.getLatitude();
//            double longitude = gps.getLongitude();
            // \n is for new line
//            Toast.makeText(mContext, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
//        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
//            gps.showSettingsAlert();
//        }








        return view ;
        //inflater.inflate(R.layout.activity_fragment_main, container, false);

    }

    public void makeNewGpsService(){
//        if(gps == null) {
//            gps = new GPSTracker( mContext ,mHandler);
//        }else{
//            gps.Update();
//        }
    }

    public void logPrint(String str){
        Log.i(LOG, str);
    }



    @OnClick(R.id.ll_fragment_main_stamp_get)
    void onClick_ll_fragment_main_stamp_get()
    {
        ((MainActivity)getActivity()).callFragmentPage(new StampGetFragment());
    }

    @OnClick(R.id.ll_fragment_main_stamp_book)
    void onClick_ll_fragment_main_stamp_book()
    {
        ((MainActivity)getActivity()).callFragmentPage(new StampBookFragment());
    }

    @OnClick(R.id.ll_fragment_main_hotplace)
    void onClick_ll_fragment_main_hotplace()
    {
        ((MainActivity)getActivity()).callFragmentPage(new HotplaceFragment());
    }

    @OnClick(R.id.ll_fragment_main_weather)
    void onClick_ll_fragment_main_weather()
    {
        ((MainActivity)getActivity()).callFragmentPage(new WeatherFragment());
    }

    @OnClick(R.id.ll_fragment_main_festival)
    void onClick_ll_fragment_main_festival()
    {
        ((MainActivity)getActivity()).callFragmentPage(new FestivalFragment());
    }
    @OnClick(R.id.ll_fragment_main_food)
    void onClick_ll_fragment_main_food()
    {
        ((MainActivity)getActivity()).callFragmentPage(new FoodFragment());
    }




}
