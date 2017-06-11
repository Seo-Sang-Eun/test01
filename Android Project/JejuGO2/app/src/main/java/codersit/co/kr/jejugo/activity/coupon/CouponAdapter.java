package codersit.co.kr.jejugo.activity.coupon;

import android.content.Context;
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
import codersit.co.kr.jejugo.util.ImageLoaderTask;

/**
 * Created by BooHee on 2017-06-11.
 */

public class CouponAdapter extends BaseAdapter {

    @Bind(R.id.couponlist)
    ListView couponlist;

    private ArrayList<String> tmp;
    Context mContext;

    public CouponAdapter(Context mContext, ArrayList<String> strings)
    {
        this.mContext = mContext;
        tmp = strings;
    }

    @Override
    public int getCount() {
        return tmp.size();
    }

    @Override
    public Object getItem(int position) {
        return tmp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CouponBookCustomViewHolder holder;

        final String m_name;
        final String m_date;

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_book_item, null,false);
            holder = new CouponBookCustomViewHolder();

            holder.couponItemLayout = (LinearLayout) convertView.findViewById(R.id.ll_coupon_item);
            holder.couponItemImage= (ImageView) convertView.findViewById(R.id.coupon_item_image_view);
            holder.couponItemName= (TextView) convertView.findViewById(R.id.coupon_item_name_textview);
            holder.couponItemEnable= (TextView) convertView.findViewById(R.id.coupon_item_checkEnable_textview);
            convertView.setTag(holder);
        }
        else {
            holder = (CouponBookCustomViewHolder) convertView.getTag();
        }

        holder.couponItemImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_book));

        if(position >= 0 && position < 3) {
            holder.couponItemName.setText("5% 쿠폰");
            m_name = "5% 쿠폰";
        }
        else if(position >= 3 && position < 6) {
            holder.couponItemName.setText("10% 쿠폰");
            m_name = "10% 쿠폰";
        }
        else if(position >= 6 && position < 9) {
            holder.couponItemName.setText("15% 쿠폰");
            m_name = "15% 쿠폰";
        }
        else
        {
            m_name = "0%쿠폰";
        }

        if(tmp.get(position).equals("false")) {
            holder.couponItemEnable.setText("사용불가");
            m_date = "아직 발급되지 않은 쿠폰";
        }
        else
        {
            holder.couponItemEnable.setText("사용가능");
            m_date =tmp.get(position)+"부터 3일간";
        }


        holder.couponItemLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               CouponDialog couponDialog = new CouponDialog(mContext,m_name,m_date,position+1);
                couponDialog.show();
            }
        });


        return convertView;
    }

    class CouponBookCustomViewHolder {
        LinearLayout couponItemLayout;
        ImageView couponItemImage;
        TextView couponItemName;
        TextView couponItemEnable;
    }

}
