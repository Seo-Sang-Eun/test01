package codersit.co.kr.jejugo.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by P200 on 2017-06-09.
 */

public class SaveDataManager {

    Context mContext;
    SharedPreferences mPref;
    public SaveDataManager(Context context) {

        mContext = context;
        mPref = mContext.getSharedPreferences("DATA", MODE_PRIVATE);
    }

    public String getData(String key)
    {
        return mPref.getString(key,null);
    }

    public void putData(String key,String data)
    {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString(key,data);
        editor.commit();
    }



    public void delData(String key)
    {
        SharedPreferences.Editor editor = mPref.edit();
        editor.remove(key);
        editor.commit();
    }

}
