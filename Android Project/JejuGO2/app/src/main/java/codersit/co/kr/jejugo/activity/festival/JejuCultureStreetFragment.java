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
import codersit.co.kr.jejugo.activity.MainActivity;
import codersit.co.kr.jejugo.dao.DAOArtstreetService;
import codersit.co.kr.jejugo.dto.DTOArtstreetService;
import codersit.co.kr.jejugo.util.ICallback;

/**
 * Created by admin on 2017-06-08.
 */

public class JejuCultureStreetFragment extends Fragment {

    private JejuCultureStreetAdpater jejuCultureStreetAdpater;

    @Bind(R.id.jeju_listView2)
    ListView view_action_sms;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.jae_listview, container, false);
        ButterKnife.bind(this, view);

        setData();

        return view;
    }
    private void setData() {
        DAOArtstreetService daoArtstreetService = new DAOArtstreetService("1","30");
        daoArtstreetService.setICallbackListener(iCallback);

        daoArtstreetService.getData();
    }


    ICallback iCallback = new ICallback() {
        @Override
        public void call(Object o) {

            DTOArtstreetService dtoArtstreetService = (DTOArtstreetService) o;
            jejuCultureStreetAdpater = new JejuCultureStreetAdpater(dtoArtstreetService,(MainActivity)getActivity());

            view_action_sms.setAdapter(jejuCultureStreetAdpater);

            jejuCultureStreetAdpater.notifyDataSetChanged();
        }
    };
}
