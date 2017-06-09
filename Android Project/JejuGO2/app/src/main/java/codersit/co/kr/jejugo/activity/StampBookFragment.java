package codersit.co.kr.jejugo.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.dto.DTOStampPlace;
import codersit.co.kr.jejugo.util.SaveDataManager;
import codersit.co.kr.jejugo.util.StampDataManager;

import static codersit.co.kr.jejugo.util.StampDataManager.dtoStampPlaceArrayList;

/**
 * Created by P200 on 2017-06-09.
 */

public class StampBookFragment extends Fragment {


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


        for(int i = 0 ; i < StampDataManager.dtoStampPlaceArrayList.size();i++)
        {
            Log.i(LOG," ");
            Log.i(LOG,StampDataManager.dtoStampPlaceArrayList.get(i).getId()+"");
            Log.i(LOG,StampDataManager.dtoStampPlaceArrayList.get(i).getPlaceName()+"");
            Log.i(LOG,StampDataManager.dtoStampPlaceArrayList.get(i).getGpsX()+"");
            Log.i(LOG,StampDataManager.dtoStampPlaceArrayList.get(i).getGpsY()+"");
            Log.i(LOG,StampDataManager.dtoStampPlaceArrayList.get(i).getGet()+"");
        }




        return view;

    }

}
