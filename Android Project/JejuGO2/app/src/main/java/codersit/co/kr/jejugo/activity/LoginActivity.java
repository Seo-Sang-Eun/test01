package codersit.co.kr.jejugo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import codersit.co.kr.jejugo.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.tv_login_login)
    void onClick_tv_login_login()
    {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

    @OnClick(R.id.tv_login_join)
    void onClick_tv_login_join()
    {
        startActivity(new Intent(getApplicationContext(),JoinActivity.class));
    }



}
