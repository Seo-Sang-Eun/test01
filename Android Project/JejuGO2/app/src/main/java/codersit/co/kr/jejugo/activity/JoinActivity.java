package codersit.co.kr.jejugo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import codersit.co.kr.jejugo.R;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        ButterKnife.bind(this);



    }

    @OnClick(R.id.tv_join_join)
    void onClick_tv_login_login()
    {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
