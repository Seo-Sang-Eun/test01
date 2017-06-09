package codersit.co.kr.jejugo.activity.festival;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.activity.MainActivity;
import codersit.co.kr.jejugo.dto.DTOArtcenterShowInfoService;

/**
 * Created by admin on 2017-06-08.
 */



public class JejuArtcenterAdapter extends BaseAdapter {

    Context mContext;

    private DTOArtcenterShowInfoService dtoArtcenterShowInfoService;


    public JejuArtcenterAdapter(DTOArtcenterShowInfoService dtoArtcenterShowInfoService , Context context) {

        Log.i("AAA","111");
        mContext= context;
        this.dtoArtcenterShowInfoService = dtoArtcenterShowInfoService;
    }


    @Override
    public int getCount() {

        Log.i("AAA","222");
        return dtoArtcenterShowInfoService.getData().size();
    }

    @Override
    public Object getItem(int position) {
        Log.i("AAA","333");

        return dtoArtcenterShowInfoService.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("AAA","444");
        JejuArtCenterCustomViewHolder holder;

        final String m_intro;
        final String m_runtime;
        final String m_title;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jae_artcenter_item, null, false);

            holder = new JejuArtCenterCustomViewHolder();
            holder.ll_jae_artcenter_outlayout = (LinearLayout)convertView.findViewById(R.id.ll_jae_artcenter_outlayout) ;
            holder.jejuArtcenterImage = (ImageView) convertView.findViewById(R.id.imageView_artcenter);
            holder.jejuArtcenterTitle = (TextView) convertView.findViewById(R.id.text_artcenter_title);
            holder.jejuArtcenterStartDate = (TextView) convertView.findViewById(R.id.text_artcenter_start);



            convertView.setTag(holder);
        } else {
            holder = (JejuArtCenterCustomViewHolder) convertView.getTag();
        }

        dtoArtcenterShowInfoService.getData().get(position).getP_IMG();

        ImageLoaderTask imageLoaderTask = new ImageLoaderTask(holder.jejuArtcenterImage ,dtoArtcenterShowInfoService.getData().get(position).getP_IMG());
        imageLoaderTask.execute();

        holder.jejuArtcenterTitle.setText(dtoArtcenterShowInfoService.getData().get(position).getP_NM());
        holder.jejuArtcenterStartDate.setText(dtoArtcenterShowInfoService.getData().get(position).getP_START_YMD() + " ~ " +dtoArtcenterShowInfoService.getData().get(position).getP_END_YMD());

        m_title = dtoArtcenterShowInfoService.getData().get(position).getP_NM();
        m_intro = dtoArtcenterShowInfoService.getData().get(position).getP_INTRO();
        m_runtime =dtoArtcenterShowInfoService.getData().get(position).getP_RNUTIME();

        holder.ll_jae_artcenter_outlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JejuArtcenterCustomDialog jejuArtcenterCustomDialog = new JejuArtcenterCustomDialog(mContext,m_title,m_intro,m_runtime);
                jejuArtcenterCustomDialog.show();


            }
        });

        return convertView;
    }

    class JejuArtCenterCustomViewHolder {
        LinearLayout ll_jae_artcenter_outlayout;
        ImageView jejuArtcenterImage;
        TextView jejuArtcenterTitle;
        TextView jejuArtcenterStartDate;
    }


}

