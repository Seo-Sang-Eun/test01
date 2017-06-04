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


        return view ;//inflater.inflate(R.layout.activity_fragment_main, container, false);

    }

    @OnClick(R.id.bt_frag_main_stamp)
    void onClick_bt_frag_main_stamp()
    {
        ((MainActivity)getActivity()).callFragmentPage(new StampFragment());
    }

    @OnClick(R.id.bt_frag_main_play_info)
    void onClick_bt_frag_main_play_info()
    {
        ((MainActivity)getActivity()).callFragmentPage(new PlayInfoFragment());
    }

}
