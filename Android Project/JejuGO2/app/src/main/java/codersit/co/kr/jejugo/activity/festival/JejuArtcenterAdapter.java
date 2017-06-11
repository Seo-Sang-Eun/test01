package codersit.co.kr.jejugo.activity.festival;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
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
import codersit.co.kr.jejugo.activity.MainActivity;
import codersit.co.kr.jejugo.activity.hotplace.HotplaceDetailFragment;
import codersit.co.kr.jejugo.dto.DTOArtcenterShowInfoService;
import codersit.co.kr.jejugo.dto.DTOArtcenterShowInfoServiceSpeicalList;
import codersit.co.kr.jejugo.dto.DTOArtcenterShowInfoService_List;
import codersit.co.kr.jejugo.util.ImageLoaderTask;

/**
 * Created by admin on 2017-06-08.
 */

public class JejuArtcenterAdapter extends BaseAdapter {

    Activity mActivity;

    String curQuery1 = null;
    String curQuery2 = null;

    String LOG = "JejuArtcenterAdapter";

    ArrayList<DTOArtcenterShowInfoServiceSpeicalList> dtoArtcenterShowInfoService_lists;

    public JejuArtcenterAdapter(DTOArtcenterShowInfoService dtoArtcenterShowInfoService , Activity activity) {
        mActivity= activity;

        dtoArtcenterShowInfoService_lists = new ArrayList<>();

        for(int i = 0 ; i < dtoArtcenterShowInfoService.getData().size() / 2  ; i += 2)
        {
            dtoArtcenterShowInfoService_lists.add
            (
                new DTOArtcenterShowInfoServiceSpeicalList
                (
                    dtoArtcenterShowInfoService.getData().get(i).getP_IMG(),
                    dtoArtcenterShowInfoService.getData().get(i).getP_NM(),
                    dtoArtcenterShowInfoService.getData().get(i).getP_START_YMD(),
                    dtoArtcenterShowInfoService.getData().get(i).getP_END_YMD(),

                    dtoArtcenterShowInfoService.getData().get(i+1).getP_IMG(),
                    dtoArtcenterShowInfoService.getData().get(i+1).getP_NM(),
                    dtoArtcenterShowInfoService.getData().get(i+1).getP_START_YMD(),
                    dtoArtcenterShowInfoService.getData().get(i+1).getP_END_YMD()
                )
            );
        }

        for(int i = 0 ; i < dtoArtcenterShowInfoService_lists.size() ;i++)
        {
            Log.i(LOG,"==================================");
            Log.i(LOG,dtoArtcenterShowInfoService_lists.get(i).getP_END_YMD());
            Log.i(LOG,dtoArtcenterShowInfoService_lists.get(i).getP_IMG());
            Log.i(LOG,dtoArtcenterShowInfoService_lists.get(i).getP_NM());
            Log.i(LOG,dtoArtcenterShowInfoService_lists.get(i).getP_START_YMD());

            Log.i(LOG,dtoArtcenterShowInfoService_lists.get(i).getP_END_YMD2());
            Log.i(LOG,dtoArtcenterShowInfoService_lists.get(i).getP_IMG2());
            Log.i(LOG,dtoArtcenterShowInfoService_lists.get(i).getP_NM2());
            Log.i(LOG,dtoArtcenterShowInfoService_lists.get(i).getP_START_YMD2());
        }

    }

    @Override
    public int getCount() {
        return dtoArtcenterShowInfoService_lists.size();
    }

    @Override
    public Object getItem(int position) {
        return dtoArtcenterShowInfoService_lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JejuArtCenterCustomViewHolder holder;




        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jae_artcenter_item, null, false);
            holder = new JejuArtCenterCustomViewHolder();
            holder.jejuArtcenterImage = (ImageView) convertView.findViewById(R.id.imageView_artcenter);
            holder.jejuArtcenterTitle = (TextView) convertView.findViewById(R.id.text_artcenter_title);
            holder.jejuArtcenterStartDate = (TextView) convertView.findViewById(R.id.text_artcenter_start);
            holder.jejuArtcenterLayout = (LinearLayout)convertView.findViewById(R.id.ll_jae_artcenter_outlayout);

            holder.jejuArtcenterImage2 = (ImageView) convertView.findViewById(R.id.imageView_artcenter2);
            holder.jejuArtcenterTitle2 = (TextView) convertView.findViewById(R.id.text_artcenter_title2);
            holder.jejuArtcenterStartDate2 = (TextView) convertView.findViewById(R.id.text_artcenter_start2);
            holder.jejuArtcenterLayout2 = (LinearLayout)convertView.findViewById(R.id.ll_jae_artcenter_outlayout2);


            convertView.setTag(holder);
        } else {
            holder = (JejuArtCenterCustomViewHolder) convertView.getTag();
        }

//        dtoArtcenterShowInfoService_lists.get(position).getP_IMG();


        ImageLoaderTask imageLoaderTask = new ImageLoaderTask(holder.jejuArtcenterImage ,dtoArtcenterShowInfoService_lists.get(position).getP_IMG());
        imageLoaderTask.execute();
        holder.jejuArtcenterTitle.setText(dtoArtcenterShowInfoService_lists.get(position).getP_NM());
        holder.jejuArtcenterStartDate.setText(dtoArtcenterShowInfoService_lists.get(position).getP_START_YMD() + " ~ " +dtoArtcenterShowInfoService_lists.get(position).getP_END_YMD());
        curQuery1 = dtoArtcenterShowInfoService_lists.get(position).getP_NM();
        holder.jejuArtcenterLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                searchBrowser1("https://search.naver.com/search.naver?ie=UTF-8&query=" + curQuery1, "검색 결과");
            }

        });


        ImageLoaderTask imageLoaderTask2 = new ImageLoaderTask(holder.jejuArtcenterImage2 ,dtoArtcenterShowInfoService_lists.get(position).getP_IMG2());
        imageLoaderTask2.execute();
        holder.jejuArtcenterTitle2.setText(dtoArtcenterShowInfoService_lists.get(position).getP_NM2());
        holder.jejuArtcenterStartDate2.setText(dtoArtcenterShowInfoService_lists.get(position).getP_START_YMD2() + " ~ " +dtoArtcenterShowInfoService_lists.get(position).getP_END_YMD2());
        curQuery2 = dtoArtcenterShowInfoService_lists.get(position).getP_NM2();
        holder.jejuArtcenterLayout2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                searchBrowser2("https://search.naver.com/search.naver?ie=UTF-8&query=" + curQuery2, "검색 결과");
            }

        });



        return convertView;
    }

    class JejuArtCenterCustomViewHolder {
        ImageView jejuArtcenterImage;
        TextView jejuArtcenterTitle;
        TextView jejuArtcenterStartDate;
        LinearLayout jejuArtcenterLayout;

        ImageView jejuArtcenterImage2;
        TextView jejuArtcenterTitle2;
        TextView jejuArtcenterStartDate2;
        LinearLayout jejuArtcenterLayout2;
    }


    void searchBrowser1(String url, String detailInfo)
    {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add(url); // [ url ]
        strings.add(curQuery1 + " " + detailInfo); // ex) [ 제주서귀포 블로그검색 결과 ]

        ((MainActivity)mActivity).callFragmentPageWithData(new JejuArtcenterSearchFragment(),strings);
    }

    void searchBrowser2(String url, String detailInfo)
    {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add(url); // [ url ]
        strings.add(curQuery2 + " " + detailInfo); // ex) [ 제주서귀포 블로그검색 결과 ]

        ((MainActivity)mActivity).callFragmentPageWithData(new JejuArtcenterSearchFragment(),strings);
    }

}

