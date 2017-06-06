package codersit.co.kr.jejugo.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dao.DAOGeoCode;
import codersit.co.kr.jejugo.dto.DTOGeoCode;
import codersit.co.kr.jejugo.util.ICallback;

/**
 * Created by P200 on 2017-06-04.
 */

public class FoodFragment extends Fragment {

    String LOG ="FoodFragment";

    public FoodFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DAOGeoCode daoGeoCode = new DAOGeoCode("용산");
        daoGeoCode.setICallbackListener(iCallback);

        daoGeoCode.getData();



        return inflater.inflate(R.layout.activity_fragment_food, container, false);

    }


    ICallback iCallback = new ICallback() {
        @Override
        public void call(Object o) {

            DTOGeoCode dtoGeoCode = (DTOGeoCode) o;



            Log.i(LOG, dtoGeoCode.getUserquery());
        }
    };

}
