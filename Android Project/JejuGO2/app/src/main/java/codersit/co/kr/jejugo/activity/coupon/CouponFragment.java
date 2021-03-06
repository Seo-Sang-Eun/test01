package codersit.co.kr.jejugo.activity.coupon;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.activity.MainActivity;
import codersit.co.kr.jejugo.dto.DTOCoupon;
import codersit.co.kr.jejugo.util.ICallback;
import codersit.co.kr.jejugo.util.SaveDataManager;

/**
 * Created by BooHee on 2017-06-11.
 */

public class CouponFragment extends Fragment {

    String LOG = "CouponFragment ";

    @Bind(R.id.couponlist)
    ListView couponlist;

    private ArrayList<String> strings;
//    private ArrayList<DTOCoupon> dtoCoupons = new ArrayList<>();

    CouponAdapter couponAdapter;
    SaveDataManager saveDataManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.coupon_fragment, container, false);
        ButterKnife.bind(this,view);

        reflashData();

        return view;
    }
    void reflashData()
    {
        saveDataManager = new SaveDataManager(getActivity().getApplicationContext());

        strings = new ArrayList<>();

        for(int i = 1 ; i<= 9 ;i++) {
            strings.add(saveDataManager.getData("stampInfo" + i)); // false or 2011-11-11
//            Log.i(LOG,saveDataManager.getData("stampInfo" + i));
        }


        couponAdapter = new CouponAdapter((MainActivity)getActivity(), strings);

        couponAdapter.setiCallbackListener(new ICallback() {

            @Override
            public void call(Object o) {

                strings.clear();
                for(int i = 1 ; i<= 9 ;i++) {
                    strings.add(saveDataManager.getData("stampInfo" + i)); // false or 2011-11-11
//            Log.i(LOG,saveDataManager.getData("stampInfo" + i));
                }

                couponAdapter.notifyDataSetChanged();
            }

        });

        couponlist.setAdapter(couponAdapter);
        couponAdapter.notifyDataSetChanged();
    }


}
