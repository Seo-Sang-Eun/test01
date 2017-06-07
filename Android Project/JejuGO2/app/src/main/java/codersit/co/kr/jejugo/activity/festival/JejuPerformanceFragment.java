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
import codersit.co.kr.jejugo.dao.DAOArtstreetService;
import codersit.co.kr.jejugo.dao.DAOFestivalInquiryService;
import codersit.co.kr.jejugo.dto.DTOArtstreetService;
import codersit.co.kr.jejugo.dto.DTOFestivalInquiryService;
import codersit.co.kr.jejugo.util.ICallback;

/**
 * Created by P200 on 2017-06-04.
 */

public class JejuPerformanceFragment extends Fragment {

    private festivalAdapter myAdapter;
    private ListView myListView;

 //listView해
   @Bind(R.id.jeju_listView2)
    ListView view_action_sms;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.jeju_listview, container, false);
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
            myAdapter = new festivalAdapter(dtoFestivalInquiryService);

            view_action_sms.setAdapter(myAdapter);

            myAdapter.notifyDataSetChanged();
        }
    };




}
