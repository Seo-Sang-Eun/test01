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
import codersit.co.kr.jejugo.dao.DAOCultureEvent;
import codersit.co.kr.jejugo.dto.DTOCultureEvent;
import codersit.co.kr.jejugo.util.ICallback;

/**
 * Created by admin on 2017-06-07.
 */

public class SeogwipoCultureFragment extends Fragment {
    private SeogwipoCultureAdpater seogwipoCultureAdpater;
    private ListView seogwipoCultureListView;

    //listView해
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
        DAOCultureEvent daoCultureEvent = new DAOCultureEvent();
        daoCultureEvent.setICallbackListener(iCallback);

        daoCultureEvent.getData();
    }


    ICallback iCallback = new ICallback() {
        @Override
        public void call(Object o) {

            DTOCultureEvent dtoCultureEvent = (DTOCultureEvent) o;
            seogwipoCultureAdpater = new SeogwipoCultureAdpater(dtoCultureEvent);

            view_action_sms.setAdapter(seogwipoCultureAdpater);

            seogwipoCultureAdpater.notifyDataSetChanged();
        }
    };
}
