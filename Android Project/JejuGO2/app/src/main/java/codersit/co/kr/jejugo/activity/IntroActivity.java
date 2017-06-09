package codersit.co.kr.jejugo.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.util.JejuFoodManager;
import codersit.co.kr.jejugo.util.JejuWifiDataManager;
import codersit.co.kr.jejugo.util.SaveDataManager;
import codersit.co.kr.jejugo.util.StampDataManager;

public class IntroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        JejuWifiDataManager.initData();
        JejuFoodManager.initData();

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

        for(int i = 0 ; i < 20;i++)
        {
            if(i<10)
            {
                if(saveDataManager.getData("stamp0"+i) == null)
                {
                    saveDataManager.putData("stamp0"+i,  "false");
                }

            }
            else
            {
                if(saveDataManager.getData("stamp"+i) == null)
                {
                    saveDataManager.putData("stamp"+i,  "false");
                }
            }
        }

    }

}
