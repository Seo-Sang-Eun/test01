package codersit.co.kr.jejugo.activity;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dao.DAOLongWeather;
import codersit.co.kr.jejugo.dao.DAOShortWeather;
import codersit.co.kr.jejugo.dto.DTOLongWeather;
import codersit.co.kr.jejugo.dto.DTOShortWeather;
import codersit.co.kr.jejugo.util.ICallback;
import codersit.co.kr.jejugo.util.Util;

/**
 * Created by P200 on 2017-06-04.
 */

public class WeatherFragment extends Fragment {

    String LOG = "WeatherFragment";

    int viewCnt = 0 ; // 쇼트 롱으로 콜백올시 1씩오르고 2가오르면 화면  visible

    public WeatherFragment() {

    }
    @Bind(R.id.ll_fragment_weather_all_layout)
    LinearLayout ll_fragment_weather_all_layout;

    @Bind(R.id.tv_fragment_weather_cur_date)
    TextView tv_fragment_weather_cur_date;

    @Bind(R.id.iv_fragment_weather_cur_img)
    ImageView iv_fragment_weather_cur_img;

    @Bind(R.id.tv_fragment_weather_cur_c)
    TextView tv_fragment_weather_cur_c;

    @Bind ({
            R.id.ll_fragment_weather_short_01,
            R.id.ll_fragment_weather_short_02,
            R.id.ll_fragment_weather_short_03,
            R.id.ll_fragment_weather_short_04,
            R.id.ll_fragment_weather_short_05,
            R.id.ll_fragment_weather_short_06,
            R.id.ll_fragment_weather_short_07,
            R.id.ll_fragment_weather_short_08,
            R.id.ll_fragment_weather_short_09,
            R.id.ll_fragment_weather_short_10,
            R.id.ll_fragment_weather_short_11,
            R.id.ll_fragment_weather_short_12,
            R.id.ll_fragment_weather_short_13,
            R.id.ll_fragment_weather_short_14,
            R.id.ll_fragment_weather_short_15,
            R.id.ll_fragment_weather_short_16
    })
    View []ll_fragment_weather_short_array;


    @Bind ({
            R.id.ll_fragment_weather_long_1,
            R.id.ll_fragment_weather_long_2,
            R.id.ll_fragment_weather_long_3,
            R.id.ll_fragment_weather_long_4,
            R.id.ll_fragment_weather_long_5,
            R.id.ll_fragment_weather_long_6
    })
    View []ll_fragment_weather_long_array;


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

            ArrayList<DTOLongWeather> dtoLongWeathers = (ArrayList<DTOLongWeather> )o;

            int plusCnt = 0; // 배열 순차적으로 값넣기위함

            for(int i = 0 ; i < dtoLongWeathers.size();i++)
            {
                // 0시12시 다음날 0시12시 다음날 (반복... x 4? ) 후에 하루씩 제공하므로
                if(i==11)
                    break;
                if(i %2 == 1)
                    continue;

                DTOLongWeather tmpDTOLongWeather = dtoLongWeathers.get(i);

                TextView tv_grid_item_long_weather_date = ButterKnife.findById(ll_fragment_weather_long_array[plusCnt],R.id.tv_grid_item_long_weather_date);
                TextView tv_grid_item_long_weather_min_c = ButterKnife.findById(ll_fragment_weather_long_array[plusCnt],R.id.tv_grid_item_long_weather_min_c);
                TextView tv_grid_item_long_weather_max_c = ButterKnife.findById(ll_fragment_weather_long_array[plusCnt],R.id.tv_grid_item_long_weather_max_c);
                ImageView iv_grid_item_long_weather_img = ButterKnife.findById(ll_fragment_weather_long_array[plusCnt],R.id.iv_grid_item_long_weather_img);

                plusCnt++;

                String curDate = tmpDTOLongWeather.getTmEf().split("-")[2].split(" ")[0]; // 2017-06-10 00:00 에서 10 (일수만 가져오기)

                tv_grid_item_long_weather_date.setText(curDate + "일");
                tv_grid_item_long_weather_min_c.setText(tmpDTOLongWeather.getTmn() + "℃");
                tv_grid_item_long_weather_max_c.setText(tmpDTOLongWeather.getTmx() + "℃");
                iv_grid_item_long_weather_img.setImageResource(getWeatherImageId(tmpDTOLongWeather.getWf()));
            }

            viewCnt++;

            if(viewCnt==2)
            {
                ll_fragment_weather_all_layout.setVisibility(View.VISIBLE);
            }

        }
    };

    ICallback iCallbackShortWeather = new ICallback() {
        @Override
        public void call(Object o) {

            ArrayList<DTOShortWeather> dtoShortWeathers = (ArrayList<DTOShortWeather> )o;

            tv_fragment_weather_cur_date.setText(Util.getCurrentDate("MM월 dd일 hh시 mm분"));
            iv_fragment_weather_cur_img.setImageResource(getWeatherImageId(dtoShortWeathers.get(0).getWfKor()));
            tv_fragment_weather_cur_c.setText(dtoShortWeathers.get(0).getTemp()+"℃");

            int curDayInt = Integer.parseInt( Util.getCurrentDate("dd") );

            for(int i = 0 ; i < dtoShortWeathers.size();i++)
            {
                if(i==16)
                    break;
                DTOShortWeather tmpDTOShortWeather = dtoShortWeathers.get(i);


                TextView tv_grid_item_short_weather_c = ButterKnife.findById(ll_fragment_weather_short_array[i],R.id.tv_grid_item_short_weather_c);
                ImageView iv_grid_item_short_weather_img = ButterKnife.findById(ll_fragment_weather_short_array[i],R.id.iv_grid_item_short_weather_img);
                TextView tv_grid_item_short_weather_date = ButterKnife.findById(ll_fragment_weather_short_array[i],R.id.tv_grid_item_short_weather_date);

                iv_grid_item_short_weather_img.setImageResource(getWeatherImageId(tmpDTOShortWeather.getWfKor()));
                tv_grid_item_short_weather_date.setText( (Integer.parseInt( tmpDTOShortWeather.getDay() ) + curDayInt) + "일\n" + tmpDTOShortWeather.getHour() +"시");
                tv_grid_item_short_weather_c.setText(tmpDTOShortWeather.getTemp()+"℃");

            }

            viewCnt++;

            if(viewCnt==2)
            {
                ll_fragment_weather_all_layout.setVisibility(View.VISIBLE);
            }

        }
    };

    int getWeatherImageId(String str)
    {
        if(str.compareTo("구름 조금")==0 || str.compareTo("구름조금")==0)
        {
            return R.drawable.we_cloud1;
        }
        else if(str.compareTo("구름 많음")==0 || str.compareTo("구름많음")==0)
        {
            return R.drawable.we_cloud2;
        }
        else if(str.compareTo("흐림")==0)
        {
            return R.drawable.we_su_cloud;
        }
        else if(str.compareTo("비")==0)
        {
            return R.drawable.we_rain;
        }
        else if(str.compareTo("눈")==0)
        {
            return R.drawable.we_snow;
        }
        else
        {
            return R.drawable.we_sunny;
        }


    }




}
