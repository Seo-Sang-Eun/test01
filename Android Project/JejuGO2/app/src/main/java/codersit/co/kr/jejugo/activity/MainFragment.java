package codersit.co.kr.jejugo.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import codersit.co.kr.jejugo.R;

/**
 * Created by P200 on 2017-06-04.
 */

public class MainFragment extends Fragment {

    FragmentTransaction mFragmentTransaction;


    public MainFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_fragment_main, null);
        ButterKnife.bind(this, view);

        return view ;
        //inflater.inflate(R.layout.activity_fragment_main, container, false);

    }

    @OnClick(R.id.ll_fragment_main_stamp)
    void onClick_ll_fragment_main_stamp()
    {
        ((MainActivity)getActivity()).callFragmentPage(new StampFragment());
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
