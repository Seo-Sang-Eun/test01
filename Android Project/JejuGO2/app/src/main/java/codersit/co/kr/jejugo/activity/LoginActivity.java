package codersit.co.kr.jejugo.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.util.SaveDataManager;

public class LoginActivity extends AppCompatActivity {


    @Bind(R.id.et_login_id)
    EditText et_login_id; //로그인 id


    @Bind(R.id.et_login_pw)
    EditText et_login_pw; //로그인 pw

    SaveDataManager saveDataManager;
    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        saveDataManager = new SaveDataManager(this.getApplicationContext());
    }


    @OnClick(R.id.tv_login_login)
    void onClick_tv_login_login()
    {
        String id = et_login_id.getText().toString();
        String pw = et_login_pw.getText().toString();
        String getDataCheck = saveDataManager.getData("id_"+id);

        if(getDataCheck == null) { //id 없음
            showDialogPwCheck();
        }
        else {
            if(getDataCheck.equals(pw)) {
                //로그인
                Toast.makeText(activity, id + "님, 환영합니다!" , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
            else {
                //패스워드 다름
                showDialogPwCheck();
            }
        }

    }

    @OnClick(R.id.tv_login_join)
    void onClick_tv_login_join()
    {
        startActivity(new Intent(getApplicationContext(),JoinActivity.class));
        finish();
    }

    void showDialogPwCheck() {
        // 다이얼로그 바디
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        // 메세지
        alert_confirm.setTitle("로그인 오류");
        alert_confirm.setMessage("아이디 또는 비밀번호를 다시 확인하세요.");
        // 확인 버튼 리스너
        alert_confirm.setPositiveButton("확인", null);
        // 다이얼로그 생성
        AlertDialog alert = alert_confirm.create();

        // 다이얼로그 보기
        alert.show();
    }



}
