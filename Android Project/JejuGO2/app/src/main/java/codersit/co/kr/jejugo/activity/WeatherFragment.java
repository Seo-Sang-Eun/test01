package codersit.co.kr.jejugo.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dao.DAOLongWeather;
import codersit.co.kr.jejugo.dao.DAOShortWeather;
import codersit.co.kr.jejugo.dto.DTOLongWeather;
import codersit.co.kr.jejugo.util.ICallback;

/**
 * Created by P200 on 2017-06-04.
 */

public class WeatherFragment extends Fragment {

    public WeatherFragment() {

    }

//    @Bind(R.id.gv_fragment_weather_short_weather)
//    GridView gv_fragment_weather_short_weather;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_weather, container, false);
        ButterKnife.bind(this, view);

        DAOLongWeather daoLongWeather = new DAOLongWeather();
        daoLongWeather.setiCallback(iCallbackLongWeather);
        daoLongWeather.getData();

        DAOShortWeather daoShortWeather = new DAOShortWeather();
        daoShortWeather.setiCallback(iCallbackShortWeather);
        daoShortWeather.getData();

        return view;
    }

    ICallback iCallbackLongWeather = new ICallback() {
        @Override
        public void call(Object o) {

            // XML꾸미고 데이터 가져와서 매칭

        }
    };

    ICallback iCallbackShortWeather = new ICallback() {
        @Override
        public void call(Object o) {

            // XML꾸미고 데이터 가져와서 매칭

        }
    };


}
