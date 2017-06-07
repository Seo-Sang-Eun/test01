package codersit.co.kr.jejugo.activity.festival;

import android.app.Fragment;
import android.app.FragmentManager;
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

public class FestivalFragment extends Fragment {

    public FestivalFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_festival, container, false);
        ButterKnife.bind(this, view);


        return view;


    }




    @OnClick(R.id.jejuBtn)
    void onClick_jejuBtn()
    {
        Log.i("TT","TT");
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,new JejuPerformanceFragment());
        fragmentTransaction.commit();
    }


    @OnClick(R.id.seogwipoBtn)
    void onClick_seogwipoBtn()
    {
        Log.i("TT","TT");
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,new SeoPerformanceFragment());
        fragmentTransaction.commit();
    }



}
