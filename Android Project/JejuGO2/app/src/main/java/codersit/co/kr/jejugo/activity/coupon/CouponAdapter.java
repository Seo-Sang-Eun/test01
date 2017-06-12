package codersit.co.kr.jejugo.activity.coupon;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dto.DTOCoupon;
import codersit.co.kr.jejugo.dto.DTOPartnerStore;
import codersit.co.kr.jejugo.util.ICallback;
import codersit.co.kr.jejugo.util.ImageLoaderTask;

/**
 * Created by BooHee on 2017-06-11.
 */

public class CouponAdapter extends BaseAdapter {

    String LOG = "CouponAdapter";

    private ArrayList<String> strings;
    Context mContext;

    public CouponAdapter(Context mContext, ArrayList<String> strings)
    {
        this.mContext = mContext;
        this.strings = strings;
//        for(int i = 0 ; i < strings.size();i++)
//            Log.i(LOG,strings.get(i));

    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        CouponBookCustomViewHolder holder;

//        Log.i(LOG,"POS : " + position);

        final String m_date;

        LinearLayout couponItemLayout;
        ImageView couponItemImage;

        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_book_item, null,false);

        couponItemLayout = (LinearLayout) convertView.findViewById(R.id.ll_coupon_item);
        couponItemImage= (ImageView) convertView.findViewById(R.id.coupon_item_image_view);

        m_date = strings.get(position);

        // false : 공백 / flase2 : 사용완료
        if( 0 <= position &&  position < 3)
        {
            if(strings.get(position).equals("false"))
            {
//                Log.i(LOG,position + "  GONE1");
                couponItemImage.setVisibility(View.GONE);
                couponItemLayout.setVisibility(View.GONE);
            }
            else if(strings.get(position).equals("false2"))
            {
                couponItemImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.cn5));
            }
            else
            {
                couponItemImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.c5));
                couponItemLayout.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        CouponDialog couponDialog = new CouponDialog(mContext,"5% 쿠폰",m_date,position+1);
                        couponDialog.setiCallbackListener(new ICallback() {
                            @Override
                            public void call(Object o) {

                                iCallback2.call(null);

                            }
                        });
                        couponDialog.show();
                    }
                });
            }
        }
        else if( 3 <= position &&  position < 6)
        {
            if(strings.get(position).equals("false"))
            {
//                Log.i(LOG,position + "  GONE2");
                couponItemImage.setVisibility(View.GONE);
                couponItemLayout.setVisibility(View.GONE);
            }
            else if(strings.get(position).equals("false2"))
            {
                couponItemImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.cn10));
            }
            else
            {
                couponItemImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.c10));
                couponItemLayout.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        CouponDialog couponDialog = new CouponDialog(mContext,"10% 쿠폰",m_date,position+1);
                        couponDialog.setiCallbackListener(new ICallback() {
                            @Override
                            public void call(Object o) {

                                iCallback2.call(null);

                            }
                        });
                        couponDialog.show();
                    }
                });
            }
        }
        else if( 6 <= position && position < 9)
        {
            if(strings.get(position).equals("false"))
            {
//                Log.i(LOG,position + "  GONE3");
                couponItemImage.setVisibility(View.GONE);
                couponItemLayout.setVisibility(View.GONE);
            }
            else if(strings.get(position).equals("false2"))
            {
                couponItemImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.cn15));
            }
            else
            {
                couponItemImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.c15));
                couponItemLayout.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        CouponDialog couponDialog = new CouponDialog(mContext,"15% 쿠폰",m_date,position+1);


                        couponDialog.setiCallbackListener(new ICallback() {
                            @Override
                            public void call(Object o) {

                                iCallback2.call(null);

                            }
                        });

                        couponDialog.show();
                    }
                });
            }
        }
        else
        {
            couponItemImage.setVisibility(View.GONE);
        }

        return convertView;
    }


    private ICallback iCallback2;

    public void setiCallbackListener(ICallback iCallback)
    {
        this.iCallback2 = iCallback;
    }


}
