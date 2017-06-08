package codersit.co.kr.jejugo.activity.festival;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dto.DTOCultureEvent;

/**
 * Created by admin on 2017-06-08.
 */

public class SeogwipoCultureAdpater extends BaseAdapter {

    private DTOCultureEvent dtoCultureEvent;

    public SeogwipoCultureAdpater(DTOCultureEvent dtoCultureEvent ) {
        this.dtoCultureEvent = dtoCultureEvent;
    }

    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return dtoCultureEvent.getData().size();
    }
    // 하나의 Item(ImageView 1, TextView 2)
    @Override
    public Object getItem(int position) {
        return dtoCultureEvent.getData().get(position);
    }
    // Item의 id : Item을 구별하기 위한 것으로 position 사용
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SeogwipoCultureCustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jae_seogwipo_item, null, false);

            holder = new SeogwipoCultureCustomViewHolder();

            holder.seogwipoCultureSubject = (TextView) convertView.findViewById(R.id.text_seo_subject);
            holder.seogwipoCultureAddress = (TextView) convertView.findViewById(R.id.text_seo_adress);
            holder.seogwipoCultureStartDate = (TextView) convertView.findViewById(R.id.text_seo_startDay);
            holder.seogwipoCultureEndDate = (TextView) convertView.findViewById(R.id.text_seo_endDay);
            convertView.setTag(holder);
        } else {
            holder = (SeogwipoCultureCustomViewHolder) convertView.getTag();
        }


        holder.seogwipoCultureSubject.setText(dtoCultureEvent.getData().get(position).getISubject());
        holder.seogwipoCultureAddress.setText(dtoCultureEvent.getData().get(position).getILocation());
        holder.seogwipoCultureStartDate.setText(dtoCultureEvent.getData().get(position).getISdate());
        holder.seogwipoCultureEndDate.setText(dtoCultureEvent.getData().get(position).getIEdate());

        return convertView;
    }

    class SeogwipoCultureCustomViewHolder {
        TextView seogwipoCultureSubject;
        TextView seogwipoCultureAddress;
        TextView seogwipoCultureStartDate;
        TextView seogwipoCultureEndDate;
    }


}
