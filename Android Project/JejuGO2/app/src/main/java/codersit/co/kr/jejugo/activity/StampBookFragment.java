package codersit.co.kr.jejugo.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.activity.festival.FestivalFragment;
import codersit.co.kr.jejugo.activity.hotplace.HotplaceDetailFragment;
import codersit.co.kr.jejugo.dto.DTOStampPlace;
import codersit.co.kr.jejugo.util.SaveDataManager;
import codersit.co.kr.jejugo.util.StampDataManager;

import static codersit.co.kr.jejugo.util.StampDataManager.dtoStampPlaceArrayList;

/**
 * Created by P200 on 2017-06-09.
 */

public class StampBookFragment extends Fragment {

    @Bind(R.id.stamp_place_view)
    ListView stamp_place_view;
    @Bind(R.id.stamp_num_view)
    TextView stamp_num_view;


    final String LOG = "StampBookFragment";

    public StampBookFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_stamp_book, container, false);
        ButterKnife.bind(this,view);

        SaveDataManager saveDataManager = new SaveDataManager(getActivity().getApplicationContext());

        for(int i = 0 ; i < StampDataManager.dtoStampPlaceArrayList.size();i++)
        {
            String tmpStr = "stamp";
            if( StampDataManager.dtoStampPlaceArrayList.get(i).getId() <10)
                tmpStr += "0" + i;
            else
                tmpStr +=  "" + i;

            if(saveDataManager.getData(tmpStr).compareTo("false")==0)
                StampDataManager.dtoStampPlaceArrayList.get(i).setGet(false);
            else
                StampDataManager.dtoStampPlaceArrayList.get(i).setGet(true);

        }


        ArrayList<String> tempArray = new ArrayList<>();

        for(int i = 0 ; i < StampDataManager.dtoStampPlaceArrayList.size();i++)
        {
            if(StampDataManager.dtoStampPlaceArrayList.get(i).getGet() == true)
            {
                tempArray.add(StampDataManager.dtoStampPlaceArrayList.get(i).getPlaceName());
            }
//            Log.i(LOG," ");
//            Log.i(LOG,StampDataManager.dtoStampPlaceArrayList.get(i).getId()+"");
//            Log.i(LOG,StampDataManager.dtoStampPlaceArrayList.get(i).getPlaceName()+"");
//            Log.i(LOG,StampDataManager.dtoStampPlaceArrayList.get(i).getGpsX()+"");
//            Log.i(LOG,StampDataManager.dtoStampPlaceArrayList.get(i).getGpsY()+"");
//            Log.i(LOG,StampDataManager.dtoStampPlaceArrayList.get(i).getGet()+"");

        }

        stamp_num_view.setText(String.valueOf(tempArray.size()));
        ArrayAdapter<String> adapter = new ArrayAdapter<>( ((MainActivity)getActivity()) , android.R.layout.simple_list_item_1, tempArray);
        stamp_place_view.setAdapter(adapter);




        return view;

    }

    @OnClick(R.id.store_btn)
    void onClickstoreBtn()
    {
        ((MainActivity)getActivity()).callFragmentPage(new PartnerStoreFragment());
    }



}
