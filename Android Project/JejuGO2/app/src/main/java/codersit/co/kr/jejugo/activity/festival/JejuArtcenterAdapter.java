package codersit.co.kr.jejugo.activity.festival;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dto.DTOArtcenterShowInfoService;

/**
 * Created by admin on 2017-06-08.
 */

public class JejuArtcenterAdapter extends BaseAdapter {
    private DTOArtcenterShowInfoService dtoArtcenterShowInfoService;

    public JejuArtcenterAdapter(DTOArtcenterShowInfoService dtoArtcenterShowInfoService ) {
        this.dtoArtcenterShowInfoService = dtoArtcenterShowInfoService;
    }

    @Override
    public int getCount() {
        return dtoArtcenterShowInfoService.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return dtoArtcenterShowInfoService.getData().get(position);
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
            holder.jejuArtcenterEndDate = (TextView) convertView.findViewById(R.id.text_artcenter_end);

            convertView.setTag(holder);
        } else {
            holder = (JejuArtCenterCustomViewHolder) convertView.getTag();
        }

        dtoArtcenterShowInfoService.getData().get(position).getP_IMG();

        ImageLoaderTask imageLoaderTask = new ImageLoaderTask(holder.jejuArtcenterImage,dtoArtcenterShowInfoService.getData().get(position).getP_IMG());
        imageLoaderTask.execute();

        holder.jejuArtcenterTitle.setText(dtoArtcenterShowInfoService.getData().get(position).getP_NM());
        holder.jejuArtcenterStartDate.setText(dtoArtcenterShowInfoService.getData().get(position).getP_START_YMD());
        holder.jejuArtcenterEndDate.setText(dtoArtcenterShowInfoService.getData().get(position).getP_END_YMD());
        return convertView;
    }

    class JejuArtCenterCustomViewHolder {
        ImageView jejuArtcenterImage;
        TextView jejuArtcenterTitle;
        TextView jejuArtcenterStartDate;
        TextView jejuArtcenterEndDate;
    }

}

