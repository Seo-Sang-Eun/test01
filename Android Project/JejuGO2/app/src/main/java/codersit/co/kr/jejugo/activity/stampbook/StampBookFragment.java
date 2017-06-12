package codersit.co.kr.jejugo.activity.stampbook;

/**
 * Created by P200 on 2017-06-12.
 */


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.activity.MainActivity;
import codersit.co.kr.jejugo.activity.coupon.CouponFragment;
import codersit.co.kr.jejugo.activity.partnerstore.PartnerStoreFragment;
import codersit.co.kr.jejugo.util.SaveDataManager;
import codersit.co.kr.jejugo.util.StampDataManager;

/**
 * Created by P200 on 2017-06-09.
 */

public class StampBookFragment extends Fragment {

    @Bind(R.id.stamp_place_view)
    ListView stamp_place_view;
    @Bind(R.id.stamp_num_view)
    TextView stamp_num_view;


    final String LOG = "StampBookFragment";

    public StampBookFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_stamp_book, container, false);
        ButterKnife.bind(this,view);

        SaveDataManager saveDataManager = new SaveDataManager(getActivity().getApplicationContext());

        String stampCnt = saveDataManager.getData("stampCount");
//        String bb = saveDataManager.getData("stampInfo1"); // false or 2011-11-11
//        String 1b = saveDataManager.getData("stampInfo2"); // false or 2011-11-11
//        String 2b = saveDataManager.getData("stampInfo3"); // false or 2011-11-11
//        String bb = saveDataManager.getData("stampInfo4"); // false or 2011-11-11

        for(int i = 0; i < StampDataManager.dtoStampPlaceArrayList.size(); i++)
        {
            String tmpStr = "stamp";
            if( StampDataManager.dtoStampPlaceArrayList.get(i).getId() <10)
                tmpStr += "0" + i;
            else
                tmpStr +=  "" + i;

            if(saveDataManager.getData(tmpStr).compareTo("false")==0)
                StampDataManager.dtoStampPlaceArrayList.get(i).setGet(false);
            else
                StampDataManager.dtoStampPlaceArrayList.get(i).setGet(true);
        }

        ArrayList<DTOStampBookAdapter> tempArray = new ArrayList<>();

        for(int i = 0 ; i < StampDataManager.dtoStampPlaceArrayList.size();i++)
        {
            tempArray.add(new DTOStampBookAdapter(StampDataManager.dtoStampPlaceArrayList.get(i).getPlaceName(),
                    StampDataManager.dtoStampPlaceArrayList.get(i).getGet()));

        }

        stamp_num_view.setText(stampCnt);

        StampBookAdapter adapter = new StampBookAdapter(((MainActivity)getActivity()),tempArray);

        stamp_place_view.setAdapter(adapter);




        return view;

    }

    @OnClick(R.id.store_btn)
    void onClickstoreBtn()
    {
        ((MainActivity)getActivity()).callFragmentPage(new PartnerStoreFragment());
    }

    @OnClick(R.id.couponBtn)
    void onClickCouponBtn()
    {
        ((MainActivity)getActivity()).callFragmentPage(new CouponFragment());
    }

    public class DTOStampBookAdapter
    {
        private String placeName;
        private Boolean isCheck;

        public DTOStampBookAdapter(String placeName, Boolean isCheck) {
            this.placeName = placeName;
            this.isCheck = isCheck;
        }

        public String getPlaceName() {
            return placeName;
        }

        public Boolean getCheck() {
            return isCheck;
        }
    }


}
