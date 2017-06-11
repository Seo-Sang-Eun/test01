package codersit.co.kr.jejugo.activity.festival;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dto.DTOCultureEvent;

/**
 * Created by admin on 2017-06-08.
 */

public class SeogwipoCultureAdpater extends BaseAdapter {

    private DTOCultureEvent dtoCultureEvent;
    Context mContext;

    public SeogwipoCultureAdpater(DTOCultureEvent dtoCultureEvent, Context context ) {

        mContext = context;
        this.dtoCultureEvent = dtoCultureEvent;
    }

    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return dtoCultureEvent.getItems().size();
    }
    // 하나의 Item(ImageView 1, TextView 2)
    @Override
    public Object getItem(int position) {
        return dtoCultureEvent.getItems().get(position);
    }
    // Item의 id : Item을 구별하기 위한 것으로 position 사용
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SeogwipoCultureCustomViewHolder holder;

        final String m_title;
        final String m_help;
        final String m_content;
        final String homepage;
        final String price;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jae_seogwipo_item, null, false);

            holder = new SeogwipoCultureCustomViewHolder();

            holder.ll_seo_culture_outlayout= (LinearLayout)convertView.findViewById(R.id.seogwipo_item) ;
            holder.seogwipoCultureSubject = (TextView) convertView.findViewById(R.id.text_seo_subject);
            holder.seogwipoCultureAddress = (TextView) convertView.findViewById(R.id.text_seo_adress);
            holder.seogwipoCultureStartDate = (TextView) convertView.findViewById(R.id.text_seo_startDay);
            holder.seogwipoCultureGetInfo = (TextView) convertView.findViewById(R.id.text_seo_get_info);
            convertView.setTag(holder);
        } else {
            holder = (SeogwipoCultureCustomViewHolder) convertView.getTag();
        }


        holder.seogwipoCultureSubject.setText(dtoCultureEvent.getItems().get(position).getISubject());
        holder.seogwipoCultureAddress.setText(dtoCultureEvent.getItems().get(position).getILocation());
        holder.seogwipoCultureStartDate.setText(dtoCultureEvent.getItems().get(position).getIPeriodText());



        m_title = dtoCultureEvent.getItems().get(position).getISubject();


        m_content=removeTag(dtoCultureEvent.getItems().get(position).getIContents());

        if(m_content.compareTo("false")==0)
        {
            holder.seogwipoCultureGetInfo.setText("상세정보 비제공");
            holder.seogwipoCultureGetInfo.setTextColor(Color.parseColor("#9b9b9b"));
        }
        else
        {


            holder.ll_seo_culture_outlayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view)
                {
                    SeogwipoCultureDialog seogwipoCultureDialog = new SeogwipoCultureDialog( mContext,   m_content,m_title);
                    seogwipoCultureDialog.show();
                }
            });
        }




        return convertView;
    }

    class SeogwipoCultureCustomViewHolder {
        LinearLayout ll_seo_culture_outlayout;
        TextView seogwipoCultureSubject;
        TextView seogwipoCultureAddress;
        TextView seogwipoCultureStartDate;
        TextView seogwipoCultureGetInfo;
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
            temp = "false";
        return temp.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
    }

}
