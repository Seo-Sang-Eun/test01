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
import codersit.co.kr.jejugo.dao.DAOFestivalInquiryService;
import codersit.co.kr.jejugo.dto.DTOFestivalInquiryService;
import codersit.co.kr.jejugo.util.ICallback;

/**
 * Created by admin on 2017-06-08.
 */

public class JejuFestivalFragment extends Fragment {

    private JejuFestivalAdapter jejuFestivalAdapter;
    private ListView jejuFestivalListview;

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
        DAOFestivalInquiryService daoFestivalInquiryService = new DAOFestivalInquiryService("1","10");
        daoFestivalInquiryService.setICallbackListener(iCallback);

        daoFestivalInquiryService.getData();
    }

    ICallback iCallback = new ICallback() {
        @Override
        public void call(Object o) {

            DTOFestivalInquiryService dtoFestivalInquiryService = (DTOFestivalInquiryService) o;
            jejuFestivalAdapter = new JejuFestivalAdapter(dtoFestivalInquiryService);

            view_action_sms.setAdapter(jejuFestivalAdapter);

            jejuFestivalAdapter.notifyDataSetChanged();
        }
    };

}
