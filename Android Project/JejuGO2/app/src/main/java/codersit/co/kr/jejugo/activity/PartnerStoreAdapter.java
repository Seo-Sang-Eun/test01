package codersit.co.kr.jejugo.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dto.DTOBestEating;
import codersit.co.kr.jejugo.dto.DTOPartnerStore;
import codersit.co.kr.jejugo.util.PartnerStoreManager;

/**
 * Created by BooHee on 2017-06-11.
 */

public class PartnerStoreAdapter extends BaseAdapter {

    Context mContext;

    private ArrayList<DTOPartnerStore> dtoStoreList = new ArrayList<>();
    private List<DTOPartnerStore> tempList = null;

    public PartnerStoreAdapter(Context mContext, ArrayList<DTOPartnerStore> dtoPartnerStore)
    {
        this.mContext = mContext;
        this.dtoStoreList = dtoPartnerStore;
        this.tempList = new ArrayList<>();
        this.tempList.addAll(dtoStoreList);
    }

    @Override
    public int getCount() {

        return dtoStoreList.size();
    }

    @Override
    public Object getItem(int position) {
        return dtoStoreList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.partnerstore_item, parent, false);

        TextView txt1 = (TextView)convertView.findViewById(R.id.store_title);
        txt1.setText(dtoStoreList.get(position).getStoreName());

        TextView txt2 = (TextView)convertView.findViewById(R.id.store_addr);
        txt2.setText(dtoStoreList.get(position).getAddr());

        TextView txt3 = (TextView)convertView.findViewById(R.id.store_tel);
        txt3.setText(dtoStoreList.get(position).getTelNo());

        return convertView;
    }

    public void filter(String charText)
    {
       dtoStoreList.clear();
        if(charText.length() == 0)
        {
            dtoStoreList.addAll(tempList);
        }
        else
        {
            for(DTOPartnerStore tp : tempList)
            {
                if(tp.getStoreName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    dtoStoreList.add(tp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
