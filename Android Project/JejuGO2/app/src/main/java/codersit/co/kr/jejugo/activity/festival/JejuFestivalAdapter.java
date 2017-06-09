package codersit.co.kr.jejugo.activity.festival;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dto.DTOFestivalInquiryService;

/**
 * Created by admin on 2017-06-08.
 */

public class JejuFestivalAdapter extends BaseAdapter {

    private DTOFestivalInquiryService dtoFestivalInquiryService;

    public JejuFestivalAdapter(DTOFestivalInquiryService dtoFestivalInquiryService ) {
        this.dtoFestivalInquiryService = dtoFestivalInquiryService;
    }


    @Override
    public int getCount() {
        return dtoFestivalInquiryService.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return dtoFestivalInquiryService.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JejuFestCustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jae_jeju_fest_item, null, false);

            holder = new JejuFestCustomViewHolder();
            holder.jejuFestName = (TextView) convertView.findViewById(R.id.text_jejuFest_name);
            holder.jejuFestStartDate = (TextView) convertView.findViewById(R.id.text_jejuFest_startDate);
          //  holder.jejuFestEndDate = (TextView) convertView.findViewById(R.id.text_jejuFest_endDate);
            holder.jejuFestLocation = (TextView) convertView.findViewById(R.id.text_jejuFest_location);

            convertView.setTag(holder);
        } else {
            holder = (JejuFestCustomViewHolder) convertView.getTag();
        }

        holder.jejuFestName.setText(dtoFestivalInquiryService.getData().get(position).getTitle());
        holder.jejuFestStartDate.setText(dtoFestivalInquiryService.getData().get(position).getSdate()+" ~ "+dtoFestivalInquiryService.getData().get(position).getEdate());
       // holder.jejuFestEndDate.setText(dtoFestivalInquiryService.getData().get(position).getEdate());
        holder.jejuFestLocation.setText(dtoFestivalInquiryService.getData().get(position).getLocation());

        return convertView;
    }

    class JejuFestCustomViewHolder {
        TextView jejuFestName;
        TextView jejuFestStartDate;
        TextView jejuFestEndDate;
        TextView jejuFestLocation;
    }
}
