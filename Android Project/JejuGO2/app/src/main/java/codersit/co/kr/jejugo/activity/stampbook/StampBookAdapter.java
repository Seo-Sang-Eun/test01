package codersit.co.kr.jejugo.activity.stampbook;

import android.content.Context;
import android.graphics.Color;
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
import codersit.co.kr.jejugo.activity.coupon.CouponDialog;

/**
 * Created by BooHee on 2017-06-11.
 */

public class StampBookAdapter extends BaseAdapter {

    private ArrayList<StampBookFragment.DTOStampBookAdapter> dtoStampBookAdapters;
    Context mContext;

    public StampBookAdapter(Context mContext, ArrayList<StampBookFragment.DTOStampBookAdapter> dtoStampBookAdapters)
    {
        this.mContext = mContext;
        this.dtoStampBookAdapters = dtoStampBookAdapters;
    }

    @Override
    public int getCount() {
        return dtoStampBookAdapters.size();
    }

    @Override
    public Object getItem(int position) {
        return dtoStampBookAdapters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        StampBookCustomViewHolder holder;

//        final String m_tv_adapter_stampbook_text;

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_stamp_book, null,false);
            holder = new StampBookCustomViewHolder();

            holder.tv_adapter_stampbook_text = (TextView) convertView.findViewById(R.id.tv_adapter_stampbook_text);
            holder.tv_adapter_stampbook_text2 = (TextView) convertView.findViewById(R.id.tv_adapter_stampbook_text2);

            convertView.setTag(holder);
        }
        else {
            holder = (StampBookCustomViewHolder) convertView.getTag();
        }

        if(dtoStampBookAdapters.get(position).getCheck()==true)
        {
            holder.tv_adapter_stampbook_text.setText(dtoStampBookAdapters.get(position).getPlaceName());
            holder.tv_adapter_stampbook_text.setTextColor(Color.parseColor("#000000"));
            holder.tv_adapter_stampbook_text2.setText("방문완료");
            holder.tv_adapter_stampbook_text2.setTextColor(Color.parseColor("#FFFF8000"));
        }
        else
        {
            holder.tv_adapter_stampbook_text.setText(dtoStampBookAdapters.get(position).getPlaceName());
            holder.tv_adapter_stampbook_text.setTextColor(Color.parseColor("#b4b4b4"));
            holder.tv_adapter_stampbook_text2.setText("");
        }


        return convertView;
    }

    class StampBookCustomViewHolder {
        TextView tv_adapter_stampbook_text;
        TextView tv_adapter_stampbook_text2;
    }

}
