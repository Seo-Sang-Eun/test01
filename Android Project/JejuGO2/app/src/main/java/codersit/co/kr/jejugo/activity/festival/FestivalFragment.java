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
import codersit.co.kr.jejugo.activity.MainActivity;

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



    @OnClick(R.id.artCenter_btn)
    void onClick_artCenterBtn()
    {
        ((MainActivity)getActivity()).callFragmentPage(new JejuArtcenterFragment());
    }

    @OnClick(R.id.cultureStreet_btn)
    void onClick_cultureStreetBtn()
    {
        ((MainActivity)getActivity()).callFragmentPage(new JejuCultureStreetFragment());
    }

    @OnClick(R.id.jejuFest_btn)
    void onClick_jejuFestBtn()
    {
        ((MainActivity)getActivity()).callFragmentPage(new JejuFestivalFragment());
    }

    @OnClick(R.id.seogwipoFest_btn)
    void onClick_seogwipoFestBtn()
    {
        ((MainActivity)getActivity()).callFragmentPage(new SeogwipoCultureFragment());
    }



}
