package codersit.co.kr.jejugo.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.util.JejuFoodManager;
import codersit.co.kr.jejugo.util.JejuWifiDataManager;
import codersit.co.kr.jejugo.util.SaveDataManager;
import codersit.co.kr.jejugo.util.StampDataManager;

public class IntroActivity extends AppCompatActivity {

    String LOG = "IntroActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        JejuWifiDataManager.initData();
//        JejuFoodManager.initData();


        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                Intent myIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(myIntent);
                overridePendingTransition(0, 0);
                IntroActivity.this.finish();
            }
        }, 3000);

        SaveDataManager saveDataManager = new SaveDataManager(this);

        StampDataManager.initData();

        if(saveDataManager.getData("stampCount") == null)
        {
            saveDataManager.putData("stampCount", "0" );
        }

        for(int i = 1 ;i<=9;i++)
        {
            if(saveDataManager.getData("stampInfo"+i)==null)
                saveDataManager.putData("stampInfo"+i,"false");

        }


        for(int i = 0 ; i < 20;i++)
        {
            if(i<10)
            {
                Log.i(LOG,"stamp0"+i);
                if(saveDataManager.getData("stamp0"+i) == null)
                {
                    Log.i(LOG,"stamp0"+i);


                    saveDataManager.putData("stamp0"+i,  "false");
                }

            }
            else
            {
                Log.i(LOG,"stamp"+i);
                if(saveDataManager.getData("stamp"+i) == null)
                {
                    Log.i(LOG,"stamp"+i);

                    saveDataManager.putData("stamp"+i,  "false");
                }
            }
        }

    }

}
