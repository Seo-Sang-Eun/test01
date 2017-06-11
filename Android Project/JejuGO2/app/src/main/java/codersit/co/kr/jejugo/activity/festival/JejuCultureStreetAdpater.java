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
import codersit.co.kr.jejugo.dto.DTOArtstreetService;
import codersit.co.kr.jejugo.util.ImageLoaderTask;

/**
 * Created by admin on 2017-06-08.
 */

public class JejuCultureStreetAdpater extends BaseAdapter {

    private DTOArtstreetService dtoArtstreetService;
    Context mContext;

    public JejuCultureStreetAdpater(DTOArtstreetService dtoArtstreetService, Context context ) {
        mContext = context;
        this.dtoArtstreetService = dtoArtstreetService;
    }

    @Override
    public int getCount() {
        return dtoArtstreetService.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return dtoArtstreetService.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JejuCultureStreetViewHolder holder;

        final String m_title;
        final String m_tel;
        final String m_ceo;
        final String m_intro;


        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jae_jeju_culture_street_item, null, false);

            holder = new JejuCultureStreetViewHolder();
            holder.ll_jejuCulture_outlayout = (LinearLayout)convertView.findViewById(R.id.ll_jejuCulture_outlayout);
            holder.JejuCultureStreetImageView = (ImageView) convertView.findViewById(R.id.imageView_cultureStreet);
            holder.JejuCultureStreetName = (TextView) convertView.findViewById(R.id.text_cultureStreet_name);
            holder.JejuCultureStreetAdress = (TextView) convertView.findViewById(R.id.text_cultureStreet_adress);

            convertView.setTag(holder);
        } else {
            holder = (JejuCultureStreetViewHolder) convertView.getTag();
        }

        dtoArtstreetService.getData().get(position).getImg_url();

//        ImageBackgroundLoaderTask imageBackgroundLoaderTask =
//                new ImageBackgroundLoaderTask(mContext,holder.JejuCultureStreetImageView,dtoArtstreetService.getData().get(position).getImg_url());
//        imageBackgroundLoaderTask.execute();

        ImageLoaderTask imageLoaderTask =
                new ImageLoaderTask(holder.JejuCultureStreetImageView,dtoArtstreetService.getData().get(position).getImg_url());
        imageLoaderTask.execute();


        holder.JejuCultureStreetName.setText(dtoArtstreetService.getData().get(position).getName());
        holder.JejuCultureStreetAdress.setText(dtoArtstreetService.getData().get(position).getAddress());

        m_title = dtoArtstreetService.getData().get(position).getName();
        m_intro = dtoArtstreetService.getData().get(position).getIntroduce();
        m_ceo = dtoArtstreetService.getData().get(position).getCeo();
        m_tel = dtoArtstreetService.getData().get(position).getTelephone();

        holder.ll_jejuCulture_outlayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                JejuCultureStreetCustomDialog jejuCultureStreetCustomDialog = new JejuCultureStreetCustomDialog( mContext, m_title, m_intro, m_tel, m_ceo);
                jejuCultureStreetCustomDialog.show();
            }

        });

        return convertView;
    }

    class JejuCultureStreetViewHolder {
        LinearLayout ll_jejuCulture_outlayout;
        ImageView JejuCultureStreetImageView;
        TextView JejuCultureStreetName;
        TextView JejuCultureStreetAdress;
    }

}
