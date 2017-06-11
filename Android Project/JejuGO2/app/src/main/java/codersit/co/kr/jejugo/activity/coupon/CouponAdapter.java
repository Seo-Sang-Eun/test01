package codersit.co.kr.jejugo.activity.coupon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dto.DTOCoupon;
import codersit.co.kr.jejugo.dto.DTOPartnerStore;

/**
 * Created by BooHee on 2017-06-11.
 */

public class CouponAdapter extends BaseAdapter {

    @Bind(R.id.couponlist)
    ListView couponlist;

    private ArrayList<DTOCoupon> couponList = new ArrayList<>();
    private ArrayList<String> tmp;
    Context mContext;

    public CouponAdapter(Context mContext, ArrayList<String> strings)
    {
        this.mContext = mContext;
        tmp.addAll(strings);
    }

    @Override
    public int getCount() {
        return couponList.size();
    }

    @Override
    public Object getItem(int position) {
        return couponList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // convertView = LayoutInflater.from(parent.getContext()).inflate(android R.layout.simpl, parent, false);




        return convertView;
    }
}
