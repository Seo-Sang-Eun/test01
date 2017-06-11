package codersit.co.kr.jejugo.activity.setting;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import codersit.co.kr.jejugo.R;

/**
 * Created by admin on 2017-06-11.
 */

public class AboutFragment extends Fragment {

    public AboutFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.action_about_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
