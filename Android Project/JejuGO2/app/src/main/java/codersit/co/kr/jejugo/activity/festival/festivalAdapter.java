package codersit.co.kr.jejugo.activity.festival;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dto.DTOArtstreetService;
import codersit.co.kr.jejugo.dto.DTOFestivalInquiryService;

public class festivalAdapter extends BaseAdapter {
    private DTOFestivalInquiryService dtoFestivalInquiryService;

    public festivalAdapter(DTOFestivalInquiryService dtoFestivalInquiryService ) {
        this.dtoFestivalInquiryService = dtoFestivalInquiryService;
    }

    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return dtoFestivalInquiryService.getData().size();
    }
    // 하나의 Item(ImageView 1, TextView 2)
    @Override
    public Object getItem(int position) {
        return dtoFestivalInquiryService.getData().get(position);
    }
    // Item의 id : Item을 구별하기 위한 것으로 position 사용
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 실제로 Item이 보여지는 부분
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jeju_performance_item, null, false);

            holder = new CustomViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.textTitle = (TextView) convertView.findViewById(R.id.text_title);
            holder.textContent = (TextView) convertView.findViewById(R.id.text_content);

            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        //dtoArtstreetService.getData().get(position).getImg_url();

        // ImageLoaderTask imageLoaderTask = new ImageLoaderTask(holder.imageView,dtoArtstreetService.getData().get(position).getImg_url());
        //imageLoaderTask.execute();

        holder.textTitle.setText(dtoFestivalInquiryService.getData().get(position).getTitle());
        holder.textContent.setText(dtoFestivalInquiryService.getData().get(position).getInfo());

        return convertView;
    }

    class CustomViewHolder {
        ImageView imageView;
        TextView textTitle;
        TextView textContent;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
//    public void addItem(DTOArtstreetService dtoArtstreetService) {
//        this.dtoArtstreetService = dtoArtstreetService;
//    }


}
