package codersit.co.kr.jejugo.activity.festival;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dto.DTOFestivalInquiryService;

/**
 * Created by admin on 2017-06-08.
 */

public class JejuFestivalAdapter extends BaseAdapter {

    private DTOFestivalInquiryService dtoFestivalInquiryService;
    Context mContext;

    public JejuFestivalAdapter(DTOFestivalInquiryService dtoFestivalInquiryService,Context context ) {
        mContext = context;
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

        final String m_title;
        final String m_info;
        final String m_host;


        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jae_jeju_fest_item, null, false);

            holder = new JejuFestCustomViewHolder();
            holder.ll_jejuFest_outlayout = (LinearLayout)convertView.findViewById(R.id.ll_jejuFest_outlayout);
            holder.jejuFestName = (TextView) convertView.findViewById(R.id.text_jejuFest_name);
            holder.jejuFestStartDate = (TextView) convertView.findViewById(R.id.text_jejuFest_startDate);
            holder.jejuFestLocation = (TextView) convertView.findViewById(R.id.text_jejuFest_location);

            convertView.setTag(holder);
        } else {
            holder = (JejuFestCustomViewHolder) convertView.getTag();
        }

        holder.jejuFestName.setText(dtoFestivalInquiryService.getData().get(position).getTitle());
        holder.jejuFestStartDate.setText(dtoFestivalInquiryService.getData().get(position).getSdate()+" ~ "+dtoFestivalInquiryService.getData().get(position).getEdate());
        holder.jejuFestLocation.setText(dtoFestivalInquiryService.getData().get(position).getLocation());

        m_title = dtoFestivalInquiryService.getData().get(position).getTitle();
        m_info = removeTag(dtoFestivalInquiryService.getData().get(position).getInfo());
        m_host = dtoFestivalInquiryService.getData().get(position).getHost();

        holder.ll_jejuFest_outlayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                JejuFestivalDialog jejuFestivalDialog = new JejuFestivalDialog(mContext, m_title, m_info,m_host);
                jejuFestivalDialog.show();

            }


        });

       return convertView;
    }

    public String removeTag(String html) {
        String temp;
        temp = html;
        temp = temp.replaceAll("&ldquo","");
        temp = temp.replaceAll("&rdquo","");
        temp = temp.replaceAll("&nbsp;","");
        temp = temp.replaceAll("&lsquo;","");
        temp = temp.replaceAll("&rsquo;","");
        if(temp.equals("empty"))
            temp = "api에서 내용을 제공하지 않습니다.";
        return temp.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
    }

    class JejuFestCustomViewHolder {
        LinearLayout ll_jejuFest_outlayout;
        TextView jejuFestName;
        TextView jejuFestStartDate;
        TextView jejuFestLocation;
    }
}
