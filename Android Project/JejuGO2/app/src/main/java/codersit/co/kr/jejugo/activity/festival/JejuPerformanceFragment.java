package codersit.co.kr.jejugo.activity.festival;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import codersit.co.kr.jejugo.R;

/**
 * Created by P200 on 2017-06-04.
 */

public class JejuPerformanceFragment extends Fragment {


 //listView해
  // @Bind(R.id.view_action_sms)
   // ListView view_action_sms;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_festival_jeju, container, false);
        ButterKnife.bind(this, view);






        return view;

    }



}
