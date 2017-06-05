package codersit.co.kr.jejugo.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codersit.co.kr.jejugo.R;

/**
 * Created by P200 on 2017-06-04.
 */

public class FestivalFragment extends Fragment {

    public FestivalFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment_festival, container, false);

    }



}
