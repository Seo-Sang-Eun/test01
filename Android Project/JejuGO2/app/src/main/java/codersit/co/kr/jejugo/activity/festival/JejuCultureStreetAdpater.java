package codersit.co.kr.jejugo.activity.festival;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dto.DTOArtstreetService;

/**
 * Created by admin on 2017-06-08.
 */

public class JejuCultureStreetAdpater extends BaseAdapter {

    private DTOArtstreetService dtoArtstreetService;

    public JejuCultureStreetAdpater(DTOArtstreetService dtoArtstreetService ) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jae_jeju_culture_street_item, null, false);

            holder = new JejuCultureStreetViewHolder();
            holder.JejuCultureStreetImageView = (ImageView) convertView.findViewById(R.id.imageView_cultureStreet);
            holder.JejuCultureStreetName = (TextView) convertView.findViewById(R.id.text_cultureStreet_name);
            holder.JejuCultureStreetAdress = (TextView) convertView.findViewById(R.id.text_cultureStreet_adress);

            convertView.setTag(holder);
        } else {
            holder = (JejuCultureStreetViewHolder) convertView.getTag();
        }

        dtoArtstreetService.getData().get(position).getImg_url();

        ImageLoaderTask imageLoaderTask = new ImageLoaderTask(holder.JejuCultureStreetImageView,dtoArtstreetService.getData().get(position).getImg_url());
        imageLoaderTask.execute();

        holder.JejuCultureStreetName.setText(dtoArtstreetService.getData().get(position).getName());
        holder.JejuCultureStreetAdress.setText(dtoArtstreetService.getData().get(position).getAddress());

        return convertView;
    }

    class JejuCultureStreetViewHolder {
        ImageView JejuCultureStreetImageView;
        TextView JejuCultureStreetName;
        TextView JejuCultureStreetAdress;
    }

}
