package codersit.co.kr.jejugo.activity.festival;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import codersit.co.kr.jejugo.R;

/**
 * Created by admin on 2017-06-07.
 */

public class SeoPerformanceFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_festival_seo, container, false);
        ButterKnife.bind(this, view);


        return view;

    }
}
