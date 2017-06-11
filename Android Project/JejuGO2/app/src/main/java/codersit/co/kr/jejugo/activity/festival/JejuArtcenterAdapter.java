package codersit.co.kr.jejugo.activity.festival;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.OnClick;
import codersit.co.kr.jejugo.R;
<<<<<<< HEAD
=======
import codersit.co.kr.jejugo.activity.MainActivity;
import codersit.co.kr.jejugo.activity.hotplace.HotplaceDetailFragment;
>>>>>>> f7fa4fc2ef635a4791ad4017dba276f92a7261ba
import codersit.co.kr.jejugo.dto.DTOArtcenterShowInfoService;
import codersit.co.kr.jejugo.util.ImageLoaderTask;

/**
 * Created by admin on 2017-06-08.
 */

public class JejuArtcenterAdapter extends BaseAdapter {

    Activity mActivity;

    String curQuery = null;

    String LOG = "JejuArtcenterAdapter";

    private DTOArtcenterShowInfoService dtoArtcenterShowInfoService;


<<<<<<< HEAD
    public JejuArtcenterAdapter(DTOArtcenterShowInfoService dtoArtcenterShowInfoService , Context context) {

        mContext= context;
=======
    public JejuArtcenterAdapter(DTOArtcenterShowInfoService dtoArtcenterShowInfoService , Activity activity) {
        mActivity= activity;
>>>>>>> f7fa4fc2ef635a4791ad4017dba276f92a7261ba
        this.dtoArtcenterShowInfoService = dtoArtcenterShowInfoService;
    }

    @Override
    public int getCount() {
<<<<<<< HEAD

=======
>>>>>>> f7fa4fc2ef635a4791ad4017dba276f92a7261ba
        return dtoArtcenterShowInfoService.getData().size();
    }

    @Override
    public Object getItem(int position) {
<<<<<<< HEAD

=======
>>>>>>> f7fa4fc2ef635a4791ad4017dba276f92a7261ba
        return dtoArtcenterShowInfoService.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
<<<<<<< HEAD
//        Log.i("AAA","444");
=======
>>>>>>> f7fa4fc2ef635a4791ad4017dba276f92a7261ba
        JejuArtCenterCustomViewHolder holder;




        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jae_artcenter_item, null, false);
            holder = new JejuArtCenterCustomViewHolder();
            holder.jejuArtcenterImage = (ImageView) convertView.findViewById(R.id.imageView_artcenter);
            holder.jejuArtcenterTitle = (TextView) convertView.findViewById(R.id.text_artcenter_title);
            holder.jejuArtcenterStartDate = (TextView) convertView.findViewById(R.id.text_artcenter_start);
            holder.jejuArtcenterLayout = (LinearLayout)convertView.findViewById(R.id.ll_jae_artcenter_outlayout);
            convertView.setTag(holder);
        } else {
            holder = (JejuArtCenterCustomViewHolder) convertView.getTag();
        }

        dtoArtcenterShowInfoService.getData().get(position).getP_IMG();

        ImageLoaderTask imageLoaderTask = new ImageLoaderTask(holder.jejuArtcenterImage ,dtoArtcenterShowInfoService.getData().get(position).getP_IMG());
        imageLoaderTask.execute();

        holder.jejuArtcenterTitle.setText(dtoArtcenterShowInfoService.getData().get(position).getP_NM());
        holder.jejuArtcenterStartDate.setText(dtoArtcenterShowInfoService.getData().get(position).getP_START_YMD() + " ~ " +dtoArtcenterShowInfoService.getData().get(position).getP_END_YMD());

        curQuery = dtoArtcenterShowInfoService.getData().get(position).getP_NM();
        holder.jejuArtcenterLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                searchBrowser("https://search.naver.com/search.naver?ie=UTF-8&query=" + curQuery, "통합검색 결과");
            }

        });

        return convertView;
    }

    class JejuArtCenterCustomViewHolder {
        LinearLayout jejuArtcenterLayout;
        ImageView jejuArtcenterImage;
        TextView jejuArtcenterTitle;
        TextView jejuArtcenterStartDate;
    }


    void searchBrowser(String url, String detailInfo)
    {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add(url); // [ url ]
        strings.add(curQuery + " " + detailInfo); // ex) [ 제주서귀포 블로그검색 결과 ]

        ((MainActivity)mActivity).callFragmentPageWithData(new HotplaceDetailFragment(),strings);
    }

}

